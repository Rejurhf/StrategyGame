package pl.rejurhf.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CustomSelectBox extends SelectBox {
    public CustomSelectBox(int xPos, int yPos, String[] items){
        super(prepareSkin());

        initValues(xPos, yPos, items);
    }

    private void initValues(int xPos, int yPos, String[] items) {

        this.setItems(items);
        this.setSize(200, 30);
        this.setPosition(xPos, yPos);
    }

    private static Skin prepareSkin() {
        Skin skin = new Skin(Gdx.files.internal("default\\uiskin.json"));
        return skin;
    }
}
