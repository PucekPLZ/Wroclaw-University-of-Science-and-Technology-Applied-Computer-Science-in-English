public class Dog extends Animal implements Playable, Pet {

    public Dog(String name, int age) {
        super(name, age);
    }

    public Dog() {
        super(); 
    }

    public Dog(String name) {
        super(name); 
    }

    public void bark(){
        System.out.println("Woof");
    }

    @Override
    public void makeSound() {
        System.out.println("Woof");
    }

    @Override
    public void play() {
        System.out.println(name + " is playing fetch!");
    }

    @Override
    public void feed() {
        System.out.println(name + " is being fed dog food.");
    }

    @Override
    public void groom() {
        System.out.println(name + " is being brushed and bathed.");
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " (Dog)";
    }

    @Override
    public void eat() {
        try {
            throw new FoodNotAvailableException("Food is not available for the dog.");
        } catch (FoodNotAvailableException e) {
            System.out.println("Dog: " + e.getMessage());
        }
    }

    @Override
    public void doAction() {
        bark();
    }
}
