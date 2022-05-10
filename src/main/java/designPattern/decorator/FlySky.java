package designPattern.decorator;

public class FlySky extends FlyLocation {

    FlySky(Fly fly) {
        super(fly);
    }

    @Override
    public void fly() {
        fly.fly();
        System.out.println("飞过蓝天");
    }
}
