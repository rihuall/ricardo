package com.example.mipc.andengine_laberinto_01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.io.IOException;


public class MainActivity extends SimpleBaseGameActivity {

    private static final int CAMARA_WIDTH = 800;
    private static final int CAMARA_HEIGHT = 800;

    private Camera camera;
    private Scene scene;

     @Override
    protected void onCreateResources() throws IOException {//#2
        //rectangulo = new Rectangle(camera.getWidth()/2, camera.getHeight()/2, 100, 100, getVertexBufferObjectManager());
        //rectangulo.setColor(0.3f, 0.5f, 0.2f);

    }

    @Override
    protected Scene onCreateScene() {//#3
        this.scene = new Scene();
        this.scene.setBackground(new Background(0.9804f, 0.6274f, 0.8784f));
        //scene.attachChild(rectangulo);
        return this.scene;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {//#1
        camera = new Camera(0,0, CAMARA_WIDTH, CAMARA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(
                true,
                ScreenOrientation.LANDSCAPE_FIXED,
                new FillResolutionPolicy(),
                camera);
        return engineOptions;
    }
}
// 1.- onCreateEngineOptions() --- creas las opciones del motor
// 2.- onCreateResources() --- cargas los elementos a usar
// 3.- onCreateScene() --- creas la escena
// 4.- onPopulateScene --- creas las entidades del juego