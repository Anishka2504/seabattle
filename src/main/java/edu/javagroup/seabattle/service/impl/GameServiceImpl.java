package edu.javagroup.seabattle.service.impl;

import edu.javagroup.seabattle.service.GameService;
import edu.javagroup.seabattle.service.PanelService;
import edu.javagroup.seabattle.service.PointService;
import edu.javagroup.seabattle.service.ShipService;
import edu.javagroup.seabattle.singleton.EnemyReadySingleton;
import edu.javagroup.seabattle.singleton.ForbiddenCellsSingleton;
import edu.javagroup.seabattle.singleton.ImReadySingleton;
import edu.javagroup.seabattle.singleton.SettingsSingleton;
import org.springframework.stereotype.Component;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

import static edu.javagroup.seabattle.constants.Constants.ENEMY_IP_ADDRESS;
import static edu.javagroup.seabattle.util.StringUtils.isNotEmpty;

/**
 * @author Anna Drozdovskaya
 * @version 1.1
 * @see edu.javagroup.seabattle.service.PanelService
 * @see edu.javagroup.seabattle.service.PointService
 * @see edu.javagroup.seabattle.service.ShipService
 * @see edu.javagroup.seabattle.singleton.EnemyReadySingleton
 * @see edu.javagroup.seabattle.singleton.ImReadySingleton
 * @see edu.javagroup.seabattle.singleton.SettingsSingleton
 * @see edu.javagroup.seabattle.singleton.ForbiddenCellsSingleton
 */

@Component
public class GameServiceImpl implements GameService {

    private final PanelService panelService;
    private final PointService pointService;
    private final ShipService shipService;

    public GameServiceImpl(PanelService panelService, PointService pointService, ShipService shipService) {
        this.panelService = panelService;
        this.pointService = pointService;
        this.shipService = shipService;
    }

    @Override
    public boolean imReady() {
        if (isFullMinePanel()) {
            if (!checkShipCount()) {
                JOptionPane.showMessageDialog
                        (null,
                                "Корабли расставлены неправильно",
                                "Внимание!", JOptionPane.WARNING_MESSAGE);
            } else {
                String enemyIPAddress = SettingsSingleton.instance(null).getSettingsByKey(ENEMY_IP_ADDRESS);
                if (isNotEmpty(enemyIPAddress)) {
                    ImReadySingleton.instance(true);
                    ForbiddenCellsSingleton.instance(new HashMap<>(0));
                } else {
                    JOptionPane.showMessageDialog
                            (null,
                                    "Не указан ip-address врага",
                                    "Внимание!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog
                    (null,
                            "Еще не все корабли расставлены",
                            "Внимание!", JOptionPane.WARNING_MESSAGE);
        }
        return ImReadySingleton.instance(null).imReady();
    }

    @Override
    public boolean enemyReady() {
        EnemyReadySingleton.instance(true);
        boolean imReady = JOptionPane.showConfirmDialog
                (null,
                        "Клятый враг спрашивает, готов ли ты быть поверженным?",
                        "Окно подтверждения", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0;
        return imReady() && imReady;
    }

    @Override
    public void setShipPoint(char row, int col) {
        pointService.setShipPoint(row, col);
    }

    @Override
    public boolean isFullMinePanel() {
        return panelService.isFullMinePanel();
    }

    @Override
    public boolean getBomb(char row, int col) {
        return pointService.getBomb(row, col);
    }

    @Override
    public void setSidePoint(String side, char row, int col, int value) {
        pointService.setSidePoint(side, row, col, value);
    }

    @Override
    public boolean checkShipCount() {
        return shipService.checkShipCount();
    }

    // for task 16, not realized
    @Override
    public int checkShipCount(int deckCount) {
        return checkShipCount(deckCount);
    }

    @Override
    public boolean checkEndGame(String side) {
        return panelService.checkEndGame(side);
    }
}
