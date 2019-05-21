package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import pl.rejurhf.StrategyGame;

public class InitialScreen extends AbstractScreen {
    private Texture bgImg;

    public InitialScreen(final StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        bgImg = new Texture("bg\\init_screen_bg.png");

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameplayScreen(game));
            }
        }, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(bgImg, 0, 0);
        spriteBatch.end();
    }
}
