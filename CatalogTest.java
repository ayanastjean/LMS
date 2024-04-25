import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the functionality of the Catalog class.
 */
public class CatalogTest {
    private static Catalog catalog;

    /**
     * Sets up the Catalog instance before running the tests.
     */
    @BeforeClass
    public static void setUp() {
        catalog = new Catalog();
    }

    /**
     * Tears down the Catalog instance after running the tests.
     */
    @AfterClass
    public static void tearDown() {
        catalog.closeCatalogDatabase();
    }

    /**
     * Test case to add a book to the catalog.
     */
    @Test
    public void testAddBook() {
        Book book = new Book(1, "Test Book", "Test Author");
        catalog.addBook(book);
        List<Book> inventory = catalog.inventory();
        assertTrue(inventory.contains(book));
    }

    /**
     * Test case to delete a book from the catalog by its ID.
     */
    @Test
    public void testDeleteBook() {
        Book book = new Book(2, "Another Test Book", "Another Test Author");
        catalog.addBook(book);
        catalog.deleteBook(2);
        List<Book> inventory = catalog.inventory();
        assertFalse(inventory.contains(book));
    }

    /**
     * Test case to delete a book from the catalog by its title.
     */
    @Test
    public void testDeleteBookByTitle() {
        Book book = new Book(3, "Third Test Book", "Third Test Author");
        catalog.addBook(book);
        catalog.deleteBookByTitle("Third Test Book");
        List<Book> inventory = catalog.inventory();
        assertFalse(inventory.contains(book));
    }

    /**
     * Test case to display all books in the catalog.
     * Since this method prints to System.out, we cannot directly test its output.
     * We can call the method and verify that it does not throw any exceptions.
     */
    @Test
    public void testDisplayAllBooks() {
        catalog.displayAllBooks();
    }

    /**
     * Test case to check out a book from the catalog.
     */
    @Test
    public void testCheckOutBook() {
        Book book = new Book(4, "Fourth Test Book", "Fourth Test Author");
        catalog.addBook(book);
        boolean checkedOut = catalog.checkOutBook(4);
        assertTrue(checkedOut);
    }

    /**
     * Test case to check in a book to the catalog.
     */
    @Test
    public void testCheckInBook() {
        Book book = new Book(5, "Fifth Test Book", "Fifth Test Author");
        catalog.addBook(book);
        catalog.checkOutBook(5);
        catalog.checkInBook(5);
        List<Book> inventory = catalog.inventory();
        assertFalse(inventory.get(0).isCheckedOut());
    }
}
