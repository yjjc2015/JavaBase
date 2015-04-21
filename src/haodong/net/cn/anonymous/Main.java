package haodong.net.cn.anonymous;

import java.lang.reflect.Method;
/**
 * 本实例主要验证两个知识点：
 * 1. java的匿名内部类可以继承一个类（可以是抽象类）或者实现一个接口
 * 2. java反射只能反射子类的属性和方法，getDeclaredMethods不能得到父类的属性和方法
 * @author haodong
 *
 */
public class Main {
	/**
	 * Person接口
	 * @author haodong
	 *
	 */
	static interface Person {
		public void sayHelloA();
	}
	
	/**
	 * People抽象类
	 * @author haodong
	 *
	 */
	static abstract class People {
		public abstract void sayHelloB();
		public void sayHelloC() {
			System.out.println("hello C");
		}
	}
	
	/**
	 * 验证匿名内部类可以实现接口
	 * @return
	 */
	public Person getMethod() {
		return new Person() {                     //匿名内部类实现接口
			@Override
			public void sayHelloA() {
				System.out.println("hello A");
			}
		};
	}
	
	/**
	 * 验证匿名类可以继承父类（父类可以是抽象类）
	 * @return
	 */
	public People getPeople() {
		return new People() {                      //匿名内部类继承父类
			@Override
			public void sayHelloB() {
				System.out.println("hello B");
			}
			public void sayHelloD() {
				System.out.println("hello D");
			}
		};
	}
	
	/**
	 * 测试类
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Main main = new Main();
		Person person = main.getMethod();
		Class<?> testClass = person.getClass();
		Method[] methods = testClass.getDeclaredMethods();
		for (Method method: methods) {
			System.out.println(method.getName());            //测试匿名内部类继承接口后包含的方法
		}
		
		People people = main.getPeople();
		testClass = people.getClass();
		methods = testClass.getDeclaredMethods();
		for (Method method: methods) {
			System.out.println(method.getName());          //测试匿名内部类继承父类后包含的方法
		}
		
		people.sayHelloC();
	}
}
