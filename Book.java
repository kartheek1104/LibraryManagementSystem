import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String author;
    private boolean isIssued = false;
    private int issuedToUserId = -1;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }
    public int getIssuedToUserId() { return issuedToUserId; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    public void issueTo(int userId) {
        this.isIssued = true;
        this.issuedToUserId = userId;
    }

    public void returnBook() {
        this.isIssued = false;
        this.issuedToUserId = -1;
    }

    @Override
    public String toString() {
        String status = isIssued ? "Issued to User ID " + issuedToUserId : "Available";
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "', status=" + status + "}";
    }
}
