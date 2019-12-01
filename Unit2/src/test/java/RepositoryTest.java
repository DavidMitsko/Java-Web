import com.mitsko.unit2.entity.Cube;
import com.mitsko.unit2.entity.impl.CubeImpl;
import com.mitsko.unit2.entity.impl.Point;
import com.mitsko.unit2.exception.DAOException;
import com.mitsko.unit2.exception.DataFormatException;
import com.mitsko.unit2.repository.Repository;
import com.mitsko.unit2.service.util.PointsFileReader;
import com.mitsko.unit2.service.util.StringOfPointsParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

class RepositoryTest {
    PointsFileReader readFile = new PointsFileReader();
    StringOfPointsParser stringOfPointsParser = StringOfPointsParser.getInstance();

    ArrayList<String> arrayList = new ArrayList<String>();

    @Test
    void addCube() {
        Point[] arrayPoints = new Point[8];
        boolean flag = false;

        try {
            arrayList = readFile.readAllFile("src/test/resources/info.txt");
        } catch (DAOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        Iterator<String> iterator = arrayList.iterator();

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


        Repository cubeRepository = new Repository();
        cubeRepository.addCube(new CubeImpl(arrayPoints));

        Assert.assertNotNull(cubeRepository);
    }

    @Test
    void remove() {
        Point[] arrayPoints = new Point[8];
        boolean flag = false;

        try {
            arrayList = readFile.readAllFile("src/test/resources/info.txt");
        } catch (DAOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        Iterator<String> iterator = arrayList.iterator();

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
        Cube cube = new CubeImpl(arrayPoints);

        Repository cubeRepository = new Repository();
        cubeRepository.addCube(cube);

        cubeRepository.remove(cube);

        int expected = 0;
        int actual = cubeRepository.getCubesList().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    void query() {
    }
}