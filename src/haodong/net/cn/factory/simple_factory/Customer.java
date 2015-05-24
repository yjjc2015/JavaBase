package haodong.net.cn.factory.simple_factory;

/**
 * 简单工厂模式
 * Created by haodong on 15-5-24.
 */
public class Customer {
    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        factory.getInstance(1);
        factory.getInstance(2);
    }
}
