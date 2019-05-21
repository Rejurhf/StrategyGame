package pl.rejurhf.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SubmitButton extends Button {
    public SubmitButton(final IClickCallback callback){
        super(prepareSubmitButtonStyle());

        init(callback);
    }

    private void init(final IClickCallback callback){
        this.setSize(100, 100);
        this.setPosition(1450, 450);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private static ButtonStyle prepareSubmitButtonStyle() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui_style\\ui-red.atlas"));
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("button_02");
        buttonStyle.down = skin.getDrawable("button_03");

        return buttonStyle;
    }
}
