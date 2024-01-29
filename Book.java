
/**
 * Ayana St Jean
 * CEN 3024 - Software Development 1
 * January 26, 2024
 * Book.java
 * this class creates the book objects by title, author, and id.
 */
public class Book {
    private static int counter = 1;
    private int id;
    private String title;
    private String author;
    public Book(int id, String title, String author) {
        this.id = counter++;
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
}

