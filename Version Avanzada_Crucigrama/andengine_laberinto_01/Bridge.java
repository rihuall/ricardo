//Puente
package com.example.mipc.andengine_laberinto_01;

import android.graphics.Color;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import java.util.ArrayList;

/**
 * Created by EBERCILLO on 14/06/2015.
 */
public class Bridge {
    public static  MainActivity theMainActivity;

    public static HeadBoard theHeadBoard;
    public static Grilla theGrilla;
    public static FootBoard theFootBoard;

    public static int numGrilla = 0;

    public static void generateScene(){
        if(theMainActivity==  null){return;}
        Scene scene = new Scene();
        scene.setBackground(new Background(0.9804f, 0.6274f, 0.8784f));
        
        //Probamos las celdas
        FontFactory.setAssetBasePath("fuentes/");
        final ITexture fontTexture = new BitmapTextureAtlas(theMainActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);//textura para la fuente
        Font myFont;
        myFont = FontFactory.createFromAsset(theMainActivity.getFontManager(), fontTexture, theMainActivity.getAssets(), "fuente.ttf", 28, true, Color.WHITE);
        myFont.load();

        //Creamos Elementos Visibles
        HeadBoard headBoard = new HeadBoard(theMainActivity.getVertexBufferObjectManager(), myFont);//sin patron singleton
        headBoard.addToScene(scene);
        Bridge.theHeadBoard = headBoard;

        Grilla myGrilla = new Grilla(theMainActivity.celdaTextureRegion, theMainActivity.getVertexBufferObjectManager(), myFont, scene);
        myGrilla.generar();
        Bridge.theGrilla = myGrilla;

        FootBoard footBoard = new FootBoard(theMainActivity.celdaTextureRegion, theMainActivity.getVertexBufferObjectManager(), myFont, scene);
        Bridge.theFootBoard = footBoard;
      
        myGrilla.registerTouchAreaInScene();
        theMainActivity.backScene.detachSelf();
        theMainActivity.backScene = scene;

        theMainActivity.myEngine.setScene(scene);
    }
}
