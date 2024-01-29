/**
 * Ayana St Jean
 * CEN 3024 - Software Development 1
 * January 26, 2024
 * LibraryManagementSystem.java
 *the interface skeleton for managing the library systems selection menu .
 */

import java.util.Scanner;

class LibraryManagementSystem {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);
        catalog.readFromFile();
        catalog.writeInventoryToFile();

        int choice = 0;
        while (choice != 4) {
            System.out.println("Menu:");
            System.out.println("1) Add a book");
            System.out.println("2) Delete a book by ID");
            System.out.println("3) Display all books");
            System.out.println("4) Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the book author: ");
                    String author = scanner.nextLine();
                    Book book = new Book(catalog.getAutoId(), title, author);
                    catalog.addBook(book);
                    break;
                case 2:
                    System.out.print("Enter the book ID#: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    catalog.deleteBook(id);
                    break;
                case 3:
                    catalog.displayAllBooks();
                    break;
                case 4:
                    System.out.println("Closing LMS...");
                    break;
                default:
                    System.out.println("Choice not available. Please try again.");
            }
        }
        scanner.close();
    }}
