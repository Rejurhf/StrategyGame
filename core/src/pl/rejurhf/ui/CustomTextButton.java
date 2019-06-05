package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pl.rejurhf.support.UIConstants;

public class CustomTextButton extends TextButton {
    public CustomTextButton(String buttonText, int xPos, int yPos, final IClickCallback callback) {
        super(buttonText, UIConstants.getDefaultSkin());

        this.setSize(150, 50);
        this.setPosition(xPos, yPos);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public CustomTextButton(String buttonText, int xPos, int yPos, int width, int height) {
        super(buttonText, UIConstants.getDefaultSkin());

        this.setSize(width, height);
        this.setPosition(xPos, yPos);
    }

    public CustomTextButton(String buttonText, int xPos, int yPos, int width, int height, final IClickCallback callback) {
        super(buttonText, UIConstants.getDefaultSkin());

        this.setSize(width, height);
        this.setPosition(xPos, yPos);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

}
