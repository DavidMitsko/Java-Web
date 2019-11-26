package com.mitsko.unit2.observer.impl;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.CubeImpl;
import com.mitsko.unit2.entity.impl.Point;
import com.mitsko.unit2.observer.Observable;
import com.mitsko.unit2.observer.Observer;

public class ObservableImpl implements Observable<Cube>, Cube {
    private CubeImpl cube;

    private Observer<Cube> cubeObserver;
    private boolean changed;

    public ObservableImpl(CubeImpl cube) {
        this.cube = cube;
    }

    @Override
    public void register(Observer<Cube> obj){
        this.cubeObserver = obj;
    }

    @Override
    public void unregister(){
        this.cubeObserver = null;
    }

    @Override
    public void notifyObserver(){
        if(!this.changed){
            return;
        }
        this.changed = false;
        cubeObserver.update(cube);
    }

    @Override
    public Point[] getPoints() {
        return cube.getPoints();
    }

    @Override
    public void setPoints(Point[] points) {
        cube.setPoints(points);
        this.changed = true;
        notifyObserver();
    }

    @Override
    public Point getPoint(int index) {
        return cube.getPoint(index);
    }

    @Override
    public void setPoint(Point point, int index) {
        cube.setPoint(point, index);
        this.changed = true;
        notifyObserver();
    }

    @Override
    public int getId() {
        return cube.getId();
    }

    @Override
    public void setId(int id) {
        cube.setId(id);
        this.changed = true;
        notifyObserver();
    }

    public CubeImpl getCube() {
        return cube;
    }

    public void setCube(CubeImpl cube) {
        this.cube = cube;
    }
}
