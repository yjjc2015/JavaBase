package haodong.net.cn.exception.specificexception;
/**
 * 测试自定义异常类：
 * 1. java语言Throwable类下面有两个子类分别是Exception和Error，分别代表异常和错误。
 * 2. Exception下又分为IOException和RuntimeException（运行时异常）
 * @author haodong
 *
 */
public class Main {
	private static final int TEST = 10;
	public static void main(String[] args) {
		try {
			testException();
		} catch (SpecificException e) {                     //SecificException为自定义异常类
			System.out.println("异常信息为："+e.getValue());           //e.getValue()方法返回初始化异常时传入的参数值
			e.printStackTrace();
		}
	}
	public static void testException() throws SpecificException {
		System.out.println("正在测试throw Exception方法");
		throw new SpecificException(TEST);         //抛出异常
	}
}
