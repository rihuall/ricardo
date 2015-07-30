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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by MIPC on 14/06/2015.
 */
public class FootBoard extends Rectangle{
    public static final int FOOT_HEIGHT = 160;
    public static final float SIZE_SIDE = 60; //32;//tamaño lado
    public static final float SIZE_SIDE_THREE_BOTTOM = 50;
    public static final float MARGIN = 16; //

    public ArrayList<Text> buttomTextLytics;
    public ArrayList<Sprite> buttomLytics;
    public FootBoard( ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont, Scene scene) {

        super(
                MainActivity.CAMARA_WIDTH/2,
                FOOT_HEIGHT/2,
                MainActivity.CAMARA_WIDTH, FOOT_HEIGHT
                , pVertexBufferObjectManager);

        this.setColor(0.35f, 0.46f, 0.65f);//Colo de fondo de la cabecera

        //creo los botones
        createBotonsFoot(pTextureRegion, pVertexBufferObjectManager, myFont, scene);
        scene.attachChild(this);
        createThreeBoton(pTextureRegion, pVertexBufferObjectManager, myFont, scene);

    }

    private void createBotonsFoot(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont, Scene scene){
        this.buttomTextLytics = new ArrayList<Text>();
        this.buttomLytics = new ArrayList<Sprite>();
        char[] aLetters = {'a','b','c','d','e','f','g','h','i','j','k','l'};
        for(int i = 0; i < 12; i++) {//12 vueltas 12 botones
            final Text unTextofoot = new Text(SIZE_SIDE / 2, SIZE_SIDE / 2, myFont, "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ", getVertexBufferObjectManager());
            unTextofoot.setColor(Color.RED);
            unTextofoot.setText(""+aLetters[i]);
            Sprite unBotonFoot = new Sprite(
                    (SIZE_SIDE + MARGIN)/2 + ((i%6)*(SIZE_SIDE+MARGIN/2)),
                    (FOOT_HEIGHT - (((int)(i/6)+1)*(SIZE_SIDE + MARGIN/2)-SIZE_SIDE/2)),
                    SIZE_SIDE, SIZE_SIDE,
                    pTextureRegion,
                    pVertexBufferObjectManager) {

                        @Override
                        public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                            if (pSceneTouchEvent.isActionUp()) {
                                //unTextofoot.setText("N");
                                if (Bridge.theGrilla != null) {
                                    Bridge.theGrilla.writeInCell(unTextofoot.getText().toString());
                                }

                            }
                            return true;
                            //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY)
                        }
                    };
            unBotonFoot.attachChild(unTextofoot);
            this.buttomTextLytics.add(unTextofoot);
            this.buttomLytics.add(unBotonFoot);
            //this.attachChild(unBotonFoot);
            scene.registerTouchArea(unBotonFoot);
        }
    }

    private void createThreeBoton(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont, Scene scene){

        float unMargen = (FOOT_HEIGHT-(SIZE_SIDE_THREE_BOTTOM*3))/4;
        float posX = MainActivity.CAMARA_WIDTH-SIZE_SIDE_THREE_BOTTOM/2 - unMargen;
        float posY = FOOT_HEIGHT-SIZE_SIDE_THREE_BOTTOM/2 - unMargen;

        final Text textofoot1 = new Text(SIZE_SIDE_THREE_BOTTOM / 2,SIZE_SIDE_THREE_BOTTOM / 2,
                myFont, "<", getVertexBufferObjectManager());
        final Text textofoot2 = new Text(SIZE_SIDE_THREE_BOTTOM / 2,SIZE_SIDE_THREE_BOTTOM / 2,
                myFont, "@", getVertexBufferObjectManager());
        final Text textofoot3 = new Text(SIZE_SIDE_THREE_BOTTOM / 2, SIZE_SIDE_THREE_BOTTOM / 2,
                myFont, "*", getVertexBufferObjectManager());

        textofoot1.setColor(Color.RED);textofoot2.setColor(Color.RED);textofoot3.setColor(Color.RED);
        textofoot1.setText("<");textofoot2.setText("@");textofoot3.setText("*");

        Sprite botonFoot1 = new Sprite(
                posX,posY,
                SIZE_SIDE_THREE_BOTTOM, SIZE_SIDE_THREE_BOTTOM,
                pTextureRegion,
                pVertexBufferObjectManager) {
                    @Override
                    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                        if (pSceneTouchEvent.isActionUp()) {
                            //hace algo especial (Tecla DEL)
                            if(Bridge.theGrilla!= null){
                                Bridge.theGrilla.delWriteInCell();
                            }
                        }
                        return true;
                        //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY)
                    }
                };
        Sprite botonFoot2 = new Sprite(
                posX,posY-(SIZE_SIDE_THREE_BOTTOM+unMargen),
                SIZE_SIDE_THREE_BOTTOM, SIZE_SIDE_THREE_BOTTOM,
                pTextureRegion,
                pVertexBufferObjectManager) {
                    private boolean isShow = false;
                    @Override
                    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                        if (pSceneTouchEvent.isActionUp()) {
                            //hace algo especial (Mostrar ocultar grilla)
                            if(isShow){
                                if(Bridge.theGrilla!= null){//mostramos resultado
                                    Bridge.theGrilla.showGrilla();
                                    isShow = false;
                                }
                            }else{//limpiamos la grilla
                                if(Bridge.theGrilla!= null){
                                    Bridge.theGrilla.cleanGrilla();
                                    isShow = true;
                                }
                            }

                        }
                        return true;
                        //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY)
                    }
                };
        Sprite botonFoot3 = new Sprite(
                posX,posY-((SIZE_SIDE_THREE_BOTTOM+unMargen)*2),
                SIZE_SIDE_THREE_BOTTOM, SIZE_SIDE_THREE_BOTTOM,
                pTextureRegion,
                pVertexBufferObjectManager) {
                    @Override
                    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                        if (pSceneTouchEvent.isActionUp()) {//generar nueva grilla
                            if(Bridge.theGrilla !=  null){
                                Bridge.theGrilla.generar();
                                if(Bridge.theHeadBoard != null){
                                    Bridge.theHeadBoard.setMyTextClue("Nueva grilla "+Bridge.numGrilla);
                                }
                                if(Bridge.theFootBoard!=null){
                                    Bridge.theFootBoard.detachChildren();
                                }
                            }
                        }
                        return true;
                        //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY)
                    }
                };

        botonFoot1.attachChild(textofoot1);
        botonFoot2.attachChild(textofoot2);
        botonFoot3.attachChild(textofoot3);

        //textofoot1.setText("@");

        //this.attachChild(botonFoot1);
        //this.attachChild(botonFoot2);
        //this.attachChild(botonFoot3);
        scene.attachChild(botonFoot1);
        scene.attachChild(botonFoot2);
        scene.attachChild(botonFoot3);
        scene.registerTouchArea(botonFoot1);
        scene.registerTouchArea(botonFoot2);
        scene.registerTouchArea(botonFoot3);
    }

    public void activateButtonsLetters(String theWord){
        //Activar los botones
        if(buttomTextLytics != null && theWord.length()<=buttomTextLytics.size()){
            this.detachChildren();
            theWord = shuffle(theWord);
            for(int i = 0; i < theWord.length(); i++){//3 botones de la derecha
                buttomTextLytics.get(i).setText(theWord.substring(i,i+1));
                this.attachChild(buttomLytics.get(i));
                //buttomTextLytics.get(i).setText("M");

            }
        }
    }

    /**
     * Desordena un string
     * @param str
     * @return
     */
    private static String shuffle(String str) {

        List<String> letters = Arrays.asList(str.split(""));
        Collections.shuffle(letters);

        String salida = "";
        for (String s : letters)
            salida += s;

        return salida;

    }
}
