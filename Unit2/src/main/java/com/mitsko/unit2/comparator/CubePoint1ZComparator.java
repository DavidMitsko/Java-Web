package com.mitsko.unit2.comparator;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.Point;

import java.util.Comparator;

public class CubePoint1ZComparator implements Comparator<Cube> {
    @Override
    public int compare(Cube o1, Cube o2) {
        PointZComparator pointZComparator = new PointZComparator();
        Point cubePoint1 = o1.getPoint(0);
        Point cubePoint2 = o2.getPoint(0);
        return pointZComparator.compare(cubePoint1, cubePoint2);
    }
}
