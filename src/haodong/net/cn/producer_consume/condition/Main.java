package haodong.net.cn.producer_consume.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 使用了可重入锁重写了生产者消费者模型
 * @author haodong
 *
 */
public class Main {
	static class Product {
		private String name;
		private int id;
		public Product(String name, int id) {
			this.name = name;
			this.id = id;
		}
	}
	static class Repo {
		private List<Product> list = new ArrayList<>();
		private static int count = 0;            //统计商品的个数
		private final int MAX = 10;              //定义仓库最多商品数量
		private final Lock lock = new ReentrantLock();                //定义可重入锁
		private final Condition full = lock.newCondition();          //生产者条件
		private final Condition empty = lock.newCondition();     //消费者条件
		public void consume() {
			if (lock.tryLock()) {              //这里使用tryLock()，只有当锁可用的情况下才获得锁，不然代码继续执行
													   //不同于lock()方法，lock()方法是指：如果遇到锁被其他进程占用，则该线程一直等待下去
				try {
					while (list.size() <= 0) {
						empty.await();
					}	
					list.remove(list.size()-1);
					full.signal();
					System.out.println("消费了一个产品，还剩： " + list.size() + "个产品！");
				} catch (InterruptedException e) {
					System.out.println("consumer is interrupted!");
				} finally {
					lock.unlock();         //只有当取得了锁才能解开锁
				}
			}
		}
		public void produce() {
			if (lock.tryLock()) {
				try {
					while (list.size() >= MAX) {
						full.await();
					}
					list.add(new Product("producer", ++count));
					empty.signal();
					System.out.println("生产了一个产品，还剩： " + list.size() + "个产品！");
				} catch(InterruptedException ie){
	                System.out.println("producer is interrupted!");
				} finally {
					lock.unlock();
				}
			}
		}
	}
	static class Producer implements Runnable {
		private Repo repo;
		public Producer(Repo repo) {
			this.repo = repo;
		}
		public void run() {
			while (true) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println("意外中断停止！");
				}
				repo.produce();
			}
		}
	}
	static class Consume implements Runnable {
		private Repo repo;
		public Consume(Repo repo) {
			this.repo = repo;
		}
		public void run() {
			while (true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("意外中断停止！");
				}
				repo.consume();
			}
		}
	}
	public static void main(String[] args) {
		Repo repo = new Repo();
		for (int i = 0; i < 5; i++) {
			new Thread(new Producer(repo)).start();
			new Thread(new Consume(repo)).start();
		}
	}
}
