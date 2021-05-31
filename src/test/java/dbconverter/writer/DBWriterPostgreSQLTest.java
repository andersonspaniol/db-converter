package dbconverter.writer;

import dbconverter.contants.PostgreSQLConstants;
import dbconverter.datatypes.DataType;
import dbconverter.datatypes.TableColumn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for DBWriterPostgreSQL class
 *
 * @author Anderson Spaniol
 */
public class DBWriterPostgreSQLTest {

    public DBWriterPostgreSQLTest() {
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
     * Test of getSqlCreateColumn method, of class DBWriterPostgreSQL.
     */
    @Test
    public void testGetSqlCreateColumn() {
        System.out.println("getSqlCreateColumn");
        DBWriterPostgreSQL instance = new DBWriterPostgreSQL(null);
        TableColumn tableColumn;
        String expResult;
        String result;
        // Test: char(3)
        tableColumn = new TableColumn("test_123", DataType.CHAR, 3, 0);
        expResult = "test_123 char(3)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: varchar(32)
        tableColumn = new TableColumn("test_123", DataType.VARCHAR, 32, 0);
        expResult = "test_123 varchar(32)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: numeric(4)
        tableColumn = new TableColumn("test_123", DataType.DECIMAL, 4, 0);
        expResult = "test_123 numeric(4)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: numeric(5, 2)
        tableColumn = new TableColumn("test_123", DataType.DECIMAL, 5, 2);
        expResult = "test_123 numeric(5, 2)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: smallint
        tableColumn = new TableColumn("test_123", DataType.INTEGER, PostgreSQLConstants.LENGTH_SMALLINT, 0);
        expResult = "test_123 smallint";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: integer
        tableColumn = new TableColumn("test_123", DataType.INTEGER, PostgreSQLConstants.LENGTH_INTEGER, 0);
        expResult = "test_123 integer";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: bigint
        tableColumn = new TableColumn("test_123", DataType.INTEGER, PostgreSQLConstants.LENGTH_BIGINT, 0);
        expResult = "test_123 bigint";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: decimal(20)
        tableColumn = new TableColumn("test_123", DataType.INTEGER, 20, 0);
        expResult = "test_123 numeric(20)";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: date
        tableColumn = new TableColumn("test_123", DataType.DATE, 0, 0);
        expResult = "test_123 date";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
        // Test: bytea
        tableColumn = new TableColumn("test_123", DataType.BINARY, 100, 0);
        expResult = "test_123 bytea";
        result = instance.getSqlCreateColumn(tableColumn);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSqlColumnTypeInteger method, of class DBWriterPostgreSQL.
     */
    @Test
    public void testGetSqlColumnTypeInteger() {
        System.out.println("getSqlColumnTypeInteger");
    }

    /**
     * Test of getSqlColumnTypeBinary method, of class DBWriterPostgreSQL.
     */
    @Test
    public void testGetSqlColumnTypeBinary() {
        System.out.println("getSqlColumnTypeBinary");
    }

    /**
     * Test of insertTableRecord method, of class DBWriterPostgreSQL.
     */
    @Test
    public void testInsertTableRecord() {
        System.out.println("insertTableRecord");
    }

    /**
     * Test of flushTableRecords method, of class DBWriterPostgreSQL.
     */
    @Test
    public void testFlushTableRecords() {
        System.out.println("flushTableRecords");
    }

}
