package haodong.net.cn.observer;


/**
 * 观察者模式测试类
 * Created by haodong on 15-5-24.
 */
public class Test {
    public static void main(String[] args) {
        Observer observer1 = new ConcreteObserver();
        Observer observer2 = new ConcreteObserver();

        ConcreteSubject subject = new ConcreteSubject();
        subject.registerSubject(observer1);
        subject.registerSubject(observer2);

        //加入申请者
        subject.addJob("haodong is coming!");
        subject.addJob("jikyll is coming!");
    }
}
