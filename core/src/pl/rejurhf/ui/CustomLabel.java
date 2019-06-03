package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import pl.rejurhf.support.UIConstants;

public class CustomLabel extends Label {
    public CustomLabel(CharSequence text, int xPos, int yPos) {
        super(text, UIConstants.getDefaultSkin());

        init(xPos, yPos);
    }

    public CustomLabel(CharSequence text, int xPos, int yPos, int width, int height) {
        super(text, UIConstants.getDefaultSkin());

        init(xPos, yPos, width, height);
    }

    private void init(int xPos, int yPos, int width, int height) {
        this.setSize(width, height);
        this.setPosition(xPos, yPos);
    }

    private void init(int xPos, int yPos) {
        this.setSize(70, 30);
        this.setPosition(xPos, yPos);
    }
}
