package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pl.rejurhf.support.UIConstants;

public class CustomTextButton extends TextButton {
    public CustomTextButton(IClickCallback callback) {
        super("Text", UIConstants.getDefaultSkin());
    }

    public CustomTextButton(String buttonText, int xPos, int yPos, IClickCallback callback) {
        super(buttonText, UIConstants.getDefaultSkin());

        init(xPos, yPos, callback);
    }

    private void init(int xPos, int yPos, final IClickCallback callback) {
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
}
