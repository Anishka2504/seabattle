package edu.javagroup.seabattle.singleton;

/**@author Konchits Nikita
 * @version 1.0
 * @see edu.javagroup.seabattle.model.HorizontalLine
 */

public class ImReadySingleton {

    private static ImReadySingleton instance;
    private final Boolean imReady;

    private ImReadySingleton(Boolean imReady) {
        this.imReady = imReady;
    }

    public static ImReadySingleton instance(Boolean imReady) {
        if (instance == null) {
            instance = new ImReadySingleton(true);
        }
        if (imReady != null) {
            instance = new ImReadySingleton(imReady);
        }
        return instance;
    }

    public Boolean imReady() {
        return imReady;
    }
}