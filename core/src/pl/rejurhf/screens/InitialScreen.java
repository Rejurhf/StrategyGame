package pl.rejurhf.screens;

import com.badlogic.gdx.graphics.Texture;
import pl.rejurhf.StrategyGame;

public class InitialScreen extends AbstractScreen {
    private Texture tmpImage;

    public InitialScreen(final StrategyGame game){
        super(game);
    }

    @Override
    protected void init() {
        tmpImage = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(tmpImage, 0, 0);
        spriteBatch.end();
    }
}
