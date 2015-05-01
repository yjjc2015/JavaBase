package haodong.net.cn.concurrent.threadlocal;
/**
 * 使用ThreadLocal变量声明各个线程固有变量
 * @author haodong
 *
 */
public class Main {
	static class MyRunnable implements Runnable {
		private ThreadLocal<String> thread = new ThreadLocal<String>() {                 //初始化ThreadLocal变量
			@Override protected String initialValue() {
		        return "This is the initial value";                        //如果不使用set函数设定ThreadLocal变量值，则初始为initialValue（）函数返回值
		    }
		};
		@Override
		public void run() {
			thread.set(Thread.currentThread().getName());
			System.out.println(thread.get());
		}
		
	}
	public static void main(String[] args) {
		MyRunnable runnable = new MyRunnable();
		Thread thread1 = new Thread(runnable, "thread1");
		Thread thread2 = new Thread(runnable, "thread2");
		thread1.start();
		thread2.start();
	}
}
