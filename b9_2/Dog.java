package b9_2;

public class Dog extends Pet {

    public Dog() {
        super();
    }

    public Dog(String petId, String petName, int age) {
        super(petId, petName, age);
    }

    @Override
    public void speak() {
        System.out.println("Gâu gâu");
    }
}
