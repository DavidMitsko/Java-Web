package com.mitsko.unit2.repository.specification.specificationImpl;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.repository.specification.Specification;
import com.mitsko.unit2.service.CubeLogic;

public class VolumeOfCubeSpecification implements Specification {
    private int minSquare;
    private int maxSquare;

    public VolumeOfCubeSpecification(int minSquare, int maxSquare) {
        this.minSquare = minSquare;
        this.maxSquare = maxSquare;
    }

    @Override
    public boolean specify(Cube cube) {
        CubeLogic cubeLogic = new CubeLogic();//CubeLogic.getInstance();
        int cubeVolume;
        cubeVolume = cubeLogic.calculateVolume(cube);
        return minSquare <= cubeVolume && cubeVolume <= maxSquare;
    }
}
