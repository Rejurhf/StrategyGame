package pl.rejurhf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.support.UIConstants;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.*;

import java.util.ArrayList;
import java.util.List;

public class InitialScreen extends AbstractScreen {
    private Image bgImg;
    private SubmitButton submitButton;
    private List<CustomCheckBox> checkBoxRaceList;
    private List<CustomCheckBox> checkBoxRandomPosList;
    private List<CustomSelectBox> selectBoxRaceList;
    private List<CustomSelectBox> selectBoxColorList;
    private List<CustomTextField> textInputXPosList;
    private List<CustomTextField> textInputYPosList;
    private int startPosOfSettings;

    public InitialScreen(final StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        startPosOfSettings = 100;
        initBg();
        initAddLabels();
        initCheckBoxAddRace();
        initDropDownRace();
        initDropDownColor();
        initSubmitButton();
        initTextFieldXPos();
        initTextFieldYPos();
        intCheckBoxRandomPos();
    }

    private void initAddLabels() {
        Label label = new Label("Label", UIConstants.getDefaultSkin());
        label.setSize(100, 100);
        label.setPosition(100, 100);
        stage.addActor(label);
    }

    private void initCheckBoxAddRace() {
        checkBoxRaceList = new ArrayList<CustomCheckBox>();

        for (int i = 0; i < 6; ++i){
            final CustomCheckBox customCheckBox = new CustomCheckBox("",
                    startPosOfSettings, 900  - (50 * i));

            customCheckBox.addListener(new ChangeListener(){
                @Override
                public void changed (ChangeEvent event, Actor actor) {
                    System.out.println(customCheckBox.isChecked());
                    Gdx.graphics.setContinuousRendering(customCheckBox.isChecked());
                }
            });

            checkBoxRaceList.add(customCheckBox);
            stage.addActor(customCheckBox);

        }
    }

    private void initDropDownRace() {
        selectBoxRaceList = new ArrayList<CustomSelectBox>();

        for (int i = 0; i < 6; ++i){
            CustomSelectBox selectBoxRace = new CustomSelectBox(startPosOfSettings + 40, 900 - (50 * i),
                    UnitConstants.RACES_ARRAY);
            selectBoxRaceList.add(selectBoxRace);
            stage.addActor(selectBoxRace);
        }
    }

    private void initDropDownColor() {
        selectBoxColorList = new ArrayList<CustomSelectBox>();

        for (int i = 0; i < 6; ++i){
            CustomSelectBox selectBoxColor = new CustomSelectBox(startPosOfSettings + 160, 900 - (50 * i),
                    UnitConstants.COLOR_ARRAY);
            selectBoxColorList.add(selectBoxColor);
            stage.addActor(selectBoxColor);
        }
    }

    private void initTextFieldXPos() {
        textInputXPosList = new ArrayList<CustomTextField>();

        for (int i = 0; i < 6; ++i){
            CustomTextField customTextField = new CustomTextField("",
                    startPosOfSettings + 300, 900 - (50*i));
            textInputXPosList.add(customTextField);
            stage.addActor(customTextField);
        }
    }

    private void initTextFieldYPos() {
        textInputYPosList = new ArrayList<CustomTextField>();

        for (int i = 0; i < 6; ++i){
            CustomTextField customTextField = new CustomTextField("",
                    startPosOfSettings + 360, 900 - (50*i));
            textInputYPosList.add(customTextField);
            stage.addActor(customTextField);
        }
    }

    private void intCheckBoxRandomPos() {
        checkBoxRandomPosList = new ArrayList<CustomCheckBox>();

        for (int i = 0; i < 6; ++i){
            final CustomCheckBox customCheckBox = new CustomCheckBox("",
                    startPosOfSettings + 270, 900 - (50*i));

            customCheckBox.addListener(new ChangeListener(){
                @Override
                public void changed (ChangeEvent event, Actor actor) {
                    System.out.println(customCheckBox.isChecked());
                    Gdx.graphics.setContinuousRendering(customCheckBox.isChecked());
                }
            });

            checkBoxRandomPosList.add(customCheckBox);
            stage.addActor(customCheckBox);

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
}
