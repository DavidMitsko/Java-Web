package test.java;

import main.java.com.mitsko.unit2.utils.DataParser;
import main.java.com.mitsko.unit2.dao.ReadFile;
import main.java.com.mitsko.unit2.entity.Cube;
import main.java.com.mitsko.unit2.entity.Point;
import main.java.com.mitsko.unit2.exception.DAOException;
import main.java.com.mitsko.unit2.logic.CubeLogic;
import main.java.com.mitsko.unit2.utils.StringParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

class CubeLogicTest {
    Cube cube;
    Point[] plane = new Point[3];
    CubeLogic cubeLogic = CubeLogic.getInstance();

    private final double DELTA = 0.1;

    public CubeLogicTest(){
        ReadFile readFile = new ReadFile();
        DataParser dataParser = DataParser.getInstance();
        StringParser stringParser = StringParser.getInstance();

        ArrayList<String> arrayList = new ArrayList<String>();

        try{
            arrayList = readFile.readAllFile("src/main/resources/info.txt");
        }catch (DAOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }

        Iterator<String> iterator = arrayList.iterator();
        boolean flag = false;
        while(!flag || iterator.hasNext()) {
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
                else{
                    int[] array = stringParser.parseString(temp, 9);
                    for(int i = 0; i < plane.length; i++){
                        plane[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                }
            }
        }
    }

    @Test
    void calculateVolume() {
        int expected = 27;
        int actual = cubeLogic.calculateVolume(cube);
        Assert.assertEquals(expected, actual);

    }

    @Test
    void calculateAllSquare() {
        int expected = 54;
        int actual = cubeLogic.calculateAllSquare(cube);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void isCube() {
        Assert.assertTrue(cubeLogic.isCube(cube));
    }

    @Test
    void volumeOfPart() {
        double expected = (double)1 / 3;
        double actual = cubeLogic.volumeOfPart(cube, plane);
        Assert.assertEquals(expected, actual, DELTA);
    }
}