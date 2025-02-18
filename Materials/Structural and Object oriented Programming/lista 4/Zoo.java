public class Zoo {
    
    public void visitAnimal(Animal animal) {
        animal.makeSound();

        if (animal instanceof Playable) {
            ((Playable) animal).play();
        }
        
        if (animal instanceof Pet) {
            ((Pet) animal).feed();
            ((Pet) animal).groom();
        }
    }
}
