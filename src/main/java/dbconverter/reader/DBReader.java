package dbconverter.reader;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Abstract class that represents a reader to source database
 *
 * @author Anderson Spaniol
 */
public abstract class DBReader {

    private final DBConnection dbConnection;

    protected DBReader(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public abstract List<String> getTableNames() throws SQLException;

    public TableStructure getTableStructure(String tableName) throws SQLException {
        TableStructure tableStructure = new TableStructure(tableName);
        loadTableColumns(tableStructure);
        loadTableIndexes(tableStructure);
        return tableStructure;
    }

    protected abstract void loadTableColumns(TableStructure tableStructure) throws SQLException;

    protected abstract void loadTableIndexes(TableStructure tableStructure) throws SQLException;

    protected IndexColumn createIndexColumn(TableStructure tableStructure, String columnName, int subpart) {
        Optional<TableColumn> findAny = tableStructure.getColumns()
                                                      .stream()
                                                      .filter(t -> t.getColumnName().equals(columnName))
                                                      .findAny();
        TableColumn tableColumn = findAny.get();
        return new IndexColumn(tableColumn, subpart);
    }
    
    public Stream<TableRecord> getTableRecords(final TableStructure tableStructure) throws SQLException {
        String tableName = tableStructure.getTableName();
        String command = "select * from " + tableName;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(command);
        ResultSet resultSet = preparedStatement.executeQuery();
        Stream<TableRecord> stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<TableRecord>(Long.MAX_VALUE, Spliterator.ORDERED) {

            @Override
            public boolean tryAdvance(Consumer<? super TableRecord> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createRecord(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }

            private TableRecord createRecord(ResultSet resultSet) throws SQLException {
                TableRecord tableRecord = new TableRecord();
                List<TableColumn> columns = tableStructure.getColumns();
                for (TableColumn column : columns) {
                    String columnName = column.getColumnName();
                    Object columnsValue = resultSet.getObject(columnName);
                    tableRecord.setColumnValue(columnName, columnsValue);
                }
                return tableRecord;
            }
        }, false);
        return stream;
    }

}
