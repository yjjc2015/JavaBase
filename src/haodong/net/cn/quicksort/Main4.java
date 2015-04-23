package haodong.net.cn.quicksort;

import java.util.Random;

/**
 * 严蔚敏教程中的快速排序+三数取中值优化
 * 其他快速排序算法都可以利用三数取中优化快速排序
 * @author haodong
 *
 */
public class Main4 {
	public static void main(String[] args) {
		int[] a = new int[10000];
		for (int i = 0; i < 10000; i++) {
			a[i] = 10000-i;
		}
		int left = 0;
		int right = a.length-1;
		long start = System.currentTimeMillis();
		quickSort(a, left, right);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		for (int value: a) {
			System.out.println(value);
		}
	}
	public static void quickSort(int[] a, int left, int right) {
		if (left < right) {                  //这一步是问题关键，必须在每次开始的时候加入判断，当left < right时，才开始递归内容
			int mid = partition(a, left, right);
			quickSort(a, left, mid-1);
			quickSort(a, mid+1, right);
		}
	}
	public static int partition(int[] a, int left, int right) {
		int res = convert(a, left, right);          //获取中值的下标
		swap(a, res, left);                             //三数中值与left值交换，三数中值作为pivot值
		int pivot = a[left];
		while (left < right) {
			while (left < right && a[right] >= pivot) right--;
			if (left < right) a[left++] = a[right];
			while (left < right && a[left] < pivot) left++;
			if (left < right) a[right--] = a[left];
		}
		a[left] = pivot;
		return left;
	}
	/**
	 * 三数取中函数，函数思路如下：
	 * 1. 在数组上下限之间随机取三个值；
	 * 2. 取这三个数的中值，返回中值下标
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	public static int convert(int[]a, int left, int right) {
		Random random = new Random();
		int len = right-left;
		int i, j, k;
		i = left+random.nextInt(len);
		j = left+random.nextInt(len);
		k = left+random.nextInt(len);
		if (a[i] > a[j]) swap(a, i, j);
		if (a[j] > a[k]) swap(a, j, k);
		int tmp = a[i] > a[j]? i: j;
		return tmp;
	}
	public static void swap(int[] a, int left, int right) {
		int tmp = a[left];
		a[left] = a[right];
		a[right] = tmp;
	}
}
