package b9_2;

public class Cat extends Pet {

    public Cat() {
        super();
    }

    public Cat(String petId, String petName, int age) {
        super(petId, petName, age);
    }

    @Override
    public void speak() {
        System.out.println("Meo meo");
    }
}

