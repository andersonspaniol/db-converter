package dbconverter.reader;

import dbconverter.connection.DbConnection;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class that represents a reader to source database - MariaDB
 *
 * @author Anderson Spaniol
 */
public class DBReaderMariaDB extends DBReader {

    protected DBReaderMariaDB(DbConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public List<String> getTableNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
