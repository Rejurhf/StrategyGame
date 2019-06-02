package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import pl.rejurhf.support.UIConstants;

public class CustomSelectBox extends SelectBox {
    public CustomSelectBox(int xPos, int yPos, String[] items){
        super(UIConstants.getDefaultSkin());

        init(xPos, yPos, items);
    }

    private void init(int xPos, int yPos, String[] items) {

        this.setItems(items);
        this.setSize(100, 30);
        this.setPosition(xPos, yPos);
    }
}
