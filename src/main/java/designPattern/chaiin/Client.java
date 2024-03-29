package designPattern.chaiin;

/**
 * 责任链模式，所有行为都会根据添加顺序依次执行。
 * 适用于参数一致，或有关联关系的情况下，对参数的顺序处理
 * 且，每一步骤都可灵活决定是传递到下一级处理，还是终结响应。
 * 比如：N级过滤器 、自下而上的审批
 *
 * 装饰模式，所有装饰行为都会依次执行，适用于对一致行为的扩展
 *  在处理过程中，没有终止的概念，所有装饰的效果都会依次叠加发挥效果
 * 比如：装饰飞行动作，飞过草地，飞过蓝天
 *
 * 总结：是否要求所有相关类全部走一遍！！
 * 责任链，会在某一层结束，不继续向下传导
 * 装饰模式：会一直向下传导，直到最后一层。
 */
public class Client {

    public static void main(String[] args) {
        // 可以随意组合装饰品，
        // 比如，女人每天都化不一样的妆容。
        // 责任链：链太长会有性能问题。

    }
}
