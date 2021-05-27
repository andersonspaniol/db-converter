package dbconverter.orchestration;

import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import dbconverter.reader.DBReader;
import dbconverter.writer.DBWriter;
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

    public void process() {
        processTables();
    }

    private void processTables() {
        List<String> tableNames = sourceReader.getTableNames();
        for (String tableName : tableNames) {
            // Copy the table structure
            TableStructure tableStructure = sourceReader.getTableStructure(tableName);
            targetWriter.createTable(tableStructure);
            // Copy table records
            Stream<TableRecord> tableRecords = sourceReader.getTableRecords(tableName);
            tableRecords.forEach(targetWriter::insertTableRecord);
            targetWriter.flushTableRecords();
        }
    }

}
