package designPattern.decorator;


public class Decorator2 implements Component {

    private Component component = null;

    //通过构造函数传递给被修饰者
    public Decorator2(Component component) {
        this.component = component;
    }
    private void method3() {
        System.out.print("喜鹊");
    }

    //委托给被修饰者执行
    @Override
    public void operation() {
        method3();
        if (component != null) {
            this.component.operation();
        }
    }
}
