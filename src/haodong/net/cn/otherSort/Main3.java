package haodong.net.cn.otherSort;
/**
 * 冒泡排序过程
 * @author haodong
 *
 */
public class Main3 {
	public static void main(String[] args) {
		int[] a = new int[]{30, 4, 2, 20, 40, 50, 70, 33, 22, 9};
		sort(a);
		for (int value: a) System.out.println(value);
	}
	public static void sort(int[] a) {
		int len = a.length;
		boolean flag = false;
		for (int i = 0; i < len; i++) {
			flag = false;
			for (int j = len-1; j > i; j--) {
				if (a[j] < a[j-1]) {
					swap(a, j, j-1);
					flag = true;
				}
			}
			if (!flag) return;
		}
	}
	public static void swap(int[] a, int left, int right) {
		int tmp = a[left];
		a[left] = a[right];
		a[right] = tmp;
	}
}
