package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.entities.Race;
import pl.rejurhf.entities.UnitInstance;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.IClickCallback;
import pl.rejurhf.ui.NextButton;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends AbstractScreen {
    private Image bgImg;
    private NextButton nextButton;
    private List<UnitInstance> unitsList;
    private List<Race> raceList;
    public static int[][] strategyArray;

    public GameplayScreen(StrategyGame game, int[] capitolArray, ArrayList<String> raceColors){
        super(game);
        init(capitolArray, raceColors);
    }

    private void init(int[] capitolArray, ArrayList<String> raceColors) {
        initBg();
        initBoard();
        initRace(capitolArray, raceColors);
        initNextRoundButton();

    }

    private void initNextRoundButton() {
        nextButton = new NextButton(new IClickCallback() {
            @Override
            public void onClick() {
                unitsList.get(1500).changeSide(-1, UnitConstants.MOUNTAIN);
            }
        });

        stage.addActor(nextButton);
    }

    @Override
    protected void init() {
    }

    private void initRace(int[] capitolArray, ArrayList<String> raceColors) {
        //TODO
        // Add constant size of board to main function
        for(int i = 0; i < capitolArray.length; ++i){
            Race newRace = new Race(unitsList.get(capitolArray[i]), raceColors.get(i), 2*i-1);
        }
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
                // Create default instances of empty space (id = 0)
                UnitInstance newUnit = new UnitInstance(j*20, i*20, 0);
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
