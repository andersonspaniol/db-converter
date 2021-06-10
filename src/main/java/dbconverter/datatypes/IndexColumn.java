package dbconverter.datatypes;

import java.util.Objects;

/**
 * Class that represents a index column
 *
 * @author Anderson Spaniol
 */
public class IndexColumn {

    private final TableColumn tableColumn;
    private final int subpart;

    public IndexColumn(TableColumn tableColumn, int subpart) {
        this.tableColumn = tableColumn;
        this.subpart = subpart;
    }

    public TableColumn getTableColumn() {
        return tableColumn;
    }

    public int getSubpart() {
        return subpart;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.tableColumn);
        hash = 89 * hash + this.subpart;
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
        if (!Objects.equals(this.tableColumn, other.tableColumn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IndexColumn{" + "tableColumn=" + tableColumn + ", subpart=" + subpart + '}';
    }

}
