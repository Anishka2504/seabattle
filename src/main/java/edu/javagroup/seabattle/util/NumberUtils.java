package edu.javagroup.seabattle.util;

/**
 * @author Drozdovskaya Anna
 * @version 1.0
 */

public class NumberUtils {

    public static boolean isNumber(String line) {
        if (StringUtils.isEmpty(line)) {
            return false;
        }
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isDigit(line.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
