package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pl.rejurhf.support.UIConstants;

public class Next5TimesButton extends Button {
    public Next5TimesButton(IClickCallback callback) {
        super(prepareNextButtonStyle());

        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setSize(340, 100);
        this.setPosition(1425, 400);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private static Button.ButtonStyle prepareNextButtonStyle() {
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = UIConstants.getDrawable(UIConstants.NEXT_BUTTON_5X_UP);
        buttonStyle.down = UIConstants.getDrawable(UIConstants.NEXT_BUTTON_5X_DOWN);

        return buttonStyle;
    }
}

