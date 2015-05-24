package haodong.net.cn.factory.simple_factory;

/**
 * Created by haodong on 15-5-24.
 */
public class SimpleFactory {
    public ObjectBase getInstance(int type) {
        switch(type) {
            case 1: return new Object1();
            case 2: return new Object2();
            default: return null;
        }
    }
}
