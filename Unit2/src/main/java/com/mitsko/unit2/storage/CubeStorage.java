package com.mitsko.unit2.storage;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.CubeRegistration;
import com.mitsko.unit2.observer.Observer;
import com.mitsko.unit2.service.CubeLogic;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CubeStorage implements Observer<Cube> {
    private final static Logger logger = LogManager.getLogger(CubeStorage.class);

    private static Map<Integer, CubeRegistration> map = new HashMap<>();
    private static CubeStorage instance;

    private CubeStorage() {

    }

    public static CubeStorage getInstance() {
        if (instance == null) {
            instance = new CubeStorage();
        }
        return instance;
    }

    @Override
    public void update(Cube obj) {
        CubeRegistration cubeRegistration;
        CubeLogic cubeLogic = new CubeLogic();
        int volume = cubeLogic.calculateVolume(obj);
        int square = cubeLogic.calculateAllSquare(obj);
        if (map.get(obj.getId()) == null) {
            cubeRegistration = new CubeRegistration(volume, square);
            map.put(obj.getId(), cubeRegistration);
        } else {
            cubeRegistration = map.get(obj.getId());
            cubeRegistration.setSquare(square);
            cubeRegistration.setVolume(volume);
        }
    }

    public static Map<Integer, CubeRegistration> getMap() {
        return map;
    }
}
