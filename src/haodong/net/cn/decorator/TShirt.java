package haodong.net.cn.decorator;
/**
 * TShirt类，继承自Decorator父类
 * @author haodong
 *
 */
public class TShirt extends Decorator {

	public TShirt(Component component) {
		super(component);
	}
	@Override
	public void decorate() {
		if (null != this.component) {
			this.component.decorate();
			System.out.println("穿上T恤！！");
		}
	}
}
