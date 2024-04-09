import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CatalogManager {
    private Connection connection;

    public CatalogManager(Connection connection) {
        this.connection = connection;
    }

    public boolean checkedOutBook(int id) {
        try {

            PreparedStatement checkStatusStatement = connection.prepareStatement(
                    "SELECT checked_out FROM books WHERE id = ?"
            );
            checkStatusStatement.setInt(1, id);
            ResultSet resultSet = checkStatusStatement.executeQuery();
            if (resultSet.next()) {
                boolean isCheckedOut = resultSet.getBoolean("checked_out");
                if (isCheckedOut) {
                    return false;
                }
            }

            PreparedStatement checkOutStatement = connection.prepareStatement(
                    "UPDATE books SET checked_out = ?, due_date = ?, status = ? WHERE id = ?"
            );
            checkOutStatement.setBoolean(1, true);
            LocalDate dueDate = LocalDate.now().plusDays(14);
            checkOutStatement.setString(2, dueDate.toString());
            checkOutStatement.setString(3, "Checked Out");
            checkOutStatement.setInt(4, id);
            checkOutStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void checkedInBook(int id) {
        try {
            PreparedStatement checkInStatement = connection.prepareStatement(
                    "UPDATE books SET checked_out = ?, due_date = ?, status = ? WHERE id = ?"
            );
            checkInStatement.setBoolean(1, false);
            checkInStatement.setNull(2, java.sql.Types.NULL);
            checkInStatement.setString(3, "Available");
            checkInStatement.setInt(4, id);
            checkInStatement.executeUpdate();
            System.out.println("Book ID# " + id + " checked in successfully! Status: Available");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
