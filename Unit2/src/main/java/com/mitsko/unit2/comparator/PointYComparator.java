package com.mitsko.unit2.comparator;

import com.mitsko.unit2.entity.impl.Point;

import java.util.Comparator;

public class PointYComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return (o1.getY() - o2.getY());
    }
}
