package datastructures;

import java.util.LinkedList;

public class AnimalShelter {
    private final LinkedList<Dog> dogs = new LinkedList<>();
    private final LinkedList<Cat> cats = new LinkedList<>();

    void enqueue(Dog animal) {
        dogs.add(animal);
    }

    void enqueue(Cat animal) {
        cats.add(animal);
    }

    Dog dequeueDog() {
        return dogs.removeFirst();
    }

    Cat dequeueCat() {
        return cats.removeFirst();
    }

    Animal dequeueAny() {
        Dog firstDog = dogs.getFirst();
        Cat firstCat = cats.getFirst();

        if (firstCat.age > firstDog.age) {
            cats.removeFirst();
            return firstCat;
        }

        dogs.removeFirst();
        return firstDog;
    }
}