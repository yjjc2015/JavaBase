package haodong.net.cn.observer;

/**
 * Created by haodong on 15-5-24.
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update(Subject subject) {
        System.out.println("job list 是: " + subject);
    }
}
