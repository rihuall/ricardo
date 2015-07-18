package com.example.mipc.andengine_laberinto_01;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by MIPC on 27/05/2015.
 */
public class Celda {
    public static final float SELECT_MAIN = 150;
    public static final float SELECT_SECONDARY = 200;
    public static final float NO_SELECT = 256;
    //private BitmapTextureAtlas myAtlas;
    //private ITextureRegion celdaTextureRegion;
    private Sprite celdaSprite;
    private Rectangle selectMain;//principal
    //private Rectangle selectSecondary;//secundario

    public Celda(){

    }
    public void createCelda(Sprite celdaSprite, Rectangle selectMain){

        this.selectMain = selectMain;//px, py, width, height,
        //this.selectSecondary = selectSecondary;
        selectMain.setColor(0.3f, 0.5f, 0.2f, SELECT_MAIN);//.detachChild(this);
        //selectSecondary.setColor(0.3f, 0.5f, 0.2f, NO_SELECT);
        selectMain.setX(celdaSprite.getWidth()/2);
        selectMain.setY(celdaSprite.getHeight()/2);

        celdaSprite.attachChild(selectMain);
        //celdaSprite.attachChild(selectSecondary);
    }
    public void addToScene(Scene scene){
        scene.attachChild(celdaSprite);
    }
}
