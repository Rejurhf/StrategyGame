package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.entities.*;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.*;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends AbstractScreen {
    public static List<UnitInstance> unitsList;
    public static List<Race> raceList;
    public static int[][] strategyArray;
    private int roundCounter = 0;

    public GameplayScreen(StrategyGame game, ArrayList<Integer> capitolList, ArrayList<String> raceColors,
                          ArrayList<Integer> raceIDList){
        super(game);
        init(capitolList, raceColors, raceIDList);
    }

    private void init(ArrayList<Integer> capitolList, ArrayList<String> raceColors, ArrayList<Integer> raceIDList) {
        initBg();
        initBoard();
        initRace(capitolList, raceColors, raceIDList);
        initNextRoundButton();
        initNextRound5Button();
        initNextRound10Button();
    }

    private void initNextRound10Button() {
        CustomTextButton creditsButton = new CustomTextButton("10x Next Round",
                1425, 250, new IClickCallback() {
            @Override
            public void onClick() {
                for (int i = 0; i < 10; ++i) {
                    playNextRound();
                }
            }
        });

        stage.addActor(creditsButton);
    }

    private void initNextRound5Button() {
        CustomTextButton creditsButton = new CustomTextButton("5x Next Round",
                1425, 320, new IClickCallback() {
            @Override
            public void onClick() {
                for (int i = 0; i < 5; ++i) {
                    playNextRound();
                }
            }
        });

        stage.addActor(creditsButton);
    }

    private void initNextRoundButton() {
        CustomTextButton creditsButton = new CustomTextButton("Next Round",
                1425, 390, new IClickCallback() {
            @Override
            public void onClick() {
                playNextRound();
            }
        });

        stage.addActor(creditsButton);
    }

    private void playNextRound(){
        System.out.println("\nRound: " + ++roundCounter);
        for(Race race : raceList){
            race.planNextMove();
        }
        for(Race race : raceList){
            race.executeMovePlan(unitsList);
        }
    }

    private void initRace(ArrayList<Integer> capitolList, ArrayList<String> raceColors, ArrayList<Integer> raceIDList) {
        raceList = new ArrayList<Race>();

        // protection against IndexOutOfBoundsException
        int numberOfRaces = 0;
        if(capitolList.size() <= raceColors.size() && capitolList.size() <= raceIDList.size())
            numberOfRaces = capitolList.size();
        else if(raceColors.size() <= capitolList.size() && raceColors.size() <= raceIDList.size())
            numberOfRaces = raceColors.size();
        else
            numberOfRaces = raceIDList.size();
        UnitConstants.setNumberOfRaces(numberOfRaces);

        // assign races
        for(int i = 0; i < numberOfRaces; ++i){
            Race newRace = null;

            if(raceIDList.get(i) == UnitConstants.PEOPLE_ID)
                newRace = new People(unitsList.get(capitolList.get(i)), raceColors.get(i), raceIDList.get(i), 2*i+1);
            else if(raceIDList.get(i) == UnitConstants.ELVES_ID)
                newRace = new Elves(unitsList.get(capitolList.get(i)), raceColors.get(i), raceIDList.get(i), 2*i+1);
            else if(raceIDList.get(i) == UnitConstants.ORCS_ID)
                newRace = new Orcs(unitsList.get(capitolList.get(i)), raceColors.get(i), raceIDList.get(i), 2*i+1);
            else
                newRace = new Race(unitsList.get(capitolList.get(i)), raceColors.get(i), raceIDList.get(i), 2*i+1);

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
        Image bgImg = new Image(new Texture("bg\\game_screen_bg.png"));
        bgImg.setSize(StrategyGame.WIDTH, StrategyGame.HEIGHT);
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
