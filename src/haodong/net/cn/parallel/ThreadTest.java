package haodong.net.cn.parallel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by haodong on 15-5-23.
 * 使用多线程计算1到N之间的质数
 */
public class ThreadTest {
    public static int getResultByThread() {
        AtomicInteger interatorNum = new AtomicInteger(1);
        AtomicInteger res = new AtomicInteger(0);
        CountDownLatch count = new CountDownLatch(Common.THREAD_NUM);
        for (int i = 0; i < Common.THREAD_NUM; i++) {
            new Thread(() -> {
                synchronized (interatorNum) {
                    while (interatorNum.get() < Common.N) {
                        interatorNum.incrementAndGet();

                        boolean tag = true;
                        for (int j = 2; j <= Math.sqrt(interatorNum.get()); j++) {
                            if (interatorNum.intValue() % j == 0) {
                                tag = false;
                                break;
                            }
                        }
                        if (tag) {
                            res.getAndIncrement();
                        }
                    }
                }
                count.countDown();
            }).start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res.intValue();
    }
}
