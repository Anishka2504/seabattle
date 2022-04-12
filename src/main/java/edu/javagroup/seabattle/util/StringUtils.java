package edu.javagroup.seabattle.util;

/**
 * @author Drozdovskaya Anna
 * @version 1.0
 */

public class StringUtils {

    public static boolean isEmpty(CharSequence source) {
        return source == null || source.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence source) {
       return  !isEmpty(source);
    }

    public static char letterBefore(char row) {
        return (char) (row - 1);
    }

    public static char letterAfter(char row) {
        return (char) (row + 1);
    }
}


