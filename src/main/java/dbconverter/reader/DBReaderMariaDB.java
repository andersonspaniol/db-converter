package dbconverter.reader;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableIndex;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import dbconverter.params.Parameters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd);
        preparedStatement.setString(1, schema);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {            
            String tableName = resultSet.getString(1);
            tableNames.add(tableName);
        }
        resultSet.close();
        preparedStatement.close();
        return tableNames;
    }

    @Override
    protected void loadTableColumns(TableStructure tableStructure) throws SQLException {
        String schema = Parameters.def().getSourceSchema();
        String tableName = tableStructure.getTableName();
        String cmd = "select column_name, data_type, character_maximum_length, numeric_precision, numeric_scale " +
                     "from information_schema.columns " +
                     "where table_schema = ? and table_name = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd);
        preparedStatement.setString(1, schema);
        preparedStatement.setString(2, tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {            
            String columnName = resultSet.getString(1);
            String dataType = resultSet.getString(2);
            int characterMaximumLength = resultSet.getInt(3);
            int numericPrecision = resultSet.getInt(4);
            int numericScale = resultSet.getInt(5);
            TableColumn tableColumn = createTableColumn(columnName, dataType, characterMaximumLength, numericPrecision, numericScale);
            tableStructure.addColumn(tableColumn);
        }
        resultSet.close();
        preparedStatement.close();
    }

    protected TableColumn createTableColumn(String columnName, String dataType, int characterMaximumLength, int numericPrecision, int numericScale) {
        // Implement logic here and implement unit test
        return new TableColumn();
    }
    
    @Override
    protected void loadTableIndexes(TableStructure tableStructure) throws SQLException {
        String schema = Parameters.def().getSourceSchema();
        String tableName = tableStructure.getTableName();
        String cmd = "select index_name, column_name, sub_part, non_unique " +
                     "from information_schema.statistics " +
                     "where table_schema = ? and table_name = ? " +
                     "order by table_name, index_name, seq_in_index";        
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd);
        preparedStatement.setString(1, schema);
        preparedStatement.setString(2, tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        String previousIndexName = "";
        TableIndex tableIndex = null;
        while (resultSet.next()) {            
            String indexName = resultSet.getString(1);
            String columnName = resultSet.getString(2);
            int subpart = resultSet.getInt(3);
            boolean nonUnique = resultSet.getBoolean(4);
            if (!previousIndexName.equals(indexName)) {
                tableIndex = new TableIndex(indexName, nonUnique);
                tableStructure.addIndex(tableIndex);
            }
            IndexColumn indexColumn = createIndexColumn(columnName, subpart);
            tableIndex.addColumn(indexColumn);
        }
        resultSet.close();
        preparedStatement.close();
    }

    protected IndexColumn createIndexColumn(String columnName, int subpart) {
        // Implement logic here and implement unit test
        return new IndexColumn(columnName, subpart);
    }

    @Override
    public Stream<TableRecord> getTableRecords(String tableName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
