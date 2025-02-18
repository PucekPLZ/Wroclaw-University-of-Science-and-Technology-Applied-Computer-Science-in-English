public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Lucjan", 21, 180, 68);
        Person person2 = new Person("Ksawery", 90, 10, 10);

        person1.printDetails();
        person1.printDetails("");
        Person.getCount();

        Employee employee1 = new Employee(1, 1110, "IT");
        Employee employee2 = new Employee(2, 20000, "IT");

        employee1.annualSalary();
        employee1.printDetails();
        employee1.compareSalary(employee2);
    }
}