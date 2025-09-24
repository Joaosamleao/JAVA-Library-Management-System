package Managers;

import Interfaces.ObjectDisplay;
import Interfaces.ObjectManagement;
import Interfaces.ObjectRetrieval;

import java.util.ArrayList;
import java.util.Scanner;

import Request.Request;
import Enums.RequestType;
import Exceptions.InvalidInputException;

public class RequestManager implements ObjectDisplay, ObjectManagement, ObjectRetrieval {
    
    private ArrayList<Request> requests = new ArrayList<Request>();
    private Scanner sc = new Scanner(System.in);

    public RequestManager() {
        this.requests = new ArrayList<Request>();
    }

    private RequestType getRequestTypeFromUser() {
        int requestType;

        while(true) {
            try {
                System.out.print("1 - LOW PRIORITY | 2 - MEDIUM PRIORITY | 3 - HIGH PRIORITY\nChoose the request type: ");
                requestType = sc.nextInt();
                sc.nextLine();

                switch(requestType) {
                    case 1:
                        return RequestType.LOW;
                    case 2:
                        return RequestType.MEDIUM;
                    case 3:
                        return RequestType.HIGH;
                    default:
                        throw new InvalidInputException("Error: Invalid input. Please enter a number between 1 and 3.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }   
    }

    private String getRequestDescription() {
        String description;

        while(true) {
            try {
                System.out.print("Enter the request description: ");
                description = sc.nextLine();

                if(description.isEmpty()) {
                    throw new InvalidInputException("Error: Description cannot be empty.");
                }

                return description;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getRequestID() {
        String id;

        while(true) {
            try {
                System.out.print("Request ID: ");
                id = sc.nextLine();

                if(id.isEmpty()) {
                    throw new InvalidInputException("Error: ID cannot be empty.");
                }
                if (!id.matches("REQ-\\d{4}")) {
                    throw new InvalidInputException("Error: ID must be in the format REQ-XXXX where X is a digit.");
                }
                if(!exists(id)) {
                    throw new InvalidInputException("Error: A request with this ID already exists.");
                }

                return id;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean exists(String id) {
        for(Request request : requests) {
            if(request.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String requestByID() {
        String id;

        while(true) {
            try {
                System.out.print("Enter the request ID: ");
                id = sc.nextLine();

                if(id.isEmpty()) {
                    throw new InvalidInputException("Error: ID cannot be empty.");
                }
                if (!id.matches("REQ-\\d{4}")) {
                    throw new InvalidInputException("Error: ID must be in the format REQ-XXXX where X is a digit.");
                }
                if(!exists(id)) {
                    throw new InvalidInputException("Error: No request found with the given ID.");
                }

                return id;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void addObject() {

        RequestType requestType = getRequestTypeFromUser();
        String description = getRequestDescription();
        String id = getRequestID();

        Request newRequest = new Request(id, requestType, description);
        requests.add(newRequest);
        System.out.println("Operation Successful");
    }

    @Override
    public void removeObject() {
        String IDtoRemove = requestByID();
        requests.removeIf(book -> book.getId().equals(IDtoRemove));
        System.out.println("Operation Successful");
    }

    @Override
    public void editObject() {
        listObjects();
        String IDtoEdit = requestByID();

        System.out.print("1 - Change Type | 2 - Change Description\nChoose an option: ");
        int op = sc.nextInt();

        switch(op) {
            case 1:
                RequestType newType = getRequestTypeFromUser();
                for(Request request : requests) {
                    if(request.getId().equals(IDtoEdit)) {
                        requests.set(requests.indexOf(request), new Request(request.getId(), newType, request.getDescription()));
                        break;
                    }
                }
                break;
            case 2:
                String newDescription = getRequestDescription();
                for(Request request : requests) {
                    if(request.getId().equals(IDtoEdit)) {
                        requests.set(requests.indexOf(request), new Request(request.getId(), request.getRequestType(), newDescription));
                        break;
                    }
                }
                break;
            default:
                System.out.println("Error: Invalid option selected.");
                return;
        }
    }

    @Override
    public void listObjects() {
        if(requests.isEmpty()) {
            System.out.println("Error: No requests registered in the system.");
            return;
        }

        System.out.printf("%-10s %-15s %-30s\n", "ID", "Type", "Description");
        System.out.println("-----------------------------------------------------");

        for(Request request : requests) {
            System.out.print("%-10s %-15s %-30s");
            request.getId();
            request.getRequestType();
            request.getDescription();
        }
    }

    @Override
    public void printObject(String id) {
        Request request = (Request) getObject(id);

        if (request != null) {
            System.out.println(request.toString());
        } else {
            System.out.print("Error: The requested ID: " + id + " was not found." );
        }
    }

    @Override
    public Object getObject(String id) {
        for(Request request : requests) {
            if(request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }
}
