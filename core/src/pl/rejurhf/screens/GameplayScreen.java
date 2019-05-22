package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.entities.UnitInstance;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends AbstractScreen {
    private Image bgImg;
    private List<UnitInstance> unitsList;

    public GameplayScreen(StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        initBg();
        initBoard();

    }

    // Board initialization
    private void initBoard() {
        unitsList = new ArrayList<UnitInstance>();

        for (int i = 0; i < 70; i++) {
            for (int j = 0; j < 50; j++) {
                UnitInstance newUnit = new UnitInstance(i*20, j*20, "empty_unit.png");
                unitsList.add(newUnit);
                stage.addActor(unitsList.get(unitsList.size() - 1));
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
