package hw_2;

public class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }
    public void makeSound() {
        System.out.println("Woof");
    }
}
