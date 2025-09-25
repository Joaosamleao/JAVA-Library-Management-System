package UI;

import java.util.Scanner;

public class UI {

    Scanner sc = new Scanner(System.in);

    public UI() {
        System.out.println("Library Management System UI - Ver 0.1");
        System.out.println("GitHub: Joaosamleao\n");
        System.out.println("--------------------------------------------------------------------------\n");

        System.out.println("Available Commands:"
        + "\n0. Exit System"
        + "\n1. Book - Add, Remove, List, Edit, Search"
        + "\n2. User - Add, Remove, List, Edit, Search"
        + "\n3. Loan - Issue, Return, List, Search"
        + "\n4. Request - Issue, Cancel, List, Search\n");
        System.out.println("--------------------------------------------------------------------------\n");

        System.out.print("Command Index: ");
        int op = sc.nextInt();
        System.out.println("\n--------------------------------------------------------------------------\n");

        switch(op) {
            case 0:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            case 1:
                System.out.println("Book Commands:"
                + "\n1. Add Book"
                + "\n2. Remove Book"
                + "\n3. List Books"
                + "\n4. Edit Book"
                + "\n5. Search Book by ISBN\n");
                break;
            case 2:
                System.out.println("User Commands:"
                + "\n1. Add User"
                + "\n2. Remove User"
                + "\n3. List User"
                + "\n4. Edit User"
                + "\n5. Search User by ID");
                break;
            case 3:
                System.out.println("Loan Commands:"
                + "\n1. Issue Loan"
                + "\n2. Return Loan"
                + "\n3. Cancel Loan"
                + "\n4. List Active Loans"
                + "\n5. Search Loan by ID");
                break;
            case 4:
                System.out.println("Request Commands:"
                + "\n1. Issue Request"
                + "\n2. Solve Request"
                + "\n3. Cancel Request"
                + "\n4. List Active Requests"
                + "\n5. Search Request by ID");
                break;
            default:
                System.out.println("Error: Invalid Command Index");
                break;
        }
    }

}
