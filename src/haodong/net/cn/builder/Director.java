package haodong.net.cn.builder;

/**
 * 协调类
 * 有一个Builder对象，构造方法时初始化builder对象，通过构造方法执行建造指令
 * Created by haodong on 15-5-26.
 */
public class Director {
    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }
    public void construct() {
        this.builder.buildPart1();
        this.builder.buildPart2();
    }
}
