public class Bike extends Vehicle {
    
    public Bike(String brand) {
        super(brand);
    }

    public void ringBell() {
        System.out.println("Bike rings bell: Ring Ring!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("This vehicle is a bike.");
    }
}