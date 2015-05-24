package haodong.net.cn.parallel.test;

import java.util.concurrent.ExecutionException;

import static haodong.net.cn.parallel.ExecutorTest.getResultByExecutor;
import static haodong.net.cn.parallel.NormalTest.getResultByNormal;
import static haodong.net.cn.parallel.StreamTest.getResultByStream;
import static haodong.net.cn.parallel.ThreadTest.getResultByThread;

/**
 * Created by haodong on 15-5-24.
 */
public class Test {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int res = getResultByNormal();
        long end = System.currentTimeMillis();
        System.out.println("运算结果为：" + res + " 使用单线程: " + (end - start));

        //使用普通多线程寻找1到N的质数个数
        start = System.currentTimeMillis();
        res = getResultByThread();
        end = System.currentTimeMillis();
        System.out.println("运算结果为:" + res + " 使用多线程：" + (end - start));
        //使用线程池寻找1到N的质数个数
        start = System.currentTimeMillis();
        res = getResultByExecutor();
        end = System.currentTimeMillis();
        System.out.println("运算结果为：" + res + " 使用线程池：" + (end - start));
        //使用流运算
        start = System.currentTimeMillis();
        res = getResultByStream();
        end = System.currentTimeMillis();
        System.out.println("运算结果为：" + res + " 使用流运算：" + (end - start));
        //使用流运算+forkjoin
        start = System.currentTimeMillis();
        res = getResultByStream();
        end = System.currentTimeMillis();
        System.out.println("运算结果为：" + res + " 使用流运算+forkjoin：" + (end - start));
    }
}
