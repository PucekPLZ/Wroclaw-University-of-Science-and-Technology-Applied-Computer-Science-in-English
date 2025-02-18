abstract public class Animal {

    String name;
    int age;
    private double weight; 
    private double height; 
    private static int speciesCount = 0;

    public Animal(String name, int age) {
        this.age = age;
        this.name = name;
        speciesCount++;
    }

    public Animal() {
    }

    public Animal(String name) {
        this(name, 0);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative.");
        }
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal animal = (Animal) obj;
        return age == animal.age && name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }

    public static int getSpeciesCount() {
        return speciesCount;
    }

    public String getInfo() {
        return "Name: " + name + ", Age: " + age;
    }

    public void eat() throws FoodNotAvailableException {
        throw new FoodNotAvailableException("Food is not available for this animal.");
    }

    public void doAction() {
        System.out.println("Animal is doing something.");
    }

    abstract public void makeSound();
}
