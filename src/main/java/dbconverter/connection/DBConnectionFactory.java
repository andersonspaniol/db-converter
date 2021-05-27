package dbconverter.connection;

import java.sql.SQLException;

/**
 * Factory for constructing DBConnection objects
 * 
 * @author Anderson Spaniol
 */
public class DBConnectionFactory {

    public static DBConnection newConnection(String dbms, String hostname, String port,
                                      String database, String schema, String user,
                                      String password) throws SQLException {
         if (dbms.equals("mariadb")) {
             return new DBConnectionMariaDB(hostname, port, database, schema, user, password);
         }
         if (dbms.equals("postgresql")) {
             return new DBConnectionPostgreSQL(hostname, port, database, schema, user, password);
         }
         throw new SQLException("Undefined or unrecognized DBMS name: " + dbms);
    }
    
}