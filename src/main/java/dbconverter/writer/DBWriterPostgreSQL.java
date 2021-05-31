package dbconverter.writer;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableRecord;
import dbconverter.reader.DBReaderPostgreSQL;

/**
 * Class that represents a writer to target database - PostgreSQL
 *
 * @author Anderson Spaniol
 */
public class DBWriterPostgreSQL extends DBWriter {

    protected DBWriterPostgreSQL(DBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    protected String getSqlColumnTypeInteger(TableColumn tableColumn) {
        long length = tableColumn.getLenght();
        if (length <= DBReaderPostgreSQL.LENGTH_SMALLINT) {
            return "smallint";
        } else if (length <= DBReaderPostgreSQL.LENGTH_INTEGER) {
            return "integer";
        } else if (length <= DBReaderPostgreSQL.LENGTH_BIGINT) {
            return "bigint";
        } else {
            return "numeric(" + length + ")";
        }
    }

    @Override
    protected String getSqlColumnTypeBinary(TableColumn tableColumn) {
        return "bytea";
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
