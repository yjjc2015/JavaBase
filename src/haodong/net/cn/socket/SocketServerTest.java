package haodong.net.cn.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * SocketServer端操作
 * @author haodong
 *
 */
public class SocketServerTest {
	private static class Test<V> implements Callable<V> {                  //实现Callable接口
		private SocketChannel socket;
		public Test(SocketChannel socket) {                    //构造函数传入SocketChannel实例，即为每次SocketClient端的访问进行处理
			this.socket = socket;
		}
		@Override
		public V call() throws Exception {
			socket.write(ByteBuffer.wrap("hello world".getBytes("UTF-8")));               //把String转换成buffer，往客户端Socket传入"hello world"
			return (V)"success";
		}
	
	}
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ServerSocketChannel serverSocket = ServerSocketChannel.open();      //使用ServerSocketChannel方式实现socket数据传输
		serverSocket.bind(new InetSocketAddress("localhost", 9090));                //ServerSocketChannel绑定到localhost的9090端口
		ExecutorService executor = Executors.newFixedThreadPool(5);               //使用线程池管理客户端Socket
		while (true) {
			try (SocketChannel socketChannel = serverSocket.accept()) {
					Future<String> future = executor.submit(new Test<String>(socketChannel));           //接收Future返回值
					System.out.println(future.get());
			}
		}
	}
}
