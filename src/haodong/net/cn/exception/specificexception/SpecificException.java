package haodong.net.cn.exception.specificexception;
/**
 * 自定义异常类SpecificException
 * @author haodong
 *
 */
public class SpecificException extends Exception {

	private static final long serialVersionUID = 1L;
	private final int value;
	public SpecificException(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
