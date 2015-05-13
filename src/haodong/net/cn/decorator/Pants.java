package haodong.net.cn.decorator;
/**
 * 裤子类，继承自Decorator父类
 * @author haodong
 *
 */
public class Pants extends Decorator {

	public Pants(Component component) {
		super(component);
	}
	@Override
	public void decorate() {
		if (null != this.component) {
			this.component.decorate();
			System.out.println("穿上裤子！！");
		}
	}
}
