package haodong.net.cn.class_adapter;

/**
 * 适配器模式测试类
 * Created by haodong on 15-5-25.
 */
public class Test {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter(adaptee);
        adapter.method1();
        adapter.method2();
    }
}
