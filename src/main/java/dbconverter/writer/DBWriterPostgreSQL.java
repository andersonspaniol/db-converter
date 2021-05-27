package dbconverter.writer;

import dbconverter.connection.DbConnection;

/**
 * Class that represents a writer to target database - PostgreSQL
 *
 * @author Anderson Spaniol
 */
public class DBWriterPostgreSQL extends DBWriter {

    protected DBWriterPostgreSQL(DbConnection dbConnection) {
        super(dbConnection);
    }

}
