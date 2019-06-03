package pl.rejurhf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.*;

import java.util.ArrayList;
import java.util.List;

public class InitialScreen extends AbstractScreen {
    private List<CustomCheckBox> checkBoxRaceList;
    private List<CustomCheckBox> checkBoxRandomPosList;
    private List<CustomSelectBox> selectBoxRaceList;
    private List<CustomSelectBox> selectBoxColorList;
    private List<CustomTextField> textInputXPosList;
    private List<CustomTextField> textInputYPosList;

    private int startXPosOfSettings;
    private int startYPosOfSettings;

    public InitialScreen(final StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        startXPosOfSettings = 100;
        startYPosOfSettings = 900;

        initBg();

        initAddLabels();
        initCheckBoxAddRace();
        initDropDownRace();
        initDropDownColor();
        initTextFieldXPos();
        initTextFieldYPos();
        intCheckBoxRandomPos();

        initLoadPresetsButton();
        initSubmitButton();
        initCreditsButton();
    }

    private void initAddLabels() {
        CustomLabel addRaceLabel = new CustomLabel("Add race",
                startXPosOfSettings - 25, startYPosOfSettings + 35);
        CustomLabel choseRaceLabel = new CustomLabel("Chose race",
                startXPosOfSettings + 55, startYPosOfSettings + 35);
        CustomLabel choceColorLabel = new CustomLabel("Chose color",
                startXPosOfSettings + 165, startYPosOfSettings + 35);
        CustomLabel setXPosLabel = new CustomLabel("Set X position",
                startXPosOfSettings + 270, startYPosOfSettings + 35);
        CustomLabel setYPosLabel = new CustomLabel("Set Y position",
                startXPosOfSettings + 380, startYPosOfSettings + 35);
        CustomLabel setRandPosLabel = new CustomLabel("Use random position",
                startXPosOfSettings + 490, startYPosOfSettings + 35);

        stage.addActor(addRaceLabel);
        stage.addActor(choseRaceLabel);
        stage.addActor(choceColorLabel);
        stage.addActor(setXPosLabel);
        stage.addActor(setYPosLabel);
        stage.addActor(setRandPosLabel);
    }

    private void initCheckBoxAddRace() {
        checkBoxRaceList = new ArrayList<CustomCheckBox>();

        for (int i = 0; i < 6; ++i){
            final CustomCheckBox customCheckBox = new CustomCheckBox("",
                    startXPosOfSettings, 900  - (50 * i));

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
            CustomSelectBox selectBoxRace = new CustomSelectBox(startXPosOfSettings + 50,
                    startYPosOfSettings - (50 * i),
                    UnitConstants.RACES_ARRAY);
            selectBoxRaceList.add(selectBoxRace);
            stage.addActor(selectBoxRace);
        }
    }

    private void initDropDownColor() {
        selectBoxColorList = new ArrayList<CustomSelectBox>();

        for (int i = 0; i < 6; ++i){
            CustomSelectBox selectBoxColor = new CustomSelectBox(
                    startXPosOfSettings + 165, startYPosOfSettings - (50 * i), UnitConstants.COLOR_ARRAY);
            selectBoxColorList.add(selectBoxColor);
            stage.addActor(selectBoxColor);
        }
    }

    private void intCheckBoxRandomPos() {
        checkBoxRandomPosList = new ArrayList<CustomCheckBox>();

        for (int i = 0; i < 6; ++i){
            final CustomCheckBox customCheckBox = new CustomCheckBox("",
                    startXPosOfSettings + 305, startYPosOfSettings - (50*i));

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

    private void initTextFieldXPos() {
        textInputXPosList = new ArrayList<CustomTextField>();

        for (int i = 0; i < 6; ++i){
            CustomTextField customTextField = new CustomTextField("",
                    startXPosOfSettings + 410, startYPosOfSettings - (50*i));
            textInputXPosList.add(customTextField);
            stage.addActor(customTextField);
        }
    }

    private void initTextFieldYPos() {
        textInputYPosList = new ArrayList<CustomTextField>();

        for (int i = 0; i < 6; ++i){
            CustomTextField customTextField = new CustomTextField("",
                    startXPosOfSettings + 540, startYPosOfSettings - (50*i));
            textInputYPosList.add(customTextField);
            stage.addActor(customTextField);
        }
    }

    private void initLoadPresetsButton() {
        // Array with location on the board, color of races and races IDs
        final ArrayList<Integer> capitolList = new ArrayList<Integer>();
        final ArrayList<String> colorList = new ArrayList<String>();
        final ArrayList<Integer> raceIDList = new ArrayList<Integer>();

        // Add content to arrays
        assignPresets(capitolList, colorList, raceIDList);

        CustomTextButton loadPresetsButton = new CustomTextButton("Use presets",
                startXPosOfSettings + 650, startYPosOfSettings - 50, new IClickCallback() {
            @Override
            public void onClick() {
                for (CustomSelectBox selectBoxRace : selectBoxRaceList) {
                    System.out.println(selectBoxRace.getSelected());
                }

                game.setScreen(new GameplayScreen(game, capitolList, colorList, raceIDList));
            }
        });

        stage.addActor(loadPresetsButton);
    }

    private void assignPresets(ArrayList<Integer> capitolList, ArrayList<String> colorList,
                               ArrayList<Integer> raceIDList){
        capitolList.add((StrategyGame.BOARD_HEIGHT/2) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 - 9);
        capitolList.add((StrategyGame.BOARD_HEIGHT/2) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 - 5);
//        capitolList.add((StrategyGame.BOARD_HEIGHT/2 - 1) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 + 2);
        capitolList.add((StrategyGame.BOARD_HEIGHT/2 + 4) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 - 9);
        capitolList.add((StrategyGame.BOARD_HEIGHT/2 + 4) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 - 5);
//        capitolList.add((StrategyGame.BOARD_HEIGHT/2 + 1) * StrategyGame.BOARD_WIDTH + StrategyGame.BOARD_WIDTH / 2 + 2);

//        colorList.add(UnitConstants.BLUE_CAPITOL);
        colorList.add(UnitConstants.RED_CAPITOL);
        colorList.add(UnitConstants.GREEN_CAPITOL);
//        colorList.add(UnitConstants.PINK_CAPITOL);
        colorList.add(UnitConstants.SEA_CAPITOL);
        colorList.add(UnitConstants.YELLOW_CAPITOL);

        raceIDList.add(3);
        raceIDList.add(1);
        raceIDList.add(2);
        raceIDList.add(3);
    }

    private void initSubmitButton() {
        CustomTextButton submitButton = new CustomTextButton("Submit",
                startXPosOfSettings + 650, startYPosOfSettings - 120, new IClickCallback() {
                    @Override
                    public void onClick() {
                        System.out.println("Submit");
                    }
                });

        stage.addActor(submitButton);
    }

    private void initCreditsButton() {
        CustomTextButton creditsButton = new CustomTextButton("Credits",
                startXPosOfSettings + 650, startYPosOfSettings - 190, new IClickCallback() {
                    @Override
                    public void onClick() {
                        System.out.println("Credits");
                    }
                });

        stage.addActor(creditsButton);
    }

    // set background
    private void initBg() {
        Image bgImg = new Image(new Texture("bg\\init_screen_bg.png"));
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
