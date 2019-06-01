package pl.rejurhf.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.screens.GameplayScreen;
import pl.rejurhf.support.UnitConstants;

public class UnitInstance extends Image {
    private final static int WIDTH = 20;
    private final static int HEIGHT = 20;
    private final static String PATHTOUNITS = "units\\";

    private boolean isCapitol = false;
    private int STATUS_ID;
    private int STARTING_X;
    private int STARTING_Y;
    public UnitInstance(int x, int y, int id){
        super(UnitConstants.getEmptySpaceTexture());

        STATUS_ID = 0;

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        // Starting position
        STARTING_X = x;
        STARTING_Y = y;
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public void changeSide(int id, String capitolColor){
        // Change status in strategy array and in instance
        STATUS_ID = id;
        GameplayScreen.strategyArray[getYIndex()][getXIndex()] = id;

        String unitColor = "";
        if(id == -1)
            unitColor = UnitConstants.MOUNTAIN;
        else if(id == 0)
            unitColor = UnitConstants.EMPTY_SPACE;
        else if(id%2 == 0){
            if(capitolColor.equals(UnitConstants.BLUE_CAPITOL))
                unitColor = UnitConstants.BLUE_UNIT;
            else if(capitolColor.equals(UnitConstants.GREEN_CAPITOL))
                unitColor = UnitConstants.GREEN_UNIT;
            else if(capitolColor.equals(UnitConstants.PINK_CAPITOL))
                unitColor = UnitConstants.PINK_UNIT;
            else if(capitolColor.equals(UnitConstants.RED_CAPITOL))
                unitColor = UnitConstants.RED_UNIT;
            else if(capitolColor.equals(UnitConstants.SEA_CAPITOL))
                unitColor = UnitConstants.SEA_UNIT;
            else if(capitolColor.equals(UnitConstants.YELLOW_CAPITOL))
                unitColor = UnitConstants.YELLOW_UNIT;
        } else
            unitColor = capitolColor;

        this.setDrawable(UnitConstants.getDrawable(unitColor));
    }

    void changeSide(int id, String capitolColor, boolean isCapitol){
        if(isCapitol)
            this.giveCapitolStatus();

        changeSide(id, capitolColor);
    }

    boolean equals(UnitInstance unit){
        return unit.getXIndex() == this.getXIndex() && unit.getYIndex() == this.getYIndex();
    }

    void loseCapitolStatus() {
        if(isCapitol)
            isCapitol = false;
    }

    private void giveCapitolStatus(){
        if(!isCapitol){
            isCapitol = true;
        }
    }



    /*

    Getters and Setters

     */
    public int getXIndex() {
        return STARTING_X / StrategyGame.UNIT_LEN;
    }

    public int getYIndex() {
        return STARTING_Y / StrategyGame.UNIT_LEN;
    }

    public int getSTATUS_ID() {
        return STATUS_ID;
    }
}
