package dbconverter.writer;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableRecord;
import java.sql.SQLException;

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
    public void insertTableRecord(TableRecord tableRecord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flushTableRecords() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTableIndexes() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTablePrimaryKeys() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTableConstraints() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
