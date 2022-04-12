package edu.javagroup.seabattle.service;

/**
 * @author Anna Drozdovskaya
 * @version 1.0
 */

public interface PanelService {

    boolean isPanelEmpty();

    boolean isFullMinePanel();

    boolean checkEndGame(String side);
}
