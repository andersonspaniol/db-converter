package dbconverter.reader;

import dbconverter.connection.DBConnection;
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
    protected void loadTableColumns(TableStructure tableStructure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void loadTableIndexes(TableStructure tableStructure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
