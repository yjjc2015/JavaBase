package haodong.net.cn.chain;

/**
 * Created by haodong on 15-5-24.
 */
public class ConcreteHandler1 extends Handler {
    @Override
    public void handlerRequest(String condition) {
        if (condition.equals("1")) System.out.println("使用第一个handler就可以解决");
        else getHandler().handlerRequest(condition);
    }
}
