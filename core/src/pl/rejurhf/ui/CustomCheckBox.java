package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import pl.rejurhf.support.UIConstants;

public class CustomCheckBox extends CheckBox {
    public CustomCheckBox(String text, int xPos, int yPos) {
        super(text, UIConstants.getDefaultSkin());

        init(xPos, yPos);
    }

    private void init(int xPos, int yPos) {
        this.setSize(30, 30);

        this.setPosition(xPos, yPos);
    }
}
