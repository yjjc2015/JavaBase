package haodong.net.cn.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 水果颜色注释
 * @author haodong
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
	public enum Color {BLUE, RED, GREEN};
	Color fruitColor() default Color.GREEN;           //这里的fruitColor()方法对应申明中的fruitColor属性
}
