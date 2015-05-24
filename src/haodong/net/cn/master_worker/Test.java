package haodong.net.cn.master_worker;

/**
 * Created by haodong on 15-5-24.
 * 测试类
 */
public class Test {
    public static final int THREAD_NUM = 5;
    public static void main(String[] args) {
        Worker<Job> worker = new Worker<>();
        Master master = new Master<Job>(worker, THREAD_NUM);
        master.addJob(new Job());
        master.addJob(new Job());
        master.execute();
        while (!master.isComplete());
        System.out.println("job is stoped!");
    }
}
