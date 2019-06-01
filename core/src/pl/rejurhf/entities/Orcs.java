package pl.rejurhf.entities;

public class Orcs extends Race {

    public Orcs(UnitInstance unitInstance, String raceColor, int raceType, int id){
        super(unitInstance, raceColor, raceType, id);

        // Add race constants
        this.spawnCapitolConst = 7;
        this.toSpawnCapitolCount = spawnCapitolConst;
        this.breedingFromUnitConst = 25;
        this.breedingFromCapitolConst = 3;
        this.unitPowerConst = 12;
        this.isPowerFromCapitolDependent = true;
    }
}
