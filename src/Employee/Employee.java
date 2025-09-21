package Employee;

public class Employee {
    
    private String emp_id;
    private String emp_name;

    public Employee(String emp_id, String emp_name) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }
    
}
