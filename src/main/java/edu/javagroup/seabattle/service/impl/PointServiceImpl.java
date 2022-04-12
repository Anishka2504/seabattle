package edu.javagroup.seabattle.service.impl;

import edu.javagroup.seabattle.exception.SideNotFoundException;
import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.service.PanelService;
import edu.javagroup.seabattle.service.PointService;
import edu.javagroup.seabattle.singleton.EnemyPanelSingleton;
import edu.javagroup.seabattle.singleton.ForbiddenCellsSingleton;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import edu.javagroup.seabattle.singleton.MyStepSingleton;
import edu.javagroup.seabattle.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;
import java.util.Map;

import static edu.javagroup.seabattle.constants.Constants.ENEMY;
import static edu.javagroup.seabattle.constants.Constants.MINE;

/**
 * @author Konchits Nikita
 * @version 1.9
 * @see edu.javagroup.seabattle.service.PanelService
 * @see edu.javagroup.seabattle.singleton.EnemyPanelSingleton
 * @see edu.javagroup.seabattle.singleton.ForbiddenCellsSingleton
 * @see edu.javagroup.seabattle.singleton.MinePanelSingleton
 * @see edu.javagroup.seabattle.singleton.MyStepSingleton
 * @see edu.javagroup.seabattle.util.StringUtils
 */

@Component
public class PointServiceImpl implements PointService {

    private final PanelService panelService;

    public PointServiceImpl(PanelService panelService) {
        this.panelService = panelService;
    }

    @Override
    public void setShipPoint(char row, int col) {
        if (isClearPoint(row, col)) {
            addShipPoint(row, col);
        } else {
            clearShipPoint(row, col);
        }
    }

    @Override
    public boolean setSidePoint(String side, char row, int col, int value) {
        List<HorizontalLine> panel;
        if (side.equalsIgnoreCase(MINE)) {
            panel = MinePanelSingleton.instance(null).getPanel();
        } else if (side.equalsIgnoreCase(ENEMY)) {
            panel = EnemyPanelSingleton.instance(null).getPanel();
        }
        else {
            throw new SideNotFoundException();
        }
        for (HorizontalLine horizontalLine : panel) {
            if (row == horizontalLine.getRow()) {
                for (PointElement pointElement : horizontalLine.getPointElementList()) {
                    if (col == pointElement.getCol()) {
                        pointElement.setValue(value);
                        if (side.equalsIgnoreCase(MINE)) {
                            MinePanelSingleton.instance(panel);
                        } else {
                            EnemyPanelSingleton.instance(panel);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isClearPoint(char row, int col) {
        return isOccupiedCell(row, col, 0);
    }

    @Override
    public boolean getBomb(char row, int col) {
        if (isOccupiedCell(row, col, 0) || isOccupiedCell(row, col, 2)) {
            setSidePoint(MINE, row, col, 3);
            MyStepSingleton.instance(true);
        }
        if (isOccupiedCell(row, col, 1)) {
            setSidePoint(MINE, row, col, 2);
            MyStepSingleton.instance(false);
            return true;
        }
        return false;
    }



    private void addShipPoint(char row, int col) {
        Map<String, Boolean> map = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
        if (!map.getOrDefault(col < 10 ? row + "0" + col : row + "" + col, false)) {
            if (!panelService.isFullMinePanel()) {
                if (setSidePoint(MINE, row, col, 1)) {
                    setForbiddenCells();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Нельзя использовать эту ячейку",
                            "Внимание!",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Уже занято допустимое количество ячеек",
                        "Внимание!",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Не удалось использовать эту ячейку",
                    "Внимание!",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void clearShipPoint(char row, int col) {
        setSidePoint(MINE, row, col, 0);
        setForbiddenCells();
    }

    private boolean isOccupiedCell(char row, int col, int value) {
        List<HorizontalLine> horizontalLines = MinePanelSingleton.instance(null).getPanel();
        for (HorizontalLine line : horizontalLines) {
            if (row == line.getRow()) {
                List<PointElement> pointElementList = line.getPointElementList();
                for (PointElement element : pointElementList) {
                    if (col == element.getCol()) {
                        return element.getValue() == value;
                    }
                }
            }
        }
        return false;
    }

    private void setForbiddenCells() {
        Map<String, Boolean> cellsMap = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
        cellsMap.clear();
        List<HorizontalLine> horizontalLines = MinePanelSingleton.instance(null).getPanel();
        for (HorizontalLine horizontalLine : horizontalLines) {
            for (PointElement pointElement : horizontalLine.getPointElementList()) {
                if (pointElement.getValue() == 1) {
                    char row = horizontalLine.getRow();
                    int col = pointElement.getCol();
                    String letterBefore = Character.toString(StringUtils.letterBefore(row));
                    String letterAfter = Character.toString(StringUtils.letterAfter(row));
                    String colAfter = col < 10 ? "0" + (col + 1) : "" + (col + 1);
                    String colBefore = col < 10 ? "0" + (col - 1) : "" + (col - 1);
                    cellsMap.put(row + col < 10 ? "0" + col : "" + col, true);
                    cellsMap.put(letterAfter + colBefore, true);
                    cellsMap.put(letterAfter + colAfter, true);
                    cellsMap.put(letterBefore + colBefore, true);
                    cellsMap.put(letterBefore + colAfter, true);
                }
            }
        }
    }
}
