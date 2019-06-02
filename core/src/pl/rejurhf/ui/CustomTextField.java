package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import pl.rejurhf.support.UIConstants;

public class CustomTextField extends TextField {
    public CustomTextField(String text, int xPos, int yPos) {
        super(text, UIConstants.getDefaultSkin());

        init(xPos, yPos);
    }

    private void init(int xPos, int yPos) {
        this.setSize(50, 30);
        this.setPosition(xPos, yPos);
    }
}
