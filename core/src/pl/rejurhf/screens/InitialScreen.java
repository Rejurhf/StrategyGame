package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InitialScreen extends AbstractScreen {
    private List<CustomCheckBox> checkBoxRaceList;
    private List<CustomSelectBox> selectBoxRaceList;
    private List<CustomSelectBox> selectBoxColorList;
    private List<CustomTextField> textInputXPosList;
    private List<CustomTextField> textInputYPosList;
    private List<CustomTextButton> buttonRandomPosList;

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
        initButtonRandomPos();

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
        CustomLabel setXPosLabel = new CustomLabel("X position",
                startXPosOfSettings + 270, startYPosOfSettings + 35);
        CustomLabel setYPosLabel = new CustomLabel("Y position",
                startXPosOfSettings + 355, startYPosOfSettings + 35);
        CustomLabel setRandPosLabel = new CustomLabel("Random position",
                startXPosOfSettings + 455, startYPosOfSettings + 35);

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

    private void initTextFieldXPos() {
        textInputXPosList = new ArrayList<CustomTextField>();

        for (int i = 0; i < 6; ++i){
            CustomTextField customTextField = new CustomTextField("",
                    startXPosOfSettings + 285, startYPosOfSettings - (50*i));
            textInputXPosList.add(customTextField);
            stage.addActor(customTextField);
        }
    }

    private void initTextFieldYPos() {
        textInputYPosList = new ArrayList<CustomTextField>();

        for (int i = 0; i < 6; ++i){
            CustomTextField customTextField = new CustomTextField("",
                    startXPosOfSettings + 370, startYPosOfSettings - (50*i));
            textInputYPosList.add(customTextField);
            stage.addActor(customTextField);
        }
    }

    private void initButtonRandomPos() {
        buttonRandomPosList = new ArrayList<CustomTextButton>();

        for (int i = 0; i < 6; ++i) {
            final CustomTextButton randomButton = new CustomTextButton("Random position",
                    startXPosOfSettings + 445, startYPosOfSettings - (50 * i), 150, 30);

            randomButton.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    int index = buttonRandomPosList.indexOf(randomButton);
                    // chose random x and y position
                    Random rand = new Random();
                    int tmpRandX = rand.nextInt(StrategyGame.BOARD_WIDTH);
                    int tmpRandY = rand.nextInt(StrategyGame.BOARD_HEIGHT);

                    // set values of x and y positions
                    textInputXPosList.get(index).setText(Integer.toString(tmpRandX));
                    textInputYPosList.get(index).setText(Integer.toString(tmpRandY));

                    return super.touchDown(event, x, y, pointer, button);
                }
            });

            buttonRandomPosList.add(randomButton);
            stage.addActor(randomButton);
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
                startXPosOfSettings + 620, startYPosOfSettings - 50, new IClickCallback() {
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
                startXPosOfSettings + 620, startYPosOfSettings - 120, new IClickCallback() {
                    @Override
                    public void onClick() {
                        if(!validateInput())
                            return;

                        // Array with location on the board, color of races and races IDs
                        final ArrayList<Integer> capitolList = new ArrayList<Integer>();
                        final ArrayList<String> colorList = new ArrayList<String>();
                        final ArrayList<Integer> raceIDList = new ArrayList<Integer>();
                        assignInputToArrays(capitolList, colorList, raceIDList);

                        System.out.println("Submit");
                    }
                });

        stage.addActor(submitButton);
    }

    private boolean validateInput(){
        List<String> tmpColorList = new ArrayList<String>();
        List<Integer> tmpPositionList = new ArrayList<Integer>();
        int countRaces = 0;

        for(int i = 0; i < 6; ++i){
            // if checked then validate
            if(checkBoxRaceList.get(i).isChecked()){
                ++countRaces;

                // Can not use one color 2 times
                if(tmpColorList.contains(selectBoxColorList.get(i).getSelected().toString())){
                    System.out.println("Row " + i + ": Can not use same color 2 times");
                    return false;
                }else
                    tmpColorList.add(selectBoxColorList.get(i).getSelected().toString());

                // Position x and y must be integer
                int x, y;
                try{
                    x = Integer.parseInt(textInputXPosList.get(i).getText());
                    y = Integer.parseInt(textInputYPosList.get(i).getText());
                } catch(NumberFormatException e) {
                    System.out.println("Row " + i + ": Position must be integer");
                    return false;
                } catch(NullPointerException e) {
                    System.out.println("Row " + i + ": Position must be integer Row: " + i);
                    return false;
                }

                // Position must be in range of board
                if(x < 0 || x > 69 || y < 0 || y > 49){
                    System.out.println("Row " + i +
                            ": Position X must be from range [0; 69] and Y must be from range [0; 49]");
                    return false;
                }else if(tmpPositionList.contains(y * StrategyGame.BOARD_WIDTH + x)){
                    System.out.println("Row " + i + ": Can not use same position 2 times");
                    return false;
                }else{
                    tmpPositionList.add(y * StrategyGame.BOARD_WIDTH + x);
                }
            }
        }

        // If there aren't any setup race
        if(countRaces == 0){
            System.out.println("At least one race must be set up");
            return false;
        }

        return true;
    }

    private void assignInputToArrays(ArrayList<Integer> capitolList, ArrayList<String> colorList,
                                     ArrayList<Integer> raceIDList) {

    }

    private void initCreditsButton() {
        CustomTextButton creditsButton = new CustomTextButton("Credits",
                startXPosOfSettings + 620, startYPosOfSettings - 190, new IClickCallback() {
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
