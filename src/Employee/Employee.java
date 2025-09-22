package Employee;

public abstract class Employee {
    
    private String emp_id;
    private String emp_name;
    private int work_hours;
    private double pay_per_hour;
    private double salary_total;

    public Employee(String emp_id, String emp_name, int work_hours, double pay_per_hour) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.work_hours = work_hours;
        this.pay_per_hour = pay_per_hour;
    }

    public void calcSalary() {
        salary_total = work_hours * pay_per_hour;
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

    public int getWork_hours() {
        return work_hours;
    }

    public void setWork_hours(int hours) {
        this.work_hours = hours;
    }

    public double getSalary() {
        return salary_total;
    }

    public double getPay_per_hour() {
        return pay_per_hour;
    }

    public void setPay_per_hour(double pay) {
        this.pay_per_hour = pay;
    }

    @Override
    public String toString() {
        return String.format("ID: %s - Name: %s - Work Hours: %d", emp_id, emp_name, work_hours);
    }
    
}
