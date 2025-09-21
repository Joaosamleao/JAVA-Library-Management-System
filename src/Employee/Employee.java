package Employee;

public abstract class Employee {
    
    private String emp_id;
    private String emp_name;
    private int work_hours;
    private double salary_per_hour;
    private double salary_total;

    public Employee(String emp_id, String emp_name, int work_hours, double salary_per_hour) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.work_hours = work_hours;
        this.salary_per_hour = salary_per_hour;
    }

    public void calcSalary() {
        salary_total = work_hours * salary_per_hour;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String new_id) {
        this.emp_id = new_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String new_name) {
        this.emp_name = new_name;
    }

    public double getSalary() {
        return salary_total;
    }
    
}
