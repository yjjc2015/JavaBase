package haodong.net.cn.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
/**
 * Socket端操作
 * @author haodong
 *
 */
public class SocketTest {

	public static void main(String[] args) throws IOException {
		try(SocketChannel socket = SocketChannel.open(new InetSocketAddress("localhost", 9090))) {                //从localhost打开一个SocketChannel，并指定9090端口
			ByteBuffer buffer = ByteBuffer.allocate(16);                    //分配16个字节ByteBuffer空间
			StringBuilder stringBuilder = new StringBuilder();      //使用StringBuilder接收返回数据
			int count = 0;
			while ((count = socket.read(buffer)) > 0) {
				buffer.flip();                    //因为buffer写入数据后马上要对其进行写操作，所以需要把指针移动到头部，并设置limit为position值，所有用flip()方法
				stringBuilder.append(Charset.defaultCharset().decode(buffer));              //把buffer转换成String
				buffer.clear();
			}
			System.out.println(stringBuilder);
		}
	}

}
