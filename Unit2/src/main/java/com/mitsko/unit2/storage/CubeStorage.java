package main.java.com.mitsko.unit2.storage;

import main.java.com.mitsko.unit2.entity.Cube;
import main.java.com.mitsko.unit2.entity.CubeRegistration;
import main.java.com.mitsko.unit2.logic.CubeLogic;
import main.java.com.mitsko.unit2.observer.Observer;

import java.util.HashMap;
import java.util.Map;

public class CubeStorage implements Observer<Cube> {
    private Map<Integer, CubeRegistration> map = new HashMap<Integer, CubeRegistration>();
    private static CubeStorage instance;

    private CubeStorage(){}

    public static CubeStorage getInstance(){
        if(instance == null){
           instance = new CubeStorage();
        }
        return instance;
    }

    @Override
    public void update(Cube obj){
        CubeRegistration cubeRegistration;
        CubeLogic cubeLogic = CubeLogic.getInstance();
        int volume = cubeLogic.calculateVolume(obj);
        int square = cubeLogic.calculateAllSquare(obj);
        if(map.get(obj.getId()) == null){
            cubeRegistration = new CubeRegistration(volume, square);
            map.put(obj.getId(), cubeRegistration);
        }else{
            cubeRegistration = map.get(obj.getId());
            cubeRegistration.setSquare(square);
            cubeRegistration.setVolume(volume);
        }
    }
}
