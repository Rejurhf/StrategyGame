package pl.rejurhf.support;

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


    public static final int MOUNTAIN_ID = -1;
    public static final int EMPTY_SPACE_ID = 0;
    public static final int RACE_ID = 0;
    public static final int PEOPLE_ID = 1;
    public static final int ELVES_ID = 2;
    public static final int ORCS_ID = 3;


    public static final String[] RACES_ARRAY = {"Race", "People", "Elves", "Orcs"};
    public static final String[] COLOR_ARRAY = {"Blue", "Green", "Pink", "Red", "Sea", "Yellow"};



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
            return getDrawable(imageName, mountain);

        }else if(imageName.equals(BLUE_CAPITOL)){
            return getDrawable(imageName, blueCapitol);

        }else if(imageName.equals(BLUE_UNIT)){
            return getDrawable(imageName, blueUnit);

        }else if(imageName.equals(GREEN_CAPITOL)){
            return getDrawable(imageName, greenCapitol);

        }else if(imageName.equals(GREEN_UNIT)){
            return getDrawable(imageName, greenUnit);

        }else if(imageName.equals(PINK_CAPITOL)){
            return getDrawable(imageName, pinkCapitol);

        }else if(imageName.equals(PINK_UNIT)){
            return getDrawable(imageName, pinkUnit);

        }else if(imageName.equals(RED_CAPITOL)){
            return getDrawable(imageName, redCapitol);

        }else if(imageName.equals(RED_UNIT)){
            return getDrawable(imageName, redUnit);

        }else if(imageName.equals(SEA_CAPITOL)){
            return getDrawable(imageName, seaCapitol);

        }else if(imageName.equals(SEA_UNIT)){
            return getDrawable(imageName, seaUnit);

        }else if(imageName.equals(YELLOW_CAPITOL)){
            return getDrawable(imageName, yellowCapitol);

        }else if(imageName.equals(YELLOW_UNIT)){
            return getDrawable(imageName, yellowUnit);

        }else
            return null;
    }

    private static TextureRegionDrawable getDrawable(String imageSrc, TextureRegionDrawable imageDrawable){
        if (imageDrawable == null)
            imageDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(imageSrc)));
        return imageDrawable;
    }

    public static String getColorName(String color){
        if(color.equals(BLUE_CAPITOL) || color.equals(BLUE_UNIT))
            return "BLUE";
        else if(color.equals(GREEN_CAPITOL) || color.equals(GREEN_UNIT))
            return "GREEN";
        else if(color.equals(PINK_CAPITOL) || color.equals(PINK_UNIT))
            return "PINK";
        else if(color.equals(RED_CAPITOL) || color.equals(RED_UNIT))
            return "RED";
        else if(color.equals(SEA_CAPITOL) || color.equals(SEA_UNIT))
            return "SEA";
        else if(color.equals(YELLOW_CAPITOL) || color.equals(YELLOW_UNIT))
            return "YELLOW";

        return "this is not supported color";
    }

    public static String getRaceName(int raceID){
        if(raceID == RACE_ID)
            return "RACE";
        else if(raceID == PEOPLE_ID)
            return "PEOPLE";
        else if(raceID == ELVES_ID)
            return "ELVES";
        else if(raceID == ORCS_ID)
            return "ORCS";

        return "Not compatible rrace";
    }
}
