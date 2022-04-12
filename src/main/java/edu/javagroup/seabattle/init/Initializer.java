package edu.javagroup.seabattle.init;

import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.singleton.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.javagroup.seabattle.constants.Constants.DECK;
import static edu.javagroup.seabattle.constants.Constants.VERTICAL_COORDINATE;

/**
 * @author Anna Drozdovskaya
 * @version 1.1
 */

public class Initializer {

    public void init() {
        SettingsSingleton.instance(new HashMap<>());
        initPanels();
    }

    public void initPanels() {

        MyStepSingleton.instance(true);
        ImReadySingleton.instance(false);
        EnemyReadySingleton.instance(false);
        ForbiddenCellsSingleton.instance(new HashMap<>());

        Map<String, Integer> shipMap = new HashMap<>(4);
        for (int i = 1; i <= 4; i++) {
            shipMap.put(i + DECK, 0);
        }
        ShipStorageSingleton.instance(shipMap);

        List<HorizontalLine> myPanel = new ArrayList<>();
        for (int i = 0; i < VERTICAL_COORDINATE.length(); i++) {
            myPanel.add(new HorizontalLine(VERTICAL_COORDINATE.charAt(i)));
        }
        MinePanelSingleton.instance(myPanel);

        List<HorizontalLine> enemyPanel = new ArrayList<>();
        for (int i = 0; i < VERTICAL_COORDINATE.length(); i++) {
            enemyPanel.add(new HorizontalLine(VERTICAL_COORDINATE.charAt(i)));
        }
        EnemyPanelSingleton.instance(enemyPanel);
    }
}
