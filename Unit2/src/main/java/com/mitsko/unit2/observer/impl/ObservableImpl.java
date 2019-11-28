package com.mitsko.unit2.observer.impl;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.Point;
import com.mitsko.unit2.observer.Observable;
import com.mitsko.unit2.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableImpl implements Observable<Cube>, Cube {
    private Cube cube;

    private List<Observer<Cube>> cubeObserver = new ArrayList<>();

    public ObservableImpl(Cube cube) {
        this.cube = cube;
    }

    @Override
    public void register(Observer<Cube> obj){
        this.cubeObserver.add(obj);
    }

    @Override
    public void unregister(Observer<Cube> obj){
        this.cubeObserver.remove(obj);
    }

    @Override
    public void notifyObserver(){
        for(Observer<Cube> observer : cubeObserver) {
            observer.update(cube);
        }
    }

    @Override
    public Point[] getPoints() {
        return cube.getPoints();
    }

    @Override
    public void setPoints(Point[] points) {
        cube.setPoints(points);
        notifyObserver();
    }

    @Override
    public Point getPoint(int index) {
        return cube.getPoint(index);
    }

    @Override
    public void setPoint(Point point, int index) {
        cube.setPoint(point, index);
        notifyObserver();
    }

    @Override
    public int getId() {
        return cube.getId();
    }

    @Override
    public void setId(int id) {
        cube.setId(id);
        notifyObserver();
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }
}
