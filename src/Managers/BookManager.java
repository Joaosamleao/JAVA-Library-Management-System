package Managers;

import java.util.ArrayList;
import java.util.Scanner;

import Book.Book;
import Exceptions.InvalidInputException;
import Interfaces.ObjectDisplay;
import Interfaces.ObjectManagement;
import Interfaces.ObjectRetrieval;

public class BookManager implements ObjectDisplay, ObjectManagement, ObjectRetrieval {

    private ArrayList<Book> books;
    private Scanner sc = new Scanner(System.in);

    public BookManager() {
        this.books = new ArrayList<Book>();
    }

    private String normalizeISBN(String s) {
        return s == null ? "" : s.replaceAll("[\\s]", "");
    }

    private String getBookISBN() {
        String ISBN;
        
        while(true) {
            try {
                System.out.print("ISBN Code: ");
                ISBN = sc.nextLine().trim();

                if (ISBN.isEmpty()) {
                    throw new InvalidInputException("Error: ISBN cannot be null. Try again");
                }

                if (ISBN.contains("-")) {
                    if(!ISBN.matches("[\\d\\-]+")) {
                        throw new InvalidInputException("Error: ISBN can only contain dashes and numbers. Try again");
                    }
                    return ISBN;
                } else {
                    String cleanedISBN  = ISBN.replaceAll("\\s+", "");
                    if (!cleanedISBN.matches("\\d+")) {
                        throw new InvalidInputException("Error: ISBN can only contain spaces and numbers. Try again");
                    }

                    String formattedISBN = formatISBNWithDashes(cleanedISBN);
                    if (formattedISBN == null) {
                        throw new InvalidInputException("Error: ISBN must be 10 or 13 digits long. Try again");
                    }
                    return formattedISBN;
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String formatISBNWithDashes(String ISBN) {
        if (ISBN.length() == 10) {
            return 
            ISBN.substring(0, 1) + "-" +
            ISBN.substring(1, 4) + "-" +
            ISBN.substring(4, 9) + "-" +
            ISBN.substring(9, 10);
        } else if (ISBN.length() == 13) {
            return 
            ISBN.substring(0, 3) + "-" +
            ISBN.substring(3, 4) + "-" +
            ISBN.substring(4, 7) + "-" +
            ISBN.substring(7, 12) + "-" +
            ISBN.substring(12, 13);
        } else {
            return null;
        }
    }

    private String getBookTitle() {
        String title;
        
        while(true) {
            try {
                System.out.print("Title: ");
                title = sc.nextLine().trim();

                if (title.isEmpty()) {
                    throw new InvalidInputException("Error: Title cannot be empty. Try again.");
                }
                return title;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getBookEdition() {
        String edition;

        while(true) {
            try {
                System.out.print("Edition: ");
                edition = sc.nextLine().trim();

                if (edition.isBlank()) {
                    return "1";
                }

                if (edition.length() > 50) {
                    throw new InvalidInputException("Error: Edition description too long. Try again.");
                }

                if (!edition.matches("^[a-zA-Z0-9\\s\\-.,()]+$")) {
                    throw new InvalidInputException("Error: Edition contain invalid characters. Try again.");
                }

                return edition;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getBookAuthor() {
        String author;

        while(true) {
            try {
                System.out.print("Author: ");
                author = sc.nextLine().trim();

                if (author.isEmpty()) {
                    throw new InvalidInputException("Error: Author cannot be empty. Try again");
                }
                if (!author.matches("[a-zA-Z\\s]+")) {
                    throw new InvalidInputException("Error: Author cannot contain numbers or special characters.");
                }
                return author;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void addObject() {

        String ISBN = getBookISBN();
        String title = getBookTitle();
        String edition = getBookEdition();
        String author = getBookAuthor();

        Book book = new Book(title, author, edition, ISBN);
        books.add(book);
        System.out.println("Book added successfully!");

    }

    private boolean exists(String id) {
        String normId = normalizeISBN(id);
        for (Book book : books) {
            if (normalizeISBN(book.getISBN()).equals(normId)) {
                return true;
            }
        }
        return false;
    }

    private String bookByISBN() {
        String id = "";

        while(true) {
            try {
                System.out.print("Book ISBN: ");
                id = sc.nextLine().trim();

                if (id.isEmpty()) {
                    throw new InvalidInputException("Employee ID cannot be empty.");
                }
                if(!id.matches("[\\d\\-\\s]+")) {
                    throw new InvalidInputException("Error: The selected ID is not valid. Try again.");
                }
                if(!exists(id)) {
                    throw new InvalidInputException("Error: The given ID does not exist: " + id);
                }
                return id;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    @Override
    public void removeObject() {
        String ISBNtoRemove = bookByISBN();
        String normToRemove = normalizeISBN(ISBNtoRemove);
        books.removeIf(book -> normalizeISBN(book.getISBN()).equals(normToRemove));
    }

    @Override
    public void editObject() {
        listObjects();
        String ISBNtoEdit = bookByISBN();

        System.out.print("1 - Change ISBN | 2 - Change Title | 3 - Change Edition | 4 - Change Author\nChoose an option: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch(op) {
            case 1:
                String newISBN = getBookISBN();
                for (Book book : books) {
                    if (normalizeISBN(book.getISBN()).equals(normalizeISBN(ISBNtoEdit))) {
                        books.set(books.indexOf(book), new Book(book.getName(), book.getAuthor(), book.getPublisher(), newISBN));
                        System.out.println("ISBN updated successfully!");
                        break;
                    }
                }
                break;
            case 2:
                String newTitle = getBookTitle();
                for (Book book : books) {
                    if (normalizeISBN(book.getISBN()).equals(normalizeISBN(ISBNtoEdit))) {
                        books.set(books.indexOf(book), new Book(newTitle, book.getAuthor(), book.getPublisher(), book.getISBN()));
                        System.out.println("Title updated successfully!");
                        break;
                    }
                }
                break;
            case 3:
                String newEdition = getBookEdition();
                for (Book book : books) {
                    if (normalizeISBN(book.getISBN()).equals(normalizeISBN(ISBNtoEdit))) {
                        books.set(books.indexOf(book), new Book(book.getName(), book.getAuthor(), newEdition, book.getISBN()));
                        System.out.println("Edition updated successfully!");
                        break;
                    }
                }
                break;
            case 4:
                String newAuthor = getBookAuthor();
                for (Book book : books) {
                    if (normalizeISBN(book.getISBN()).equals(normalizeISBN(ISBNtoEdit))) {
                        books.set(books.indexOf(book), new Book(book.getName(), newAuthor, book.getPublisher(), book.getISBN()));
                        System.out.println("Author updated successfully!");
                        break;
                    }
                }
                break;
            default:
                System.out.println("Error: Invalid option.");
                break;
        }
    }

    @Override
    public void listObjects() {
        if (books.isEmpty()) {
            System.out.println("Error: No books registered in the system.");
            return;
        }

        System.out.printf("%-15s %-30s %-20s %-20s%n", "ISBN", "Title", "Edition", "Author");
        System.out.println("--------------------------------------------------------------------------------");

        for (Book book : books) {
            System.out.printf("%-15s %-30s %-20s %-20s%n", 
                book.getISBN(), 
                book.getName(), 
                book.getPublisher(), 
                book.getAuthor()
            );
        }
    }

    @Override
    public void printObject(String ISBN) {
        Book book = (Book) getObject(ISBN);

        if (book != null) {
            System.out.println(book.toString());
        } else {
            System.out.print("Error: The book ISBN: " + ISBN + " was not found.");
        }
    }

    @Override
    public Object getObject(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

}