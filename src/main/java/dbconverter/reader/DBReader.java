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

    public TableStructure getTableStructure(String tableName) throws SQLException {
        TableStructure tableStructure = new TableStructure(tableName);
        loadTableColumns(tableStructure);
        loadTableIndexes(tableStructure);
        return tableStructure;
    }
    
    protected abstract void loadTableColumns(TableStructure tableStructure) throws SQLException ;
    
    protected abstract void loadTableIndexes(TableStructure tableStructure) throws SQLException ;

    public abstract Stream<TableRecord> getTableRecords(String tableName) throws SQLException ;

}
