package com.study.example.rest.demo.controller;


public class AnimalClass {

    public static void main(String[] args) {
        Animal animal = new Fish();
        System.out.println(animal.growLeg() + animal.info());
        animal = new Lizard(animal);
        System.out.println(animal.growLeg() + animal.info());
        animal = new Brid(animal);
        System.out.println(animal.growLeg() + animal.info());
    }
}

interface Animal {

    String growLeg ();

    String info ();
}

class Fish implements Animal {

    private String animal = "鱼";

    private String desc = "我是一个鱼";

    @Override
    public String growLeg() {
        return animal;
    }

    @Override
    public String info() {
        return desc;
    }


}

abstract class AnimalTransformation implements Animal {

    private Animal animal;

    public AnimalTransformation (Animal animal) {
        this.animal = animal;
    }

    @Override
    public String growLeg() {
        return animal.growLeg()+ "变身了";
    }

    @Override
    public String info() {
        return animal.info();
    }
}

class Lizard extends AnimalTransformation {

    public Lizard(Animal animal) {
        super(animal);
    }

    @Override
    public String growLeg() {
        return super.growLeg() + "长了腿";
    }

    @Override
    public String info() {
        return super.info() + "变成了蜥蜴";
    }
}

class Brid extends AnimalTransformation {

    public Brid(Animal animal) {
        super(animal);
    }

    @Override
    public String growLeg() {
        return super.growLeg() + "长了翅膀";
    }

    @Override
    public String info() {
        return super.info() + "变成了鸟";
    }
}
