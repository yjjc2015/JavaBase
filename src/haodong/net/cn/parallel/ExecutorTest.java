package haodong.net.cn.parallel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by haodong on 15-5-24.
 * 使用Executor 计算1到N之间的素数个数
 */
public class ExecutorTest {
    public static int getResultByExecutor() {
        AtomicInteger res = new AtomicInteger(0);
        AtomicInteger interatorNum = new AtomicInteger(1);
        ExecutorService service = Executors.newFixedThreadPool(Common.THREAD_NUM);
        Future future = service.submit(() -> {
            while (interatorNum.intValue() < Common.N) {
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
        try {
            return (int) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
