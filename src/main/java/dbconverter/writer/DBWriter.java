package dbconverter.writer;

import dbconverter.connection.DBConnection;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableRecord;
import dbconverter.datatypes.TableStructure;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class that represents a writer to target database
 *
 * @author Anderson Spaniol
 */
public abstract class DBWriter {

    private final DBConnection dbConnection;

    protected DBWriter(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public void dropTableIfExists(TableStructure tableStructure) throws SQLException {
        String tableName = tableStructure.getTableName();
        String cmd = "drop table if exists " + tableName;
        try (var preparedStatement = getDbConnection().prepareStatement(cmd)) {
            preparedStatement.executeUpdate();
        }
    }

    public void createTable(TableStructure tableStructure) throws SQLException {
        String tableName = tableStructure.getTableName();
        List<String> collect = tableStructure.getColumns()
                                             .stream()
                                             .map(this::getSqlCreateColumn)
                                             .collect(Collectors.toList());
        String cmd = "create table " + tableName + " (" + String.join(", ", collect) + ")";
        try (var preparedStatement = getDbConnection().prepareStatement(cmd)) {
            preparedStatement.executeUpdate();
        }
    }
    
    protected String getSqlCreateColumn(TableColumn tableColumn) {
        return tableColumn.getColumnName() + " " + getSqlColumnType(tableColumn);
    }
    
    private String getSqlColumnType(TableColumn tableColumn) {
        long length = tableColumn.getLenght();
        switch (tableColumn.getDataType()) {
            case CHAR:
                return "char(" + length + ")";
            case VARCHAR:
                return "varchar(" + length + ")";
            case INTEGER:
                return getSqlColumnTypeInteger(tableColumn);
            case DECIMAL:
                long decimais = tableColumn.getScale();
                if (decimais == 0) {
                    return "numeric(" + length + ")";
                } else {
                    return "numeric(" + length + ", " + decimais + ")";
                }
            case DATE:
                return "date";
            case BINARY:
                return getSqlColumnTypeBinary(tableColumn);
            default:
                return "<tipo de coluna inválido>";
        }
    }

    protected abstract String getSqlColumnTypeInteger(TableColumn tableColumn);

    protected abstract String getSqlColumnTypeBinary(TableColumn tableColumn);

    public void insertTableRecord(TableStructure tableStructure, TableRecord tableRecord) {
        String tableName = tableStructure.getTableName();
        List<String> columnsNames = tableStructure.getColumns().stream().map(TableColumn::getColumnName).collect(Collectors.toList());
        List<String> columnsSubstitutions = tableStructure.getColumns().stream().map(t -> "?").collect(Collectors.toList());
        String cmd = "insert into " + tableName + " (" + String.join(", ", columnsNames) + ") values (" + String.join(", ", columnsSubstitutions) + ")";
        try (var preparedStatement = getDbConnection().prepareStatement(cmd)) {
            int index = 0;
            for (String columnName : columnsNames) {
                Object columnValue = tableRecord.getColumnValue(columnName);
                index++;
                preparedStatement.setObject(index, columnValue);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void flushTableRecords() throws SQLException {
        getDbConnection().commit();
    }

    public abstract void createTableIndexes() throws SQLException;
    
    public abstract void createTablePrimaryKeys() throws SQLException;

    public abstract void createTableConstraints() throws SQLException;


}
