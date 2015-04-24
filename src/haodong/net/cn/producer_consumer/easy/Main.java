package haodong.net.cn.producer_consumer.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * 说明一：程序使用四个内部类，分别是Apple（商品类）、Repo（仓库类）、Producer（生产者类）、Consumer（消费者类）
 * 
 * 说明二：不使用concurrence实现生产者消费者（注：为了方便测试，把所有的类都作为子类写在一个类中，
 * 而且使用了大量的static 变量，实际编程中应该减少static使用，过多static变量，减慢GC）
 * @author haodong
 *
 */
public class Main {
	/**
	 * 苹果作为商品类，只有一个属性id
	 * @author haodong
	 *
	 */
	private static class Apple {
		private final String id;
		public Apple(String id) {
			this.id = id;
		}
	}
	/**
	 * 仓库类，使用list存储商品，使用wait和notifyAll方法限制list。
	 * @author haodong
	 *
	 */
	private static class Repo {
		private int count = 0;
		private static List<Apple> list = new LinkedList<>();
		public void comsume() throws InterruptedException {
			synchronized (list) {
				while (list.isEmpty()) {
					list.wait();
				}
				list.remove(list.remove(list.size()-1));
				count--;
				System.out.println("消费了一个苹果！！还剩下： "+count+"个苹果！！");
				list.notifyAll();                   //这里使用notifyAll而不使用notify，使用程序性能换取程序正确性，
														 //notify可能造成资源没有被利用上
			}
		}
		public void produce() throws InterruptedException {
			synchronized (list) {
				while (list.size() >= 10) {
					list.wait();
				}
				count++;
				list.add(new Apple(String.valueOf(count)));
				System.out.println("生产了一个苹果！！还剩下： "+count+"个苹果！！");
				list.notifyAll();
			}
		}
	}
	private static class Producer implements Runnable {
		private Repo repo;
		private static volatile boolean isStop;           //使用volatile标识isStop变量，维护isStop变量的可见性
		public Producer(Repo repo) {
			this.repo = repo;
		}
		@Override
		public void run() {
			while (!isStop) {
				try {
					repo.produce();
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		public static void stop() {
			isStop = true;
		}
	}
	private static class Comsumer implements Runnable {
		private Repo repo;
		public Comsumer(Repo repo) {
			this.repo = repo;
		}
		@Override
		public void run() {
			while (true) {
				try {
					repo.comsume();
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Repo repo = new Repo();
		for (int i = 0; i < 5; i++) {
			new Thread(new Producer(repo)).start();
			new Thread(new Comsumer(repo)).start();
		}
		Thread.sleep(20000);
		Producer.stop();
	}
	
}
