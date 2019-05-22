package pl.rejurhf.constants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UnitConstants {
    public static final String EMPTY_SPACE = "units\\empty_unit.png";
    public static final String MOUNTAIN = "units\\mountain.png";
    public static final String BLUE_CAPITOL = "units\\blue_boss.png";
    public static final String BLUE_UNIT = "units\\blue_unit.png";
    public static final String GREEN_CAPITOL = "units\\green_boss.png";
    public static final String GREEN_UNIT = "units\\green_unit.png";
    public static final String PINK_CAPITOL = "units\\pink_boss.png";
    public static final String PINK_UNIT = "units\\pink_unit.png";
    public static final String RED_CAPITOL = "units\\red_boss.png";
    public static final String RED_UNIT = "units\\red_unit.png";
    public static final String SEA_CAPITOL = "units\\sea_boss.png";
    public static final String SEA_UNIT = "units\\sea_unit.png";
    public static final String YELLOW_CAPITOL = "units\\yellow_boss.png";
    public static final String YELLOW_UNIT = "units\\yellow_unit.png";

    private static Texture emptySpaceTexture;
    private static TextureRegionDrawable emptySpace;
    private static TextureRegionDrawable mountain;
    private static TextureRegionDrawable blueCapitol;
    private static TextureRegionDrawable blueUnit;
    private static TextureRegionDrawable greenCapitol;
    private static TextureRegionDrawable greenUnit;
    private static TextureRegionDrawable pinkCapitol;
    private static TextureRegionDrawable pinkUnit;
    private static TextureRegionDrawable redCapitol;
    private static TextureRegionDrawable redUnit;
    private static TextureRegionDrawable seaCapitol;
    private static TextureRegionDrawable seaUnit;
    private static TextureRegionDrawable yellowCapitol;
    private static TextureRegionDrawable yellowUnit;

    public static Texture getEmptySpaceTexture(){
        if(emptySpaceTexture == null)
            emptySpaceTexture = new Texture(EMPTY_SPACE);

        return emptySpaceTexture;
    }

    public static TextureRegionDrawable getDrawable(String imageName){
        if(imageName.equals(EMPTY_SPACE)){
            if(emptySpace == null)
                emptySpace = new TextureRegionDrawable(new TextureRegion(emptySpaceTexture));
            return emptySpace;
        }else if(imageName.equals(MOUNTAIN)){
            if(mountain == null){
                mountain = new TextureRegionDrawable(new TextureRegion(new Texture(MOUNTAIN)));
            }
            return mountain;
        }else if(imageName.equals(BLUE_CAPITOL)){
            if(blueCapitol == null){
                blueCapitol = new TextureRegionDrawable(new TextureRegion(new Texture(BLUE_CAPITOL)));
            }
            return blueCapitol;
        }else if(imageName.equals(BLUE_UNIT)){
            if(blueUnit == null){
                blueUnit = new TextureRegionDrawable(new TextureRegion(new Texture(BLUE_UNIT)));
            }
            return blueUnit;
        }else if(imageName.equals(GREEN_CAPITOL)){
            if(greenCapitol == null){
                greenCapitol = new TextureRegionDrawable(new TextureRegion(new Texture(GREEN_CAPITOL)));
            }
            return greenCapitol;
        }else if(imageName.equals(GREEN_UNIT)){
            if(greenUnit == null){
                greenUnit = new TextureRegionDrawable(new TextureRegion(new Texture(GREEN_UNIT)));
            }
            return greenUnit;
        }else if(imageName.equals(PINK_CAPITOL)){
            if(pinkCapitol == null){
                pinkCapitol = new TextureRegionDrawable(new TextureRegion(new Texture(PINK_CAPITOL)));
            }
            return pinkCapitol;
        }else if(imageName.equals(PINK_UNIT)){
            if(pinkUnit == null){
                pinkUnit = new TextureRegionDrawable(new TextureRegion(new Texture(PINK_UNIT)));
            }
            return pinkUnit;
        }else if(imageName.equals(RED_CAPITOL)){
            if(redCapitol == null){
                redCapitol = new TextureRegionDrawable(new TextureRegion(new Texture(RED_CAPITOL)));
            }
            return redCapitol;
        }else if(imageName.equals(RED_UNIT)){
            if(redUnit == null){
                redUnit = new TextureRegionDrawable(new TextureRegion(new Texture(RED_UNIT)));
            }
            return redUnit;
        }else if(imageName.equals(SEA_CAPITOL)){
            if(seaCapitol == null){
                seaCapitol = new TextureRegionDrawable(new TextureRegion(new Texture(SEA_CAPITOL)));
            }
            return seaCapitol;
        }else if(imageName.equals(SEA_UNIT)){
            if(seaUnit == null){
                seaUnit = new TextureRegionDrawable(new TextureRegion(new Texture(SEA_UNIT)));
            }
            return seaUnit;
        }else if(imageName.equals(YELLOW_CAPITOL)){
            if(yellowCapitol == null){
                yellowCapitol = new TextureRegionDrawable(new TextureRegion(new Texture(YELLOW_CAPITOL)));
            }
            return yellowCapitol;
        }else if(imageName.equals(YELLOW_UNIT)){
            if(yellowUnit == null){
                yellowUnit = new TextureRegionDrawable(new TextureRegion(new Texture(YELLOW_UNIT)));
            }
            return yellowUnit;
        }else
            return null;
    }
}
