package haodong.net.cn.parallel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import static java.util.stream.IntStream.range;

/**
 * Created by haodong on 15-5-23.
 * 使用forkjoin框架计算1到N之间的质数
 */
public class ForkJoinTest {
    public static int getResultByForkJoin() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Common.THREAD_NUM);
        Future future = forkJoinPool.submit(() -> {
            int res = (int) range(2, Common.N).parallel().filter(PrimesPrint::isPrime).count();
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

    private static class PrimesPrint {
        public static boolean isPrime(int number) {
            boolean tag = true;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    tag = false;
                    break;
                }
            }
            return tag;
        }
    }
}
