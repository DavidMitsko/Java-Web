package main.java.com.mitsko.unit2.controller;

import main.java.com.mitsko.unit2.dao.Parser;
import main.java.com.mitsko.unit2.dao.ReadFile;
import main.java.com.mitsko.unit2.entity.Cube;
import main.java.com.mitsko.unit2.entity.Point;
import main.java.com.mitsko.unit2.exception.CanNotCreateCubException;
import main.java.com.mitsko.unit2.exception.DAOException;
import org.apache.log4j.Logger;

public class Controller {
    private static final Controller instance = new Controller();
    private Logger logger = Logger.getLogger(Controller.class);

    private ReadFile readFile;
    private Parser parser;

    private Controller(){
        try {
            readFile = new ReadFile("src/main/resources/info.txt");
        }
        catch (DAOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
        parser = Parser.getInstance();
    }

    public static Controller getInstance(){
        return instance;
    }

    public Cube createCube() throws CanNotCreateCubException {
        String points;

        int j = 0;
        boolean flag = false;
        while(!flag) {
            try {
                points = readFile.readString();
            }
            catch (DAOException ex){
                ex.printStackTrace();
                return null;
            }

            if (parser.check(points)) {
                if (points.length() > 20) {
                    int[] array = parser.parseString(points, 24);
                    Point[] arrayPoints = new Point[8];
                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                    Cube cube = new Cube(arrayPoints);
                    flag = true;
                    logger.info("create cube");
                    return cube;
                }
            }
        }
        throw new CanNotCreateCubException("Error");
    }
}
