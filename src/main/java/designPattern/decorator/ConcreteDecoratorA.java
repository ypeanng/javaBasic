package designPattern.decorator;

public class ConcreteDecoratorA extends Decorator {

    //定义被修饰者
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    private void method1() {
        System.out.print("喜鹊");
    }

    //定义自己的修饰方法
    @Override
    public void operation() {
        this.method1();
        super.operation();
    }
}
