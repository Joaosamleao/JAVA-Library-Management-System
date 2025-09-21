package Managers;

import Interfaces.ObjectManagement;
import Interfaces.ObjectDisplay;
import Interfaces.ObjectRetrieval;

import java.util.ArrayList;
import java.util.Scanner;

import Employee.Employee;
import Employee.Clerk;
import Employee.Janitor;
import Employee.Librarian;
import Employee.Manager;

public class EmployeeManager implements ObjectDisplay, ObjectManagement, ObjectRetrieval {

    private ArrayList<Employee> employees;
    private Scanner sc = new Scanner(System.in);

    public EmployeeManager() {
        this.employees = new ArrayList<Employee>();
    }

    @Override
    public void addObject() {

        System.out.print("Employee type: ");
        int i = sc.nextInt();

        System.out.print("Employee ID: ");
        String id = sc.nextLine();
        
        System.out.print("Employee Name:");
        String name = sc.nextLine();

        System.out.print("Employee Work Hours: ");
        int work_hours = sc.nextInt();

        System.out.print("Employee Pay per Hour: ");
        double salary_per_hour = sc.nextDouble();

        switch(i) {
            case 1:
                employees.add(new Clerk(id, name, work_hours, salary_per_hour));
                System.out.print("Clerk created succesfully!");
                break;
            
            case 2:
                employees.add(new Janitor(id, name, work_hours, salary_per_hour));
                System.out.print("Janitor created succesfully!");
                break;
            
            case 3:
                employees.add(new Librarian(id, name, work_hours, salary_per_hour));
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

                if (managerExists = true) {
                    System.out.print("A manager is already in your system, cannot create another!");
                    break;
                } else {
                    employees.add(new Manager(id, name, work_hours, salary_per_hour));
                    System.out.print("Manager created succesfully!");
                }
        }

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
    public void printObject(int i) {

    }

    @Override
    public Object getObject(Object i) {
        return i;
    }

}
