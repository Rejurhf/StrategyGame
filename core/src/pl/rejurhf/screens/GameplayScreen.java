package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.entities.*;
import pl.rejurhf.support.UIConstants;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends AbstractScreen {
    private static List<UnitInstance> unitsList;
    public static List<Race> raceList;
    public static List<Label> raceLabelList;
    public static int[][] strategyArray;

    private CustomTextField speedTextField;
    private CustomCheckBox autoplayCheckBox;
    private int interval = 30;
    private int updateCounter = 0;

    private int roundCounter = 0;
    private CustomLabel roundInfoLabel;


    GameplayScreen(StrategyGame game, ArrayList<Integer> capitolList, ArrayList<String> raceColors,
                   ArrayList<Integer> raceIDList, String mapName){
        super(game);
        init(capitolList, raceColors, raceIDList, mapName);
    }


    private void init(ArrayList<Integer> capitolList, ArrayList<String> raceColors,
                      ArrayList<Integer> raceIDList, String mapName) {
        initBg();
        if(mapName.equals(""))
            initBoard();
        else
            initBoard(mapName);
        initRace(capitolList, raceColors, raceIDList);

        initLabels();
        initNextRoundButtons();
        initAutoplay();
        initTable();
    }


    private void initAutoplay() {
        CustomLabel autoplayLabel = new CustomLabel("Autoplay:", 1440, 170);
        autoplayCheckBox = new CustomCheckBox("", 1520, 170);
        CustomLabel speedLabel = new CustomLabel("Speed:", 1610, 170);
        speedTextField = new CustomTextField("", 1680, 170);
        speedTextField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                // get interval
                try{
                    interval = Integer.parseInt(speedTextField.getText());
                } catch (NumberFormatException e) {
                    System.out.println("Interval must be integer");
                }

                if(interval < 0 || interval > 120)
                    interval = 30;
            }
        });

        stage.addActor(autoplayLabel);
        stage.addActor(autoplayCheckBox);
        stage.addActor(speedLabel);
        stage.addActor(speedTextField);
    }


    private void initTable() {
        raceLabelList = new ArrayList<Label>();

        Table unitInfoTable = new Table();
        unitInfoTable.setPosition(1420, 330);
        unitInfoTable.setSize(338, 300);

        // Header
        Label topInfoLabel = new Label("ID\nRaace\nColor", UIConstants.getDefaultSkin());
        topInfoLabel.setAlignment(Align.center);
        Label topUnitLabel = new Label("Units\nBreeding", UIConstants.getDefaultSkin());
        topUnitLabel.setAlignment(Align.center);
        Label topMoveLabel = new Label("Peace Moves\nWar Moves", UIConstants.getDefaultSkin());
        topMoveLabel.setAlignment(Align.center);

        unitInfoTable.add(topInfoLabel).expandX();
        unitInfoTable.add(topUnitLabel).expandX();
        unitInfoTable.add(topMoveLabel).expandX();

        // init content
        for (int i = 0; i < UnitConstants.numberOfRaces; i++) {
            unitInfoTable.row();

            int raceID = 2*i+1;
            Label infoLabel = new Label(raceID + "\n" + raceList.get(i).getRaceName() + "\n" +
                    raceList.get(i).getRaceColor(), UIConstants.getDefaultSkin());
            infoLabel.setAlignment(Align.center);
            Label unitsLabel = new Label("1\n4", UIConstants.getDefaultSkin());
            unitsLabel.setAlignment(Align.center);
            Label movesLabel = new Label("4\n0", UIConstants.getDefaultSkin());
            movesLabel.setAlignment(Align.center);

            raceLabelList.add(unitsLabel);
            raceLabelList.add(movesLabel);

            unitInfoTable.add(infoLabel);
            unitInfoTable.add(unitsLabel);
            unitInfoTable.add(movesLabel);
        }

        stage.addActor(unitInfoTable);
    }


    private void initLabels() {
        roundInfoLabel = new CustomLabel("Round 0", 1430, 950);

        stage.addActor(roundInfoLabel);
    }


    private void initNextRoundButtons() {
        CustomTextButton nextRoundButton = new CustomTextButton("Next Round",
                1425, 100, new IClickCallback() {
            @Override
            public void onClick() {
                playNextRound();
            }
        });


        CustomTextButton nextRound5Button = new CustomTextButton("5x Next Round",
                1603, 100, new IClickCallback() {
            @Override
            public void onClick() {
                for (int i = 0; i < 5; ++i) {
                    playNextRound();
                }
            }
        });

        CustomTextButton nextRound10Button = new CustomTextButton("10x Next Round",
                1425, 30, new IClickCallback() {
            @Override
            public void onClick() {
                for (int i = 0; i < 10; ++i) {
                    playNextRound();
                }
            }
        });

        CustomTextButton nextRound20Button = new CustomTextButton("20x Next Round",
                1603, 30, new IClickCallback() {
            @Override
            public void onClick() {
                for (int i = 0; i < 20; ++i) {
                    playNextRound();
                }
            }
        });

        stage.addActor(nextRoundButton);
        stage.addActor(nextRound5Button);
        stage.addActor(nextRound10Button);
        stage.addActor(nextRound20Button);
    }


    private void playNextRound(){
        System.out.println("\nRound: " + ++roundCounter);
        roundInfoLabel.setText("Round " + roundCounter);

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

    // Board initialization
    private void initBoard(String mapName) {
        unitsList = new ArrayList<UnitInstance>();
        // Array for strategy purpose
        strategyArray = new int[StrategyGame.BOARD_HEIGHT][StrategyGame.BOARD_WIDTH];

        // BufferedImage to get map from
        BufferedImage map = null;
        try{
            map = ImageIO.read(new File("maps\\" + mapName));
        } catch (IOException e) {
            System.out.println("No such image");
            initBoard();
        }

        if(map.getWidth() < 70 || map.getHeight() < 50)
            initBoard();

        // flip image
        for (int i=0;i<map.getWidth();i++)
            for (int j=0;j<map.getHeight()/2;j++)
            {
                int tmp = map.getRGB(i, j);
                map.setRGB(i, j, map.getRGB(i, map.getHeight()-j-1));
                map.setRGB(i, map.getHeight()-j-1, tmp);
            }


        // Populate board, i and j inverted due to more logic insertion to ArrayList
        // (starting in left top corner, ending in right bottom corner, going row by row)
        for (int i = 0; i < StrategyGame.BOARD_HEIGHT; i++) {
            for (int j = 0; j < StrategyGame.BOARD_WIDTH; j++) {
                UnitInstance newUnit;
                // Create default instances of empty space (id = 0) and mountain (id = -1)
                // 50-i cause 50th element on screen (top left) is first element in array (top left)
                if(map.getRGB(j, i) > -8388608){
                    strategyArray[i][j] = 0;
                    newUnit = new UnitInstance(j*StrategyGame.UNIT_LEN, i*StrategyGame.UNIT_LEN, 0);
                }else{
                    strategyArray[i][j] = -1;
                    newUnit = new UnitInstance(j*StrategyGame.UNIT_LEN, i*StrategyGame.UNIT_LEN, -1);
                }
                unitsList.add(newUnit);
                stage.addActor(unitsList.get(unitsList.size() - 1));


            }
        }
    }


    // set background
    private void initBg() {
        Image bgImg = new Image(new Texture("bg\\game_screen_bg.png"));
        bgImg.setSize(StrategyGame.WIDTH, StrategyGame.HEIGHT);
        stage.addActor(bgImg);
    }


    private void autoplay(){
        ++updateCounter;

        if(updateCounter > interval){
            playNextRound();
            updateCounter = 0;
        }
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

        if(autoplayCheckBox.isChecked()){
            autoplay();
        }
    }

    @Override
    protected void init() { }
}
