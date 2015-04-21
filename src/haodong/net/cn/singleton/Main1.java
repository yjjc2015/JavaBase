package haodong.net.cn.singleton;
/**
 * 使用内部类引用单例模式
 * @author haodong
 *
 */
public class Main1 {
	static final class Singleton {
		private static final Main1 INSTANCE = new Main1();
	}
	public Main1 getInstance() {
		return Singleton.INSTANCE;
	} 
}
