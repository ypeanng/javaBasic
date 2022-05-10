package designPattern.decorator;

public class ConcreteDecoratorB extends Decorator {

    //定义被修饰者
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    private void method2() {
        System.out.print("上了我家的枝头");
    }

    //定义自己的修饰方法
    @Override
    public void operation() {
        super.operation();
        this.method2();
    }
}
