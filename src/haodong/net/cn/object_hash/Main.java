package haodong.net.cn.object_hash;

import java.util.HashMap;
import java.util.Map;
/**
 * 判断java中两个对象的值是否相等时需要重写equals()和hashCode()两个方法
 * @author haodong
 *
 */
public class Main {
	static class People {
		private String name;
		private int age;
		private String address;
		public People(String name, int age, String address) {
			this.name = name;
			this.age = age;
			this.address = address;
		} 
		/**
		 * 重写equals方法，分别对比People类中的各个属性的值
		 */
		@Override
		public boolean equals(Object obj) {
			if (null == obj) return false;
			if (!(obj instanceof People)) return false;
			People other = (People)obj;
			if (other.name != this.name) return false;
			if (other.age != this.age) return false;
			if (other.address != this.address) return false;
			return true;
		}
		/**
		 * 重写hashCode方法，针对name属性做hash，仿照原hashCode函数的写法
		 */
		@Override
		public int hashCode() {
			final int prime = 31;  
	        int result = 1;  
	        result = prime * result + ((name == null) ? 0 : name.hashCode());  
	        return result;
		}
	}
	public static void main(String[] args) {
		People people1 = new People("haodong", 24, "beijing");
		People people2 = new People("haodong", 24, "beijing");
		System.out.println(people1.equals(people2));
		Map<People, Integer> map = new HashMap<People, Integer>();
		map.put(people1, 20);
		map.put(people2, 22);               //当把people2放入map中时，map首先调用equals方法和hashCode方法判断map里面是否有和people2相等key的值，
															//如果有就直接改写对应的hash值，如果没有，则加入新的key-value对
		System.out.println(map.get(people1));
	}

}
