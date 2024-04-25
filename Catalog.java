import java.sql.Connection;
import java.util.List;

/**
 * The Catalog class represents a catalog of books in a library.
 */
public class Catalog {
    private static CatalogDatabase catalogDatabase;
    private CatalogManager catalogManager;

    /**
     * Constructs a new Catalog object and initializes the catalog database and manager.
     */
    public Catalog() {
        catalogDatabase = new CatalogDatabase();
        catalogManager = new CatalogManager(catalogDatabase.getConnection());
    }

    /**
     * Retrieves the connection to the catalog database.
     *
     * @return The connection to the catalog database.
     */
    public Connection getConnection() {
        return catalogDatabase.getConnection();
    }

    /**
     * Retrieves the inventory of books from the catalog database.
     *
     * @return A list of books representing the inventory.
     */
    public List<Book> inventory() {
        return catalogDatabase.getAllBooks();
    }

    /**
     * Adds a new book to the catalog database.
     *
     * @param book The book to be added to the catalog.
     */
    public void addBook(Book book) {
        catalogDatabase.addBook(book);
    }

    /**
     * Deletes a book from the catalog database based on its ID.
     *
     * @param id The ID of the book to be deleted.
     */
    public void deleteBook(int id) {
        catalogDatabase.deleteBook(id);
    }

    /**
     * Deletes a book from the catalog database based on its title.
     *
     * @param title The title of the book to be deleted.
     */
    public void deleteBookByTitle(String title) {
        catalogDatabase.deleteBookByTitle(title);
    }
    public void deleteAllBooksFromDatabase() {
        catalogDatabase.deleteAllBooksFromDatabase();
    }
    /**
     * Displays all books in the catalog.
     */

    public void displayAllBooks() {
        List<Book> books = inventory();
        for (Book book : books) {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + "," + book.getGenre());
        }
    }

    /**
     * Checks out a book from the catalog.
     *
     * @param id The ID of the book to be checked out.
     * @return true if the book is successfully checked out, false otherwise.
     */
    public boolean checkOutBook(int id) {
        return catalogManager.checkedOutBook(id);
    }

    /**
     * Checks in a book to the catalog.
     *
     * @param id The ID of the book to be checked in.
     */
    public void checkInBook(int id) {
        catalogManager.checkedInBook(id);
        System.out.println("Book ID# " + id + " checked in successfully!");
    }

    /**
     * Closes the connection to the catalog database.
     */
    public void closeCatalogDatabase() {
        catalogDatabase.closeConnection();
    }
}
