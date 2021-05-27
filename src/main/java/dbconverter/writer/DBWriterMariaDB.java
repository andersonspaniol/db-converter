package dbconverter.writer;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;

/**
 * Class that represents a writer to target database - MariaDB
 *
 * @author Anderson Spaniol
 */
public class DBWriterMariaDB extends DBWriter {

    protected DBWriterMariaDB(DBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public void createTable(TableStructure tableStructure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertTableRecord(TableRecord tableRecord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flushTableRecords() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
