package haodong.net.cn.iterable;

import java.io.Serializable;
/**
 * User类
 * @author haodong
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String passwd;
	public User(String name, String passwd) {
		this.name = name;
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String toString() {
		return "name is: " + this.name + ", passwd is: " + this.passwd;
	}
}
