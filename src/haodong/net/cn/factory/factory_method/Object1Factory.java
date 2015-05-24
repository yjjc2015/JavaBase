package haodong.net.cn.factory.factory_method;

/**
 * Created by haodong on 15-5-24.
 */
public class Object1Factory implements Factory {
    @Override
    public ObjectBase create() {
        return new Object1();
    }
}
