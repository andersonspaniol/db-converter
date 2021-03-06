package dbconverter.reader;

import dbconverter.contants.MariaDBConstants;
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
 * Unit test for DBReaderMariaDB class
 *
 * @author Anderson Spaniol
 */
public class DBReaderMariaDBTest {

    public DBReaderMariaDBTest() {
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
     * Test of createTableColumn method, of class DBReaderMariaDB.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateTableColumn() throws Exception {
        System.out.println("createTableColumn");
        DBReaderMariaDB instance = new DBReaderMariaDB(null);
        TableColumn expResult;
        TableColumn result;
        // Test: char(3)
        expResult = new TableColumn("test_123", DataType.CHAR, 3, 0, false);
        result = instance.createTableColumn("test_123", "char", 3, 0, 0, false);
        assertEquals(expResult, result);
        // Test: varchar(32)
        expResult = new TableColumn("test_123", DataType.VARCHAR, 32, 0, false);
        result = instance.createTableColumn("test_123", "varchar", 32, 0, 0, false);
        assertEquals(expResult, result);
        // Test: decimal(4)
        expResult = new TableColumn("test_123", DataType.INTEGER, 4, 0, false);
        result = instance.createTableColumn("test_123", "decimal", 0, 4, 0, false);
        assertEquals(expResult, result);
        // Test: decimal(5, 2)
        expResult = new TableColumn("test_123", DataType.DECIMAL, 5, 2, false);
        result = instance.createTableColumn("test_123", "decimal", 0, 5, 2, false);
        assertEquals(expResult, result);
        // Test: tinyint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_TINYINT, 0, false);
        result = instance.createTableColumn("test_123", "tinyint", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: smallint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_SMALLINT, 0, false);
        result = instance.createTableColumn("test_123", "smallint", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: mediumint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_MEDIUMINT, 0, false);
        result = instance.createTableColumn("test_123", "mediumint", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: int
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_INT, 0, false);
        result = instance.createTableColumn("test_123", "int", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: bigint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_BIGINT, 0, false);
        result = instance.createTableColumn("test_123", "bigint", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: date
        expResult = new TableColumn("test_123", DataType.DATE, 0, 0, false);
        result = instance.createTableColumn("test_123", "date", 0, 0, 0, false);
        assertEquals(expResult, result);
        // Test: tinyblob
        expResult = new TableColumn("test_123", DataType.BINARY, 50, 0, false);
        result = instance.createTableColumn("test_123", "tinyblob", 50, 0, 0, false);
        assertEquals(expResult, result);
        // Test: blob
        expResult = new TableColumn("test_123", DataType.BINARY, 50, 0, false);
        result = instance.createTableColumn("test_123", "blob", 50, 0, 0, false);
        assertEquals(expResult, result);
        // Test: blob
        expResult = new TableColumn("test_123", DataType.BINARY, 50, 0, false);
        result = instance.createTableColumn("test_123", "mediumblob", 50, 0, 0, false);
        assertEquals(expResult, result);
        // Test: longblob
        expResult = new TableColumn("test_123", DataType.BINARY, 50, 0, false);
        result = instance.createTableColumn("test_123", "longblob", 50, 0, 0, false);
        assertEquals(expResult, result);
    }

    /**
     * Test of createIndexColumn method, of class DBReaderMariaDB.
     */
    @Test
    public void testCreateIndexColumn() {
        System.out.println("createIndexColumn");
        String columnName = "test_123";
        TableStructure tableStructure = new TableStructure("table_test");
        TableColumn tableColumn = new TableColumn(columnName, DataType.INTEGER, 4, 0, false);
        tableStructure.addColumn(tableColumn);
        int subpart = 12;
        DBReaderMariaDB instance = new DBReaderMariaDB(null);
        IndexColumn expResult;
        IndexColumn result;
        // Test: Create a single index column
        expResult = new IndexColumn(tableColumn, subpart);
        result = instance.createIndexColumn(tableStructure, columnName, subpart);
        assertEquals(expResult, result);
    }

}
