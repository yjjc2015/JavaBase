package haodong.net.cn.prototype;

/**
 * 原型类
 * 继承Cloneable接口，并实现clone方法
 * Created by haodong on 15-5-26.
 */
public abstract class Prototype implements Cloneable {
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
