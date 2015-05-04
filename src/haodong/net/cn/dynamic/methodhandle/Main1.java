package haodong.net.cn.dynamic.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
/**
 * MethodHandle的基本用法
 * @author haodong
 *
 */
public class Main1 {
	public static void main(String[] args) throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType type = MethodType.methodType(String.class, int.class, int.class);      //参数分别是返回值类型，方法参数类型
		MethodHandle mh = lookup.findVirtual(String.class, "substring", type);     //参数分别是接收对象，方法名字，MethodType对象
		String res = (String)mh.invokeExact("Hello world", 1, 4);     //当方法句柄类型和方法声明类型完全一致时，invokeExact等价于invoke
		//String res = (String)mh.invoke("Hello world", 1, 4);
		System.out.println(res);
	}
}
