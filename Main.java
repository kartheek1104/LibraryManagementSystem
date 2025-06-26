import java.util.Scanner;

public class Main {
    private static LibraryManager manager = new LibraryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    addUser();
                    break;
                case 6:
                    listUsers();
                    break;
                case 7:
                    updateUser();
                    break;
                case 8:
                    deleteUser();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        System.out.println("Exiting system. Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Add Book");
        System.out.println("2. List Books");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Add User");
        System.out.println("6. List Users");
        System.out.println("7. Update User");
        System.out.println("8. Delete User");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine().trim();
        Book book = manager.addBook(title, author);
        System.out.println("Added book: " + book);
    }

    private static void listBooks() {
        System.out.println("\n--- List of Books ---");
        if (manager.listBooks().isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book b : manager.listBooks()) {
                System.out.println(b);
            }
        }
    }

    private static void updateBook() {
        System.out.print("Enter book ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter new author: ");
        String author = scanner.nextLine().trim();
        if (manager.updateBook(id, title, author)) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    private static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (manager.deleteBook(id)) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    private static void addUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine().trim();
        User user = manager.addUser(name, email);
        System.out.println("Added user: " + user);
    }

    private static void listUsers() {
        System.out.println("\n--- List of Users ---");
        if (manager.listUsers().isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User u : manager.listUsers()) {
                System.out.println(u);
            }
        }
    }

    private static void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine().trim();
        if (manager.updateUser(id, name, email)) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }

    private static void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (manager.deleteUser(id)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }
}