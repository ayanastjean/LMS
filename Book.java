import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean checkedOut;
    private boolean available;
    private LocalDate dueDate;
    private String status;

    public Book(int id, String title, String author, String genre, boolean checkedOut, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.checkedOut = checkedOut;
        this.available = !checkedOut;
        this.dueDate = dueDate;
        this.status = checkedOut ? "Checked Out" : "Available";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
