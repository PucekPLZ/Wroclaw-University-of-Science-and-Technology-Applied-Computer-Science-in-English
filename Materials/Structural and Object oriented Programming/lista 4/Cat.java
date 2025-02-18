public class Cat extends Animal implements Playable, Pet {

    public Cat(String name, int age) {
        super(name, age);
    }

    public Cat() {
        super(); 
    }

    public Cat(String name) {
        super(name); 
    }

    public void meow(){
        System.out.println("Meow");
    }

    @Override
    public void makeSound() {
        System.out.println("Meow");
    }

    @Override
    public void play() {
        System.out.println(name + " is playing with a ball!");
    }

    @Override
    public void feed() {
        System.out.println(name + " is being fed cat food.");
    }

    @Override
    public void groom() {
        System.out.println(name + " is being groomed with a comb.");
    }   

    @Override
    public String getInfo() {
        return super.getInfo() + " (Cat)";
    }

    @Override
    public void eat() {
        try {
            throw new FoodNotAvailableException("Food is not available for the cat.");
        } catch (FoodNotAvailableException e) {
            System.out.println("Cat: " + e.getMessage());
        }
    }

    @Override
    public void doAction() {
        meow();
    }
}

