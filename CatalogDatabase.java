import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CatalogDatabase {
    private Connection connection;

    public CatalogDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");


            this.connection = DriverManager.getConnection("jdbc:sqlite:LibraryLMS.db");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void addBook(Book book) {
        try {
            PreparedStatement addBook = connection.prepareStatement(
                    "INSERT INTO books (id, title, author) VALUES (?, ?, ?)"
            );
            addBook.setInt(1, book.getId());
            addBook.setString(2, book.getTitle());
            addBook.setString(3, book.getAuthor());
            addBook.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Book with the same ID already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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



    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  }


