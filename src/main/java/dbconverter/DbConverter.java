package dbconverter;

import dbconverter.params.Parameters;
import dbconverter.params.ParametersParsingException;
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
        } catch (ParametersParsingException ex) {
            Logger.getLogger(DbConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
