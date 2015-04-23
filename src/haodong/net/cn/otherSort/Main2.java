package haodong.net.cn.otherSort;

/**
 * 插入排序过程
 * 
 * @author haodong
 *
 */
public class Main2 {
	public static void main(String[] args) {
		int[] a = new int[]{30, 4, 2, 20, 40, 50, 70, 33, 22, 9};
		sort(a);
		for (int value: a) System.out.println(value);
	}
	public static void sort(int[] a) {
		int len = a.length;
		if (len <= 1) return;
		for (int i = 1; i < len; i++) {
			int tmp = a[i];
			int j = i;
			while (j-1 >= 0 && tmp < a[j-1]) {
				a[j] = a[j-1];
				j--;
			}
			a[j] = tmp;
		}
	}
}
