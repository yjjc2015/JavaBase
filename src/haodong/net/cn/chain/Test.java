package haodong.net.cn.chain;

/**
 * 责任链模式测试类
 * Created by haodong on 15-5-24.
 */
public class Test {
    public static void main(String[] args) {
        //把一个个handler串起来
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setHandler(handler2);

        handler1.handlerRequest("2");
    }
}
