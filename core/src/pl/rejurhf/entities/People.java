package pl.rejurhf.entities;

public class People extends Race {

    public People(UnitInstance unitInstance, String raceColor, int raceType, int id){
        super(unitInstance, raceColor, raceType, id);

        // Add race constants
        this.spawnCapitolConst = 5;
        this.toSpawnCapitolCount = spawnCapitolConst;
        this.breedingFromUnitConst = 10;
        this.breedingFromCapitolConst = 1;
        this.unitPowerConst = 1;
        this.isPowerFromCapitolDependent = true;
    }
}
