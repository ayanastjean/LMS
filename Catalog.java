import java.sql.Connection;
import java.util.List;

public class Catalog {
    private static CatalogDatabase catalogDatabase;
    private CatalogManager catalogManager;

    public Catalog() {
        catalogManager = new CatalogManager(catalogDatabase.getConnection());
    }


    public Connection getConnection() {
        return catalogDatabase.getConnection();
    }

    public List<Book> inventory() {
        return catalogDatabase.getAllBooks();
    }

    public void addBook(Book book) {
        catalogDatabase.addBook(book);
    }

    public void deleteBook(int id) {
        catalogDatabase.deleteBook(id);
    }

    public void deleteBookByTitle(String title) {
        catalogDatabase.deleteBookByTitle(title);
    }

    public void displayAllBooks() {
        List<Book> books = inventory();
        for (Book book : books) {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor());
        }
    }

    public boolean checkOutBook(int id) {
        return catalogManager.checkedOutBook(id);
    }

    public void checkInBook(int id) {
        catalogManager.checkedInBook(id);
        System.out.println("Book ID# " + id + " checked in successfully!");
    }

    public void closeCatalogDatabase() {
        catalogDatabase.closeConnection();
    }
}
