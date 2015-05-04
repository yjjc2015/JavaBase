package haodong.net.cn.dynamic.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
/**
 * 介绍动态调用点CallSite的用法
 * 1. 动态调用点有三种实现，分别是：ConstantCallSite, MutableCallSite和VolatileCallSite
 * 2. 使用dynamicInvoker方法获取一个方法句柄，测试动态调用点
 * @author haodong
 *
 */
public class Main2 {
	public static void main(String[] args) throws Throwable {
		MethodType type = MethodType.methodType(int.class, int.class, int.class);
		MutableCallSite callSite = new MutableCallSite(type);              //方法句柄类型作为动态调用点参数
		MethodHandle mh = callSite.dynamicInvoker();       //根据动态调用点获取MethodHandle对象
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		
		MethodHandle mhMax = lookup.findStatic(Math.class, "max", type);         //定义具体方法句柄
		MethodHandle mhMin = lookup.findStatic(Math.class, "min", type);         //定义具体方法句柄
		
		callSite.setTarget(mhMax);
		int res = (int)mh.invokeExact(10, 5);
		System.out.println("最大值为："+res);
		callSite.setTarget(mhMin);             //设置具体方法句柄
		res = (int)mh.invokeExact(10, 5);
		System.out.println("最小值为："+res);
	}
}
