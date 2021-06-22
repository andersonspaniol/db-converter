package dbconverter.datatypes;

import java.util.Objects;

/**
 * Class that represents a table column
 *
 * @author Anderson Spaniol
 */
public class TableColumn {

    private final String columnName;
    private final DataType dataType;
    private final long lenght;
    private final int scale;
    private final boolean nullable;

    public TableColumn(String columnName, DataType dataType, long lenght, int scale, boolean nullable) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.lenght = lenght;
        this.scale = scale;
        this.nullable = nullable;
    }

    public String getColumnName() {
        return columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public long getLenght() {
        return lenght;
    }

    public int getScale() {
        return scale;
    }

    public boolean isNullable() {
        return nullable;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.columnName);
        hash = 23 * hash + Objects.hashCode(this.dataType);
        hash = 23 * hash + (int) (this.lenght ^ (this.lenght >>> 32));
        hash = 23 * hash + this.scale;
        hash = 23 * hash + (this.nullable ? 1 : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableColumn other = (TableColumn) obj;
        if (this.lenght != other.lenght) {
            return false;
        }
        if (this.scale != other.scale) {
            return false;
        }
        if (this.nullable != other.nullable) {
            return false;
        }
        if (!Objects.equals(this.columnName, other.columnName)) {
            return false;
        }
        if (this.dataType != other.dataType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TableColumn{" + "columnName=" + columnName + ", dataType=" + dataType + ", lenght=" + lenght + ", scale=" + scale + ", nullable=" + nullable + '}';
    }

}
