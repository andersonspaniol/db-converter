package dbconverter.orchestration;

import dbconverter.reader.DBReader;
import dbconverter.writer.DBWriter;

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
        // Implement here the logic
    }

}
