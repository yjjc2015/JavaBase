package haodong.net.cn.master_worker;

import java.util.Map;
import java.util.Queue;

/**
 * Created by haodong on 15-5-24.
 * 并行计算模式之Master-Worker模式的Worker部分
 */
class Worker<V> implements Runnable {
    private Queue<V> workQueue;
    private Map<String, Object> resultMap;
    public void setWorkQueue(Queue queue) {
        this.workQueue = queue;
    }
    public void setResultMap(Map map) {
        this.resultMap = map;
    }
    public Object objectHandle(V target) {
        System.out.println("I'm in Worker!");
        return new Object();
    }
    @Override
    public void run() {
        while (true) {
            V object = workQueue.poll();
            if (object == null) break;
            Object result = objectHandle(object);
            this.resultMap.put(String.valueOf(object.hashCode()), result);
        }
    }
}
