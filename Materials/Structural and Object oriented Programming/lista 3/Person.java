public class Person {
    private String name;
    private int age;
    public int height;
    public int weight;

    static int count = 0;
    static final int MAX_AGE = 100;

    public Person(String name, int age, int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        
        if (MAX_AGE < age) {
            throw new IllegalArgumentException("the person is too old");
        }

        count++;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public void printDetails() {
        System.out.println("Name: " + this.name + " Age: " + this.age + " Height: " + this.height + " Weight: " + this.weight);
    }

    public void printDetails(String name) {
        System.out.println("Name: " + this.name);
    }

    public static void getCount() {
        System.out.println("Count: " + count);
    }
}