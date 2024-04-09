import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LMSTests {

    @Test
    public void testAddBookToLibrary() {
        Catalog library = new Catalog();
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", false, null); // Provide appropriate values for genre, checkedOut, and dueDate

        library.addBook(book);

        assertTrue(library.inventory().contains(book));
    }

    @Test
    public void testRemoveBookByBarcode() {
        Catalog library = new Catalog();
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", false, null);
        library.addBook(book);

        assertTrue("Book should exist in the library before removal", library.inventory().contains(book));

        library.deleteBook(1);

        assertFalse("Book should be removed from the library", library.inventory().contains(book));
    }

    @Test
    public void testCheckInBook() {
        Catalog library = new Catalog();
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", true, LocalDate.now()); // Provide appropriate values for genre, checkedOut, and dueDate
        library.addBook(book);

        library.checkInBook(1);

        assertFalse("Book should be checked in", book.isCheckedOut());
        assertNull("Due date should be null after check-in", book.getDueDate());
    }
}
