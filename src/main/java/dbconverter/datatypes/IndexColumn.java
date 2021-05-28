package dbconverter.datatypes;

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

}
