package ru.belov;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Fox.newInstance().say();
        Wolf.newInstance().say();
        Cat.newInstance().say();
        Animal.print("hey!");
    }
}

abstract class Animal{
    String voice;
    String name;
    void say(){System.out.println(this.voice);};
    static void print(String str){System.out.print(str);}
}

class Fox extends Animal {

    private Fox(){
        this.name = "Lisitca";
        this.voice = "Mimimi!";
    }

    static Fox newInstance(){
        return new Fox();
    }

}

class Wolf extends Animal{

    private Wolf(){
        this.name = "Volchara";
        this.voice = "Wooooo!";
    }

    static Wolf newInstance(){
        return new Wolf();
    }

}

class Cat extends Animal{

    private Cat(){
        this.name = "Kotenok";
        this.voice = "Meow!";
    }

    static Cat newInstance(){
        return new Cat();
    }

}
