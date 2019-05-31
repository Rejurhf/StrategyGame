package pl.rejurhf.entities;

import javafx.util.Pair;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.screens.GameplayScreen;
import pl.rejurhf.support.PositionPair;
import pl.rejurhf.support.UnitConstants;

import java.util.*;

public class Race {
    private int ID;
    private int CAPITOL_X_INDEX;



    private int CAPITOL_Y_INDEX;
    private ArrayList<UnitInstance> raceUnitsList;
    private List<PositionPair> unitsToClaim;
    private String COLOR;
    private boolean hasCapitol = true;

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

        // Get possible moves to empty spaces and to enemy units
        LinkedList<PositionPair> possibleMoves = new LinkedList<PositionPair>();
        LinkedList<Pair<Integer, PositionPair>> possibleWarMoves = new LinkedList<Pair<Integer, PositionPair>>();
        assignPossibleMoves(possibleMoves, possibleWarMoves);

        // Get breeding ability of race
        int breedingAbility = (int)(raceUnitsList.size()/10);
        if(hasCapitol)
            breedingAbility += 1;

        if(!hasCapitol)
            System.out.print("No capitol ");
        System.out.println(ID + " Breeding ability: " + breedingAbility + " number of units: " + raceUnitsList.size());

        // If there is more neighboring empty spaces than breeding ability
        if(possibleMoves.size() >= breedingAbility){
            Random rand = new Random();

            for (int i = 0; i < breedingAbility; i++) {
                int tmpRand = rand.nextInt(possibleMoves.size());
                unitsToClaim.add(possibleMoves.remove(tmpRand));
            }

        // if breeding ability greater than number of empty spaces in neighborhood
        }else if(possibleMoves.size() > 0 || possibleWarMoves.size() > 0){
            Random rand = new Random();

            for (int i = 0; i < possibleMoves.size(); i++) {
                int tmpRand = rand.nextInt(possibleMoves.size());
                unitsToClaim.add(possibleMoves.remove(tmpRand));
            }

            // If enemy neighbor units number greater than breeding ability
            if(possibleWarMoves.size() >= (breedingAbility - possibleMoves.size())){
                for (int i = 0; i < (breedingAbility - possibleMoves.size()); ++i){
                    int tmpRand = rand.nextInt(possibleWarMoves.size());
                    unitsToClaim.add(possibleWarMoves.remove(tmpRand).getValue());
                }

            // If enemy neighbor units number smaller than breeding ability
            }else{
                for (int i = 0; i < possibleWarMoves.size(); ++i){
                    int tmpRand = rand.nextInt(possibleWarMoves.size());
                    unitsToClaim.add(possibleWarMoves.remove(tmpRand).getValue());
                }
            }
        }
    }

    private void assignPossibleMoves(LinkedList<PositionPair> possibleMoves,
                                     LinkedList<Pair<Integer, PositionPair>> possibleWarMoves) {
//        LinkedList<PositionPair> possibleMoves = new LinkedList<PositionPair>();

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
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                }else if(currentPosition.Y == StrategyGame.BOARD_HEIGHT-1){
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                }else{
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                }
            }else if(currentPosition.X == StrategyGame.BOARD_WIDTH-1){
                if(currentPosition.Y == 0){
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                }else if(currentPosition.Y == StrategyGame.BOARD_HEIGHT-1){
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                }else{
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                    assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                            possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                }
            }else if(currentPosition.Y == 0){
                // Previous cases covered corner options, thus only edge is checked
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);

            }else if(currentPosition.Y == StrategyGame.BOARD_HEIGHT-1){
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);

            }else{
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y-1),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X, currentPosition.Y+1),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X-1, currentPosition.Y),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
                assignToArray(new PositionPair(currentPosition.X+1, currentPosition.Y),
                        possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
            }
        }

//        return possibleMoves;
    }

    private void assignToArray(PositionPair testedPosition, List<PositionPair> possibleMoves,
                               LinkedList<Pair<Integer, PositionPair>> possibleWarMoves,
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
        }else if(testedId > 0){
            if(!positionInArrayWar(possibleWarMoves, testedPosition)){
                possibleWarMoves.add(new Pair<Integer, PositionPair>(testedId, testedPosition));
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

    public boolean positionInArrayWar(LinkedList<Pair<Integer, PositionPair>> array, PositionPair testedPosition){
        for(Pair pair : array){
            if(pair.getValue().equals(testedPosition))
                return true;
        }

        return false;
    }

    public void executeMovePlan(List<UnitInstance> unitsList){
        while (unitsToClaim.size() > 0){
            PositionPair claimingUnit = unitsToClaim.remove(unitsToClaim.size()-1);
            int indexNumber = claimingUnit.Y * StrategyGame.BOARD_WIDTH + claimingUnit.X;
            int unitID = GameplayScreen.strategyArray[claimingUnit.Y][claimingUnit.X];

//            if(!hasCapitol)
//                System.out.print("No capitol ");
//            System.out.println(ID + " claims: " + claimingUnit.toString() + " " + indexNumber + " ID: " + unitID);

            if(unitID == 0){
                unitsList.get(indexNumber).changeSide(ID+1, COLOR);
                raceUnitsList.add(unitsList.get(indexNumber));
            }else if(unitID > 0 && unitID%2 == 0){
                // if battle is won claim position
                if(isVictoryAchieved(unitsList.get(indexNumber))){
                    // ((unitID+1)/2)-1 eq. (7+1/2-1)=3 which is index in list
                    GameplayScreen.raceList.get(((unitID+1)/2)-1).loseUnit(unitsList.get(indexNumber));
                    unitsList.get(indexNumber).changeSide(ID+1, COLOR);
                    raceUnitsList.add(unitsList.get(indexNumber));
                }
            }else {
                // ((unitID+1)/2)-1 eq. (7+1/2-1)=3 which is index in list
                GameplayScreen.raceList.get(((unitID+1)/2)-1).loseUnit(unitsList.get(indexNumber));
                unitsList.get(indexNumber).changeSide(ID+1, COLOR);
                raceUnitsList.add(unitsList.get(indexNumber));
            }
        }
    }

    public void loseUnit(UnitInstance unit){
        if(GameplayScreen.strategyArray[unit.getYIndex()][unit.getXIndex()] == ID){
            hasCapitol = false;
            return;
        }
        for(int i = 0; i < raceUnitsList.size(); ++i){
            UnitInstance testedUnit = raceUnitsList.get(i);
            if(testedUnit.equals(unit)){
                raceUnitsList.remove(i);
                break;
            }
        }
    }

    public boolean isVictoryAchieved(UnitInstance claimingUnit){
        int indexNumber = claimingUnit.getYIndex() * StrategyGame.BOARD_WIDTH + claimingUnit.getXIndex();
        int unitID = GameplayScreen.strategyArray[claimingUnit.getYIndex()][claimingUnit.getXIndex()];

        // ((unitID+1)/2)-1 eq. (7+1/2-1)=3 which is index in list
        int raceIndexInList = ((unitID+1)/2)-1;
        // max distance
        int maxDistance = 87;
        // constant to make max power max value equals 10
        int constantDiv = 9;
        
        int enemyCapitolX = GameplayScreen.raceList.get(raceIndexInList).getCAPITOL_X_INDEX();
        int enemyCapitolY = GameplayScreen.raceList.get(raceIndexInList).getCAPITOL_Y_INDEX();

        // calculate distance from capitol and divide it by max distance square root/
        int enemyPower = (int)((maxDistance - (int) Math.sqrt(Math.abs(enemyCapitolX - claimingUnit.getXIndex()) *
                Math.abs(enemyCapitolX - claimingUnit.getXIndex()) + 
                Math.abs(enemyCapitolY - claimingUnit.getYIndex()) *
                Math.abs(enemyCapitolY - claimingUnit.getYIndex()))) / constantDiv) + 1;
        int thisUnitPower = (int)((maxDistance - (int) Math.sqrt(Math.abs(CAPITOL_X_INDEX - claimingUnit.getXIndex()) *
                Math.abs(CAPITOL_X_INDEX - claimingUnit.getXIndex()) +
                Math.abs(CAPITOL_Y_INDEX - claimingUnit.getYIndex()) * 
                Math.abs(CAPITOL_Y_INDEX - claimingUnit.getYIndex()))) / constantDiv) + 1;

        Random rand = new Random();
        int tmpRand = rand.nextInt(enemyPower + thisUnitPower);

        return tmpRand >= enemyPower;
    }



    /*

    Getters and Setters

     */

    public int getID() {
        return ID;
    }

    public int getCAPITOL_X_INDEX() {
        return CAPITOL_X_INDEX;
    }

    public int getCAPITOL_Y_INDEX() {
        return CAPITOL_Y_INDEX;
    }
}
