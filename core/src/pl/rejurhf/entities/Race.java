package pl.rejurhf.entities;

import javafx.util.Pair;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.screens.GameplayScreen;
import pl.rejurhf.support.PositionPair;
import pl.rejurhf.support.UnitConstants;

import java.util.*;

public class Race {
    private int ID;
    private int RACE_TYPE;
    private int CAPITOL_X_INDEX;
    private int CAPITOL_Y_INDEX;
    private ArrayList<UnitInstance> raceUnitsList;
    private List<PositionPair> unitsToClaim;
    private List<Integer> enemyList;
    private String COLOR;
    private boolean hasCapitol = true;

    // Race specific const
    int toSpawnCapitolCount;
    int spawnCapitolConst;
    int breedingFromUnitConst;
    int breedingFromCapitolConst;
    int unitPowerConst;
    boolean isPowerFromCapitolDependent;

    public Race(UnitInstance unitInstance, String raceColor, int raceType, int id) {
        // Set variables
        ID = id;
        COLOR = raceColor;
        CAPITOL_X_INDEX = unitInstance.getXIndex();
        CAPITOL_Y_INDEX = unitInstance.getYIndex();
        RACE_TYPE = raceType;

        // Change UnitInstance to Capitol
        unitInstance.changeSide(ID, COLOR, true);

        // Initialize race unit list
        raceUnitsList = new ArrayList<UnitInstance>();
        unitsToClaim = new ArrayList<PositionPair>();
        enemyList = new ArrayList<Integer>();

        raceUnitsList.add(unitInstance);

        // Add race constants
        spawnCapitolConst = 5;
        toSpawnCapitolCount = spawnCapitolConst;
        breedingFromUnitConst = 20;
        breedingFromCapitolConst = 1;
        unitPowerConst = 1;
        isPowerFromCapitolDependent = true;

        //TODO remove adding this to enemy list
        addRaceToEnemyList(1);
        addRaceToEnemyList(3);
        addRaceToEnemyList(5);
        addRaceToEnemyList(7);
        addRaceToEnemyList(9);
        addRaceToEnemyList(11);
        removeRaceFromEnemyList(ID);
    }

    public void planNextMove(){
        // Get possible moves to empty spaces and to enemy units
        LinkedList<PositionPair> possibleMoves = new LinkedList<PositionPair>();
        LinkedList<Pair<Integer, PositionPair>> possibleWarMoves = new LinkedList<Pair<Integer, PositionPair>>();
        assignPossibleMoves(possibleMoves, possibleWarMoves);

        // Get breeding ability of race
        int breedingAbility = 0;
        if(hasCapitol) {
            breedingAbility = (int) (raceUnitsList.size() / breedingFromUnitConst) + breedingFromCapitolConst;
        }else if(raceUnitsList.size() > 10){
            // if no capitol count to capitol rebuild
            breedingAbility = (int) (raceUnitsList.size() / (2*breedingFromUnitConst));
            toSpawnCapitolCount--;
            if(toSpawnCapitolCount == 0){
                this.makeNewCapitol();
            }
        }

        // Info text
        if(raceUnitsList.size() == 0){
            System.out.println(ID + " Race " + UnitConstants.getRaceName(RACE_TYPE) + " " +
                    UnitConstants.getColorName(COLOR) + " is dead");
        }else{
            if(!hasCapitol)
                System.out.print("No capitol ");
            System.out.println(ID + " " + UnitConstants.getRaceName(RACE_TYPE) + " " +
                    UnitConstants.getColorName(COLOR) + ": Moves: " + possibleMoves.size() + " War moves: " +
                    possibleWarMoves.size() + " Breeding: " + breedingAbility + " Units: " + raceUnitsList.size());
        }

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
        }else if(testedId > 0 && enemyList.contains(testedId)){
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

            if(unitID == 0){
                // Claim empty space
                unitsList.get(indexNumber).changeSide(ID+1, COLOR);
                raceUnitsList.add(unitsList.get(indexNumber));
            }else if(unitID > 0 && unitID%2 == 0){
                // Claim enemy unit
                // if battle is won claim position and if this is an enemy
                if(isVictoryAchieved(unitsList.get(indexNumber)) && enemyList.contains(unitID)){
                    // ((unitID+1)/2)-1 eq. (7+1/2-1)=3 which is index in list
                    GameplayScreen.raceList.get(((unitID+1)/2)-1).loseUnit(unitsList.get(indexNumber), ID);
                    unitsList.get(indexNumber).changeSide(ID+1, COLOR);
                    raceUnitsList.add(unitsList.get(indexNumber));
                }
            }else {
                // Claim enemy capitol if this is an enemy, battle is won and you are lucky
                if(enemyList.contains(unitID) && isVictoryAchieved(unitsList.get(indexNumber)) && capitolClaimingLuck()){
                    // ((unitID+1)/2)-1 eq. (7+1/2-1)=3 which is index in list
                    GameplayScreen.raceList.get(((unitID+1)/2)-1).loseUnit(unitsList.get(indexNumber), ID);
                    unitsList.get(indexNumber).changeSide(ID+1, COLOR);
                    raceUnitsList.add(unitsList.get(indexNumber));
                }
            }
        }
    }

    // randomly chose if capitol can by taken
    private boolean capitolClaimingLuck() {
        // if random number is equals to 1 you can claim capitol
        Random rand = new Random();
        int tmpRand = rand.nextInt(5);
        return tmpRand == 1;
    }

    private void makeNewCapitol() {
        if(!hasCapitol){
            hasCapitol = true;
            toSpawnCapitolCount = spawnCapitolConst;

            // chose random unit from unit list
            Random rand = new Random();
            int tmpRand = rand.nextInt(raceUnitsList.size());

            // get unit to make it capitol
            UnitInstance unitInstance = raceUnitsList.get(tmpRand);

            CAPITOL_X_INDEX = unitInstance.getXIndex();
            CAPITOL_Y_INDEX = unitInstance.getYIndex();

            // Change UnitInstance to Capitol
            unitInstance.changeSide(ID, COLOR, true);
        }
    }

    public void loseUnit(UnitInstance unit, int enemyID){
        // if capitol lose capitol
        if(GameplayScreen.strategyArray[unit.getYIndex()][unit.getXIndex()] == ID){
            hasCapitol = false;
            unit.loseCapitolStatus();
        }
        for(int i = 0; i < raceUnitsList.size(); ++i){
            UnitInstance testedUnit = raceUnitsList.get(i);
            if(testedUnit.equals(unit)){
                raceUnitsList.remove(i);
                break;
            }
        }

        // if new enemy add to enemy list
        addRaceToEnemyList(enemyID);
    }

    // Add capitol and unit as enemy
    private void addRaceToEnemyList(int enemyID) {
        if(!enemyList.contains(enemyID)){
            enemyList.add(enemyID);
            enemyList.add(enemyID+1);
        }
    }

    private void removeRaceFromEnemyList(int enemyID) {
        if(enemyList.contains(enemyID)){
            enemyList.remove(enemyList.indexOf(enemyID));
            enemyList.remove(enemyList.indexOf(enemyID+1));
        }
    }

    public boolean isVictoryAchieved(UnitInstance claimingUnit){
//        int indexNumber = claimingUnit.getYIndex() * StrategyGame.BOARD_WIDTH + claimingUnit.getXIndex();
        int unitID = GameplayScreen.strategyArray[claimingUnit.getYIndex()][claimingUnit.getXIndex()];

        // ((unitID+1)/2)-1 eq. (7+1/2-1)=3 which is index in list
        int raceIndexInList = ((unitID+1)/2)-1;

        // calculate distance from capitol and divide it by max distance square root/
        int enemyPower = GameplayScreen.raceList.get(raceIndexInList).calculateUnitPower(claimingUnit);
        int thisUnitPower = this.calculateUnitPower(claimingUnit);

        Random rand = new Random();
        int tmpRand = rand.nextInt(enemyPower + thisUnitPower);

        return tmpRand >= enemyPower;
    }

    public int calculateUnitPower(UnitInstance claimingUnit){
        // max distance
        int maxDistance = 87;
        // constant to make max power max value equals 10
        int constantDiv = 9;
        int power = 0;

        if(isPowerFromCapitolDependent){
            power = (int)((maxDistance - (int) Math.sqrt(Math.abs(CAPITOL_X_INDEX - claimingUnit.getXIndex()) *
                    Math.abs(CAPITOL_X_INDEX - claimingUnit.getXIndex()) +
                    Math.abs(CAPITOL_Y_INDEX - claimingUnit.getYIndex()) *
                            Math.abs(CAPITOL_Y_INDEX - claimingUnit.getYIndex()))) / constantDiv) + unitPowerConst;
        }else{
            power = (int) (87 / 9)/2 + unitPowerConst;
        }

        if(power < 1)
            return 1;

        return power;
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

    public void setCAPITOL_X_INDEX(int CAPITOL_X_INDEX) {
        this.CAPITOL_X_INDEX = CAPITOL_X_INDEX;
    }

    public void setCAPITOL_Y_INDEX(int CAPITOL_Y_INDEX) {
        this.CAPITOL_Y_INDEX = CAPITOL_Y_INDEX;
    }
}
