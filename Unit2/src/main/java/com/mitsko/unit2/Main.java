package main.java.com.mitsko.unit2;

import main.java.com.mitsko.unit2.controller.Controller;
import main.java.com.mitsko.unit2.entity.Cube;
import main.java.com.mitsko.unit2.exception.CanNotCreateCubException;
import main.java.com.mitsko.unit2.logic.CubeLogic;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cube> cubes = new ArrayList<Cube>();
        try {
            Controller controller = Controller.getInstance();
            cubes = controller.createCube();
            CubeLogic cubeLogic = new CubeLogic();
            System.out.println(cubeLogic.isCube(cubes.get(0)));
        }catch (Exception | CanNotCreateCubException ex){
            ex.printStackTrace();
        }
    }
}
