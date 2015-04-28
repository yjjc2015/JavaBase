package haodong.net.cn.producer_consumer.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
/**
 * 使用Semophore类模拟生产者消费者模式
 * 1. 利用信号量机制控制存储空间是否满足条件
 * 2. 使用互斥锁防止对存储空间的读写操作重合
 * @author haodong
 *
 */
public class Main {
	static class Product {
		private String id;
		private String name;
	}

	static class Repo {
		private final Semaphore semaphore = new Semaphore(1);          //定义存储互斥条件（互斥锁）
		private final Semaphore empty = new Semaphore(0);                  //定义存储空间为空信号量
		private final Semaphore full = new Semaphore(10);                    //定义存储空间已满的信号量
		private List<Product> list = new ArrayList<>();
		private int count = 0;

		public void consume() {
			try {
				empty.acquire();           //存储空间不为空信号量减一
				semaphore.acquire();    //申请互斥锁
				list.remove(list.size() - 1);
				count--;
				System.out.println("消费了一个商品，还剩： " + count + "个商品！！");
			} catch (InterruptedException e) {
				System.out.println("中断");
			} finally {
				semaphore.release();     //释放互斥锁
				full.release();            //释放存储空间满信号量（信号量增加一）
			}
		}

		public void produce() {
			try {
				full.acquire();          //存储空间满信号量减一
				semaphore.acquire();
				list.add(new Product());
				count++;
				System.out.println("生产了一个商品，还剩： " + count + "个商品！！");
			} catch (InterruptedException e) {
				System.out.println("中断");
			} finally {
				semaphore.release();
				empty.release();       //释放存储空间为空信号量(信号量增加一)
			}
		}
	}

	static class Producer implements Runnable {

		Repo repo = new Repo();

		public Producer(Repo repo) {
			this.repo = repo;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repo.produce();
			}
		}

	}

	static class Consumer implements Runnable {
		Repo repo = new Repo();

		public Consumer(Repo repo) {
			this.repo = repo;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repo.consume();
			}
		}

	}

	public static void main(String[] args) {
		Repo repo = new Repo();
		for (int i = 0; i < 5; i++) {
			new Thread(new Producer(repo)).start();
			new Thread(new Consumer(repo)).start();
		}
	}
}
