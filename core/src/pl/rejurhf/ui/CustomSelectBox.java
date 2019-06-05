package pl.rejurhf.ui;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import pl.rejurhf.support.UIConstants;

public class CustomSelectBox extends SelectBox {
    public CustomSelectBox(int xPos, int yPos, String[] items){
        super(UIConstants.getDefaultSkin());

        this.setItems(items);
        this.setSize(100, 30);
        this.setPosition(xPos, yPos);
    }

    public CustomSelectBox(int xPos, int yPos, int width, int height, String[] items){
        super(UIConstants.getDefaultSkin());

        this.setItems(items);
        this.setSize(width, height);
        this.setPosition(xPos, yPos);
    }
}
