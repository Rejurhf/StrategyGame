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

        this.setDrawable(UnitConstants.getDrawable(capitolColor));
    }

    public void changeSide(int id, String capitolColor, boolean isCapitol){
        this.isCapitol = isCapitol;

        changeSide(id, capitolColor);
    }

    public int getXIndex() {
        return STARTING_X / StrategyGame.UNIT_LEN;
    }

    public int getYIndex() {
        return STARTING_Y / StrategyGame.UNIT_LEN;
    }
}
