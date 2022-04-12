package edu.javagroup.seabattle.controller.api;

/**
 * @author kaa
 * @version 1.0
 */
public interface EnemyService {

    Boolean lanTest();

    Boolean areYouReady();

    Boolean sendBomb(int row, int col);
}
