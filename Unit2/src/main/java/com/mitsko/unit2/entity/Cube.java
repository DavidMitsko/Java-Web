package main.java.com.mitsko.unit2.entity;

import main.java.com.mitsko.unit2.entity.impl.Point;

public interface Cube {
    Point[] getPoints();

    void setPoints(Point[] points);

    Point getPoint(int index);

    void setPoint(Point point, int index);

    int getId();

    void setId(int id);
}
