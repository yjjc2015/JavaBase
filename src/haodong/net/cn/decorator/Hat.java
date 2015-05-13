package haodong.net.cn.decorator;
/**
 * 帽子类，继承自Decorator父类
 * @author haodong
 *
 */
public class Hat extends Decorator {

	public Hat(Component component) {
		super(component);
	}
	@Override
	public void decorate() {
		if (null != this.component) {
			this.component.decorate();
			System.out.println("戴上帽子！！");
		}
	}
}
