public class Truck extends Vehicle {
    public Truck(String brand) {
        super(brand);
    }

    public void load() {
        System.out.println("Truck is loading goods.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("This vehicle is a truck.");
    }
}