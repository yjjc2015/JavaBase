package haodong.net.cn.singleton;
/**
 * 使用枚举类实现单例模式
 * @author haodong
 *
 */
public class Main3 {
	private enum Singeton {
		INSTANCE;
		public void print() {
			System.out.println("hello world");
		}
		public void doOtherThing() {
			// do other thing
		}
	}
	public static void main(String[] args) {
		Singeton singleton = Singeton.INSTANCE;          //直接由类直接引用枚举中的单例即可
		singleton.print();
		singleton.doOtherThing();
	}
}
