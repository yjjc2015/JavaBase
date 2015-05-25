package haodong.net.cn.strategy;

/**
 * 1. 该类引用Strategy类实例，然后调用strategy方法
 * 2. 该类实例初始化时给strategy实例赋值
 * Created by haodong on 15-5-25.
 */
public class Context {
    private Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public void contextInterface() {
        this.strategy.strategyInterface();
    }
}
