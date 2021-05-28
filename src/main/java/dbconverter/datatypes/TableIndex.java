package dbconverter.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a table index
 *
 * @author Anderson Spaniol
 */
public class TableIndex {

    private final String indexName;
    private final boolean nonUnique;
    private final List<IndexColumn> columns;

    public TableIndex(String indexName, boolean nonUnique) {
        this.indexName = indexName;
        this.nonUnique = nonUnique;
        this.columns = new ArrayList();
    }

    public void addColumn(IndexColumn indexColumn) {
        columns.add(indexColumn);
    }

}
