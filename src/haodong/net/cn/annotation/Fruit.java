package haodong.net.cn.annotation;

import haodong.net.cn.annotation.FruitColor.Color;

public class Fruit {
	@FruitName(value="apple")          //对应自定义申明中等的value方法
	private String name;
	@FruitColor(fruitColor=Color.GREEN)         //对应自定义申明中的fruitColor方法
	private String color;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
