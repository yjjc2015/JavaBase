package haodong.net.cn.otherSort;
/**
 * 堆排序算法
 * @author haodong
 *
 */
public class Main1 {
	private static int len;
	public static void main(String[] args) {
		int[] a = new int[]{30, 4, 2, 20, 40, 50, 70, 33, 22, 9};
		len = a.length;
		construct(a);
		for (int i = 0; i < a.length; i++) {
			swap(a, 0, len-1);
			adjust(a);
		}
		for (int value: a) System.out.println(value);
	}
	/**
	 * 调整过程，时间复杂度为O(logn)
	 * @param a
	 */
	public static void adjust(int[] a) {
		int tmp = 0;
		len--;
		while (tmp < len) {
			int right = (tmp+1)<<1;
			int left = right-1;
			if (right < len) {
				int minTmp = a[left] < a[right]? left: right;
				if (a[tmp] > a[minTmp]) {
					swap(a, tmp, minTmp);
					tmp = minTmp;
					continue;
				}
				break;
			} else {
				if (left < tmp && a[tmp] > a[left]) {
					swap(a, tmp, left);
					tmp = left;
					continue;
				}
				break;
			}
		}
	}
	/**
	 * 建堆过程，时间复杂度为O(n)
	 * @param a
	 */
	public static void construct(int[] a) {
		int start = (len>>1)-1;
		while (start >= 0) {
			int tmp = start;
			while (tmp < len) {
				int right = (tmp+1)<<1;
				int left = right-1;
				if (right < len) {
					int minTmp = a[left] < a[right]? left: right;
					if (a[tmp] > a[minTmp]) {
						swap(a, tmp, minTmp);
						tmp = minTmp;
						continue;
					}
					break;
				} else {
					if (left < tmp && a[tmp] > a[left]) {
						swap(a, tmp, left);
						tmp = left;
						continue;
					}
					break;
				}
			}
			start--;
		}
	}
	
	public static void swap(int[] a, int left, int right) {
		int tmp = a[left];
		a[left] = a[right];
		a[right] = tmp;
	}
}
