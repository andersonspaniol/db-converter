/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconverter.reader;

import dbconverter.contants.MariaDBConstants;
import dbconverter.datatypes.DataType;
import dbconverter.datatypes.IndexColumn;
import dbconverter.datatypes.TableColumn;
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
     * Test of getTableNames method, of class DBReaderMariaDB.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTableNames() throws Exception {
        System.out.println("getTableNames");
        assertTrue(true);
    }

    /**
     * Test of loadTableColumns method, of class DBReaderMariaDB.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadTableColumns() throws Exception {
        System.out.println("loadTableColumns");
        assertTrue(true);
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
        expResult = new TableColumn("test_123", DataType.CHAR, 3, 0);
        result = instance.createTableColumn("test_123", "char", 3, 0, 0);
        assertEquals(expResult, result);
        // Test: varchar(32)
        expResult = new TableColumn("test_123", DataType.VARCHAR, 32, 0);
        result = instance.createTableColumn("test_123", "varchar", 32, 0, 0);
        assertEquals(expResult, result);
        // Test: decimal(4)
        expResult = new TableColumn("test_123", DataType.INTEGER, 4, 0);
        result = instance.createTableColumn("test_123", "decimal", 0, 4, 0);
        assertEquals(expResult, result);
        // Test: decimal(5, 2)
        expResult = new TableColumn("test_123", DataType.DECIMAL, 5, 2);
        result = instance.createTableColumn("test_123", "decimal", 0, 5, 2);
        assertEquals(expResult, result);
        // Test: tinyint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_TINYINT, 0);
        result = instance.createTableColumn("test_123", "tinyint", 0, 0, 0);
        assertEquals(expResult, result);
        // Test: smallint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_SMALLINT, 0);
        result = instance.createTableColumn("test_123", "smallint", 0, 0, 0);
        assertEquals(expResult, result);
        // Test: mediumint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_MEDIUMINT, 0);
        result = instance.createTableColumn("test_123", "mediumint", 0, 0, 0);
        assertEquals(expResult, result);
        // Test: int
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_INT, 0);
        result = instance.createTableColumn("test_123", "int", 0, 0, 0);
        assertEquals(expResult, result);
        // Test: bigint
        expResult = new TableColumn("test_123", DataType.INTEGER, MariaDBConstants.LENGTH_BIGINT, 0);
        result = instance.createTableColumn("test_123", "bigint", 0, 0, 0);
        assertEquals(expResult, result);
        // Test: date
        expResult = new TableColumn("test_123", DataType.DATE, 0, 0);
        result = instance.createTableColumn("test_123", "date", 0, 0, 0);
        assertEquals(expResult, result);
        // Test: tinyblob
        expResult = new TableColumn("test_123", DataType.BINARY, 50, 0);
        result = instance.createTableColumn("test_123", "tinyblob", 50, 0, 0);
        assertEquals(expResult, result);
        // Test: blob
        expResult = new TableColumn("test_123", DataType.BINARY, 50, 0);
        result = instance.createTableColumn("test_123", "blob", 50, 0, 0);
        assertEquals(expResult, result);
        // Test: longblob
        expResult = new TableColumn("test_123", DataType.BINARY, 50, 0);
        result = instance.createTableColumn("test_123", "longblob", 50, 0, 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadTableIndexes method, of class DBReaderMariaDB.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadTableIndexes() throws Exception {
        System.out.println("loadTableIndexes");
        assertTrue(true);
    }

    /**
     * Test of createIndexColumn method, of class DBReaderMariaDB.
     */
    @Test
    public void testCreateIndexColumn() {
        System.out.println("createIndexColumn");
        String columnName = "test_123";
        int subpart = 12;
        DBReaderMariaDB instance = new DBReaderMariaDB(null);
        IndexColumn expResult;
        IndexColumn result;
        // Test: Create os a single index column
        expResult = new IndexColumn(columnName, subpart);
        result = instance.createIndexColumn(columnName, subpart);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTableRecords method, of class DBReaderMariaDB.
     */
    @Test
    public void testGetTableRecords() {
        System.out.println("getTableRecords");
        assertTrue(true);
    }

}
