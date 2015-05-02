package haodong.net.cn.java8.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * java8中的stream支持java.util.Collection中的list和set数据结构
 * @author haodong
 *
 */
public class Main {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("abc");
		list.add("bac");
		list.add("acb");
		list.add("bbc");
		list.add("pcc");
		list.add("icd");
		list.add("qpc");
		Main main = new Main();
		main.testFilter(list);
		main.testSorted(list);
		main.testMap(list);
		main.testMatch(list);
		main.testCount(list);
		main.testReduce(list);
	
	}
	/**
	 * 测试filter函数
	 * @param list
	 */
	public void testFilter(List<String> list) {
		System.out.println("测试filter函数");
		list.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
	}
	/**
	 * 测试sorted函数
	 * @param list
	 */
	public void testSorted(List<String> list) {
		System.out.println("测试sorted函数");
		list.stream().filter((s) -> s.startsWith("a")).sorted().forEach(System.out::println);
	}
	/**
	 * 测试map函数
	 * @param list
	 */
	public void testMap(List<String> list) {
		System.out.println("测试map函数");
		list.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
	}
	/**
	 * 测试match函数
	 * @param list
	 */
	public void testMatch(List<String> list) {
		System.out.println("测试match函数");
		boolean res1 = list.stream().anyMatch((s) -> s.startsWith("a"));
		boolean res2 = list.stream().allMatch((s) -> s.startsWith("a"));
		boolean res3 = list.stream().noneMatch((s) -> s.startsWith("a"));
		System.out.println("res1:"+res1);
		System.out.println("res2:"+res2);
		System.out.println("res3:"+res3);
	}
	/**
	 * 测试count函数
	 * @param list
	 */
	public void testCount(List<String> list) {
		System.out.println("测试count函数");
		long res = list.stream().filter((s) -> s.startsWith("a")).count();
		System.out.println("res: "+res);
	}
	/**
	 * 测试reduce函数
	 * @param list
	 */
	public void testReduce(List<String> list) {
		System.out.println("测试reduce函数");
		list.stream().sorted().reduce((a, b) -> a+"#"+b).ifPresent(System.out::println);
	}
}
