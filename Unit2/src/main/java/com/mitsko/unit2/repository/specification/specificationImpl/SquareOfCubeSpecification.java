package com.mitsko.unit2.repository.specification.specificationImpl;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.repository.specification.Specification;
import com.mitsko.unit2.service.CubeLogic;

public class SquareOfCubeSpecification implements Specification {
    private int minSquare;
    private int maxSquare;

    public SquareOfCubeSpecification(int minSquare, int maxSquare) {
        this.minSquare = minSquare;
        this.maxSquare = maxSquare;
    }

    @Override
    public boolean specify(Cube cube) {
        CubeLogic cubeLogic = CubeLogic.getInstance();
        int cubeSquare;
        cubeSquare = cubeLogic.calculateAllSquare(cube);
        return minSquare <= cubeSquare && cubeSquare <= maxSquare;
    }
}
