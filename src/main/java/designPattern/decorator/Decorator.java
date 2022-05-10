package designPattern.decorator;


public class Decorator  implements Component{

    private Component component = null;

    //通过构造函数传递给被修饰者
    public Decorator(Component component) {
        component = component;
    }

    //委托给被修饰者执行
    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}
