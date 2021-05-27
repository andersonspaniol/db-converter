package dbconverter.writer;

import dbconverter.connection.DbConnection;

/**
 * Abstract class that represents a writer to target database
 *
 * @author Anderson Spaniol
 */
public abstract class DBWriter {

    private final DbConnection dbConnection;

    protected DBWriter(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

}
