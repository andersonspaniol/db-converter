package dbconverter.writer;

import dbconverter.connection.DBConnection;
import dbconverter.contants.MariaDBConstants;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;

/**
 * Class that represents a writer to target database - MariaDB
 *
 * @author Anderson Spaniol
 */
public class DBWriterMariaDB extends DBWriter {

    protected DBWriterMariaDB(DBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    protected String getSqlColumnTypeInteger(TableColumn tableColumn) {
        long length = tableColumn.getLenght();
        if (length <= MariaDBConstants.LENGTH_TINYINT) {
            return "tinyint";
        } else if (length <= MariaDBConstants.LENGTH_SMALLINT) {
            return "smallint";
        } else if (length <= MariaDBConstants.LENGTH_MEDIUMINT) {
            return "mediumint";
        } else if (length <= MariaDBConstants.LENGTH_INT) {
            return "int";
        } else if (length <= MariaDBConstants.LENGTH_BIGINT) {
            return "bigint";
        } else {
            return "numeric(" + length + ")";
        }
    }

    @Override
    protected String getSqlColumnTypeBinary(TableColumn tableColumn) {
        long length = tableColumn.getLenght();
        if (length <= MariaDBConstants.LENGTH_TINYBLOB) {
            return "tinyblob";
        } else if (length <= MariaDBConstants.LENGTH_BLOB) {
            return "blob";
        } else if (length <= MariaDBConstants.LENGTH_MEDIUMBLOB) {
            return "mediumblob";
        } else {
            return "longblob";
        }
    }

    @Override
    protected String getSqlColumnIndex(IndexColumn indexColumn) {

//        if (tableColumn.getTipoCampoBD() == TipoCampoBD.BINARY) {
//            return tableColumn.getNomeBD() + "(" + tableColumn.getTamanho() + ")";
//        } else {
//            return tableColumn.getNomeBD();
//        }


        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
