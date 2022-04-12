package edu.javagroup.seabattle.model;

import edu.javagroup.seabattle.model.parent.ModelValue;
import lombok.Getter;

/**@author Konchits Nikita
 * @version 1.0
 */

public class ShipPoint extends ModelValue implements Comparable<ShipPoint> {

    @Getter
    private final int point;

    public ShipPoint(int point, int value) {
        super(value);
        this.point = point;
    }

    @Override
    public int compareTo(ShipPoint shipPoint) {
        return point - shipPoint.getPoint();
    }
}
