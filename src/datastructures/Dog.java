package datastructures;

class Animal {
    public final int age;

    Animal(int age) {
        this.age = age;
    }
}

class Dog extends Animal{
    Dog(int id) {
        super(id);
    }
}

class Cat extends Animal{
    Cat(int id) {
        super(id);
    }
}
