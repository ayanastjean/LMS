import javax.swing.*;
import java.util.Scanner;

class LibraryManagementSystem {

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);

        SwingUtilities.invokeLater(() -> {
            UserInterface ui = new UserInterface(catalog);
        });

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

            switch (choice) {
                case 1:
                    System.out.print("Enter the book barcode: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter the book title: ");
                    String title = scanner.next();
                    System.out.print("Enter the book author: ");
                    String author = scanner.next();
                    Book book = new Book(id, title, author);
                    catalog.addBook(book);
                    break;
                case 2:
                    System.out.print("Enter the book barcode#: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    catalog.deleteBook(removeId);
                    break;
                case 3:
                    System.out.println("Compiling inventory..");
                    catalog.displayAllBooks();
                    break;
                case 4:
                    System.out.println("CHECK IN BOOK");
                    System.out.print("Enter the book barcode#: ");
                    int checkInId = scanner.nextInt();
                    scanner.nextLine();
                    catalog.checkInBook(checkInId);
                    break;
                case 5:
                    System.out.println("CHECK OUT BOOK");
                    System.out.print("Enter the book barcode#: ");
                    int checkOutId = scanner.nextInt();
                    scanner.nextLine();
                    catalog.checkOutBook(checkOutId);
                    break;
                case 6:
                    System.out.println("Closing LMS...");
                    break;
                default:
                    System.out.println("Choice not available. Please try again.");
            }
        }
        scanner.close();
    }
}

