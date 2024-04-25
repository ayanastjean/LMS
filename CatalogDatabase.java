import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The CatalogDatabase class manages interactions with the database for book-related operations.
 */
public class CatalogDatabase {
    private Connection connection;

    /**
     * Constructs a new CatalogDatabase object and establishes a connection to the database.
     */
    public CatalogDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:LibraryLMS.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the connection to the database.
     *
     * @return The connection to the database.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Adds a new book to the database.
     *
     * @param book The book to be added to the database.
     */
    public void addBook(Book book) {
        try {
            PreparedStatement addBook = connection.prepareStatement(
                    "INSERT INTO books (id, title, author, genre) VALUES (?, ?, ?,?)"
            );
            addBook.setInt(1, book.getId());
            addBook.setString(2, book.getTitle());
            addBook.setString(3, book.getAuthor());
            addBook.setString(4, book.getGenre());
            addBook.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Book with the same ID already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all books from the database.
     *
     * @return A list containing all books retrieved from the database.
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement getAllBooks = connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = getAllBooks.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                boolean checkedOut = resultSet.getBoolean("checked_out");
                LocalDate dueDate = resultSet.getString("due_date") != null ? LocalDate.parse(resultSet.getString("due_date")) : null;
                String status = checkedOut ? "Checked Out" : "Available";
                Book book = new Book(id, title, author, genre, checkedOut, dueDate);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Deletes a book from the database based on its ID.
     *
     * @param id The ID of the book to be deleted.
     */
    public void deleteBook(int id) {
        try {
            PreparedStatement deleteBookById = connection.prepareStatement(
                    "DELETE FROM books WHERE id = ?"
            );
            deleteBookById.setInt(1, id);
            deleteBookById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a book from the database based on its title.
     *
     * @param title The title of the book to be deleted.
     */
    public void deleteBookByTitle(String title) {
        try {
            PreparedStatement deleteBookByTitle = connection.prepareStatement(
                    "DELETE FROM books WHERE title = ?"
            );
            deleteBookByTitle.setString(1, title);
            deleteBookByTitle.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllBooksFromDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM books");
            System.out.println("All books deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection to the database.
     */
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
