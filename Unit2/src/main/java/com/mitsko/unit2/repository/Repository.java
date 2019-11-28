package com.mitsko.unit2.repository;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.observer.Observable;
import com.mitsko.unit2.observer.Observer;
import com.mitsko.unit2.observer.impl.ObservableImpl;
import com.mitsko.unit2.repository.specification.Specification;
import com.mitsko.unit2.storage.CubeStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private final static Logger logger = LogManager.getLogger(Repository.class);

    private List<Cube> cubes;
    private Map<Integer, Observable<Cube>> map;

    public Repository() {
        cubes = new ArrayList<>();
        map = new HashMap<>();
    }

    public void addCube(Cube cube){
        logger.info("Add cube " + cube);

        cubes.add(cube);
        Observable<Cube> cube1 = new ObservableImpl(cube);
        map.put(cube.getId(), cube1);

        Observer<Cube> observer = CubeStorage.getInstance();
        cube1.register(observer);
    }

    public void remove(Cube cube){
        logger.info("Delete cube " + cube);

        cubes.remove(cube);
        map.remove(cube.getId());
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

    public List<Cube> getCubesList(){
        return cubes;
    }
}
