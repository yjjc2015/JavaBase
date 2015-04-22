package haodong.net.cn.serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
/**
 * 本实例验证四个问题：
 * 1. transient和static 变量默认状态下不能进行序列化和反序列化；
 * 2. 可以继承Externalizable接口并实现writeExternal和readExternal方法
 * 或者继承Serializable接口并"实现"readObject和writeObject接口来实现可以序列化和反序列化transient和static变量；
 * 3. 在反序列化之前，JVM调用readResovle方法；
 * 4. 继承Externalizable接口，需要加入无参构造函数，不然反序列化时会报错；
 * @author haodong
 *
 */
public class Main {
	/**
	 * 这里也可以继承Serialable接口，并且加入readObject和writeObject方法即可
	 * @author haodong
	 *
	 */
	static class User implements Externalizable {
		private static final long serialVersionUID = 8294180014912103005L;                //初始化uuid
		
		private String username;                     //普通变量
		private transient String passwd;       //transient变量
		private static String others;               //static变量
		public User() {}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public static String getOthers() {
			return others;
		}
		public static void setOthers(String others) {
			User.others = others;
		}
		/**
		 * 重写writeExternal方法，人工加入对transient变量和static变量的操作，使得其可以进行序列化操作
		 */
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(username);
			out.writeObject(passwd);
			out.writeObject(others);
		}
		/**
		 * 重写readExternal方法，人工加入对transient变量和static变量的操作，使得其可以进行反序列化操作
		 */
		@Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
			username = (String) in.readObject();
			passwd = (String) in.readObject();
			others = (String) in.readObject();
		}
		/**
		 * 反序列化时JVM会首先调用该方法
		 * @return
		 */
		private Object readResolve() {  
	        System.out.println("readResolve invoked");  
	        return this;  
	    }
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("haodong");
		user.setPasswd("123456");
		User.setOthers("hello world");
		System.out.println("before serialization: ");
		System.out.println(user.getUsername());
		System.out.println(user.getPasswd());
		System.out.println(User.getOthers());
		System.out.println("***********************");
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("user.txt"));
			os.writeObject(user);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		User.others = "others";             //尝试改变others值，如果反序列化之后的值仍然是之前的值，说明序列化和反序列化成功
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.txt"));
			User user1 = (User)ois.readObject();
			ois.close();
			System.out.println("after serialization: ");
			System.out.println(user1.getUsername());
			System.out.println(user1.getPasswd());
			System.out.println(User.getOthers());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
