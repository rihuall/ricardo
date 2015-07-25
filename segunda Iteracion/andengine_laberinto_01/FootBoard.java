package com.example.mipc.andengine_laberinto_01;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/**
 * Created by MIPC on 14/06/2015.
 */
public class FootBoard extends Rectangle{
    public static final int FOOT_HEIGHT = 160;
    public static final float SIZE_SIDE = 39; //32;//tamaño lado

    public FootBoard( ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont, Scene scene) {

        super(
                MainActivity.CAMARA_WIDTH/2,
                FOOT_HEIGHT/2,
                MainActivity.CAMARA_WIDTH, FOOT_HEIGHT
                , pVertexBufferObjectManager);
        this.setColor(0.35f, 0.46f, 0.65f);//Colo de fondo de la cabecera


        //creo los botones
        final Text unTextofoot = new Text(SIZE_SIDE/2,SIZE_SIDE/2, myFont, "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ", getVertexBufferObjectManager());
        unTextofoot.setColor(Color.RED);
        unTextofoot.setText("M");
        Sprite unBotonFoot = new Sprite(
                SIZE_SIDE,
                FOOT_HEIGHT - SIZE_SIDE,
                SIZE_SIDE, SIZE_SIDE,
                pTextureRegion,
                pVertexBufferObjectManager){
                    @Override
                    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                        if(pSceneTouchEvent.isActionUp()){
                            unTextofoot.setText("N");
                        }
                        return true;
                        //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY)
                    }
                };
        unBotonFoot.attachChild(unTextofoot);

        this.attachChild(unBotonFoot);
        scene.registerTouchArea(unBotonFoot);
        scene.attachChild(this);
    }

    public void activateButtonsLetters(){
        //Activar los botones

    }
}
