package edu.javagroup.seabattle.singleton;

/**@author Konchits Nikita
 * @version 1.0
 * @see edu.javagroup.seabattle.model.HorizontalLine
 */

public class EnemyReadySingleton {


    private static EnemyReadySingleton instance;
    private final Boolean enemyReady;

    private EnemyReadySingleton(Boolean enemyReady) {
        this.enemyReady = enemyReady;
    }

    public static EnemyReadySingleton instance(Boolean enemyReady) {
        if (instance == null) {
            instance = new EnemyReadySingleton(false);
        }
        if (enemyReady != null) {
            instance = new EnemyReadySingleton(enemyReady);
        }
        return instance;
    }

    public Boolean enemyReady() {
        return enemyReady;
    }
}
