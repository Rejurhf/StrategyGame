package pl.rejurhf.entities;

import pl.rejurhf.screens.GameplayScreen;

public class Orcs extends Race {

    public Orcs(UnitInstance unitInstance, String raceColor, int raceType, int id){
        super(unitInstance, raceColor, raceType, id);

        // Add race constants
        this.spawnCapitolConst = 7;
        this.toSpawnCapitolCount = spawnCapitolConst;
        this.breedingFromUnitConst = 25;
        this.breedingFromCapitolConst = 3;
        this.unitPowerConst = 14;
        this.isPowerFromCapitolDependent = true;

        this.createEnemyList();
    }

    private void createEnemyList() {
        // Orcs attacks everyone
        for(int i = 0; i < GameplayScreen.raceList.size(); ++i){
            // For index 0 it will be 2*0+1=1
            addRaceToEnemyList(2*i + 1);
        }
        removeRaceFromEnemyList(ID);
    }
}
