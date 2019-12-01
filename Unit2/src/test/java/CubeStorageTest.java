import com.mitsko.unit2.service.util.PointsFileReader;
import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.CubeImpl;
import com.mitsko.unit2.entity.impl.Point;
import com.mitsko.unit2.exception.DAOException;
import com.mitsko.unit2.exception.DataFormatException;
import com.mitsko.unit2.service.CubeLogic;
import com.mitsko.unit2.observer.Observer;
import com.mitsko.unit2.observer.impl.ObservableImpl;
import com.mitsko.unit2.storage.CubeStorage;
import com.mitsko.unit2.service.util.StringOfPointsParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

class CubeStorageTest {

    ObservableImpl observable;
    CubeLogic cubeLogic = new CubeLogic();//CubeLogic.getInstance();
    PointsFileReader readFile = new PointsFileReader();
    StringOfPointsParser stringOfPointsParser = StringOfPointsParser.getInstance();

    ArrayList<String> arrayList = new ArrayList<String>();
    Iterator<String> iterator;

    CubeStorageTest() {


        try {
            arrayList = readFile.readAllFile("src/test/resources/info.txt");
        } catch (DAOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        iterator = arrayList.iterator();
        boolean flag = false;
        while (!flag && iterator.hasNext()) {
            String temp = iterator.next();
            int j = 0;
            try {
                if (temp.length() > 20) {
                    int[] array = stringOfPointsParser.parseString(temp, 24);
                    Point[] arrayPoints = new Point[8];
                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                    observable = new ObservableImpl(new CubeImpl(arrayPoints));
                    flag = true;
                }
            } catch (DataFormatException ex) {
                if (iterator.hasNext()) {
                    continue;
                } else {
                    break;
                }
            }
        }
    }

    @Test
    void observeOfCube() {
        Observer<Cube> cubeStorage = CubeStorage.getInstance();
        observable.register(cubeStorage);

        Point[] arrayPoints = new Point[8];
        boolean flag = false;
        while (!flag || iterator.hasNext()) {
            String temp = iterator.next();
            int j = 0;
            try {
                if (temp.length() > 20) {
                    int[] array = stringOfPointsParser.parseString(temp, 24);
                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                    flag = true;
                }
            } catch (DataFormatException ex) {
                if (iterator.hasNext()) {
                    continue;
                } else {
                    break;
                }
            }
        }

        observable.setPoints(arrayPoints);
        int expected = cubeLogic.calculateVolume(observable.getCube());
        int actual = CubeStorage.getMap().get(observable.getId()).getVolume();
        Assert.assertEquals(expected, actual);
    }

}