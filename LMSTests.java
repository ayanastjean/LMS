import org.junit.Test;
import static org.junit.Assert.*;

    public class LMSTests {



        @Test
        public void testAddBookToLibrary() {
            Catalog library = new Catalog();
            Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");

            library.addBook(book);

            assertTrue(library.inventory().contains(book));
        }
        @Test
        public void testRemoveBookByBarcode() {
            Catalog library = new Catalog();
            Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
            library.addBook(book);

            assertTrue("Book should exist in the library before removal", library.inventory().contains(book));

            library.deleteBook(1);

            assertFalse("Book should be removed from the library", library.inventory().contains(book));
        }

            @Test
            public void testCheckOutBook() {
                Catalog library = new Catalog();
                Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
                library.addBook(book);

                assertNull(book.getDueDate());

                Catalog.checkOutBook(1);

                assertNotNull(book.getDueDate());
            }

            @Test
            public void testCheckInBook() {
                Catalog library = new Catalog();
                Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
                library.addBook(book);

                library.checkInBook(1);

                assertNull(book.getDueDate());
            }
        }

