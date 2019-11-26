import com.mitsko.unit2.exception.DataFormatException;
import com.mitsko.unit2.service.utils.ReadFile;
import com.mitsko.unit2.entity.impl.CubeImpl;
import com.mitsko.unit2.entity.impl.Point;
import com.mitsko.unit2.exception.DAOException;
import com.mitsko.unit2.service.CubeLogic;
import com.mitsko.unit2.service.utils.StringParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

class CubeLogicTest {
    private CubeImpl cubeImpl;
    private Point[] plane = new Point[3];
    private CubeLogic cubeLogic = CubeLogic.getInstance();

    private final double DELTA = 0.1;

    public CubeLogicTest() {
        ReadFile readFile = new ReadFile();
        StringParser stringParser = StringParser.getInstance();

        ArrayList<String> arrayList = new ArrayList<String>();

        try {
            arrayList = readFile.readAllFile("src/main/resources/info.txt");
        } catch (DAOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        Iterator<String> iterator = arrayList.iterator();
        boolean flag = false;
        while (!flag || iterator.hasNext()) {
            String temp = iterator.next();
            int j = 0;
            try {
                if (temp.length() > 20) {
                    int[] array = stringParser.parseString(temp, 24);
                    Point[] arrayPoints = new Point[8];

                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }

                    cubeImpl = new CubeImpl(arrayPoints);
                    flag = true;
                } else {
                    int[] array = stringParser.parseString(temp, 9);

                    for (int i = 0; i < plane.length; i++) {
                        plane[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                }
            } catch (DataFormatException ex) {
                if(iterator.hasNext()) {
                    continue;
                } else {
                    break;
                }
            }
        }
    }

    @Test
    void calculateVolume() {
        int expected = 27;
        int actual = cubeLogic.calculateVolume(cubeImpl);
        Assert.assertEquals(expected, actual);

    }

    @Test
    void calculateAllSquare() {
        int expected = 54;
        int actual = cubeLogic.calculateAllSquare(cubeImpl);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void isCube() {
        Assert.assertTrue(cubeLogic.isCube(cubeImpl));
    }

    @Test
    void volumeOfPart() {
        double expected = (double) 1 / 3;
        double actual = cubeLogic.volumeOfPart(cubeImpl, plane);
        Assert.assertEquals(expected, actual, DELTA);
    }
}