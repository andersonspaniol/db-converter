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
    private final boolean primaryKey;
    private final boolean nonUnique;
    private final List<IndexColumn> columns;

    public TableIndex(String indexName, boolean primaryKey, boolean nonUnique) {
        this.indexName = indexName;
        this.primaryKey = primaryKey;
        this.nonUnique = nonUnique;
        this.columns = new ArrayList();
    }

    public String getIndexName() {
        return indexName;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public boolean isNonUnique() {
        return nonUnique;
    }

    public List<IndexColumn> getColumns() {
        return columns;
    }

    public void addColumn(IndexColumn indexColumn) {
        columns.add(indexColumn);
    }

    @Override
    public String toString() {
        return "TableIndex{" + "indexName=" + indexName + ", primaryKey=" + primaryKey + ", nonUnique=" + nonUnique + ", columns=" + columns + '}';
    }

}
