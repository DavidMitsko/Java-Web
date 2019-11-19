package test.java;

import main.java.com.mitsko.unit2.dao.ReadFile;
import main.java.com.mitsko.unit2.entity.Cube;
import main.java.com.mitsko.unit2.entity.Point;
import main.java.com.mitsko.unit2.exception.DAOException;
import main.java.com.mitsko.unit2.logic.CubeLogic;
import main.java.com.mitsko.unit2.observer.Observer;
import main.java.com.mitsko.unit2.storage.CubeStorage;
import main.java.com.mitsko.unit2.utils.DataParser;
import main.java.com.mitsko.unit2.utils.StringParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

class CubeStorageTest {

    Cube cube;
    CubeLogic cubeLogic = CubeLogic.getInstance();
    ReadFile readFile = new ReadFile();
    DataParser dataParser = DataParser.getInstance();
    StringParser stringParser = StringParser.getInstance();

    ArrayList<String> arrayList = new ArrayList<String>();
    Iterator<String> iterator;

    CubeStorageTest(){


        try{
            arrayList = readFile.readAllFile("src/test/resources/info.txt");
        }catch (DAOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }

        iterator = arrayList.iterator();
        boolean flag = false;
        while(!flag && iterator.hasNext()) {
            String temp = iterator.next();
            int j = 0;
            if (dataParser.check(temp)){
                if(temp.length() > 20) {
                    int[] array = stringParser.parseString(temp, 24);
                    Point[] arrayPoints = new Point[8];
                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                    cube = new Cube(arrayPoints);
                    flag = true;
                }
            }
        }
    }

    @Test
    void observeOfCube(){
        Observer<Cube> cubeStorage = CubeStorage.getInstance();
        cube.register(cubeStorage);

        Point[] arrayPoints = new Point[8];
        boolean flag = false;
        while(!flag || iterator.hasNext()) {
            String temp = iterator.next();
            int j = 0;
            if (dataParser.check(temp)){
                if(temp.length() > 20) {
                    int[] array = stringParser.parseString(temp, 24);
                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                    flag = true;
                }
            }
        }

        cube.setPoints(arrayPoints);
        int expected = cubeLogic.calculateVolume(cube);
        int actual = CubeStorage.getMap().get(cube.getId()).getVolume();
        Assert.assertEquals(expected, actual);
    }

}