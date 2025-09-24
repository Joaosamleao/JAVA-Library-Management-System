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

        System.out.println("Type 1 - Clerk | Type 2 - Janitor | Type 3 - Librarian | Type 4 - Manager");

        while(true) {
            try {
                System.out.print("Employee type: ");
                i = sc.nextInt();
                sc.nextLine();

                if (i < 1 || i > 4) {
                    throw new InvalidInputException("Error: Employee type is invalid. Please choose an index within 1-4.");
                }
                return i;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
            }
        }
    }

    private String getEmployeeName() {
        String name;
        while(true) {
            try {
                System.out.print("Employee Name: ");
                name = sc.nextLine().trim();

                if (name.isEmpty()) {
                    throw new InvalidInputException("Error: Employee name cannot be empty.");
                } else if (!name.matches("[a-zA-Z\\s]+")) {
                    throw new InvalidInputException("Error: Employee name cannot contain numbers or special characters.");
                }
                return name;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
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
                System.out.print(e.getMessage());
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
                System.out.println(e.getMessage());
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
                break;
            
            case 2:
                employees.add(new Janitor(id, name, work_hours, pay_per_hour));
                break;
            
            case 3:
                employees.add(new Librarian(id, name, work_hours, pay_per_hour));
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
                    System.out.print("Error: You cannot register multiple managers.");
                    return;
                } else {
                    employees.add(new Manager(id, name, work_hours, pay_per_hour));
                }
                break;
            default:
                System.out.print("Error: Invalid employee type.");
                return;
        }
        System.out.print("Operation successful.");
    }

    private boolean exists(String id) {
        for (Employee emp : employees) {
            if (emp.getEmp_id().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String empByID() {
        String id;

        while(true) {
            try {
                System.out.print("Employee ID: ");
                id = sc.nextLine();


                if (id.isEmpty()) {
                    throw new InvalidInputException("Employee ID cannot be empty.");
                }
                if(!id.matches("\\d+")) {
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
        String idToRemove = empByID();
        employees.removeIf(emp -> emp.getEmp_id().equals(idToRemove));
        System.out.print("Operation Successful");
    }

    private Employee createNewInstance(int type, Employee oldEmployee) {
        String id = oldEmployee.getEmp_id();
        String name = oldEmployee.getEmp_name();
        int work_hours = oldEmployee.getWork_hours();
        double pay_per_hour = oldEmployee.getPay_per_hour();

        switch(type) {
            case 1:
                return new Clerk(id, name, work_hours, pay_per_hour);
            case 2:
                return new Janitor(id, name, work_hours, pay_per_hour);
            case 3:
                return new Librarian(id, name, work_hours, pay_per_hour);
            case 4:
                boolean managerExists = false;
                for(Employee emp: employees) {
                    if (emp.getClass() == Manager.class) {
                        managerExists = true;
                        break;
                    }
                }

                if (managerExists) {
                    System.out.print("Error: A manager already exists. Cannot change to this type.");
                    return null;
                }
                return new Manager(id, name, work_hours, pay_per_hour);
            default:
                System.out.print("Error: Invalid employee type");
                return null;
        }
    }

    @Override
    public void editObject() {
        listObjects();
        String idToEdit = empByID();
        Employee empToEdit = (Employee) getObject(idToEdit);

        System.out.println("1 - Change employee type | 2 - Change employee id | 3 - Change employee name | 4 - Change employee work hours | 5 - Change employee pay per hour");
        
        int op = sc.nextInt();
        sc.nextLine();
        
        switch(op) {
            case 1:
                int newType = getEmployeeType();

                Employee newEmp = createNewInstance(newType, empToEdit);
                if (newEmp != null) {
                    for(int i = 0; i < employees.size(); i++) {
                        if(employees.get(i).getEmp_id().equals(idToEdit)) {
                            employees.set(i, newEmp);
                            return;
                        }
                    }
                }
                break;
            case 2:
                String newId = getEmployeeID();
                empToEdit.setEmp_id(newId);
                break;
            case 3:
                String newName = getEmployeeName();
                empToEdit.setEmp_name(newName);
                break;
            case 4:
                int newWork_hours = getWorkHours();
                empToEdit.setWork_hours(newWork_hours);
                break;
            case 5:
                double newPay_per_hour = getPayPerHour();
                empToEdit.setPay_per_hour(newPay_per_hour);
                break;
            default:
                System.out.print("Error: Invalid operation");
                break;
        }
        System.out.print("Operation Successful");
    }

    @Override
    public void listObjects() {
        if (employees.isEmpty()) {
            System.out.print("Error: No employees registered in the system.");
            return;
        }

        System.out.printf("%-10s %-20s %-15s %-15s %-15s\n", "ID", "Name", "Type", "Work Hours", "Pay per Hour");
        System.out.println("--------------------------------------------------------------------------");

        for (Employee emp : employees) {
            System.out.printf("%-10s %-20s %-15s %-15s %-15s");
            emp.getEmp_id();
            emp.getEmp_name();
            emp.getClass().getSimpleName();
            emp.getWork_hours();
            emp.getPay_per_hour();
        }
    }

    @Override
    public void printObject(String id) {
        Employee emp = (Employee) getObject(id);

        if (emp != null) {
            System.out.println(emp.toString());
        } else {
            System.out.print("Error: The employee ID: " + id + " was not found.");
        }
    }

    @Override
    public Object getObject(String id) {
        for (Employee emp: employees) {
            if (emp.getEmp_id().equals(id)) {
                return emp;
            }
        }
        return null;
    } 
}
