package haodong.net.cn.quicksort;
/**
 * 严蔚敏教程中的快速排序
 * @author haodong
 *
 */
public class Main1 {
	public static void main(String[] args) {
		int[] a = new int[10000];
		for (int i = 0; i < 10000; i++) {
			a[i] = i;
		}
		int left = 0;
		int right = a.length-1;
		long start = System.currentTimeMillis();
		quickSort(a, left, right);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static void quickSort(int[] a, int left, int right) {
		if (left < right) {                  //这一步是问题关键，必须在每次开始的时候加入判断，当left < right时，才开始递归内容
			int mid = partition(a, left, right);
			quickSort(a, left, mid-1);
			quickSort(a, mid+1, right);
		}
	}
	public static int partition(int[] a, int left, int right) {
		int pivot = a[left];           //使用数组的left元素作为pivot变量
		while (left < right) {
			while (left < right && a[right] >= pivot) right--;
			if (left < right) a[left++] = a[right];
			while (left < right && a[left] < pivot) left++;
			if (left < right) a[right--] = a[left];
		}
		a[left] = pivot;
		return left;
	}
}
