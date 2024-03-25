import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ayana St Jean
 * CEN 3024 - Software Development 1
 * March 3rd, 2024
 * Catalog.java
 * this class manages the catalog. It allows books to be added and deleted from the inventory while updating the catalog.txt file.
 */
class Catalog {
    private static List<Book> inventory;


    public Catalog() {
        inventory = new ArrayList<>();
    }
    public List<Book> inventory() {
        return inventory;
    }

    /**
     * method: addBook
     * parameters: Book book
     * return: none
     * purpose: adds book to the inventory
     */
    public void addBook(Book book) {
        inventory.add(book);
        System.out.println("Book Added to Inventory!");
        writeInventoryToFile();
    }
    /**
     * method: writeInventoryToFile
     * parameters: none
     * return: none
     * purpose: writes inventory to the .txt file
     */
    public void writeInventoryToFile() {
        try (FileWriter writer = new FileWriter("catalog.txt")) {
            for (Book book : inventory) {
                writer.write(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * method: deleteBook
     * parameters: int id
     * return: none
     * purpose: deletes books based on the book id
     */
    public void deleteBook(int id) {
        Book bookToRemove = null;
        for (Book book : inventory) {
            bookToRemove = book;
            break;
        }
        if (bookToRemove != null) {
            inventory.remove(bookToRemove);
            System.out.println("Book ID# " + id + " deleted from inventory!");
        } else {
            System.out.println("Book ID# " + id + " not found in inventory.");
        }
        writeInventoryToFile();
        try (FileWriter writer = new FileWriter("catalog.txt")) {
            for (Book book : inventory) {
                writer.write(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeInventoryToFile(); // Update inventory file

    }
    public void deleteBookByTitle(String title) {
        Book bookToRemove = null;
        for (Book book : inventory) {
            bookToRemove = book;
            break;
        }
        if (bookToRemove != null) {
            inventory.remove(bookToRemove);
            System.out.println("Book : " + title + " deleted from inventory!");
        } else {
            System.out.println("Book: " + title + " not found in inventory.");
        }
        writeInventoryToFile();
        try (FileWriter writer = new FileWriter("catalog.txt")) {
            for (Book book : inventory) {
                writer.write(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeInventoryToFile(); // Update inventory file

    }
    /**
     * method: displayAllBooks
     * parameters: none
     * return: none
     * purpose: returns the full list of books in the inventory
     */
    public void displayAllBooks() {
        for (Book book : inventory) {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor());

        }
    }
    /**
     * method: readFromFile
     * parameters: none
     * return: none
     * purpose: reads from the file and adds to the book inventory
     */
    public void readFromFile(String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] bookData = line.split(",", 3);
                if (bookData.length == 3) {
                    int id = Integer.parseInt(bookData[0].trim());
                    String title = bookData[1].trim();
                    String author = bookData[2].trim();
                    Book book = new Book(id, title, author);
                    inventory.add(book);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }



    public void checkOutBook(int id) {
        for (Book book : inventory) {
            if (book.getId() == id && !book.isCheckedOut()) {
                book.setCheckedOut(true);
                book.setDueDate(LocalDate.now().plusDays(14));
                book.setStatus("Checked Out");
                System.out.println("Book ID# " + id + " checked out successfully!");
                System.out.println("Book is due " + book.getDueDate() + "!");
                writeInventoryToFile(); // Update inventory file
                return;
            }
        }
        System.out.println("Book ID# " + id + " not found or already checked out.");
    }



    public static void checkInBook(int id) {
        Book bookToCheckIn = null;
        for (Book book : inventory) {
            if (book.getId() == id) {
                bookToCheckIn = book;
                break;
            }
        }
        if (bookToCheckIn != null) {
            bookToCheckIn.setCheckedOut(false);
            bookToCheckIn.setDueDate(null);
            bookToCheckIn.setStatus("Available");
            System.out.println("Book ID#" + id + " checked in successfully!");
        } else {
            System.out.println("Book ID# " + id + " not found in inventory.");
        }
    }




    public void addBook() {

    }
}


