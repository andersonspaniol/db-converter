package dbconverter.writer;

import dbconverter.contants.MariaDBConstants;
import dbconverter.datatypes.DataType;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableIndex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for DBWriterMariaDB class
 *
 * @author Anderson Spaniol
 */
public class DBWriterMariaDBTest {

    public DBWriterMariaDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSqlCreateColumn method, of class DBWriterMariaDB.
     */
    @Test
    public void testGetSqlCreateColumn() {
        System.out.println("getSqlCreateColumn");
        DBWriterMariaDB instance = new DBWriterMariaDB(null);
        TableColumn tableColumn;
        String expResult;
        String result;
        // Test: char(3)
        tableColumn = new TableColumn("test_123", DataType.CHAR, 3, 0, true);
        expResult = "test_123 char(3)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: varchar(32)
        tableColumn = new TableColumn("test_123", DataType.VARCHAR, 32, 0, true);
        expResult = "test_123 varchar(32)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: numeric(4)
        tableColumn = new TableColumn("test_123", DataType.DECIMAL, 4, 0, true);
        expResult = "test_123 numeric(4)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: numeric(5, 2)
        tableColumn = new TableColumn("test_123", DataType.DECIMAL, 5, 2, true);
        expResult = "test_123 numeric(5, 2)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: tinyint
        tableColumn = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_TINYINT, 0, true);
        expResult = "test_123 tinyint";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: smallint
        tableColumn = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_SMALLINT, 0, true);
        expResult = "test_123 smallint";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: mediumint
        tableColumn = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_MEDIUMINT, 0, true);
        expResult = "test_123 mediumint";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: int
        tableColumn = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_INT, 0, true);
        expResult = "test_123 int";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: bigint
        tableColumn = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_BIGINT, 0, true);
        expResult = "test_123 bigint";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: decimal(20)
        tableColumn = new TableColumn("test_123", DataType.INTEGER, 20, 0, true);
        expResult = "test_123 numeric(20)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: date
        tableColumn = new TableColumn("test_123", DataType.DATE, 0, 0, true);
        expResult = "test_123 date";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: tinyblob
        tableColumn = new TableColumn("test_123", DataType.BINARY, MariaDBConstants.LENGTH_TINYBLOB, 0, true);
        expResult = "test_123 tinyblob";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: blob
        tableColumn = new TableColumn("test_123", DataType.BINARY, MariaDBConstants.LENGTH_BLOB, 0, true);
        expResult = "test_123 blob";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: mediumblob
        tableColumn = new TableColumn("test_123", DataType.BINARY, MariaDBConstants.LENGTH_MEDIUMBLOB, 0, true);
        expResult = "test_123 mediumblob";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: longblob
        tableColumn = new TableColumn("test_123", DataType.BINARY, MariaDBConstants.LENGTH_LONGBLOB, 0, true);
        expResult = "test_123 longblob";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: char(3) not null
        tableColumn = new TableColumn("test_123", DataType.CHAR, 3, 0, false);
        expResult = "test_123 char(3) not null";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSqlCreateIndex method, of class DBWriterMariaDB.
     */
    @Test
    public void testGetSqlCreateIndexIndex() {
        System.out.println("getSqlCreateIndex");
        DBWriterMariaDB instance = new DBWriterMariaDB(null);
        TableColumn tableColumn1 = new TableColumn("column_1", DataType.INTEGER, 4, 0, true);
        TableColumn tableColumn2 = new TableColumn("column_2", DataType.INTEGER, 4, 0, true);
        TableIndex tableIndex;
        String expResult;
        String result;
        // Create primary key
        tableIndex = new TableIndex("index_test", true, false);
        tableIndex.addColumn(new IndexColumn(tableColumn1, 0));
        tableIndex.addColumn(new IndexColumn(tableColumn2, 0));
        expResult = "alter table table_test add constraint table_test_pk primary key (column_1, column_2)";
        result = instance.getSqlCreateIndex("table_test", tableIndex);
        assertEquals(expResult, result);
        // Create non-unique index
        tableIndex = new TableIndex("index_test", false, true);
        tableIndex.addColumn(new IndexColumn(tableColumn1, 0));
        tableIndex.addColumn(new IndexColumn(tableColumn2, 0));
        expResult = "create index index_test on table_test (column_1, column_2)";
        result = instance.getSqlCreateIndex("table_test", tableIndex);
        assertEquals(expResult, result);
        // Create unique index
        tableIndex = new TableIndex("index_test", false, false);
        tableIndex.addColumn(new IndexColumn(tableColumn1, 0));
        tableIndex.addColumn(new IndexColumn(tableColumn2, 0));
        expResult = "create unique index index_test on table_test (column_1, column_2)";
        result = instance.getSqlCreateIndex("table_test", tableIndex);
        assertEquals(expResult, result);
    }

}
