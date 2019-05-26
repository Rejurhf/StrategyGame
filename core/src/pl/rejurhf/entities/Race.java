package pl.rejurhf.entities;

import pl.rejurhf.StrategyGame;
import pl.rejurhf.screens.GameplayScreen;
import pl.rejurhf.support.PositionPair;
import pl.rejurhf.support.UnitConstants;

import java.util.*;

public class Race {
    private int ID;
    private int CAPITOL_X_INDEX;
    private int CAPITOL_Y_INDEX;
    private List<UnitInstance> raceUnitsList;
    private List<PositionPair> unitsToClaim;
    private String COLOR;

    public Race(UnitInstance unitInstance, String raceColor, int id) {
        // Set variables
        ID = id;
        COLOR = raceColor;
        CAPITOL_X_INDEX = unitInstance.getXIndex();
        CAPITOL_Y_INDEX = unitInstance.getYIndex();

        // Change UnitInstance to Capitol
        unitInstance.changeSide(ID, raceColor, true);

        // Initialize race unit list
        raceUnitsList = new ArrayList<UnitInstance>();
        unitsToClaim = new ArrayList<PositionPair>();
    }

    public void planNextMove(){
        System.out.println("Plan");
        LinkedList<PositionPair> possibleMoves = getPossibleMoves();

        System.out.println("Choose");
        int breedingAbility = (int)(1 + raceUnitsList.size()/10);
        if(possibleMoves.size() > breedingAbility){
            Random rand = new Random();

            for (int i = 0; i < breedingAbility; i++) {
                int tmpRand = rand.nextInt(possibleMoves.size());
                System.out.println(tmpRand);
                unitsToClaim.add(possibleMoves.remove(tmpRand));
            }
        }else if(possibleMoves.size() > 0){
            Random rand = new Random();

            for (int i = 0; i < possibleMoves.size(); i++) {
                int tmpRand = rand.nextInt(possibleMoves.size());
                System.out.println(tmpRand);
                unitsToClaim.add(possibleMoves.remove(tmpRand));
            }
        }
    }

    private LinkedList<PositionPair> getPossibleMoves() {
        LinkedList<PositionPair> possibleMoves = new LinkedList<PositionPair>();

        // Queue of units to visit starting with capitol
        Queue<PositionPair> unitsToVisit = new LinkedList<PositionPair>();
        unitsToVisit.add(new PositionPair(CAPITOL_X_INDEX, CAPITOL_Y_INDEX));

        List<PositionPair> visitedUnits = new ArrayList<PositionPair>();

        while (unitsToVisit.size() > 0){
            PositionPair currentPosition = unitsToVisit.poll();
            visitedUnits.add(currentPosition);

            if(currentPosition.X == 0){
                if(currentPosition.Y == 0){
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                            possibleMoves, unitsToVisit, visitedUnits);
                }else if(currentPosition.Y == StrategyGame.BOARD_HEIGHT-1){
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                            possibleMoves, unitsToVisit, visitedUnits);
                }else{
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                            possibleMoves, unitsToVisit, visitedUnits);
                }
            }else if(currentPosition.X == StrategyGame.BOARD_WIDTH-1){
                if(currentPosition.Y == 0){
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                            possibleMoves, unitsToVisit, visitedUnits);
                }else if(currentPosition.Y == StrategyGame.BOARD_HEIGHT-1){
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                            possibleMoves, unitsToVisit, visitedUnits);
                }else{
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                            possibleMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                            possibleMoves, unitsToVisit, visitedUnits);
                }
            }else if(currentPosition.Y == 0){
                // Previous cases covered corner options, thus only edge is checked
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                        possibleMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                        possibleMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                        possibleMoves, unitsToVisit, visitedUnits);

            }else if(currentPosition.Y == StrategyGame.BOARD_HEIGHT-1){
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                        possibleMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                        possibleMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                        possibleMoves, unitsToVisit, visitedUnits);

            }else{
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                        possibleMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                        possibleMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                        possibleMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                        possibleMoves, unitsToVisit, visitedUnits);
            }
        }

        return possibleMoves;
    }

    private void assignToArray(PositionPair testedPosition, List<PositionPair> possibleMoves,
                               Queue<PositionPair> unitsToVisit, List<PositionPair> visitedUnits) {
        int testedId = GameplayScreen.strategyArray[testedPosition.Y][testedPosition.X];

        if(testedId == UnitConstants.MOUNTAIN_ID){
            return;
        }else if(testedId == ID || testedId == (ID + 1)){
            if(!positionInArray(visitedUnits, testedPosition) && !positionInArray((List)unitsToVisit, testedPosition)){

                unitsToVisit.add(testedPosition);
            }
        }else if(testedId == UnitConstants.EMPTY_SPACE_ID){
            if(!positionInArray(possibleMoves, testedPosition)){

                possibleMoves.add(testedPosition);
            }
        }
    }

    public boolean positionInArray(List<PositionPair> array, PositionPair testedPosition){
        for(PositionPair position : array){
            if(position.equals(testedPosition))
                return true;
        }

        return false;
    }

    public void executeMovePlan(List<UnitInstance> unitsList){
        while (unitsToClaim.size() > 0){
            PositionPair claimingUnit = unitsToClaim.remove(unitsToClaim.size()-1);
            int indexNumber = claimingUnit.Y * StrategyGame.BOARD_WIDTH + claimingUnit.X;

            System.out.println(ID + " " + claimingUnit.toString() + " " + indexNumber);

            unitsList.get(indexNumber).changeSide(ID+1, COLOR);
            raceUnitsList.add(unitsList.get(indexNumber));
        }
    }
}
