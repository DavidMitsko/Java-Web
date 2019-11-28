package com.mitsko.unit2.observer;

import com.mitsko.unit2.entity.Cube;

public interface Observable<T> {
    void register(Observer<T> obj);
    void unregister(Observer<Cube> obj);
    void notifyObserver();
}
