package dbconverter.datatypes;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a table record
 *
 * @author Anderson Spaniol
 */
public class TableRecord {

    private final Map<String, Object> columnValues;

    public TableRecord() {
        this.columnValues = new HashMap();
    }

    public void setColumnValue(String columnName, Object columnsValue) {
        columnValues.put(columnName, columnsValue);
    }

    public Object getColumnValue(String columnName) {
        return columnValues.get(columnName);
    }

    @Override
    public String toString() {
        return "TableRecord{" + "columnValues=" + columnValues + '}';
    }

}
