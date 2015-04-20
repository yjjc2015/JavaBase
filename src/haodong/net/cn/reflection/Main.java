package haodong.net.cn.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 反射相关的函数介绍,包含通过类名获得类实例, 获得构造函数, 调用private属性和方法等.
 * @author haodong
 *
 * @param <T>
 */
public class Main<T> {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class<?> sample = Class.forName("haodong.net.cn.reflection.People");                                                                                                //通过反射方法forName得到sample类
		Class<?>[] argsClass = new Class[]{String.class, String.class, int.class, int.class};                                                                        //构造函数参数类型
		Object[] sampleArgs = new Object[]{"haodong", "1", 24, 1};                                                                                                                       //构造函数传入参数
		Constructor<?> con = sample.getConstructor(argsClass);                                                                                                                           //通过反射获得构造函数
		People people = (People)con.newInstance(sampleArgs);                                                                                                                           //得到People类实例,非private方法可以直接使用People实例调用,private方法还得用反射获得
		Method methodReflection1 = sample.getDeclaredMethod("sayHelloPrivate", 
																																	new Class<?>[]{String.class, int.class});                              //获得sayHelloPrivate方法,通过第二个参数对通过函数进行筛选
		Method methodReflection2 = sample.getDeclaredMethod("sayHelloPrivate", 
																																	new Class<?>[]{String.class, String.class});
		Method methodReflection3 = sample.getDeclaredMethod("sayHelloPublic");
		methodReflection1.setAccessible(true);                                                                                                                                                            //设定对private方法可访问
		methodReflection2.setAccessible(true);																																							   
		Object[] inputArgs1 = new Object[]{"haodong", 2};																																									
		Object[] inputArgs2 = new Object[]{"haodong", "jikyll"};
		methodReflection1.invoke(people, inputArgs1);																																			  //通过invoke方法调用反射方法,第一个参数为上面程序中反射得到的
																																																												 //People类实例,第二个参数是传入方法的具体参数
		methodReflection2.invoke(people, inputArgs2);
		methodReflection3.invoke(people);

		Field field1 = sample.getDeclaredField("name");                                                                                                                                         //获得名为name的Field
		field1.setAccessible(true);																																														//设定可访问private声明的字段
		String name = (String) field1.get(people);																																						//获得name属性的值
		System.out.println("name from reflection is "+name);
		field1.set(people, "jikyll");																																														//通过重新设定name属性的值
		name = (String) field1.get(people);																																										
		System.out.println("name from reflection is "+name);
	}
}
