public class Car extends Vehicle {
    
    public Car(String brand) {
        super(brand);
    }

    public void honk() {
        System.out.println("Car honks: Beep Beep!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("This vehicle is a car.");
    }
}