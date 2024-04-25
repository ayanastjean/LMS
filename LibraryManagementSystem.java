import javax.swing.*;
import java.util.Scanner;

/**
 * The LibraryManagementSystem class represents a simple library management system.
 * It provides a menu-driven interface for users to interact with the library catalog.
 */
public class LibraryManagementSystem {

    /**
     * The main method initializes the library catalog and user interface,
     * and provides a menu-driven interface for users to interact with the system.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize the catalog and scanner for user input
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);

        // Initialize the user interface using SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            UserInterface ui = new UserInterface(catalog);
        });

        // Display the menu and process user input until the user chooses to exit
        int choice = 0;
        while (choice != 6) {
            System.out.println("Menu:");
            System.out.println("1) Add a book");
            System.out.println("2) Remove Book");
            System.out.println("3) Display all books");
            System.out.println("4) Check In Book");
            System.out.println("5) Check Out Book");
            System.out.println("6) Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            // Process user's choice based on the selected menu option
            switch (choice) {
                case 1:
                    // Add a new book to the catalog
                    System.out.print("Enter the book barcode: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter the book title: ");
                    String title = scanner.next();
                    System.out.print("Enter the book author: ");
                    String author = scanner.next();
                    System.out.print("Enter the book genre: ");
                    String genre = scanner.next();
                    Book book = new Book(id, title, author, genre, false, null);
                    catalog.addBook(book);
                    break;
                case 2:
                    // Remove a book from the catalog
                    System.out.print("Enter the book barcode#: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    catalog.deleteBook(removeId);
                    break;
                case 3:
                    // Display all books in the catalog
                    System.out.println("Compiling inventory..");
                    catalog.displayAllBooks();
                    break;
                case 4:
                    // Check in a book to the library
                    System.out.println("CHECK IN BOOK");
                    System.out.print("Enter the book barcode#: ");
                    int checkInId = scanner.nextInt();
                    scanner.nextLine();
                    catalog.checkInBook(checkInId);
                    break;
                case 5:
                    // Check out a book from the library
                    System.out.println("CHECK OUT BOOK");
                    System.out.print("Enter the book barcode#: ");
                    int checkOutId = scanner.nextInt();
                    scanner.nextLine();
                    catalog.checkOutBook(checkOutId);
                    break;
                case 6:
                    // Exit the library management system
                    System.out.println("Closing LMS...");
                    catalog.closeCatalogDatabase();
                    break;
                default:
                    System.out.println("Choice not available. Please try again.");
            }
        }
        // Close the scanner when done
        scanner.close();
    }
}
