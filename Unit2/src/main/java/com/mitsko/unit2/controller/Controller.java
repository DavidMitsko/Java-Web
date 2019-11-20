package main.java.com.mitsko.unit2.controller;

import main.java.com.mitsko.unit2.utils.DataParser;
import main.java.com.mitsko.unit2.dao.ReadFile;
import main.java.com.mitsko.unit2.entity.impl.CubeImpl;
import main.java.com.mitsko.unit2.entity.impl.Point;
import main.java.com.mitsko.unit2.exception.CanNotCreateCubException;
import main.java.com.mitsko.unit2.exception.DAOException;
import main.java.com.mitsko.unit2.utils.StringParser;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class Controller {
    private static final Controller instance = new Controller();
    private Logger logger = Logger.getLogger(Controller.class);

    private ReadFile readFile;
    private DataParser dataParser;
    private StringParser stringParser;

    private Controller(){
        readFile = new ReadFile();
        dataParser = DataParser.getInstance();
        stringParser = StringParser.getInstance();
    }

    public static Controller getInstance(){
        return instance;
    }

    public ArrayList<CubeImpl> createCube() throws CanNotCreateCubException {
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<CubeImpl> cubeImpls = new ArrayList<CubeImpl>();


        try{
            arrayList = readFile.readAllFile("src/main/resources/info.txt");
        }catch (DAOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
        Iterator<String> iterator = arrayList.iterator();
        int j = 0, k = 0;
        boolean flag = false;
        while(iterator.hasNext()){
            String temp = iterator.next();
            if (dataParser.check(temp)){
                if (temp.length() > 20) {
                    int[] array = stringParser.parseString(temp, 24);
                    Point[] arrayPoints = new Point[8];
                    for (int i = 0; i < 8; i++) {
                        arrayPoints[i] = new Point(array[j], array[j + 1], array[j + 2]);
                        j += 3;
                    }
                    cubeImpls.add(new CubeImpl(arrayPoints));
                    flag = true;
                    logger.info("create cube");
                }
            }
        }
        if(flag){
            return cubeImpls;
        }else {
            throw new CanNotCreateCubException("Error");
        }
    }
}
