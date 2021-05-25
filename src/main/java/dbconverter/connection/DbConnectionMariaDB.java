package dbconverter.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to represent a MariaDB connection
 *
 * @author Anderson Spaniol
 */
public class DbConnectionMariaDB extends DbConnection {

    protected DbConnectionMariaDB(String hostname, String port, String database, String schema, String user, String password) throws SQLException {
        super(hostname, port, database, schema, user, password);
    }

    @Override
    public String getDriver() {
        return "org.mariadb.jdbc.Driver";
    }

    @Override
    public Connection connect(String hostname, String port, String database, String user, String password) throws SQLException {
        String url = "jdbc:mariadb://" + hostname + ":" + port + "?rewriteBatchedStatements=true";
        return DriverManager.getConnection(url, user, password);
    }
    
}
