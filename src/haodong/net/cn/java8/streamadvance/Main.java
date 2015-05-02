package haodong.net.cn.java8.streamadvance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
/**
 * 1. 对比java8中Stream流的ParallelStream和Stream方法耗时；
 * 2. 对比map类的foreach方法和entrySet方法耗时；
 * @author haodong
 *
 */
public class Main {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		Map<Integer, String> map = new HashMap<>();
		int max = 100;
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			list.add(uuid.toString());
			map.put(i, uuid.toString());
		}
		testStreamVsParallelStream(list);
		testForeachVsMapentry(map);
	}
	/**
	 * 对比java8中Stream流的ParallelStream和Stream方法耗时；
	 * @param list
	 */
	public static void testStreamVsParallelStream(List<String> list) {
		long start = System.nanoTime();
		long count = list.stream().sorted().count();
		long end = System.nanoTime();
		System.out.println("普通排序："+(end-start));
		start = System.nanoTime();
		count = list.parallelStream().sorted().count();
		end = System.nanoTime();
		System.out.println("并行排序："+(end-start));
	}
	/**
	 * 对比map类的foreach方法和entrySet方法耗时；
	 * @param map
	 */
	public static void testForeachVsMapentry(Map<Integer, String> map) {
		long start = System.nanoTime();
		map.forEach((key, value) -> {String s = value;});
		long end = System.nanoTime();
		System.out.println("foreach耗时："+(end-start));
		start = System.nanoTime();
		for (Entry<Integer, String> entry: map.entrySet()) {
			String s= entry.getValue();
		}
		end = System.nanoTime();
		System.out.println("MapEntry耗时："+(end-start));
	}
}
