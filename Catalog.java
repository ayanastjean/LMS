import java.io.*;
import java.util.*;

/**
 * Ayana St Jean
 * CEN 3024 - Software Development 1
 * January 26, 2024
 * Catalog.java
 * this class manages the catalog. It allows books to be added and deleted from the inventory while updating the catalog.txt file.
 */
class Catalog {
    private List<Book> inventory;
    private int autoId;
    public Catalog() {
        this.autoId = 1;
        inventory = new ArrayList<>();
    }
    /**
     * method: getAutoId
     * parameters: none
     * return: int
     * purpose: returns the auto generated id
     */
    public int getAutoId() {
        return autoId;
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
            e.printStackTrace();
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
            if (book.getId() == id) {
                bookToRemove = book;
                break;
            }
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
    }
    /**
     * method: readFromFile
     * parameters: none
     * return: none
     * purpose: reads from the file and adds to the book inventory
     */
    public void readFromFile() {
        try (Scanner fileScanner = new Scanner(new File("catalog.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] bookData = line.split(", ");
                int id = Integer.parseInt(bookData[0]);
                String title = bookData[1];
                String author = bookData[2];
                Book book = new Book(id, title, author);
                inventory.add(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    }}


