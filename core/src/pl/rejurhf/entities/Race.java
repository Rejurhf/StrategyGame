package pl.rejurhf.entities;

import pl.rejurhf.support.PositionPair;

import java.util.List;

public class Race {
    private int ID;
    private int CAPITOL_X_INDEX;
    private int CAPITOL_Y_INDEX;
    private List<PositionPair> raceUnitsList;

    public Race(UnitInstance unitInstance, String raceColor, int id) {
        // Set variables
        ID = id;
        CAPITOL_X_INDEX = unitInstance.getXIndex();
        CAPITOL_Y_INDEX = unitInstance.getYIndex();

        // Change UnitInstance to Capitol
        unitInstance.changeSide(ID, raceColor, true);
    }
}
