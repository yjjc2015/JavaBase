package haodong.net.cn.singleton;

/**
 * 懒加载模式实现单例模式
 * @author haodong
 *
 */
public class Main4 {
	private static Main4 INSTANCE;
	public Main4 getInstance() {
		if (INSTANCE == null) {
			synchronized (INSTANCE) {
				if (INSTANCE == null) {
					INSTANCE = new Main4();
				}
			}
		}
		return INSTANCE;
	}
}
