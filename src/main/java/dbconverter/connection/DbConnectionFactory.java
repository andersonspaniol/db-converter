/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconverter.connection;

import java.sql.SQLException;

/**
 * Factory for constructing DBConnection objects
 * 
 * @author Anderson Spaniol
 */
public class DbConnectionFactory {

    public static DbConnection newConnection(String dbms, String hostname, String port,
                                      String database, String schema, String user,
                                      String password) throws SQLException {
         if (dbms.equals("mariadb")) {
             return new DbConnectionMariaDB(hostname, port, database, schema, user, password);
         }
         if (dbms.equals("postgresql")) {
             return new DbConnectionPostgreSQL(hostname, port, database, schema, user, password);
         }
         throw new SQLException("Undefined or unrecognized DBMS name: " + dbms);
    }
    
}
