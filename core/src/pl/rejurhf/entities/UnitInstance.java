package pl.rejurhf.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.support.UnitConstants;

public class UnitInstance extends Image {
    private final static int WIDTH = 20;
    private final static int HEIGHT = 20;
    private final static String PATHTOUNITS = "units\\";

    private int CAPITOL_ID;
    private int STARTING_X;
    private int STARTING_Y;

    public UnitInstance(int x, int y, int id){
        super(UnitConstants.getEmptySpaceTexture());

        CAPITOL_ID = 0;

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        // Starting position
        STARTING_X = x;
        STARTING_Y = y;
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public void changeSide(int id, String capitolColor){
        CAPITOL_ID = id;
        this.setDrawable(UnitConstants.getDrawable(capitolColor));
    }

    public int getXIndex() {
        return STARTING_X / WIDTH;
    }

    public int getYIndex() {
        return STARTING_Y / WIDTH;
    }
}