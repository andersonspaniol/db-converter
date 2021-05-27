package dbconverter.reader;

import dbconverter.connection.DbConnection;

/**
 * Class that represents a reader to source database - MariaDB
 *
 * @author Anderson Spaniol
 */
public class DBReaderMariaDB extends DBReader {

    protected DBReaderMariaDB(DbConnection dbConnection) {
        super(dbConnection);
    }

}
