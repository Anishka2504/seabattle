package edu.javagroup.seabattle.singleton;

import edu.javagroup.seabattle.model.HorizontalLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see HorizontalLine
 */

public class MinePanelSingleton {

    private static MinePanelSingleton instance;
    private final List<HorizontalLine> panel;

    private MinePanelSingleton(List<HorizontalLine> panel) {
        Collections.sort(panel);
        this.panel = panel;
    }

    public static MinePanelSingleton instance(List<HorizontalLine> panel) {
        MinePanelSingleton singleton = instance;
        if (singleton == null) {
            instance = new MinePanelSingleton(new ArrayList<>(0));
        }
        if (panel != null) {
            instance = new MinePanelSingleton(panel);
        }
        return instance;
    }

    public List<HorizontalLine> getPanel() {
        return panel;
    }
}
