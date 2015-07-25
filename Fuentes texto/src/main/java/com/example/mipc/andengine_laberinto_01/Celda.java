package com.example.mipc.andengine_laberinto_01;

import android.graphics.Color;
import android.util.Log;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

/**
 * Created by MIPC on 27/05/2015.
 */
public class Celda extends Sprite{
    public static final float SELECT_MAIN = 150;
    public static final float SELECT_SECONDARY = 200;
    public static final float NO_SELECT = 256;
    //private BitmapTextureAtlas myAtlas;
    //private ITextureRegion celdaTextureRegion;
    //private Sprite celdaSprite;
    private Rectangle selectMain;//principal
    private Text myLetter;

    public Celda(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

        this.selectMain = new Rectangle(this.getWidth() / 2, this.getHeight() / 2,32,32, getVertexBufferObjectManager());
        selectMain.setColor(0.9f, 0.1f, 0.2f, NO_SELECT);//.detachChild(this);

        myLetter = new Text(this.getWidth()/2, this.getHeight()/2, myFont, "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ", getVertexBufferObjectManager());
        myLetter.setColor(Color.BLACK);
        myLetter.setText("w");
        //myLetter.setSize(this.getWidth()*0.5f,this.getHeight()*0.5f);

        this.attachChild(selectMain);
        this.attachChild(myLetter);

    }
    public void addToScene(Scene scene){
        scene.registerTouchArea(this);
        scene.attachChild(this);
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
        //boolean de control
        //Cuando el modifier empieza
        Log.d("TOUCH", "Cocasteee");
        selectMain.setColor(0.3f, 0.5f, 0.2f, SELECT_MAIN);
        return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
    }
}
