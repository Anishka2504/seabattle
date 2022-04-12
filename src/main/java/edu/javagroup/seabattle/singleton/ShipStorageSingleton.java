package edu.javagroup.seabattle.singleton;

import edu.javagroup.seabattle.model.HorizontalLine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see HorizontalLine
 */

public class ShipStorageSingleton {

    private static ShipStorageSingleton instance;
    private final Map<String, Integer> shipMap;

    private ShipStorageSingleton(Map<String, Integer> shipMap) {
        this.shipMap = shipMap;
    }

    public static ShipStorageSingleton instance(Map<String, Integer> shipMap) {
        if (instance == null) {
            instance = new ShipStorageSingleton(new HashMap<>());
        }
        if (shipMap != null) {
            instance = new ShipStorageSingleton(shipMap);
        }
        return instance;
    }

    public Map<String, Integer> getShipMap() {
        return shipMap;
    }
}
