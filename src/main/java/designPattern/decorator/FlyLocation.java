package designPattern.decorator;

public abstract class FlyLocation implements Fly{
    protected Fly fly;
    FlyLocation(Fly fly){
        this.fly = fly;
    }

}
