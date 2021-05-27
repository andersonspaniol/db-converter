package dbconverter.reader;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract class that represents a reader to source database
 *
 * @author Anderson Spaniol
 */
public abstract class DBReader {

    private final DBConnection dbConnection;

    protected DBReader(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public abstract List<String> getTableNames() throws SQLException ;

    public abstract TableStructure getTableStructure(String tableName) throws SQLException ;
    
    public abstract Stream<TableRecord> getTableRecords(String tableName) throws SQLException ;

}
