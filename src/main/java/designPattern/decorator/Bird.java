package designPattern.decorator;

public class Bird implements Fly {

    String name;

    public Bird(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + "飞");
    }
}
