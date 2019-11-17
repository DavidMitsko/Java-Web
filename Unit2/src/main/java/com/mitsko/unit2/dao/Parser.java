package main.java.com.mitsko.unit2.dao;

import org.apache.log4j.Logger;

public class Parser {
    private static final Parser instance = new Parser();
    private Logger logger = Logger.getLogger(Parser.class);

    private Parser(){}

    public static Parser getInstance(){
        return instance;
    }

    public boolean check(String string){
        if(string.matches("[- | 0-9 | . | /s*]+")){
            return true;
        }
        else{
            logger.info("this string isn`t correct -- " + string);
            return false;
        }
    }

    public int[] parseString(String points, int length){
        int array[] = new int[length];
        points += " ";
        for(int i = 0; i < length; i++){
            array[i] = Integer.parseInt(points.substring(0, points.indexOf(' ')));
            points = points.substring(points.indexOf(' ') + 1);
        }
        return array;
    }
}
