package dbconverter.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Abstract class to represent a database connection
 *
 * @author Anderson Spaniol
 */
public abstract class DBConnection {

    private final Connection connection;

    protected DBConnection(String hostname, String port, String database, String schema, String user, String password) throws SQLException {
        String driver = getDriver();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Não conseguiu carregar o driver jdbc:" + driver, e);
        }
        connection = connect(hostname, port, database, user, password);
        afterConnect(connection, database, schema);
        connection.setAutoCommit(false);
    }

    public PreparedStatement prepareStatement(String cmd) throws SQLException {
        return connection.prepareStatement(cmd);
    }

    public abstract String getDriver();

    public abstract Connection connect(String hostname, String port, String database, String user, String password) throws SQLException;

    protected abstract void afterConnect(Connection connection, String database, String schema) throws SQLException;

    public void commit() throws SQLException {
        connection.commit();
    }

}
