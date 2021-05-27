package dbconverter;

import dbconverter.connection.DBConnection;
import dbconverter.connection.DBConnectionFactory;
import dbconverter.orchestration.Orchestrator;
import dbconverter.params.Parameters;
import dbconverter.params.ParametersParsingException;
import dbconverter.reader.DBReader;
import dbconverter.reader.DBReaderFactory;
import dbconverter.writer.DBWriter;
import dbconverter.writer.DBWriterFactory;
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
            // Create a connection to source database
            String sourceDbms = Parameters.def().getSourceDbms();
            String sourceHostname = Parameters.def().getSourceHostname();
            String sourcePort = Parameters.def().getSourcePort();
            String sourceDatabase = Parameters.def().getSourceDatabase();
            String sourceSchema = Parameters.def().getSourceSchema();
            String sourceUser = Parameters.def().getSourceUser();
            String sourcePassword = Parameters.def().getSourcePassword();
            DBConnection sourceConnection = DBConnectionFactory.newConnection(sourceDbms, sourceHostname, sourcePort, sourceDatabase, sourceSchema, sourceUser, sourcePassword);
            // Create a reader to source database
            DBReader sourceReader = DBReaderFactory.newReader(sourceDbms, sourceConnection);
            // Create a connection to target database
            String targetDbms = Parameters.def().getTargetDbms();
            String targetHostname = Parameters.def().getTargetHostname();
            String targetPort = Parameters.def().getTargetPort();
            String targetDatabase = Parameters.def().getTargetDatabase();
            String targetSchema = Parameters.def().getTargetSchema();
            String targetUser = Parameters.def().getTargetUser();
            String targetPassword = Parameters.def().getTargetPassword();
            DBConnection targetConnection = DBConnectionFactory.newConnection(targetDbms, targetHostname, targetPort, targetDatabase, targetSchema, targetUser, targetPassword);
            // Create a writer to target database
            DBWriter targetWriter = DBWriterFactory.newWriter(targetDbms, targetConnection);
            // Create an orchestrator that performs the processing
            Orchestrator orchestrator = new Orchestrator(sourceReader, targetWriter);
            orchestrator.process();
        } catch (ParametersParsingException | SQLException ex) {
            Logger.getLogger(DbConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
