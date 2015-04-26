package haodong.net.cn.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 1. 使用CountDownLatch协调各个子线程运转，当所有子线程都运行完毕后，结束主线程中的关闭线程池操作
 * 2. Executors.newFixedThreadPool(int count)方法，count属性指定线程池中的可用线程数量，和实际程序需要执行的任务量无关
 * @author haodong
 *
 */
public class Main {
	private static final int THREAD_COUNT = 10;
	public static void main(String[] args) throws InterruptedException {
		AtomicInteger count = new AtomicInteger(THREAD_COUNT);
		final CountDownLatch begin = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(THREAD_COUNT);
		ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT>>1);
		begin.countDown();
		for (int i = 0; i < THREAD_COUNT; i++) {
			Thread.sleep(2000);
			service.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("开始等待！");
					try {
						begin.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count.decrementAndGet();
					System.out.println("当前任务使用线程池中的名为： "
							+Thread.currentThread().getName()+"的线程执行，还剩"+count.get()+"个任务没有做！");
					end.countDown();
				}
			});
		}
		end.await();                     //等待所有子线程运行完毕
		System.out.println(THREAD_COUNT+"个任务已经执行完成！");
		service.shutdown();         //关闭线程池
	}
}
