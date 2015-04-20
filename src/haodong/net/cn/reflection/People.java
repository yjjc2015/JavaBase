package haodong.net.cn.reflection;

public class People {
	private String name;
	private String id;
	private int age;
	private int sex;
	public People (String name, String id, int age, int sex) {
		this.name = name;
		this.id = id;
		this.age = age;
		this.sex = sex;
	}
	public void sayHello() {
		System.out.println(name+" says hello to everyone!");
	}
	public void sayHelloPublic() {
		System.out.println("hello world");
	}
	private void sayHelloPrivate(String test1, String test2) {
		System.out.println("this is a private method!");
		System.out.println("the type of second args is String");
	}
	private void sayHelloPrivate(String test1, int test2) {
		System.out.println("this is a private method!");
		System.out.println("the type of second args is int");
	}
}
