package haodong.net.cn.decorator;
/**
 * 装饰者测试类
 * 1. BIO就是基于装饰者模式实现的
 * @author haodong
 *
 */
public class Main {
	public static void main(String[] args) {
		Person person = new Person();
		TShirt tshirt = new TShirt(person);
		Pants pants = new Pants(tshirt);
		Hat hat = new Hat(pants);
		hat.decorate();
	}
}
