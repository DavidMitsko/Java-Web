package com.mitsko.unit2.service.utils;

import com.mitsko.unit2.exception.DataFormatException;
import org.apache.log4j.Logger;

public class StringParser {
    private static final StringParser instance = new StringParser();
    private Logger logger = Logger.getLogger(StringParser.class);

    private StringParser() {
    }

    public static StringParser getInstance() {
        return instance;
    }

    public int[] parseString(String points, int length) throws DataFormatException {
        if (!check(points)) {
            throw new DataFormatException("Not correct data");
        }
        int array[] = new int[length];
        points += " ";
        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(points.substring(0, points.indexOf(' ')));
            points = points.substring(points.indexOf(' ') + 1);
        }
        return array;
    }

    private boolean check(String string) {
        if (string.matches("[- | 0-9 | . | /s*]+")) {
            return true;
        } else {
            logger.info("this string isn`t correct -- " + string);
            return false;
        }
    }
}
