package dbconverter.reader;

import dbconverter.connection.DBConnection;
import dbconverter.contants.PostgreSQLConstants;
import dbconverter.datatypes.DataType;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableIndex;
import dbconverter.datatypes.TableStructure;
import dbconverter.params.Parameters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a reader to source database - PostgreSQL
 *
 * @author Anderson Spaniol
 */
public class DBReaderPostgreSQL extends DBReader {

    protected DBReaderPostgreSQL(DBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public List<String> getTableNames() throws SQLException {
        List<String> tableNames = new ArrayList();
        String schema = Parameters.def().getSourceSchema();
        String cmd = "select c.relname from pg_catalog.pg_class c join pg_catalog.pg_namespace n on n.oid = c.relnamespace where n.nspname = ? and c.relkind = 'r'";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd)) {
            preparedStatement.setString(1, schema);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString(1);
                    tableNames.add(tableName);
                }
            }
        }
        return tableNames;
    }

    @Override
    protected void loadTableColumns(TableStructure tableStructure) throws SQLException {
        String schema = Parameters.def().getSourceSchema();
        String tableName = tableStructure.getTableName();
        String cmd = "select column_name, data_type, character_maximum_length, numeric_precision, numeric_scale, is_nullable " +
                     "from information_schema.columns " +
                     "where table_schema = ? and table_name = ?";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd)) {
            preparedStatement.setString(1, schema);
            preparedStatement.setString(2, tableName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String columnName = resultSet.getString(1);
                    String dataType = resultSet.getString(2);
                    int characterMaximumLength = resultSet.getInt(3);
                    int numericPrecision = resultSet.getInt(4);
                    int numericScale = resultSet.getInt(5);
                    boolean nullable = resultSet.getBoolean(6);
                    TableColumn tableColumn = createTableColumn(columnName, dataType, characterMaximumLength, numericPrecision, numericScale, nullable);
                    tableStructure.addColumn(tableColumn);
                }
            }
        }
    }

    protected TableColumn createTableColumn(String columnName, String dataTypeStr, int characterMaximumLength, int numericPrecision, int numericScale, boolean nullable) throws SQLException {
        DataType dataType = null;
        int lenght = -1;
        int scale = 0;
        switch (dataTypeStr) {
            case "character":
                dataType = DataType.CHAR;
                break;
            case "character varying":
                dataType = DataType.VARCHAR;
                break;
            case "numeric":
                dataType = DataType.DECIMAL;
                break;
            case "smallint":
                dataType = DataType.INTEGER;
                lenght = PostgreSQLConstants.LENGTH_SMALLINT;
                break;
            case "integer":
                dataType = DataType.INTEGER;
                lenght = PostgreSQLConstants.LENGTH_INTEGER;
                break;
            case "bigint":
                dataType = DataType.INTEGER;
                lenght = PostgreSQLConstants.LENGTH_BIGINT;
                break;
            case "date":
                dataType = DataType.DATE;
                lenght = 0;
                break;
            case "bytea":
                dataType = DataType.BINARY;
                lenght = PostgreSQLConstants.LENGTH_BYTEA;
                break;
            default:
                throw new SQLException("Unknown data! Column name: " + columnName);
        }
        if (dataType == DataType.CHAR || dataType == DataType.VARCHAR) {
            lenght = characterMaximumLength;
        }
        if (dataType == DataType.DECIMAL) {
            lenght = numericPrecision;
            scale = numericScale;
            if (scale == 0) {
                dataType = DataType.INTEGER;
            }
        }
        if (lenght < 0) {
            throw new SQLException("Undefined length! Column name: " + columnName);
        }
        return new TableColumn(columnName, dataType, lenght, scale, nullable);
    }
    
    @Override
    protected void loadTableIndexes(TableStructure tableStructure) throws SQLException {
        String schema = Parameters.def().getSourceSchema();
        String tableName = tableStructure.getTableName();
        String cmd = "select indexname, indexdef " +
                  "from pg_indexes " +
                  "where schemaname = ? and tablename = ? " +
                  "order by tablename, indexname";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd)) {
            preparedStatement.setString(1, schema);
            preparedStatement.setString(2, tableName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String indexName = resultSet.getString(1);
                    String cmdCreateIndex = resultSet.getString(2).toLowerCase();
                    boolean primaryKey = indexName.endsWith("_pk");
                    boolean nonUnique = cmdCreateIndex.startsWith("create index");
                    int parenthesesOpeningPosition = cmdCreateIndex.indexOf("(");
                    int parenthesesClosingPosition = cmdCreateIndex.indexOf(")");
                    String columnList = cmdCreateIndex.substring(parenthesesOpeningPosition + 1, parenthesesClosingPosition);
                    String[] columns = columnList.split(", ");
                    TableIndex tableIndex = new TableIndex(indexName, primaryKey, nonUnique);
                    tableStructure.addIndex(tableIndex);
                    for (String columnName : columns) {
                        IndexColumn indexColumn = createIndexColumn(tableStructure, columnName, 0);
                        tableIndex.addColumn(indexColumn);
                    }
                }
            }
        }
    }

}
