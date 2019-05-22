package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;
import pl.rejurhf.support.UnitConstants;
import pl.rejurhf.ui.IClickCallback;
import pl.rejurhf.ui.SubmitButton;

import java.util.ArrayList;

public class InitialScreen extends AbstractScreen {
    private Image bgImg;
    private SubmitButton submitButton;
    private int[] capitolsArray;

    public InitialScreen(final StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        initBg();
        initSubmitButton();
    }

    private void initSubmitButton() {
        final int[] capitolArray = {0, 500 , 1000};
        final ArrayList<String> colorList = new ArrayList<String>();
        colorList.add(UnitConstants.BLUE_CAPITOL);
        colorList.add(UnitConstants.RED_CAPITOL);
        colorList.add(UnitConstants.GREEN_CAPITOL);

        submitButton = new SubmitButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.setScreen(new GameplayScreen(game, capitolArray, colorList));
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
