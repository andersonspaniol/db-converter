package dbconverter.reader;

import dbconverter.connection.DBConnection;
import java.sql.SQLException;

/**
 * Factory for constructing DBReader objects
 *
 * @author Anderson Spaniol
 */
public class DBReaderFactory {

    public static DBReader newReader(String dbms, DBConnection dbConnection) throws SQLException {
        if (dbms.equals("mariadb")) {
            return new DBReaderMariaDB(dbConnection);
        }
        if (dbms.equals("postgresql")) {
            return new DBReaderPostgreSQL(dbConnection);
        }
        throw new SQLException("Undefined or unrecognized DBMS name: " + dbms);
    }

}
