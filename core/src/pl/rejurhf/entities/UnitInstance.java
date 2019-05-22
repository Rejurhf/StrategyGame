package pl.rejurhf.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.constants.UnitConstants;

public class UnitInstance extends Image {
    private final static int WIDTH = 20;
    private final static int HEIGHT = 20;
    private final static String PATHTOUNITS = "units\\";

    private int CAPITOL_ID;
    private int STARTING_X;
    private int STARTING_Y;

    public UnitInstance(int x, int y){
        super(UnitConstants.getEmptySpaceTexture());

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        // Starting position
        STARTING_X = x;
        STARTING_Y = y;
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public void changeSide(){
        this.setDrawable(UnitConstants.getDrawable(UnitConstants.BLUE_CAPITOL));
    }
}
