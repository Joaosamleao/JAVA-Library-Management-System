package Managers;

import Interfaces.ObjectManagement;
import Interfaces.ObjectDisplay;
import Interfaces.ObjectRetrieval;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Employee.Employee;
import Employee.Clerk;
import Employee.Janitor;
import Employee.Librarian;
import Employee.Manager;

import Exceptions.InvalidInputException;

public class EmployeeManager implements ObjectDisplay, ObjectManagement, ObjectRetrieval {

    private ArrayList<Employee> employees;
    private Scanner sc = new Scanner(System.in);

    public EmployeeManager() {
        this.employees = new ArrayList<Employee>();
    }

    private int getEmployeeType() {
        int i;
        while(true) {
            try {
                System.out.print("Employee type: ");
                i = sc.nextInt();
                sc.nextLine();

                if (i < 1 || i > 4) {
                    throw new InvalidInputException("Employee type is invalid. Please choose an index within 1-4.");
                }
                return i;
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input. Please enter a number.");
                sc.nextLine();
            }
        }
    }

    private String getEmployeeID() {
        String id;
        while(true) {
            try {
                System.out.print("Employee ID: ");
                id = sc.nextLine();

                if (id.isEmpty()) {
                    throw new InvalidInputException("Employee ID cannot be null.");
                } else if(!id.matches("\\d+")) {
                    throw new InvalidInputException("Employee ID must contain only numbers.");
                }
                return id;
            } catch (InvalidInputException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String getEmployeeName() {
        String name;
        while(true) {
            try {
                System.out.print("Employee Name: ");
                name = sc.nextLine();

                if (name.isEmpty()) {
                    throw new InvalidInputException("Error: Employee name cannot be empty.");
                } else if (!name.matches("[a-zA-Z\\s]+")) {
                    throw new InvalidInputException("Error: Employee name cannot contain numbers or special characters.");
                }
                return name;
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private int getWorkHours() {
        int work_hours;
        while(true) {
            try {
                System.out.print("Employee Work Hours: ");
                work_hours = sc.nextInt();

                if (work_hours == 0) {
                    throw new InvalidInputException("Error: Work hours cannot be null.");
                } else if (work_hours < 0) {
                    throw new InvalidInputException("Error: Work hours cannot be a negative value.");
                }
                return work_hours;
            } catch (InvalidInputException e) {
                System.out.print("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.print("Error: Invalid Input. Please try again.");
                sc.nextLine();
            }
        }
    }

    private double getPayPerHour() {
        double pay_per_hour;
        while (true) {
            try {
                System.out.print("Employee Pay per Hour: ");
                pay_per_hour = sc.nextDouble();

                if(pay_per_hour == 0) {
                    throw new InvalidInputException("Error: Pay per Hour cannot be null.");
                } else if (pay_per_hour < 0) {
                    throw new InvalidInputException("Error: Pay per Hour cannot be a negative value.");
                }
                return pay_per_hour;
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input. Please try again.");
                sc.nextLine();
            }
        }
    }

    @Override
    public void addObject() {

        int i = getEmployeeType();
        String id = getEmployeeID();
        String name = getEmployeeName();
        int work_hours = getWorkHours();
        double pay_per_hour = getPayPerHour();

        switch(i) {
            case 1:
                employees.add(new Clerk(id, name, work_hours, pay_per_hour));
                System.out.print("Clerk created succesfully!");
                break;
            
            case 2:
                employees.add(new Janitor(id, name, work_hours, pay_per_hour));
                System.out.print("Janitor created succesfully!");
                break;
            
            case 3:
                employees.add(new Librarian(id, name, work_hours, pay_per_hour));
                System.out.print("Librarian created succesfully!");
                break;

            case 4:
                boolean managerExists = false; 

                for(Employee emp : employees) {
                    if(emp.getClass() == Manager.class) {
                        managerExists = true;
                        break;
                    }
                }

                if (managerExists) {
                    System.out.print("A manager is already in your system, cannot create another!");
                    return;
                } else {
                    employees.add(new Manager(id, name, work_hours, pay_per_hour));
                    System.out.print("Manager created succesfully!");
                }
        }
    }

    @Override
    public void removeObject() {

        System.out.print("Employee ID: ");
        String i = sc.nextLine();
    }

    @Override
    public void editObject() {

    }

    @Override
    public void listObjects() {

    }

    @Override
    public void printObject(int i) {

    }

    @Override
    public Object getObject(Object i) {
        return i;
    }

}
