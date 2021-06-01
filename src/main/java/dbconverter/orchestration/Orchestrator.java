package dbconverter.orchestration;

import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import dbconverter.reader.DBReader;
import dbconverter.writer.DBWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class that orchestrate all the processing
 *
 * @author Anderson Spaniol
 */
public class Orchestrator {

    private final DBReader sourceReader;
    private final DBWriter targetWriter;

    public Orchestrator(DBReader sourceReader, DBWriter targetWriter) {
        this.sourceReader = sourceReader;
        this.targetWriter = targetWriter;
    }

    public void process() throws SQLException {
        processTables();
    }

    private void processTables() throws SQLException, SQLException {
        List<String> tableNames = sourceReader.getTableNames();
        for (String tableName : tableNames) {
            // Create tables, without any constraint
            TableStructure tableStructure = sourceReader.getTableStructure(tableName);
            targetWriter.dropTableIfExists(tableStructure);
            targetWriter.createTable(tableStructure);
            // Copy table records
            Stream<TableRecord> tableRecords = sourceReader.getTableRecords(tableName);
            tableRecords.forEach(targetWriter::insertTableRecord);
            targetWriter.flushTableRecords();
            // Create tables constraints
            targetWriter.createTablePrimaryKeys();
            targetWriter.createTableConstraints();
        }
    }

}
