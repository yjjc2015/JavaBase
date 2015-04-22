package haodong.net.cn.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Users类，继承Iterable接口，并实现Iterator方法
 * @author haodong
 *
 * @param <T>
 */
public class Users<T> implements Iterable<T> {
	private List<T> list = new ArrayList<>();           //使用list保存user实例
	public void add(T t) {
		list.add(t);
	}
	public Iterator<T> iterator() {
		return new UsersIterator<T>(this);
	}
	/**
	 * 实现Iterator接口，并实现hasNext, next, remove方法
	 * @author haodong
	 *
	 * @param <T1>
	 */
	private class UsersIterator<T1> implements Iterator<T1> {
		private List<T1> list = new ArrayList<>();
		private int index = 0;
		/**
		 * 做Users类中List的映射
		 * @param users
		 */
		public UsersIterator(Users<?> users) {
			this.list = (List<T1>) users.list;
		}
		@Override
		public boolean hasNext() {
			if (index >= list.size()) return false;
			return true;
		}
		
		@Override
		public T1 next() {
			int tmpIndex = this.index;
			index++;
			return (T1) list.get(tmpIndex);
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("exception");
		}
	}
}
