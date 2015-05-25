package haodong.net.cn.strategy;

/**
 * Created by haodong on 15-5-25.
 */
public class ConcreteStrategyA implements Strategy {
    @Override
    public void strategyInterface() {
        System.out.println("采用strategy A");
    }
}
