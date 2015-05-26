package haodong.net.cn.prototype;

/**
 * 原型模型测试类
 * Created by haodong on 15-5-26.
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcretePrototype1 prototype1 = new ConcretePrototype1();
        ConcretePrototype1 concretePrototype1 = (ConcretePrototype1) prototype1.clone();
        System.out.println("concretePrototype1 的类型是： "+concretePrototype1.CLASS_TAG);

        ConcretePrototype2 prototype2 = new ConcretePrototype2();
        ConcretePrototype2 concretePrototype2 = (ConcretePrototype2) prototype2.clone();
        System.out.println("concretePrototype2 的类型是： "+concretePrototype2.CLASS_TAG);
    }
}
