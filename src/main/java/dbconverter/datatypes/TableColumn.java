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
    private final int lenght;
    private final int scale;
    private final boolean nullable;
    private final Object defaultValue;

    public TableColumn(String columnName, DataType dataType, int lenght, int scale, boolean nullable, Object defaultValue) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.lenght = lenght;
        this.scale = scale;
        this.nullable = nullable;
        this.defaultValue = defaultValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public int getLenght() {
        return lenght;
    }

    public int getScale() {
        return scale;
    }

    public boolean isNullable() {
        return nullable;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.columnName);
        hash = 37 * hash + Objects.hashCode(this.dataType);
        hash = 37 * hash + this.lenght;
        hash = 37 * hash + this.scale;
        hash = 37 * hash + (this.nullable ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.defaultValue);
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
        if (!Objects.equals(this.defaultValue, other.defaultValue)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TableColumn{" + "columnName=" + columnName + ", dataType=" + dataType + ", lenght=" + lenght + ", scale=" + scale + ", nullable=" + nullable + ", defaultValue=" + defaultValue + '}';
    }

}
