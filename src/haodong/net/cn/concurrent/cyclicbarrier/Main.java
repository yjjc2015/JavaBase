package haodong.net.cn.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * CyclicBarrier可以理解为可循环屏障，和CountDownLatch的不同在于这种方式是可循环的
 * 下面的例子中很好的诠释了”循环"的概念
 * 当等待线程数到达五个时，即执行一次操作，然后把等待线程数置位0，重复之前的行为
 * @author haodong
 *
 */
public class Main {
	private static final int NUM = 10; 
	private static final int BARRIER = 5;
	private int[] numArray = new int[NUM];
	private static AtomicInteger cycle = new AtomicInteger(0);
	private final CyclicBarrier barrier = new CyclicBarrier(BARRIER, new Runnable() {
		//这个匿名函数是指满足屏障条件时程序要做的操作
		@Override
		public void run() {
			int sum = 0;
			System.out.println("收集工作完成！！开始计算数组和");
			for (int i = 0; i < BARRIER; i++) {
				sum += numArray[i];
			}
			System.out.println("总和是："+sum);
		}
	});
	public void collection() {
		for (int i = 0; i < NUM; i++) {
			final int j = i;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					numArray[j-cycle.get()*BARRIER] = j+1;
					if (barrier.getNumberWaiting()+1 == BARRIER) {
						System.out.println("已经满足条件，开始计算数组和！");
						cycle.addAndGet(1);
					}
					try {
						System.out.println("线程： "+Thread.currentThread().getName()+"开始等待");
						barrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.collection();;
	}
}
