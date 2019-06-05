package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.support.UIConstants;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.*;

import java.io.File;
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

    private CustomLabel infoLabel;
    private Dialog warningDialog;

    private int startXPosOfSettings;
    private int startYPosOfSettings;

    private CustomSelectBox mapSelectBox;

    public InitialScreen(final StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        startXPosOfSettings = 100;
        startYPosOfSettings = 875;

        initBg();

        initAddLabels();
        initCheckBoxAddRace();
        initDropDownRace();
        initDropDownColor();
        initTextFieldXPos();
        initTextFieldYPos();
        initButtonRandomPos();
        initDropDownMap();

        initInfoLabel();
        initWarningDialog();

        initLoadPresetsButton();
        initSubmitButton();
        initCreditsButton();
    }

    private void initWarningDialog() {
        warningDialog = new Dialog("Warning", UIConstants.getDefaultSkin(), "dialog"){};

        warningDialog.button("OK", true); //sends "true" as the result
    }


    /*
    Menu
     */
    private void initInfoLabel() {
        infoLabel = new CustomLabel("", 1500, 200);

        stage.addActor(infoLabel);
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
                    startXPosOfSettings, startYPosOfSettings  - (50 * i));

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

    private void initDropDownMap() {
        CustomLabel mapLabel = new CustomLabel("Maps:",
                startXPosOfSettings + 620, startYPosOfSettings - 5);

        // Fill maps array
        List<String> maps = new ArrayList<String>();
        maps.add("");

        File folder = new File("maps");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                String substringName = fileName.substring(fileName.length() - 3);
                if(substringName.equals("png") || substringName.equals("jpg"))
                    maps.add(listOfFile.getName());
            }
        }


        mapSelectBox = new CustomSelectBox(startXPosOfSettings + 620, startYPosOfSettings - 40,
                150, 30, maps.toArray(new String[0]));

        stage.addActor(mapLabel);
        stage.addActor(mapSelectBox);
    }

    /*
    Buttons control
     */

    private void initLoadPresetsButton() {
        // Array with location on the board, color of races and races IDs
        final ArrayList<Integer> capitolList = new ArrayList<Integer>();
        final ArrayList<String> colorList = new ArrayList<String>();
        final ArrayList<Integer> raceIDList = new ArrayList<Integer>();

        // Add content to arrays
        assignPresets(capitolList, colorList, raceIDList);

        CustomTextButton loadPresetsButton = new CustomTextButton("Use presets",
                startXPosOfSettings + 620, startYPosOfSettings - 110, new IClickCallback() {
            @Override
            public void onClick() {
                System.out.println("Submit presets");
                game.setScreen(new GameplayScreen(game, capitolList, colorList, raceIDList, ""));
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

        raceIDList.add(1);
        raceIDList.add(1);
        raceIDList.add(3);
        raceIDList.add(1);
    }

    private void initSubmitButton() {
        CustomTextButton submitButton = new CustomTextButton("Submit",
                startXPosOfSettings + 620, startYPosOfSettings - 180, new IClickCallback() {
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

                        game.setScreen(new GameplayScreen(game,
                                capitolList, colorList, raceIDList, mapSelectBox.getSelected().toString()));
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

                    warningDialog.getContentTable().clear();
                    warningDialog.text("Row " + i + ": Can not use same color 2 times");
                    warningDialog.show(stage);
                    return false;
                }else
                    tmpColorList.add(selectBoxColorList.get(i).getSelected().toString());

                // Position x and y must be integer
                int x, y;
                try{
                    x = Integer.parseInt(textInputXPosList.get(i).getText());
                    y = Integer.parseInt(textInputYPosList.get(i).getText());
                } catch(NumberFormatException e) {
                    System.out.println("Row " + i + ": Position X and Y must be integer");

                    warningDialog.getContentTable().clear();
                    warningDialog.text("Row " + i + ": Position X and Y must be integer");
                    warningDialog.show(stage);
                    return false;
                } catch(NullPointerException e) {
                    System.out.println("Row " + i + ": Position X and Y must be integer");

                    warningDialog.getContentTable().clear();
                    warningDialog.text("Row " + i + ": Position X and Y must be integer");
                    warningDialog.show(stage);
                    return false;
                }

                // Position must be in range of board
                if(x < 0 || x > 69 || y < 0 || y > 49){
                    System.out.println("Row " + i +
                            ": Position X must be from range [0; 69] and Y must be from range [0; 49]");

                    warningDialog.getContentTable().clear();
                    warningDialog.text("Row " + i +
                            ": Position X must be from range [0; 69] and Y must be from range [0; 49]");
                    warningDialog.show(stage);
                    return false;
                }else if(tmpPositionList.contains(y * StrategyGame.BOARD_WIDTH + x)){
                    System.out.println("Row " + i + ": Can not use same position 2 times");

                    warningDialog.getContentTable().clear();
                    warningDialog.text("Row " + i + ": Can not use same position 2 times");
                    warningDialog.show(stage);
                    return false;
                }else{
                    tmpPositionList.add(y * StrategyGame.BOARD_WIDTH + x);
                }
            }
        }

        // If there aren't any setup race
        if(countRaces == 0){
            System.out.println("At least one race must be set up");

            warningDialog.getContentTable().clear();
            warningDialog.text("At least one race must be set up");
            warningDialog.show(stage);
            return false;
        }

        return true;
    }


    private void assignInputToArrays(ArrayList<Integer> capitolList, ArrayList<String> colorList,
                                     ArrayList<Integer> raceIDList) {
        for(int i = 0; i < 6; ++i){
            // if checked then add to list
            if(checkBoxRaceList.get(i).isChecked()){
                // Add position
                capitolList.add(Integer.parseInt(textInputYPosList.get(i).getText()) * StrategyGame.BOARD_WIDTH +
                        Integer.parseInt(textInputXPosList.get(i).getText()));

                // Add color
                colorList.add(UnitConstants.getCapitolColorFromColorName(
                        selectBoxColorList.get(i).getSelected().toString()));

                // Add race ID
                raceIDList.add(UnitConstants.getIDFromRaceName(selectBoxRaceList.get(i).getSelected().toString()));
            }
        }
    }

    private void initCreditsButton() {
        CustomTextButton creditsButton = new CustomTextButton("Credits",
                startXPosOfSettings + 620, startYPosOfSettings - 250, new IClickCallback() {
                    @Override
                    public void onClick() {
                        System.out.println("Credits");

                        infoLabel.setText("Author\n" +
                                "Mateusz Ziomek\n\n" +
                                "Design support\n" +
                                "Justyna Mikrut\n\n" +
                                "Powered by\n" +
                                "LibGDX\n\n" +
                                "Images\n" +
                                "freepik.com");
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
