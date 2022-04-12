package edu.javagroup.seabattle.exception;

import static edu.javagroup.seabattle.constants.Constants.ENEMY;
import static edu.javagroup.seabattle.constants.Constants.MINE;

/**
 * @author Drozdovskaya Anna
 * @version 1.1
 */

public class SideNotFoundException extends RuntimeException {

    public SideNotFoundException() {
        this("Уточните сторону " + MINE + " or " + ENEMY );
    }

    public SideNotFoundException(String message) {
        super(message);
    }
}
