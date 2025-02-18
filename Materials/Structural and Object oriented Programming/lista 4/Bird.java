public final class Bird extends Animal {

    public Bird(String name, int age) {
        super(name, age);
    }

    public Bird() {
        super(); 
    }

    @Override
    public void makeSound() {
        System.out.println("Tweet");
    }

    public final void fly() {
        System.out.println(name + " is flying high in the sky!");
    }
}

