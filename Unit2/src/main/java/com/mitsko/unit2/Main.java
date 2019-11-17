package main.java.com.mitsko.unit2;

import main.java.com.mitsko.unit2.controller.Controller;
import main.java.com.mitsko.unit2.entity.Cube;
import main.java.com.mitsko.unit2.exception.CanNotCreateCubException;
import main.java.com.mitsko.unit2.logic.CubeLogic;

public class Main {
    public static void main(String[] args) {
        try {
            Controller controller = Controller.getInstance();
            Cube cube = controller.createCube();
            CubeLogic cubeLogic = new CubeLogic();
            cubeLogic.isCube(cube);
        }catch (Exception | CanNotCreateCubException ex){
            ex.printStackTrace();
        }
    }
}
