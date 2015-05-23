package haodong.net.cn.parallel;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.IntStream.range;

/**
 * Created by haodong on 15-5-23.
 */
public class ThreadParallel {
    private static final int N = 10000000;
    private static final int THREAD_NUM = 5;
    public static int getResultByForkJoin() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Future future = forkJoinPool.submit(() -> {
            int res = (int) range(2, N).parallel().filter(PrimesPrint::isPrime).count();
            return res;
        });
        try {
            return (int) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int getResultByStream() {
        int res = (int) range(2, N).parallel().filter(PrimesPrint::isPrime).count();
        return res;
    }
    public static int getResultByExecutor() throws ExecutionException, InterruptedException {
        AtomicInteger res = new AtomicInteger(0);
        AtomicInteger interatorNum = new AtomicInteger(1);
        ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
        Future future = service.submit(() -> {
            while (interatorNum.intValue() < N) {
                interatorNum.incrementAndGet();
                boolean tag = true;
                for (int i = 2; i <= Math.sqrt(interatorNum.intValue()); i++) {
                    if (interatorNum.intValue() % i == 0) {
                        tag = false;
                        break;
                    }
                }
                if (tag) {
                    res.getAndIncrement();
                }
            }
            return res.intValue();
        });
        service.shutdown();
        return (int) future.get();
    }
    public static int getResultByThread() {
        AtomicInteger interatorNum = new AtomicInteger(1);
        AtomicInteger res = new AtomicInteger(0);
        CountDownLatch count = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                synchronized(interatorNum) {
                    while (interatorNum.intValue() < N) {
                        interatorNum.incrementAndGet();
                        boolean tag = true;
                        for (int j = 2; j <= Math.sqrt(interatorNum.intValue()); j++) {
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
    public static int getResultByNormal() {
        int res = 0;
        for (int i = 2; i < N; i++) {
            boolean tag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i%j == 0) {
                    tag = false;
                    break;
                }
            }
            if (tag == true) {
                res++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int res = getResultByNormal();
        long end = System.currentTimeMillis();
        System.out.println("运算结果为："+res+" 使用单线程: "+(end-start));

        //使用普通多线程寻找1到N的质数个数
        start = System.currentTimeMillis();
        res = getResultByThread();
        end = System.currentTimeMillis();
        System.out.println("运算结果为:"+res+" 使用多线程："+(end-start));
        //使用线程池寻找1到N的质数个数
        start = System.currentTimeMillis();
        try {
            res = getResultByExecutor();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("运算结果为："+res+" 使用线程池："+(end-start));
        //使用流运算
        start = System.currentTimeMillis();
        res = getResultByStream();
        end = System.currentTimeMillis();
        System.out.println("运算结果为："+res+" 使用流运算："+(end-start));
        //使用流运算+forkjoin
        start = System.currentTimeMillis();
        res = getResultByStream();
        end = System.currentTimeMillis();
        System.out.println("运算结果为："+res+" 使用流运算+forkjoin："+(end-start));
    }

    private static class PrimesPrint {
        public static boolean isPrime(int number) {
            boolean tag = true;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number%i == 0) {
                    tag = false;
                    break;
                }
            }
            return tag;
        }
    }
}
