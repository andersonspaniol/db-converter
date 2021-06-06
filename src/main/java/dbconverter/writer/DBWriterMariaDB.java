package dbconverter.writer;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;

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
    protected String getSqlColumnTypeInteger(TableColumn tableColumn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getSqlColumnTypeBinary(TableColumn tableColumn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getSqlColumnIndex(IndexColumn indexColumn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
