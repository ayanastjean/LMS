import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBTransfer {
    private static final String DB_URL = "jdbc:sqlite:LibraryLMS.db";


    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL)) {

            int id = 1;
            String title = "Sample Title";
            String author = "Sample Author";


            String insertQuery = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";


            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, author);


                preparedStatement.executeUpdate();

                System.out.println("Data inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Message: " + e.getMessage());
        }
    }
}
