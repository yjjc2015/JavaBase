package haodong.net.cn.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
/**
 * 使用forjoin框架计算数组的最大值
 * @author haodong
 *
 */
public class Main {
	private static final int RANGE_LENGTH = 4;
	static class MaxValueTask extends RecursiveTask<Integer> {
		private static final long serialVersionUID = 1L;
		private final int start;
		private final int end;
		private final int[] array;
		public MaxValueTask(int[] array, int start, int end) {
			this.start = start;
			this.end = end;
			this.array = array;
		}
		@Override
		protected Integer compute() {
			int max = Integer.MIN_VALUE;
			if (end-start < RANGE_LENGTH) {
				for (int i = start; i <= end; i++) {
					if (array[i] > max) {
						max = array[i];
					}
				}
			} else {
				int mid = (start+end)/2;
				MaxValueTask lowTask = new MaxValueTask(array, start, mid);
				MaxValueTask highTask = new MaxValueTask(array, mid+1, end);
				lowTask.fork();
				highTask.fork();
				max = Math.max(max, lowTask.join());
				max = Math.max(max, highTask.join());
			}
			return max;
		}
	}
	public static void main(String[] args) {
		int[] a = new int[]{50, 40, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		MaxValueTask maxTask = new MaxValueTask(a, 0, a.length-1);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		int res = forkJoinPool.invoke(maxTask);
		System.out.println("最大值为： "+res);
	}
}
