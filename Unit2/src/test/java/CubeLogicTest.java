package test.java;

import main.java.com.mitsko.unit2.dao.Parser;
import main.java.com.mitsko.unit2.dao.ReadFile;
import main.java.com.mitsko.unit2.entity.Cube;
import main.java.com.mitsko.unit2.entity.Point;
import main.java.com.mitsko.unit2.exception.DAOException;
import main.java.com.mitsko.unit2.logic.CubeLogic;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CubeLogicTest {
    Cube cube;
    Point[] plane = new Point[3];
    CubeLogic cubeLogic = new CubeLogic();

    private final double DELTA = 0.1;

    public CubeLogicTest(){
        ReadFile readFile = null;
        try {
            readFile = new ReadFile("src/test/resources/info.txt");
        }catch (DAOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
        Parser parser = Parser.getInstance();

        boolean flag = false;
        String points = null;
        while(!flag) {
            try {
                points = readFile.readString();
            }catch (DAOException ex){
                ex.printStackTrace();
                System.exit(-1);
            }

            int j = 0;
            if (parser.check(points)) {
                if(points.length() > 20) {
                    int[] array = parser.parseString(points, 24);
                    Point[] arrayPoints = new Point[8];
                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                    cube = new Cube(arrayPoints);
                    flag = true;
                }
                else{
                    int[] array = parser.parseString(points, 9);
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