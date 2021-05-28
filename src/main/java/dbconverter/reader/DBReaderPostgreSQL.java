package dbconverter.reader;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import java.util.List;
import java.util.stream.Stream;

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
    public List<String> getTableNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void loadTableColumns(TableStructure tableStructure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void loadTableIndexes(TableStructure tableStructure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stream<TableRecord> getTableRecords(String tableName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
