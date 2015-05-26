package haodong.net.cn.prototype;

/**
 * 具体原型类1
 * Created by haodong on 15-5-26.
 */
public class ConcretePrototype1 extends Prototype {
    public static final int CLASS_TAG = 1;

    /**
     * 克隆自身的方法
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        return (ConcretePrototype1)super.clone();
    }
}
