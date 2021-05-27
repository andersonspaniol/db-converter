package dbconverter.reader;

import dbconverter.connection.DBConnection;
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
        String cmd = "select table_name from information_schema.tables where table_type = 'BASE TABLE' and table_schema = '" + schema + "' order by table_name";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(cmd);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {            
            String tableName = resultSet.getString(1);
            tableNames.add(tableName);
        }
        return tableNames;
    }

    @Override
    public TableStructure getTableStructure(String tableName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stream<TableRecord> getTableRecords(String tableName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
