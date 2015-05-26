package haodong.net.cn.prototype;

/**
 * 具体原型类2
 * Created by haodong on 15-5-26.
 */
public class ConcretePrototype2 extends Prototype {
    public static final int CLASS_TAG = 2;

    /**
     * 克隆自身的方法
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        return (ConcretePrototype2)super.clone();
    }
}
