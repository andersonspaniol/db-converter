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

    public TableColumn(String columnName, DataType dataType, int lenght, int scale) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.lenght = lenght;
        this.scale = scale;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.columnName);
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
        return "TableColumn{" + "columnName=" + columnName + ", dataType=" + dataType + ", lenght=" + lenght + ", scale=" + scale + '}';
    }

}
