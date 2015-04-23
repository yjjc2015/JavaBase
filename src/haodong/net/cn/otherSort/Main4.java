package haodong.net.cn.otherSort;
/**
 * 归并排序实现过程
 * @author haodong
 *
 */
public class Main4 {
	public static void main(String[] args) {
		int[] a = new int[]{30, 4, 2, 20, 40, 50, 70, 33, 22, 9};
		sort(a, 0, a.length-1);
		for (int value: a) System.out.println(value);
	}
	public static void sort(int[] a, int left, int right) {
		if (left < right) {
			int mid = (left+right)/2;
			sort(a, left, mid);
			sort (a, mid+1, right);
			mergArray(a, left, mid, right);
		}
	}
	/**
	 * 归并的过程
	 * @param a
	 * @param left
	 * @param mid
	 * @param right
	 */
	public static void mergArray(int[] a, int left, int mid, int right) {
		int ll = left;
		int rr = right;
		int lr = mid;
		int rl = mid+1;
		int len = right-left+1;
		int[] tmp = new int[len];
		int index = 0;
		while (ll <= lr && rl <= rr) {
			if (a[ll] < a[rl]) tmp[index++] = a[ll++];
			else tmp[index++] = a[rl++];
		}
		while (ll <= lr) tmp[index++] = a[ll++];
		while (rl <= rr) tmp[index++] = a[rl++];
		for (int i = left, j = 0; i <= right; i++) {
			a[i] = tmp[j++];
		}
	}
}
