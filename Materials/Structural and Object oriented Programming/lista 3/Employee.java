public class Employee {
    private int id;
    private int salary;
    private String departament;

    public Employee(int id, int salary, String departament) {
        this.id = id;
        this.salary = salary;
        this.departament = departament;
    }

    public int annualSalary() {
        return this.salary * 12;
    }

    public void printDetails() {
        System.out.println("ID: " + this.id + " Salary: " + this.salary + " Departament: " + this.departament);
    }

    public void compareSalary(Employee employee1) {
        if (this.salary > employee1.salary) {
            System.out.println("Salary of " + this.id + " is bigger than " + employee1.id);
        } else {
            System.out.println("Salary of " + employee1.id + " is bigger than " + this.id);
        }
    }
}