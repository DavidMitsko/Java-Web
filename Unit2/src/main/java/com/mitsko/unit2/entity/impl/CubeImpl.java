package com.mitsko.unit2.entity.impl;

import com.mitsko.unit2.entity.Cube;

import java.util.Arrays;

public class CubeImpl implements Cube {
    private int id;
    private Point[] points;


    public CubeImpl(Point[] points) {
        this.points = points;
        this.id = 0;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Point getPoint(int index){
        return points[index];
    }

    public void setPoint(Point point, int index){
        points[index] = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeImpl cubeImpl = (CubeImpl) o;
        return Arrays.equals(points, cubeImpl.points);
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
