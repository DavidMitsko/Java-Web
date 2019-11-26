package com.mitsko.unit2.observer;

public interface Observable<T> {
    void register(Observer<T> obj);
    void unregister();
    void notifyObserver();
}
