package dbconverter.reader;

import dbconverter.connection.DbConnection;

/**
 * Abstract class that represents a reader to source database
 *
 * @author Anderson Spaniol
 */
public abstract class DBReader {

    private final DbConnection dbConnection;

    protected DBReader(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

}
