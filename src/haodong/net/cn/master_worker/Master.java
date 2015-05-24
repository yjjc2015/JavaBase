package haodong.net.cn.master_worker;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by haodong on 15-5-24.
 */
public class Master<V> {
    private Queue<V> workQueue = new ConcurrentLinkedQueue<>();
    private Map<String, Object> resultMap = new ConcurrentHashMap<>();
    private Map<String, Thread> threadMap = new ConcurrentHashMap<>();

    /**
     * 初始化Master，传入Worker
     *
     * @param worker
     * @param workerCount
     */
    public Master(Worker<V> worker, int workerCount) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < workerCount; i++) {
            threadMap.put(String.valueOf(i), new Thread(worker, String.valueOf(i)));
        }
    }

    /**
     * 查看任务状态
     *
     * @return
     */
    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) return false;
        }
        return true;
    }

    /**
     * 增加任务
     *
     * @param job
     */
    public void addJob(V job) {
        workQueue.add(job);
    }

    /**
     * 返货结果集
     *
     * @return
     */
    public Map<String, Object> getResult() {
        return this.resultMap;
    }

    /**
     * 开始执行
     */
    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }
}
