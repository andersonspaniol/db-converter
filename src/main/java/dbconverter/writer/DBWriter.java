package dbconverter.writer;

import dbconverter.connection.DbConnection;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;

/**
 * Abstract class that represents a writer to target database
 *
 * @author Anderson Spaniol
 */
public abstract class DBWriter {

    private final DbConnection dbConnection;

    protected DBWriter(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public abstract void createTable(TableStructure tableStructure);

    public abstract void insertTableRecord(TableRecord tableRecord);

    public abstract void flushTableRecords();

}
