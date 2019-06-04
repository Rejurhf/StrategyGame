package pl.rejurhf.support;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIConstants {
    public static final String NEXT_BUTTON_UP = "ui_style\\next_button1x_up.png";
    public static final String NEXT_BUTTON_DOWN = "ui_style\\next_button1x_down1.png";
    public static final String NEXT_BUTTON_DOWN_ALT = "ui_style\\next_button1x_down2.png";
    public static final String NEXT_BUTTON_5X_UP = "ui_style\\next_button5x_up.png";
    public static final String NEXT_BUTTON_5X_DOWN = "ui_style\\next_button5x_down1.png";
    public static final String NEXT_BUTTON_5X_DOWN_ALT = "ui_style\\next_button5x_down2.png";
    public static final String NEXT_BUTTON_10X_UP = "ui_style\\next_button10x_up.png";
    public static final String NEXT_BUTTON_10X_DOWN = "ui_style\\next_button10x_down1.png";
    public static final String NEXT_BUTTON_10X_DOWN_ALT = "ui_style\\next_button10x_down2.png";


    private static TextureRegionDrawable nextButtonUp;
    private static TextureRegionDrawable nextButtonDown;
    private static TextureRegionDrawable nextButtonDownAlt;
    private static TextureRegionDrawable nextButton5xUp;
    private static TextureRegionDrawable nextButton5xDown;
    private static TextureRegionDrawable nextButton5xDownAlt;
    private static TextureRegionDrawable nextButton10xUp;
    private static TextureRegionDrawable nextButton10xDown;
    private static TextureRegionDrawable nextButton10xDownAlt;


    private static Skin skin;


    public static TextureRegionDrawable getDrawable(String imageName){
        if(imageName.equals(NEXT_BUTTON_UP)) {
            return getDrawable(imageName, nextButtonUp);

        }else if(imageName.equals(NEXT_BUTTON_DOWN)) {
            return getDrawable(imageName, nextButtonDown);

        }else if(imageName.equals(NEXT_BUTTON_DOWN_ALT)) {
            return getDrawable(imageName, nextButtonDownAlt);

        }else if(imageName.equals(NEXT_BUTTON_5X_UP)) {
            return getDrawable(imageName, nextButton5xUp);

        }else if(imageName.equals(NEXT_BUTTON_5X_DOWN)) {
            return getDrawable(imageName, nextButton5xDown);

        }else if(imageName.equals(NEXT_BUTTON_5X_DOWN_ALT)) {
            return getDrawable(imageName, nextButton5xDownAlt);

        }else if(imageName.equals(NEXT_BUTTON_10X_UP)) {
            return getDrawable(imageName, nextButton10xUp);

        }else if(imageName.equals(NEXT_BUTTON_10X_DOWN)) {
            return getDrawable(imageName, nextButton10xDown);

        }else if(imageName.equals(NEXT_BUTTON_10X_DOWN_ALT)) {
            return getDrawable(imageName, nextButton10xDownAlt);

        } else
            return null;
    }

    public static Skin getDefaultSkin() {
        if(skin == null)
            skin = new Skin(Gdx.files.internal("default\\uiskin.json"));
        return skin;
    }

    /*

    private

     */

    private static TextureRegionDrawable getDrawable(String imageSrc, TextureRegionDrawable imageDrawable){
        if (imageDrawable == null)
            imageDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(imageSrc)));
        return imageDrawable;
    }
}
