import java.time.LocalDate;

/**
 * The Book class represents a book in the library catalog.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean checkedOut;
    private boolean available;
    private LocalDate dueDate;
    private String status;

    /**
     * Constructs a new Book object with the specified attributes.
     *
     * @param id         The unique identifier of the book.
     * @param title      The title of the book.
     * @param author     The author of the book.
     * @param genre      The genre of the book.
     * @param checkedOut A boolean indicating whether the book is checked out.
     * @param dueDate    The due date of the book if checked out.
     */
    public Book(int id, String title, String author, String genre, boolean checkedOut, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre; // Initialize genre field
        this.checkedOut = checkedOut;
        this.available = !checkedOut;
        this.dueDate = dueDate;
        this.status = checkedOut ? "Checked Out" : "Available";
    }

    /**
     * Constructs a new Book object with the specified ID, title, and author.
     * This constructor is intended for testing purposes.
     *
     * @param id    The unique identifier of the book.
     * @param title The title of the book.
     * @param author The author of the book.
     */
    public Book(int id, String title, String author) {
    }

    /**
     * Retrieves the ID of the book.
     *
     * @return The ID of the book.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Retrieves the genre of the book.
     *
     * @return The genre of the book.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the checked-out status of the book.
     *
     * @param checkedOut A boolean indicating whether the book is checked out.
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * Checks if the book is currently checked out.
     *
     * @return true if the book is checked out, otherwise false.
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }

    /**
     * Checks if the book is currently available for checkout.
     *
     * @return true if the book is available, otherwise false.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability status of the book.
     *
     * @param available A boolean indicating whether the book is available.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Sets the due date of the book if it is checked out.
     *
     * @param dueDate The due date of the book.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Retrieves the due date of the book if it is checked out.
     *
     * @return The due date of the book.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Retrieves the status of the book (e.g., Available, Checked Out).
     *
     * @return The status of the book.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the book.
     *
     * @param status The status of the book.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
