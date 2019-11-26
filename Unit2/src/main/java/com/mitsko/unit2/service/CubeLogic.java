package com.mitsko.unit2.service;

import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.Point;

public class CubeLogic {
    private static CubeLogic instance = null;

    private CubeLogic(){

    }

    public static CubeLogic getInstance(){
        if(instance == null){
            instance = new CubeLogic();
        }
        return instance;
    }

    public int calculateVolume(Cube cube){
        int side = calculateSide(cube);
        return (int)Math.pow(side, 3);
    }

    public int calculateAllSquare(Cube cube){
        int side = calculateSide(cube);
        int square = (int)Math.pow(side, 2);
        return square * 6;
    }

    public boolean isCube(Cube cubeImpl){
        int side = calculateSide(cubeImpl);

        for(int i = 0; i < cubeImpl.getPoints().length; i++){
            for(int j = i + 1; j < cubeImpl.getPoints().length; j++){
                if(pointComparator(cubeImpl.getPoint(i), cubeImpl.getPoint(j)))
                {
                    if (findDistanceBetweenPoints(cubeImpl.getPoint(i), cubeImpl.getPoint(j)) != side) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean pointComparator(Point p1, Point p2){
        if(p1.getX() == p2.getX() && p1.getY() == p2.getY()){
            return true;
        }
        else if(p1.getX() == p2.getX() && p1.getZ() == p2.getZ()){
            return true;
        } else if(p1.getY() == p2.getY() && p1.getZ() == p2.getZ()){
            return true;
        }else{
            return false;
        }
    }

    private double findDistanceBetweenPoints(Point p2, Point p1) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2) + Math.pow(p2.getZ() - p1.getZ(), 2));
    }

    private int calculateSide(Cube cube){
        int x1 = cube.getPoint(0).getX();
        int y1 = cube.getPoint(0).getY();
        int z1 = cube.getPoint(0).getZ();
        int z2 = 0;
        for(int i = 1; i < cube.getPoints().length; i++){
            if(cube.getPoint(i).getX() == x1 && cube.getPoint(i).getY() == y1){
                z2 = cube.getPoint(i).getZ();
            }
        }
        return Math.abs(z1 - z2);
    }

    public double volumeOfPart(Cube cube, Point[] plane){
        int volume = calculateVolume(cube);
        if(plane[0].getX() == plane[1].getX() && plane[0].getX() == plane[2].getX()){
            return volumeOfPartX(cube, plane) / volume;
        } else if(plane[0].getY() == plane[1].getY() && plane[0].getY() == plane[2].getY()){
            return  volumeOfPartY(cube, plane) / volume;
        } else{
            return volumeOfPartZ(cube, plane) / volume;
        }
    }

    private double volumeOfPartX(Cube cubeImpl, Point[] plane){
        int x = 0, planeX = plane[0].getX();
        for(int i = 0; i < cubeImpl.getPoints().length; i++){
            x = cubeImpl.getPoint(i).getX();
            if(plane[0].getX() < x){
                break;
            }
        }

        int side = calculateSide(cubeImpl);
        int square = (int)Math.pow(side, 2);

        return square * (x - planeX);
    }

    private double volumeOfPartY(Cube cubeImpl, Point[] plane){
        int y = 0, planeY = plane[0].getY();
        for(int i = 0; i < cubeImpl.getPoints().length; i++){
            y = cubeImpl.getPoint(i).getY();
            if(plane[0].getY() < y){
                break;
            }
        }

        int side = calculateSide(cubeImpl);
        int square = (int)Math.pow(side, 2);

        return square * (y - planeY);
    }

    private double volumeOfPartZ(Cube cubeImpl, Point[] plane){
        int z = 0, planeZ = plane[0].getZ();
        for(int i = 0; i < cubeImpl.getPoints().length; i++){
            z = cubeImpl.getPoint(i).getZ();
            if(plane[0].getZ() < z){
                break;
            }
        }

        int side = calculateSide(cubeImpl);
        int square = (int)Math.pow(side, 2);

        return square * (z - planeZ);
    }
}
