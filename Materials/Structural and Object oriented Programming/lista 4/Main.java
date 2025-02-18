public class Main {
    public static void main(String[] args) {

        // zadanie 8

        Dog dogg = new Dog("Buddy", 3);
        Animal animal = dogg;
        if (animal instanceof Dog) { 
            Dog downcastedDog = (Dog) animal;
            downcastedDog.bark();
        }

        // zadanie 10

        Dog dog1 = new Dog("Buddy", 3);
        Dog dog2 = new Dog("Buddy", 3);
        Dog dog3 = new Dog("Max", 5);

        Cat cat1 = new Cat("Whiskers", 2);
        Cat cat2 = new Cat("Whiskers", 2);

        System.out.println(dog1.toString()); 
        System.out.println(cat1.toString()); 

        System.out.println(dog1.equals(dog2)); 
        System.out.println(dog1.equals(dog3)); 
        System.out.println(cat1.equals(cat2)); 

        System.out.println(dog1.hashCode() == dog2.hashCode()); 
        System.out.println(dog1.hashCode() == dog3.hashCode()); 
        System.out.println(cat1.hashCode() == cat2.hashCode()); 

        // zadanie 11

        Dog dog = new Dog("Buddy", 3);
        dog.setWeight(10.5); // 
        dog.setHeight(0.5); 
        System.out.println(dog.getName() + " is " + dog.getWeight() + " kg and " + dog.getHeight() + " m tall.");
        dog.makeSound(); 

        Cat cat = new Cat("Whiskers", 2);
        cat.setWeight(4.2);  
        cat.setHeight(0.3);  
        System.out.println(cat.getName() + " is " + cat.getWeight() + " kg and " + cat.getHeight() + " m tall.");
        cat.makeSound(); 

        // zadanie 12

        Dog dog4 = new Dog("Buddy", 3);
        Dog dog5 = new Dog("Max", 5);
        Cat cat3 = new Cat("Whiskers", 2);

        System.out.println(dog4.getInfo()); 
        System.out.println(dog5.getInfo()); 
        System.out.println(cat3.getInfo()); 

        System.out.println("Total Animals Created: " + Animal.getSpeciesCount()); 

        // zadanie 13

        Dog dog6 = new Dog();
        Dog dog7 = new Dog("Buddy");
        Dog dog8 = new Dog("Max", 5);

        Cat cat4 = new Cat();
        Cat cat5 = new Cat("Whiskers");
        Cat cat6 = new Cat("Mittens", 3);

        // zadanie 14

        Animal[] animals = new Animal[3];
        animals[0] = new Dog();
        animals[1] = new Cat();
        animals[2] = new Bird();

        for (Animal animal1 : animals) {
            animal1.makeSound(); 
        }

        // zadanie 16

        Animal dog9 = new Dog();
        Animal cat7 = new Cat();

        dog9.doAction();   
        cat7.doAction();   

        // zadanie 17

        Vehicle car = new Car("Toyota");
        Vehicle bike = new Bike("Yamaha");
        Vehicle truck = new Truck("Ford");

        car.displayInfo();
        ((Car) car).honk(); 

        bike.displayInfo();
        ((Bike) bike).ringBell();  

        truck.displayInfo();
        ((Truck) truck).load();
    }
}