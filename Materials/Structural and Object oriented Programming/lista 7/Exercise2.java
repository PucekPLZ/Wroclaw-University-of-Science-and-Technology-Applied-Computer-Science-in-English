import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Exercise2 {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Book\n2. Remove Book\n3. Search by ISBN\n4. Display All Books\n5. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(isbn, title, author);
                }
                case 2 -> {
                    System.out.print("Enter ISBN to Remove: ");
                    String isbn = scanner.nextLine();
                    library.removeBook(isbn);
                }
                case 3 -> {
                    System.out.print("Enter ISBN to Search: ");
                    String isbn = scanner.nextLine();
                    Book book = library.searchByISBN(isbn);
                    System.out.println(book != null ? book : "Book not found");
                }
                case 4 -> library.displayAllBooks();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

class LibrarySystem {
    private TreeMap<String, Book> books;

    public LibrarySystem() {
        books = new TreeMap<>();
    }

    public void addBook(String isbn, String title, String author) {
        books.put(isbn, new Book(isbn, title, author));
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public Book searchByISBN(String isbn) {
        return books.get(isbn);
    }

    public void displayAllBooks() {
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}

class Book {
    private String isbn;
    private String title;
    private String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author;
    }
}
