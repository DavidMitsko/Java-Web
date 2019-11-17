package main.java.com.mitsko.unit2.entity;

import java.util.Arrays;

public class Cube {
    private Point[] points;

    public Cube(Point[] points) {
        this.points = points;
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
