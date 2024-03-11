import java.time.LocalDate;

/**
 * Ayana St Jean
 * CEN 3024 - Software Development 1
 * January 26, 2024
 * Book.java
 * this class creates the book objects by title, author, and id.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean checkedOut;
    private boolean available;
    private LocalDate dueDate;
    private String status;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;

    }


    /**
     * method: getId
     * parameters: none
     * return: int
     * purpose: gets the id#.
     */
    public int getId() {
        return id;
    }

    /**
     * method: getTitle
     * parameters: none
     * return: int
     * purpose: gets the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * method: getAuthor
     * parameters: none
     * return: String
     * purpose: gets the author's name.
     */
    public String getAuthor() {
        return author;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

