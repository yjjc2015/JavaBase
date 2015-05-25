package haodong.net.cn.strategy;

/**
 * 策略模式测试类
 * 策略模式看上去和简单工厂模式类似，区别在于简单工厂模式创建得到的是一个类，而策略模式使用接口简化方法和策略调用。
 * Created by haodong on 15-5-25.
 */
public class Test {
    public static void main(String[] args) {
        Strategy strategy = new ConcreteStrategyA();
        Context context = new Context(strategy);
        context.contextInterface();
    }
}
