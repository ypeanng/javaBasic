package designPattern.decorator;

public class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.print("飞");
    }
}
