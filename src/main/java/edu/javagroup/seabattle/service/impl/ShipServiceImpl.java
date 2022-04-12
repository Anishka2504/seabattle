package edu.javagroup.seabattle.service.impl;

import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.model.ShipPoint;
import edu.javagroup.seabattle.service.ShipService;
import edu.javagroup.seabattle.singleton.ImReadySingleton;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import edu.javagroup.seabattle.singleton.ShipStorageSingleton;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.*;

import static edu.javagroup.seabattle.constants.Constants.DECK;
import static edu.javagroup.seabattle.constants.Constants.VERTICAL_COORDINATE;

/**
 * @author Anna Drozdovskaya
 * @version 1.2
 * @see edu.javagroup.seabattle.model.HorizontalLine
 * @see edu.javagroup.seabattle.model.ShipPoint
 * @see edu.javagroup.seabattle.singleton.MinePanelSingleton
 * @see edu.javagroup.seabattle.singleton.ShipStorageSingleton
 */

@Component
public class ShipServiceImpl implements ShipService {

    private List<ShipPoint> coordinateList;

    @Override
    public boolean checkShipCount() {
        getCoordinateList(MinePanelSingleton.instance(null).getPanel());
        Map<String, Integer> map = new HashMap<>(4);
        for (int i = 4; i > 0; i--) {
            map.put("" + i + DECK, findShipDeck(i));
        }
        ShipStorageSingleton.instance(map);
        return map.get("4" + DECK) == 1 && map.get("3" + DECK) == 2
                && map.get("2" + DECK) == 3 && map.get("1" + DECK) == 4;
    }

    // for task 17, not realized
    @Override
    public int checkShipCount(int deckCount) {
        if (deckCount > 4) {
            JOptionPane.showMessageDialog
                    (null,
                            "Кораби расставлены неправильно",
                            "Внимание!", JOptionPane.WARNING_MESSAGE);
            MinePanelSingleton.instance(new ArrayList<>());
            return 0;
        } else if (deckCount < 1) {
            JOptionPane.showMessageDialog
                    (null,
                            "Кораби не расставлены",
                            "Внимание!", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        ImReadySingleton.instance(null).imReady();
        getCoordinateList(MinePanelSingleton.instance(null).getPanel());
        return findShipDeck(deckCount);
    }

    private void getCoordinateList(List<HorizontalLine> panel) {
        coordinateList = new ArrayList<>(220);
        coordinateList.addAll(getHorizontalCoordinateList(panel));
        coordinateList.addAll(getVerticalCoordinateList(panel));
        List<ShipPoint> shipPoints = new ArrayList<>();
        for (int i = 0; i < coordinateList.size() - 1; i++) {
            if (coordinateList.get(i).getValue() == 0 && coordinateList.get(i + 1).getValue() == 0) {
                shipPoints.add(coordinateList.get(i));
            }
            coordinateList.removeAll(shipPoints);
        }
        Collections.sort(coordinateList);
    }

    private List<ShipPoint> getHorizontalCoordinateList(List<HorizontalLine> panel) {
        List<ShipPoint> horizontalCoordinateList = new ArrayList<>(panel.size() * 11);
        int number = 1;
        for (HorizontalLine line : panel) {
            List<PointElement> pointElements = line.getPointElementList();
            for (PointElement element : pointElements) {
                horizontalCoordinateList.add(new ShipPoint(number, element.getValue()));
                number++;
            }
            if (horizontalCoordinateList.size() < 109) {
                horizontalCoordinateList.add(new ShipPoint(number, 0));
                number++;
            }
        }
        horizontalCoordinateList.add(new ShipPoint(number, 0));
        return horizontalCoordinateList;
    }

    private List<ShipPoint> getVerticalCoordinateList(List<HorizontalLine> panel) {
        List<ShipPoint> verticalCoordinateList = new ArrayList<>(panel.size() * 11);
        char rowLetter;
        int number = 111;
        for (int i = 0; i < VERTICAL_COORDINATE.length(); i++) {
            for (int j = 0; j < VERTICAL_COORDINATE.length(); j++) {
                rowLetter = VERTICAL_COORDINATE.charAt(j);
                for (HorizontalLine line : panel) {
                    if (line.getRow() == rowLetter) {
                        List<PointElement> pointElements = line.getPointElementList();
                        verticalCoordinateList.add(new ShipPoint(number, pointElements.get(i).getValue()));
                        number++;
                    }
                }
            }
            if (verticalCoordinateList.size() < 109) {
                verticalCoordinateList.add(new ShipPoint(number, 0));
                number++;
            }
        }
        verticalCoordinateList.add(new ShipPoint(number, 0));
        return verticalCoordinateList;
    }

    private int findShipDeck(int deckCount) {
        StringBuilder allValues = new StringBuilder();
        int number = 0;
        for (ShipPoint coordinate : coordinateList) {
            allValues.append(coordinate.getValue());
        }
        String[] allDecks = allValues.toString().split("0");
        for (String deck : allDecks) {
            if (deck.length() == deckCount) {
                number++;
            }
        }
        return deckCount != 1 ? number : number / 6;
    }
}
