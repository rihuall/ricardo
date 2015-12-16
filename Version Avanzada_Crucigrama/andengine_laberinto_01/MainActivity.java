//Actividad Principal
package com.example.mipc.andengine_laberinto_01;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.io.IOException;


public class MainActivity extends SimpleBaseGameActivity {

    public static final int CAMARA_WIDTH = 480;//Anchura de Pantalla
    public static final int CAMARA_HEIGHT = 800;//Altura de Pantalla

    private Camera camera;
    public Scene backScene;

    private BitmapTextureAtlas myAtlas;
    public ITextureRegion celdaTextureRegion;
    public Engine myEngine;

    //private Celda unaCelda;

     @Override
    protected void onCreateResources() throws IOException {//#2

         BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");//indicamos donde están las imágenes
         myAtlas = new BitmapTextureAtlas(getTextureManager(), 1000, 1000, TextureOptions.DEFAULT);//Atlas lugar en memoria donde estaran las iamgenes
         celdaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(myAtlas, this, "celda.png", 0, 0);//ubicamos la imagen en el atlas

         myAtlas.load();

    }

    @Override
    protected Scene onCreateScene() {//#3
        myEngine = this.mEngine;
        Bridge.theMainActivity =  this;

        this.backScene= new Scene();
        this.backScene.setBackground(new Background(0.9804f, 0.6274f, 0.8784f));
        //Probamos la celda

        FontFactory.setAssetBasePath("fuentes/");
        final ITexture fontTexture = new BitmapTextureAtlas(getTextureManager(), 256, 256, TextureOptions.BILINEAR);//textura para la fuente
        Font myFont;
        myFont = FontFactory.createFromAsset(getFontManager(), fontTexture, getAssets(), "fuente.ttf", 28, true, Color.WHITE);
        myFont.load();

        //creamos elementos visibles
        HeadBoard headBoard = new HeadBoard(getVertexBufferObjectManager(), myFont);//sin patron singleton
        headBoard.addToScene(backScene);
        Bridge.theHeadBoard = headBoard;

        Grilla myGrilla = new Grilla(celdaTextureRegion, getVertexBufferObjectManager(), myFont, backScene);
        myGrilla.generar();
        Bridge.theGrilla = myGrilla;

        FootBoard footBoard = new FootBoard(celdaTextureRegion, getVertexBufferObjectManager(), myFont, backScene);
        Bridge.theFootBoard = footBoard;
      
        myGrilla.registerTouchAreaInScene();

        return this.backScene;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {//#1
        camera = new Camera(0,0, CAMARA_WIDTH, CAMARA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(
                true,
                ScreenOrientation.PORTRAIT_FIXED,//Vertical
                new FillResolutionPolicy(),
                camera);
        return engineOptions;
    }

}
