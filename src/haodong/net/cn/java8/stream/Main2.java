package haodong.net.cn.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Stream流中reduce()方法测试
 * Created by haodong on 15-5-26.
 */
public class Main2 {
    private static List<Person> persons =
            Arrays.asList(
                    new Person("haodong", 18),
                    new Person("jikyll", 23),
                    new Person("monc", 23),
                    new Person("test", 12));

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "name is: "+name+"\n"+"age is: "+this.age;
        }
    }

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    /**
     * reduce()方法返回Optional的例子
     */
    public static void test1() {
        persons.stream()
                .reduce((p1, p2) -> {
                    return p1.age > p2.age ? p1 : p2;
                })
                .ifPresent(System.out::println);    // Pamela
    }

    /**
     * reduce()方法返回类实例的例子
     */
    public static void test2() {
        Person person = persons.stream()
                .reduce(new Person("", 0), (p1, p2) -> {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1;
                });
        System.out.println(person);
    }

    /**
     * 测试串行stream中reduce方法中的combiner方法
     */
    public static void test3() {
        Integer ageSum = persons
                .stream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
        System.out.println(ageSum);
    }

    /**
     * 测试并行stream中reduce方法中的combiner方法
     */
    public static void test4() {
        Integer ageSum = persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
    }
}
