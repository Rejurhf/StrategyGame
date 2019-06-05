package pl.rejurhf.entities;

import javafx.util.Pair;
import pl.rejurhf.screens.GamePlayScreen;
import pl.rejurhf.support.PositionPair;
import pl.rejurhf.support.UnitConstants;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class People extends Race {
    private boolean isNothingToTake;

    public People(UnitInstance unitInstance, String raceColor, int raceType, int id){
        super(unitInstance, raceColor, raceType, id);

        // Add race constants
        this.spawnCapitolConst = 5;
        this.toSpawnCapitolCount = spawnCapitolConst;
        this.breedingFromUnitConst = 10;
        this.breedingFromCapitolConst = 1;
        this.unitPowerConst = 1;
        this.isPowerFromCapitolDependent = true;
        this.isNothingToTake = false;
    }


    @Override
    void choseUnitsToConcur(LinkedList<PositionPair> possibleMoves, LinkedList<Pair<Integer, PositionPair>> possibleWarMoves, int breedingAbility) {
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
        }else if(possibleMoves.size() == 0 && possibleWarMoves.size() == 0){
            isNothingToTake = true;
        }
    }

    @Override
    void assignToArray(PositionPair testedPosition, List<PositionPair> possibleMoves, LinkedList<Pair<Integer, PositionPair>> possibleWarMoves, Queue<PositionPair> unitsToVisit, List<PositionPair> visitedUnits) {
//        super.assignToArray(testedPosition, possibleMoves, possibleWarMoves, unitsToVisit, visitedUnits);
        int testedId = GamePlayScreen.strategyArray[testedPosition.Y][testedPosition.X];

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
        }else if(isNothingToTake && testedId > 0 && !enemyList.contains(testedId)){
            if(testedId % 2 == 0)
                addRaceToEnemyList(testedId - 1);
            else
                addRaceToEnemyList(testedId);

            isNothingToTake = false;
        }
    }
}
