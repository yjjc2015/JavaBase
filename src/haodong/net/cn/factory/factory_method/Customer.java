package haodong.net.cn.factory.factory_method;

/**
 * 工厂方法模式测试类
 * Created by haodong on 15-5-24.
 */
public class Customer {
    public static void main(String[] args) {
        Factory factory = new Object1Factory();
        Object1 object1 = (Object1) factory.create();
        factory = new Object2Factory();
        Object2 object2 = (Object2) factory.create();
    }
}
