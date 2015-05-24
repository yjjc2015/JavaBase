package haodong.net.cn.chain;

/**
 * Handler接口，需要handlerRequest方法和下一个handler对象
 * Created by haodong on 15-5-24.
 */
abstract class Handler {
    private Handler handler;
    abstract public void handlerRequest(String condition);

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
