package main.java.com.mitsko.unit2.utils;

public class StringParser {
    private static final StringParser instance = new StringParser();

    private StringParser(){}

    public static StringParser getInstance(){
        return instance;
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
