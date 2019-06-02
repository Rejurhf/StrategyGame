package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.entities.Elves;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.IClickCallback;
import pl.rejurhf.ui.CustomSelectBox;
import pl.rejurhf.ui.SubmitButton;

import java.util.ArrayList;
import java.util.List;

public class InitialScreen extends AbstractScreen {
    private Image bgImg;
    private SubmitButton submitButton;
    private List<CustomSelectBox> selectBoxRaceList;
    private List<CustomSelectBox> selectBoxColorList;

    public InitialScreen(final StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        initBg();
        initDropDownRace();
        initDropDownColor();
        initSubmitButton();
    }

    private void initDropDownColor() {
        selectBoxColorList = new ArrayList<CustomSelectBox>();

        for (int i = 0; i < 6; ++i){
            CustomSelectBox selectBoxRace = new CustomSelectBox(430, 900 - (50 * i), UnitConstants.COLOR_ARRAY);
            selectBoxRaceList.add(selectBoxRace);
            stage.addActor(selectBoxRace);
        }
    }

    private void initDropDownRace() {
        selectBoxRaceList = new ArrayList<CustomSelectBox>();

        for (int i = 0; i < 6; ++i){
            CustomSelectBox selectBoxRace = new CustomSelectBox(200, 900 - (50 * i), UnitConstants.RACES_ARRAY);
            selectBoxRaceList.add(selectBoxRace);
            stage.addActor(selectBoxRace);
        }
    }

    private void initSubmitButton() {
        // Array with location on the board
        final ArrayList<Integer> capitolList = new ArrayList<Integer>();
        capitolList.add((StrategyGame.BOARD_HEIGHT/2 - 2) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 - 3);
        capitolList.add((StrategyGame.BOARD_HEIGHT/2 - 2) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 + 1);
//        capitolList.add((StrategyGame.BOARD_HEIGHT/2 - 1) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 + 2);
        capitolList.add((StrategyGame.BOARD_HEIGHT/2 + 2) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 - 3);
        capitolList.add((StrategyGame.BOARD_HEIGHT/2 + 2) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 + 1);
//        capitolList.add((StrategyGame.BOARD_HEIGHT/2 + 1) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 + 2);

        // Array with color of races
        final ArrayList<String> colorList = new ArrayList<String>();
//        colorList.add(UnitConstants.BLUE_CAPITOL);
        colorList.add(UnitConstants.RED_CAPITOL);
        colorList.add(UnitConstants.GREEN_CAPITOL);
//        colorList.add(UnitConstants.PINK_CAPITOL);
        colorList.add(UnitConstants.SEA_CAPITOL);
        colorList.add(UnitConstants.YELLOW_CAPITOL);

        // Array with races IDs
        final ArrayList<Integer> raceIDList = new ArrayList<Integer>();
        raceIDList.add(0);
        raceIDList.add(1);
        raceIDList.add(2);
        raceIDList.add(3);

        submitButton = new SubmitButton(new IClickCallback() {
            @Override
            public void onClick() {
                for (CustomSelectBox selectBoxRace : selectBoxRaceList){
                    System.out.println(selectBoxRace.getSelected());
                }

                game.setScreen(new GameplayScreen(game, capitolList, colorList, raceIDList));
            }
        });

        stage.addActor(submitButton);
    }

    // set background
    private void initBg() {
        bgImg = new Image(new Texture("bg\\init_screen_bg.png"));
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
