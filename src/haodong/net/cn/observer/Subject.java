package haodong.net.cn.observer;

/**
 * Created by haodong on 15-5-24.
 */
interface Subject {
    void registerSubject(Observer o);
    void removeSubject(Observer o);
    void notifyAllObserver();
}
