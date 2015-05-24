package haodong.net.cn.parallel;

/**
 * Created by haodong on 15-5-23.
 * 单线程计算1到N之间的素数
 */
public class NormalTest {
    public static int getResultByNormal() {
        int res = 0;
        for (int i = 2; i < Common.N; i++) {
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
}
