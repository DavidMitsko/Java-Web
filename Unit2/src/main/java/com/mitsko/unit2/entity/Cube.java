package main.java.com.mitsko.unit2.entity;

import main.java.com.mitsko.unit2.observer.Observable;
import main.java.com.mitsko.unit2.observer.Observer;

import java.util.Arrays;

public class Cube implements Observable {
    private int id;
    private Point[] points;
    private Observer cubeObserver;
    private boolean changed;

    public Cube(Point[] points) {
        this.points = points;
        this.id = 0;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
        this.changed = true;
        notifyObserver();
    }

    public Point getPoint(int index){
        return points[index];
    }

    public void setPoint(Point point, int index){
        points[index] = point;
        this.changed = true;
        notifyObserver();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.changed = true;
        notifyObserver();
    }

    @Override
    public void register(Observer obj){
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
        cubeObserver.update(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Arrays.equals(points, cube.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
}
