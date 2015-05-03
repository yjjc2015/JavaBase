package haodong.net.cn.annotation;

import java.lang.reflect.Field;
/**
 * 使用反射获取Annotation信息
 * @author haodong
 *
 */
public class FruitInfoUtil {
	public static void getInfo(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		for (Field field: fields) {
			if (field.isAnnotationPresent(FruitName.class)) {
				FruitName fruitName = field.getAnnotation(FruitName.class);
				System.out.println(fruitName.value());
			} else if (field.isAnnotationPresent(FruitColor.class)) {
				FruitColor fruitColor = field.getAnnotation(FruitColor.class);
				System.out.println(fruitColor.fruitColor());
			} else {
				System.out.println("others");
			}
		}
	}
}
