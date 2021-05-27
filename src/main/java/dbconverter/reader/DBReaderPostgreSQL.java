package dbconverter.reader;

import dbconverter.connection.DbConnection;

/**
 * Class that represents a reader to source database - PostgreSQL
 *
 * @author Anderson Spaniol
 */
public class DBReaderPostgreSQL extends DBReader {

    protected DBReaderPostgreSQL(DbConnection dbConnection) {
        super(dbConnection);
    }

}
