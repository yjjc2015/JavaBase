package haodong.net.cn.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haodong on 15-5-24.
 */
public class ConcreteSubject implements Subject {
    private List<Observer> observerList;
    private List<String> jobList;
    public ConcreteSubject() {
        this.observerList = new ArrayList<>();
        this.jobList = new ArrayList<>();
    }
    @Override
    public void registerSubject(Observer o) {
        this.observerList.add(o);
    }

    @Override
    public void removeSubject(Observer o) {
        this.observerList.remove(this.observerList.size()-1);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer: this.observerList) {
            observer.update(this);
        }
    }
    //加入任务后必须调用notifyAllObserver()方法来通知所有Observer对象
    public void addJob(String job) {
        this.jobList.add(job);
        notifyAllObserver();
    }
    public String toString() {
        return jobList.toString();
    }
}
