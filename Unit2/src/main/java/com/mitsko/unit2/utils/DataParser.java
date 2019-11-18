package main.java.com.mitsko.unit2.utils;

import org.apache.log4j.Logger;

public class DataParser {
    private static final DataParser instance = new DataParser();
    private Logger logger = Logger.getLogger(DataParser.class);

    private DataParser(){}

    public static DataParser getInstance(){
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
}
