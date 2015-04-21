package haodong.net.cn.singleton;
/**
 * 静态final变量引用单例模式
 * @author haodong
 *
 */
public class Main2 {
	private static final Main2 INSTANCE = new Main2();
	public final Main2 getInstance() {
		return INSTANCE;
	}
}
