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
                for(Race race : raceList){
                    race.planNextMove();
                }
                for(Race race : raceList){
                    race.executeMovePlan(unitsList);
                }
            }
        });

        stage.addActor(nextButton);
    }

    private void initRace(int[] capitolArray, ArrayList<String> raceColors) {
        raceList = new ArrayList<Race>();

        for(int i = 0; i < capitolArray.length; ++i){
            Race newRace = new Race(unitsList.get(capitolArray[i]), raceColors.get(i), 2*i+1);
            raceList.add(newRace);
        }
    }

    // Board initialization
    private void initBoard() {
        unitsList = new ArrayList<UnitInstance>();
        // Array for strategy purpose
        strategyArray = new int[StrategyGame.BOARD_HEIGHT][StrategyGame.BOARD_WIDTH];

        // Populate board, i and j inverted due to more logic insertion to ArrayList
        // (starting in left top corner, ending in right bottom corner, going row by row)
        for (int i = 0; i < StrategyGame.BOARD_HEIGHT; i++) {
            for (int j = 0; j < StrategyGame.BOARD_WIDTH; j++) {
                // Create default instances of empty space (id = 0)
                UnitInstance newUnit = new UnitInstance(j*StrategyGame.UNIT_LEN, i*StrategyGame.UNIT_LEN, 0);
                unitsList.add(newUnit);
                stage.addActor(unitsList.get(unitsList.size() - 1));

                // 50-i cause 50th element on screen (top left) is first element in array (top left)
                strategyArray[i][j] = 0;
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

    @Override
    protected void init() { }
}
