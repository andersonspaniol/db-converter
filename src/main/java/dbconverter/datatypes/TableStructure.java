package dbconverter.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a complete table structure
 *
 * @author Anderson Spaniol
 */
public class TableStructure {

    private final String tableName;
    private final List<TableColumn> columns;
    private final List<TableIndex> indexes;

    public TableStructure(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList();
        this.indexes = new ArrayList();
    }

    public String getTableName() {
        return tableName;
    }

    public void addColumn(TableColumn tableColumn) {
        columns.add(tableColumn);
    }

    public void addIndex(TableIndex tableIndex) {
        indexes.add(tableIndex);
    }

    @Override
    public String toString() {
        return "TableStructure{" + "tableName=" + tableName + ", columns=" + columns + ", indexes=" + indexes + '}';
    }

}
