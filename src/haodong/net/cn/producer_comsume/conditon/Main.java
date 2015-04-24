package haodong.net.cn.producer_comsume.conditon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
		private static int count = 0;
		Lock lock = new ReentrantLock();
		Condition full = lock.newCondition();
		Condition empty = lock.newCondition();
		public void consume() {
			lock.tryLock();
			try {
				while (list.size() <= 0) {
					empty.await();
				}
				list.remove(list.size()-1);
				full.signal();
			} catch (InterruptedException e) {
				System.out.println("consumer is interrupted!");
			} finally {
				lock.unlock();
			}
		}
		public void produce() {
			lock.tryLock();
			try {
				while (list.size() >= 10) {
					full.await();
				}
				list.add(new Product("producer", ++count));
				empty.signal();
			} catch(InterruptedException ie){
                System.out.println("producer is interrupted!");
			} finally {
				lock.unlock();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
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
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
			new Thread(new Consume(repo)).start();
		}
	}
}
