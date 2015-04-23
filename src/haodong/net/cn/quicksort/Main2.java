package haodong.net.cn.quicksort;
/**
 * 算法导论中最基本的快速排序方法
 * @author haodong
 *
 */
public class Main2 {
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
	/**
	 * 此种partition方法思路是：
	 * 1. 设定两个指针，i指针初始为-1， j指针初始为0，以j指针为基准扫描a数组；
	 * 2. 选取最右边节点值为pivot；
	 * 3. 每次扫描数组值，与pivot进行比较
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	public static int partition(int[] a, int left, int right) {
		int pivot = a[right];
		int i = left-1;
		int j = left;
		while (j < right) {
			if (a[j] <= pivot) {
				i++;
				swap(a, i, j);
			}
			j++;
		}
		swap(a, i+1, j);
		return i+1;
	}
	public static void swap(int[] a, int left, int right) {
		int tmp = a[left];
		a[left] = a[right];
		a[right] = tmp;
	}
}
