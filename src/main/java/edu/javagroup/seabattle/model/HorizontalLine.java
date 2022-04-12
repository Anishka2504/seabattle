package edu.javagroup.seabattle.model;

import edu.javagroup.seabattle.model.parent.ModelRow;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**@author Konchits Nikita
 * @version 1.0
 */

public class HorizontalLine extends ModelRow implements Comparable<HorizontalLine> {

    @Setter
    @Getter
    private List<PointElement> pointElementList;

    public HorizontalLine(char row) {
        super(row);
        List<PointElement> colElement = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            colElement.add(new PointElement(i, 0));
        }
        this.pointElementList = colElement;
    }

    @Override
    public int compareTo(HorizontalLine horizontalLine) {
        return getRow() - horizontalLine.getRow();
    }
}


