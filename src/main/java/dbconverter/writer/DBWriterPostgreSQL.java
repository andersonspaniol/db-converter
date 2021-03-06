package dbconverter.writer;

import dbconverter.connection.DBConnection;
import dbconverter.contants.PostgreSQLConstants;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;

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
        if (length <= PostgreSQLConstants.LENGTH_SMALLINT) {
            return "smallint";
        } else if (length <= PostgreSQLConstants.LENGTH_INTEGER) {
            return "integer";
        } else if (length <= PostgreSQLConstants.LENGTH_BIGINT) {
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
    protected String getSqlColumnIndex(IndexColumn indexColumn) {
        return indexColumn.getTableColumn().getColumnName();
    }

}
