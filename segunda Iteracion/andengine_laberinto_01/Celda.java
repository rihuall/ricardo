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

import java.util.ArrayList;

/**
 * Created by MIPC on 27/05/2015.
 */
public class Celda extends Sprite{
    public static final float SIZE_SIDE = 39; //32;//tamaño lado
    public static final float SELECT_MAIN = 150;
    public static final float SELECT_SECONDARY = 200;
    //public static final float NO_SELECT = 256;
    public static final float NO_SELECT = 0;
    //private BitmapTextureAtlas myAtlas;
    //private ITextureRegion celdaTextureRegion;
    //private Sprite celdaSprite;
    private Rectangle selectMain;//principal
    private Text myLetter;
    private String letra;
    private Grilla myGrilla;

    private int posRow;//de la celda Row
    private int posCol;//de la celda Column
    private ArrayList<Word> words;// palabras a las que pertenece la celda
    private int indexSelect = 0;

    public Celda(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

        this.words = new ArrayList<Word>();
        this.selectMain = new Rectangle(this.getWidth() / 2, this.getHeight() / 2,32,32, getVertexBufferObjectManager());
        selectMain.setColor(0.9f, 0.1f, 0.2f, NO_SELECT);//.detachChild(this);

        myLetter = new Text(this.getWidth()/2, this.getHeight()/2, myFont, "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ", getVertexBufferObjectManager());
        myLetter.setColor(Color.BLACK);
        myLetter.setText("W");
        //myLetter.setSize(this.getWidth()*0.5f,this.getHeight()*0.5f);

        this.setScale(SIZE_SIDE/this.getWidth());

        this.attachChild(selectMain);
        this.attachChild(myLetter);


    }
    public void selectCelda(){
        selectMain.setColor(0.3f, 0.1f, 0.5f, SELECT_SECONDARY);
    }
    public void deselectCelda(){
        //selectMain.setColor(256.0f, 256.0f, 0.0f);
        selectMain.setColor(0.0f, 0.0f, 0.0f, 256.0f);
    }
    public void setRowCol(int posRow, int posCol){
        this.posRow = posRow;
        this.posCol = posCol;
    }
    public boolean isEqualsRowCol(int aRow, int aCol){
        return (this.posRow==aRow);
    }

    public void setGrilla(Grilla unaGrilla){
        this.myGrilla = unaGrilla;
    }
    public void addToScene(Scene scene){
        scene.registerTouchArea(this);
        scene.attachChild(this);
    }
    public void addWord(Word theWord){
        this.words.add(theWord);
    }

    public void setLetra(String unaLetra){
        letra = unaLetra;
        myLetter.setText(letra);
    }
    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
        //boolean de control
        //Cuando el modifier empieza
        if(pSceneTouchEvent.isActionUp()){
            if(words!=null){
                if(myGrilla!=null){
                    if(myGrilla.getSelectedWord()!= null){
                        myGrilla.getSelectedWord().deselectWord();
                    }
                    if(this.words.size()>1){//entonces pertenece a dos palabras
                        if(myGrilla.getSelectedCelda()!= null){//Existe celda seleccionada
                            if(myGrilla.getSelectedCelda().isEqualsRowCol(this.posRow, this.posCol)){
                                //selecciono el siguiente
                                indexSelect++;
                                if(indexSelect>=words.size()){
                                    indexSelect = 0;
                                }
                            }
                        }
                    }
                    myGrilla.setSelectedWord(words.get(indexSelect));
                    myGrilla.setSelectedCelda(this);
                }
                words.get(indexSelect).selectWord();
            }
            selectMain.setColor(0.3f, 0.5f, 0.2f, SELECT_MAIN);
        }
        return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
    }
}
