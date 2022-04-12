package edu.javagroup.seabattle.util;

import edu.javagroup.seabattle.model.HorizontalLine;

import java.util.Arrays;

import static edu.javagroup.seabattle.constants.Constants.LOCALHOST;
import static edu.javagroup.seabattle.util.NumberUtils.isNumber;
import static edu.javagroup.seabattle.util.StringUtils.isEmpty;

/**
 * @author Drozdovskaya Anna
 * @version 1.0
 */

public class IpAddressUtils {

    public static boolean isIpAddress(String source) {
        if (isEmpty(source)) {
            return false;
        }

        String[] parts = source.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        int[] numericParts = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            if (!(isNumber(parts[i]))) {
                return false;
            }
            numericParts[i] = Integer.parseInt(parts[i]);
        }
        if (Arrays.equals(numericParts, LOCALHOST)) {
            return false;
        }
        for (int j = 0; j < parts.length; j++) {
            if (!isCorrectValue(parts[j], j)) {
                return false;
            }
        }

        return !source.endsWith(".");
    }

    private static boolean isCorrectValue(String part, int position) {
        int number = Integer.parseInt(part);
        return position == 0 ? (number > 0 && number <= 255) : (number >= 0 && number <= 255);
    }
}




