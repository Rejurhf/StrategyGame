package pl.rejurhf.entities;

public class Elves extends Race {

    public Elves(UnitInstance unitInstance, String raceColor, int raceType, int id){
        super(unitInstance, raceColor, raceType, id);

        // Add race constants
        this.spawnCapitolConst = 3;
        this.toSpawnCapitolCount = spawnCapitolConst;
        this.breedingFromUnitConst = 20;
        this.breedingFromCapitolConst = 2;
        this.unitPowerConst = 5;
        this.isPowerFromCapitolDependent = true;
    }
}
