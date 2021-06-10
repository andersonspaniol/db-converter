package dbconverter.reader;

import dbconverter.connection.DBConnection;
import dbconverter.contants.MariaDBConstants;
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
 * Class that represents a reader to source database - MariaDB
 *
 * @author Anderson Spaniol
 */
public class DBReaderMariaDB extends DBReader {

    protected DBReaderMariaDB(DBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public List<String> getTableNames() throws SQLException {
        List<String> tableNames = new ArrayList();
        String schema = Parameters.def().getSourceSchema();
        String cmd = "select table_name " +
                     "from information_schema.tables " +
                     "where table_type = 'BASE TABLE' and table_schema = ? " +
                     "order by table_name";
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
        String cmd = "select column_name, data_type, character_maximum_length, numeric_precision, numeric_scale " +
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
                    TableColumn tableColumn = createTableColumn(columnName, dataType, characterMaximumLength, numericPrecision, numericScale);
                    tableStructure.addColumn(tableColumn);
                }
            }
        }
    }

    protected TableColumn createTableColumn(String columnName, String dataTypeStr, int characterMaximumLength, int numericPrecision, int numericScale) throws SQLException {
        DataType dataType = null;
        int lenght = -1;
        int scale = 0;
        switch (dataTypeStr) {
            case "char":
                dataType = DataType.CHAR;
                break;
            case "varchar":
                dataType = DataType.VARCHAR;
                break;
            case "decimal":
                dataType = DataType.DECIMAL;
                break;
            case "tinyint":
                dataType = DataType.INTEGER;
                lenght = MariaDBConstants.LENGTH_TINYINT;
                break;
            case "smallint":
                dataType = DataType.INTEGER;
                lenght = MariaDBConstants.LENGTH_SMALLINT;
                break;
            case "mediumint":
                dataType = DataType.INTEGER;
                lenght = MariaDBConstants.LENGTH_MEDIUMINT;
                break;
            case "int":
                dataType = DataType.INTEGER;
                lenght = MariaDBConstants.LENGTH_INT;
                break;
            case "bigint":
                dataType = DataType.INTEGER;
                lenght = MariaDBConstants.LENGTH_BIGINT;
                break;
            case "date":
                dataType = DataType.DATE;
                lenght = 0;
                break;
            case "tinyblob":
                dataType = DataType.BINARY;
                break;
            case "blob":
                dataType = DataType.BINARY;
                break;
            case "mediumblob":
                dataType = DataType.BINARY;
                break;
            case "longblob":
                dataType = DataType.BINARY;
                break;
            default:
                throw new SQLException("Unknown data! Column name: " + columnName);
        }
        if (dataType == DataType.CHAR || dataType == DataType.VARCHAR || dataType == DataType.BINARY) {
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
        return new TableColumn(columnName, dataType, lenght, scale);
    }
    
    @Override
    protected void loadTableIndexes(TableStructure tableStructure) throws SQLException {
        String schema = Parameters.def().getSourceSchema();
        String tableName = tableStructure.getTableName();
        String cmd = "select index_name, column_name, sub_part, non_unique " +
                     "from information_schema.statistics " +
                     "where table_schema = ? and table_name = ? " +
                     "order by table_name, index_name, seq_in_index";        
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd)) {
            preparedStatement.setString(1, schema);
            preparedStatement.setString(2, tableName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String previousIndexName = "";
                TableIndex tableIndex = null;
                while (resultSet.next()) {
                    String indexName = resultSet.getString(1);
                    String columnName = resultSet.getString(2);
                    int subpart = resultSet.getInt(3);
                    boolean primaryKey = indexName.equalsIgnoreCase("primary");
                    boolean nonUnique = resultSet.getBoolean(4);
                    if (!previousIndexName.equals(indexName)) {
                        tableIndex = new TableIndex(indexName, primaryKey, nonUnique);
                        tableStructure.addIndex(tableIndex);
                    }
                    IndexColumn indexColumn = createIndexColumn(columnName, subpart);
                    tableIndex.addColumn(indexColumn);
                }
            }
        }
    }

    protected IndexColumn createIndexColumn(String columnName, int subpart) {
        return new IndexColumn(columnName, subpart);
    }

}
