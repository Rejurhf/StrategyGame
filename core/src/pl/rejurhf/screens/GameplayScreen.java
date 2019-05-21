package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.rejurhf.StrategyGame;

public class GameplayScreen extends AbstractScreen {
    private Image bgImg;

    public GameplayScreen(StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        initBg();
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
