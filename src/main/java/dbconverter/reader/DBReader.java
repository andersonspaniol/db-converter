package dbconverter.reader;

import dbconverter.connection.DbConnection;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract class that represents a reader to source database
 *
 * @author Anderson Spaniol
 */
public abstract class DBReader {

    private final DbConnection dbConnection;

    protected DBReader(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public abstract List<String> getTableNames();

    public abstract TableStructure getTableStructure(String tableName);
    
    public abstract Stream<TableRecord> getTableRecords(String tableName);

}
