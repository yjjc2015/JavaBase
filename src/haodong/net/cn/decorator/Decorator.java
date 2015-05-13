package haodong.net.cn.decorator;
/**
 * Decorator类，继承自Component父类
 * @author haodong
 *
 */
public class Decorator extends Component {
	protected final Component component;
	public Decorator(Component component) {
		this.component = component;
	}
	/**
	 * 每个Decorator类都要复写decorate方法
	 */
	public void decorate() {
		if (null != this.component) this.component.decorate();
	}

}
