package Book;

public class Book {
    
    private String name;
    private String author;
    private String publisher;
    private String ISBN;

    public Book(String name, String author, String publisher, String ISBN) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s, Title: %s, Edition: %s, Author: %s", ISBN, name, publisher, author);
    }
}
