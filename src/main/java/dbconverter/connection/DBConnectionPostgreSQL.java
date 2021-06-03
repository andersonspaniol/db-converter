package dbconverter.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class to represent a PostgreSQL connection
 *
 * @author Anderson Spaniol
 */
public class DBConnectionPostgreSQL extends DBConnection {

    protected DBConnectionPostgreSQL(String hostname, String port, String database, String schema, String user, String password) throws SQLException {
        super(hostname, port, database, schema, user, password);
    }

    @Override
    public String getDriver() {
        return "org.postgresql.Driver";
    }

    @Override
    public Connection connect(String hostname, String port, String database, String user, String password) throws SQLException {
        String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    protected void afterConnect(Connection connection, String database, String schema) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("create schema if not exists \"" + schema +"\"")) {
            preparedStatement.executeUpdate();
        }
        connection.setSchema(schema);
    }
    
}