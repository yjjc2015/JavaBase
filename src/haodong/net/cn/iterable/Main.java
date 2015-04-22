package haodong.net.cn.iterable;

import java.util.Iterator;
/**
 * 该实例为了实现java迭代器，并且支持foreach语法
 * @author haodong
 *
 */
public class Main {
	public static void main(String[] args) {
		Users<User> users = new Users<User>();
		users.add(new User("haodong", "123456"));
		users.add(new User("jikyll", "098765"));
		users.add(new User("tanghaodong", "24680"));
		//方式一：直接通过iterator访问
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		System.out.println("***************************************************");
		//方式二：使用for循环访问
		for (User user: users) {
			System.out.println(user.toString());
		}
	}
}
