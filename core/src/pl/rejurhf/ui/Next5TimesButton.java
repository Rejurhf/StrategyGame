package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pl.rejurhf.support.UIConstants;

public class Next5TimesButton extends Button {
    public Next5TimesButton(IClickCallback callback) {
        super(UIConstants.getDefaultSkin());

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
}

