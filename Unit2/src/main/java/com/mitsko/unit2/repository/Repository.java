package com.mitsko.unit2.repository;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final static Logger logger = LogManager.getLogger(Repository.class);

    private List<Cube> cubes;

    public Repository() {
        cubes = new ArrayList<>();
    }

    public void addCube(Cube cube) {
        logger.info("Add cube " + cube);

        cubes.add(cube);
    }

    public void remove(Cube cube) {
        logger.info("Delete cube " + cube);

        cubes.remove(cube);
    }

    public List<Cube> query(Specification specification) {
        List<Cube> tempList = new ArrayList<>();
        for (Cube figure : cubes) {
            if (specification.specify(figure)) {
                tempList.add(figure);
            }
        }
        return tempList;
    }

    public List<Cube> getCubesList() {
        return cubes;
    }
}
