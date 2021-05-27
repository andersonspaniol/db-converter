package dbconverter.writer;

import dbconverter.connection.DBConnection;
import java.sql.SQLException;

/**
 * Factory for constructing DBReader objects
 *
 * @author Anderson Spaniol
 */
public class DBWriterFactory {

    public static DBWriter newWriter(String dbms, DBConnection dbConnection) throws SQLException {
        if (dbms.equals("mariadb")) {
            return new DBWriterMariaDB(dbConnection);
        }
        if (dbms.equals("postgresql")) {
            return new DBWriterPostgreSQL(dbConnection);
        }
        throw new SQLException("Undefined or unrecognized DBMS name: " + dbms);
    }

}
