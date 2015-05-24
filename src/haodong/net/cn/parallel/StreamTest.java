package haodong.net.cn.parallel;

import static java.util.stream.IntStream.range;

/**
 * Created by haodong on 15-5-23.
 */
public class StreamTest {
    public static int getResultByStream() {
        int res = (int) range(2, Common.N).parallel().filter(PrimesPrint::isPrime).count();
        return res;
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
