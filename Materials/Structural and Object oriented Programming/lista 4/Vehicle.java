public class Vehicle {

    String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void displayInfo() {
        System.out.println("This is a vehicle of brand: " + brand);
    }
}