package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import pl.rejurhf.support.UIConstants;

public class SubmitButton extends Button {
    public SubmitButton(final IClickCallback callback){
        super(UIConstants.getDefaultSkin());

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
}
