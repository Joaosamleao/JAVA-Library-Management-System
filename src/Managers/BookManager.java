package Managers;

import Interfaces.ObjectDisplay;
import Interfaces.ObjectManagement;
import Interfaces.ObjectRetrieval;

public class BookManager implements ObjectDisplay, ObjectManagement, ObjectRetrieval {

    public BookManager() {
        this.books = new ArrayList<Book>();
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

                // Valid non-blank edition; return it
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

    }

    @Override
    public void removeObject() {

    }

    @Override
    public void editObject() {

    }

    @Override
    public void listObjects() {

    }

    @Override
    public void printObject(String id) {

    }

    @Override
    public Object getObject(String id) {
        return id;
    }

}
