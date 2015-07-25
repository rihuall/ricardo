package com.example.mipc.andengine_laberinto_01;

import android.graphics.Color;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by MIPC on 11/06/2015.
 */
public class HeadBoard extends Rectangle {
    public static final int HEAD_HEIGHT = 160;
    private Text myTextClue;//mi indicio, pista


    public HeadBoard(VertexBufferObjectManager pVertexBufferObjectManager,Font myFont) {

        super(
                MainActivity.CAMARA_WIDTH/2,
                MainActivity.CAMARA_HEIGHT - HEAD_HEIGHT/2,
                MainActivity.CAMARA_WIDTH, HEAD_HEIGHT,
                pVertexBufferObjectManager);

        myTextClue = new Text(this.getWidth()/2, this.getHeight()/2, myFont, "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ", getVertexBufferObjectManager());
        myTextClue.setColor(Color.BLACK);//color del texto
        myTextClue.setText("- - -");
        myTextClue.setAutoWrapWidth(HEAD_HEIGHT);

        this.setColor(0.65f, 0.46f, 0.35f);//Colo de fondo de la cabecera
        this.attachChild(myTextClue);
    }

    public void addToScene(Scene scene){
        scene.attachChild(this);
    }

    public void setMyTextClue(String newText){
        myTextClue.setText(newText);
    }
}
