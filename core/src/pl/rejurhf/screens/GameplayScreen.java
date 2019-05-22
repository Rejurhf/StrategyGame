package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.constants.UnitConstants;
import pl.rejurhf.entities.UnitInstance;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends AbstractScreen {
    private Image bgImg;
    private List<UnitInstance> unitsList;
    private int[][] strategyArray;

    public GameplayScreen(StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        initBg();
        initBoard();
        initCapitols();
    }

    private void initCapitols() {
        //TODO
        // Add constant size of board to main function

        // x max is 49 for it to work properly
        int x = 49;
        // y max is 69 for it to work properly
        int y = 69;
        int boardWidth = 70;
        unitsList.get(x*boardWidth + y).changeSide();
    }

    // Board initialization
    private void initBoard() {
        unitsList = new ArrayList<UnitInstance>();
        // Array for strategy purpose
        strategyArray = new int[50][70];

        // Populate board, i and j inverted due to more logic insertion to ArrayList
        // (starting in left top corner, ending in right bottom corner, going row by row)
        for (int i = 49; i >= 0; i--) {
            for (int j = 0; j < 70; j++) {
                // Create default instances of empty space
                UnitInstance newUnit = new UnitInstance(j*20, i*20);
                unitsList.add(newUnit);
                stage.addActor(unitsList.get(unitsList.size() - 1));

                // 50-i cause 50th element on screen (top left) is first element in array (top left)
                strategyArray[49-i][j] = 0;
            }
        }
    }


    // set background
    private void initBg() {
        bgImg = new Image(new Texture("bg\\game_screen_bg.png"));
        stage.addActor(bgImg);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
    }
}
