package dbconverter.datatypes;

import java.util.Objects;

/**
 * Class that represents a index column
 *
 * @author Anderson Spaniol
 */
public class IndexColumn {

    private final String columnName;
    private final int subpart;

    public IndexColumn(String columnName, int subpart) {
        this.columnName = columnName;
        this.subpart = subpart;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getSubpart() {
        return subpart;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.columnName);
        hash = 59 * hash + this.subpart;
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
        final IndexColumn other = (IndexColumn) obj;
        if (this.subpart != other.subpart) {
            return false;
        }
        if (!Objects.equals(this.columnName, other.columnName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IndexColumn{" + "columnName=" + columnName + ", subpart=" + subpart + '}';
    }

}
