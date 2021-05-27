package dbconverter.writer;

import dbconverter.connection.DbConnection;

/**
 * Class that represents a writer to target database - MariaDB
 *
 * @author Anderson Spaniol
 */
public class DBWriterMariaDB extends DBWriter {

    protected DBWriterMariaDB(DbConnection dbConnection) {
        super(dbConnection);
    }

}
