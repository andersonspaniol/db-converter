package dbconverter;

import dbconverter.connection.DbConnectionFactory;
import dbconverter.params.Parameters;
import dbconverter.params.ParametersParsingException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class to execute the DB Converter from command line
 * 
 * @author Anderson Spaniol
 */
public class DbConverter {
    
    public static void main(String[] args) {
        try {
            Parameters.def().parse(args);

            String sourceDbms = Parameters.def().getSourceDbms();
            String sourceHostname = Parameters.def().getSourceHostname();
            String sourcePort = Parameters.def().getSourcePort();
            String sourceDatabase = Parameters.def().getSourceDatabase();
            String sourceSchema = Parameters.def().getSourceSchema();
            String sourceUser = Parameters.def().getSourceUser();
            String sourcePassword = Parameters.def().getSourcePassword();
            DbConnectionFactory.newConnection(sourceDbms, sourceHostname, sourcePort, sourceDatabase, sourceSchema, sourceUser, sourcePassword);
            
            String targetDbms = Parameters.def().getTargetDbms();
            String targetHostname = Parameters.def().getTargetHostname();
            String targetPort = Parameters.def().getTargetPort();
            String targetDatabase = Parameters.def().getTargetDatabase();
            String targetSchema = Parameters.def().getTargetSchema();
            String targetUser = Parameters.def().getTargetUser();
            String targetPassword = Parameters.def().getTargetPassword();
            DbConnectionFactory.newConnection(targetDbms, targetHostname, targetPort, targetDatabase, targetSchema, targetUser, targetPassword);
            
        } catch (ParametersParsingException | SQLException ex) {
            Logger.getLogger(DbConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
