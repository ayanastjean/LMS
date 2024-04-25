import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The DBTransfer class is responsible for transferring data to the database.
 * It connects to the SQLite database and inserts data into the "books" table.
 */
public class DBTransfer {
    // Database URL
    private static final String DB_URL = "jdbc:sqlite:LibraryLMS.db";

    /**
     * The main method to start the data transfer process.
     * It connects to the database, inserts sample data into the "books" table, and handles any exceptions that occur.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // Print the stack trace if the driver class is not found
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            // Data to be inserted
            int id = 1;
            String title = "Sample Title";
            String author = "Sample Author";
            String genre = "Sample Genre"; // Add genre data

            // SQL query to insert data into the books table
            String insertQuery = "INSERT INTO books (id, title, author, genre) VALUES (?, ?, ?, ?)";


            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set parameters for the SQL query
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, author);
                preparedStatement.setString(4, genre); // Set genre parameter

                // Execute the SQL query to insert data
                preparedStatement.executeUpdate();

                // Print success message
                System.out.println("Data inserted successfully.");
            }
        } catch (SQLException e) {
            // Print stack trace and error message if SQL exception occurs
            e.printStackTrace();
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Message: " + e.getMessage());
        }
    }
}
