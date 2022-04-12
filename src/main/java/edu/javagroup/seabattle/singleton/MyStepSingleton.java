package edu.javagroup.seabattle.singleton;

import edu.javagroup.seabattle.model.HorizontalLine;

/**
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see HorizontalLine
 */

public class MyStepSingleton {

    private static MyStepSingleton instance;
    private final Boolean myStep;

    private MyStepSingleton(Boolean myStep) {
        this.myStep = myStep;
    }

    public static MyStepSingleton instance(Boolean myStep) {
        MyStepSingleton singleton = instance;
        if (singleton == null) {
            instance = new MyStepSingleton(true);
        }
        if (myStep != null) {
            instance = new MyStepSingleton(myStep);
        }
        return instance;
    }

    public Boolean myStep() {
        return myStep;
    }
}
