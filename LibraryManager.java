import java.io.*;
import java.util.*;

public class LibraryManager {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private int bookIdCounter = 1;
    private int userIdCounter = 1;

    private final String BOOKS_FILE = "books.txt";
    private final String USERS_FILE = "users.txt";

    public LibraryManager() {
        loadBooks();
        loadUsers();
    }

    // --- BOOKS ---

    public Book addBook(String title, String author) {
        Book book = new Book(bookIdCounter++, title, author);
        books.add(book);
        saveBooks();
        return book;
    }

    public boolean updateBook(int id, String newTitle, String newAuthor) {
        for (Book b : books) {
            if (b.getId() == id) {
                b.setTitle(newTitle);
                b.setAuthor(newAuthor);
                saveBooks();
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(int id) {
        boolean removed = books.removeIf(b -> b.getId() == id);
        if (removed) saveBooks();
        return removed;
    }

    public List<Book> listBooks() {
        return books;
    }

    private void saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                writer.write(b.getId() + "," + b.getTitle() + "," + b.getAuthor() + "," +
                             (b.isIssued() ? b.getIssuedToUserId() : -1));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    private void loadBooks() {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int issuedTo = parts.length == 4 ? Integer.parseInt(parts[3]) : -1;

                    Book book = new Book(id, title, author);
                    if (issuedTo != -1) {
                        book.issueTo(issuedTo);
                    }
                    books.add(book);
                    if (id >= bookIdCounter) bookIdCounter = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    public boolean issueBook(int bookId, int userId) {
        for (Book b : books) {
            if (b.getId() == bookId && !b.isIssued()) {
                b.issueTo(userId);
                saveBooks();
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId && b.isIssued()) {
                b.returnBook();
                saveBooks();
                return true;
            }
        }
        return false;
    }

    // --- USERS ---

    public User addUser(String name, String email) {
        User user = new User(userIdCounter++, name, email);
        users.add(user);
        saveUsers();
        return user;
    }

    public boolean updateUser(int id, String newName, String newEmail) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setName(newName);
                u.setEmail(newEmail);
                saveUsers();
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (removed) saveUsers();
        return removed;
    }

    public List<User> listUsers() {
        return users;
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) {
                writer.write(u.getId() + "," + u.getName() + "," + u.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];
                    users.add(new User(id, name, email));
                    if (id >= userIdCounter) userIdCounter = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
}
