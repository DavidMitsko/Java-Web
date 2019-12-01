package com.mitsko.unit2.comparator;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.Point;

import java.util.Comparator;

public class CubePoint1XComparator implements Comparator<Cube> {
    @Override
    public int compare(Cube o1, Cube o2) {
        PointXComparator pointXComparator = new PointXComparator();
        Point cubePoint1 = o1.getPoint(0);
        Point cubePoint2 = o2.getPoint(0);
        return pointXComparator.compare(cubePoint1, cubePoint2);
    }
}
