package main.java.com.mitsko.unit2.observer;

public interface Observable {
    void register(Observer obj);
    void unregister();
    void notifyObserver();
}
