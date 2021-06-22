package dbconverter.reader;

import dbconverter.contants.PostgreSQLConstants;
import dbconverter.datatypes.DataType;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;
import dbconverter.datatypes.TableStructure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for DBReaderPostgreSQL class
 *
 * @author Anderson Spaniol
 */
public class DBReaderPostgreSQLTest {

    public DBReaderPostgreSQLTest() {
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
     * Test of createTableColumn method, of class DBReaderPostgreSQL.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateTableColumn() throws Exception {
        System.out.println("createTableColumn");
        DBReaderPostgreSQL instance = new DBReaderPostgreSQL(null);
        TableColumn expResult;
        TableColumn result;
        // Test: character(3)
        expResult = new TableColumn("test_123", DataType.CHAR, 3, 0, false);
        result = instance.createTableColumn("test_123", "character", 3, 0, 0, false);
        assertEquals(expResult, result);
        // Test: character varying(32)
        expResult = new TableColumn("test_123", DataType.VARCHAR, 32, 0, false);
        result = instance.createTableColumn("test_123", "character varying", 32, 0, 0, false);
        assertEquals(expResult, result);
        // Test: numeric(4)
        expResult = new TableColumn("test_123", DataType.INTEGER, 4, 0, false);
        result = instance.createTableColumn("test_123", "numeric", 0, 4, 0, false);
        assertEquals(expResult, result);
        // Test: numeric(5, 2)
        expResult = new TableColumn("test_123", DataType.DECIMAL, 5, 2, false);
        result = instance.createTableColumn("test_123", "numeric", 0, 5, 2, false);
        assertEquals(expResult, result);
        // Test: smallint
        expResult = new TableColumn("test_123", DataType.INTEGER, PostgreSQLConstants.LENGTH_SMALLINT, 0, false);
        result = instance.createTableColumn("test_123", "smallint", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: integer
        expResult = new TableColumn("test_123", DataType.INTEGER, PostgreSQLConstants.LENGTH_INTEGER, 0, false);
        result = instance.createTableColumn("test_123", "integer", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: bigint
        expResult = new TableColumn("test_123", DataType.INTEGER, PostgreSQLConstants.LENGTH_BIGINT, 0, false);
        result = instance.createTableColumn("test_123", "bigint", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: date
        expResult = new TableColumn("test_123", DataType.DATE, 0, 0, false);
        result = instance.createTableColumn("test_123", "date", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: bytea
        expResult = new TableColumn("test_123", DataType.BINARY, PostgreSQLConstants.LENGTH_BYTEA, 0, false);
        result = instance.createTableColumn("test_123", "bytea", 50, 0, 0, false);
        assertEquals(expResult, result);
    }

    /**
     * Test of createIndexColumn method, of class DBReaderPostgreSQL.
     */
    @Test
    public void testCreateIndexColumn() {
        System.out.println("createIndexColumn");
        String columnName = "test_123";
        TableStructure tableStructure = new TableStructure("table_test");
        TableColumn tableColumn = new TableColumn(columnName, DataType.INTEGER, 4, 0, false);
        tableStructure.addColumn(tableColumn);
        int subpart = 12;
        DBReaderPostgreSQL instance = new DBReaderPostgreSQL(null);
        IndexColumn expResult;
        IndexColumn result;
        // Test: Create a single index column
        expResult = new IndexColumn(tableColumn, subpart);
        result = instance.createIndexColumn(tableStructure, columnName, subpart);
        assertEquals(expResult, result);
    }

}
