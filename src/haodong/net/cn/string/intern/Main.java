package haodong.net.cn.string.intern;
/**
 * 1. 测试String.intern方法功能
 * 2. java7及以上把字符串池存在堆中，可以通过-XX:StringTaleSize调整字符串池的大小
 * @author haodong
 *
 */
public class Main {
	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hello";
		System.out.println(s1==s2);
		String s3 = new String("hello");
		System.out.println(s1==s3);
		String s4 = new String("hello").intern();           //使用intern时，没有新建对象，而是把引用指向给新的变量
		System.out.println(s1 == s4);
	}
}
