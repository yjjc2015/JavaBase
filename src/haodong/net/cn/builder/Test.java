package haodong.net.cn.builder;

/**
 * 建造者模式测试类
 * Created by haodong on 15-5-26.
 */
public class Test {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director derector = new Director(builder);
        derector.construct();
        Product product = builder.retrieveResult();
        System.out.println("第一部分为： "+product.getPart1());
        System.out.println("第二部分为： "+product.getPart2());
    }
}
