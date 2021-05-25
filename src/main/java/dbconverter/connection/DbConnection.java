package dbconverter.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract class to represent a database connection
 * 
 * @author Anderson Spaniol
 */
public abstract class DbConnection {
    
    private final Connection connection;

    protected DbConnection(String hostname, String port, String database,
                        String schema, String user, String password) throws SQLException {
        String driver = getDriver();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Não conseguiu carregar o driver jdbc:" + driver, e);
        }
        connection = connect(hostname, port, database, user, password);
    }

    public abstract String getDriver();

    public abstract Connection connect(String hostname, String port, String database, String user, String password) throws SQLException ;
    
}
