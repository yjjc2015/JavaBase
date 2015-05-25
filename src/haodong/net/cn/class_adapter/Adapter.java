package haodong.net.cn.class_adapter;

/**
 * 适配器类，该类继承被适配类，同时实现目标接口方法
 * Created by haodong on 15-5-25.
 */
public class Adapter extends Adaptee implements Target {
    private Adaptee adaptee;
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    @Override
    public void method1() {
        System.out.println("正在执行target的method1方法");
    }
}
