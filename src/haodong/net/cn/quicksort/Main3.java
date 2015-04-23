package haodong.net.cn.quicksort;

/**
 * 编程珠玑上的快速排序
 * @author haodong
 *
 */
public class Main3 {
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
	}
	public static void quickSort(int[] a, int left, int right) {
		if (left < right) {
			int mid = partition(a, left, right);
			quickSort(a, left, mid-1);
			quickSort(a, mid+1, right);
		}
	}
	public static int partition(int[] a, int left, int right) {
		int pivot = a[left];
		int i = left;
		int j = right+1;
		while (true) {
			do j--;
			while (a[j] > pivot);
			do i++;
			while (i <= j && a[i] < pivot);
			if (i >= j) break;
			swap(a, i, j);
		}
		swap(a, left, j);
		return j;
	}
	public static void swap(int[] a, int left, int right) {
		int tmp = a[left];
		a[left] = a[right];
		a[right] = tmp;
	}
}
