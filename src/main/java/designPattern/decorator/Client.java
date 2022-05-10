package designPattern.decorator;

/**
 * 装饰者模式的特点
 * 增强，新增行为
 * 动态继承，让类在运行期改变行为的能力。
 * 符合开闭原则，增加新类就可以新的行为。
 *
 * 装饰模式，所有装饰行为都会依次执行，适用于对一致行为的扩展
 * 特点：在处理过程中，没有终止的概念，所有装饰的效果都会依次叠加发挥效果
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        //第一次修饰
        component = new ConcreteDecoratorA(component);
        //第二次修饰
        component = new ConcreteDecoratorB(component);
        //修饰后运行
        component.operation();
        System.out.println("");

        Component component2 = new ConcreteComponent();
        component2 = new Decorator(component2);
        component2 = new Decorator2(component2);
        component2.operation();
        System.out.println("");
        System.out.println("");
        // 经典用法
        // 增强 Bird 类
        // 有本体功能是 飞 的行为
        // 可以相应增加  飞过天空  飞过海洋 飞过丛林 飞过沼泽 等行为
        // 因功能类似，可封装父类  飞过的地方
        Fly bird = new Bird("雄鹰");
        //赋予 bird 飞过天空
        bird = new FlySky(bird);
        // 飞一圈
        bird.fly();
        //再飞过高山
        System.out.println("");
        bird = new FlyMountain(bird);
        // 飞一圈
        bird.fly();
        //根据需要，可继续增加任何行为，而不需要修改原有类











    }
}
