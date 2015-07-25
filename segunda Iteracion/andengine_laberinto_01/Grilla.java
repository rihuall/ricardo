package com.example.mipc.andengine_laberinto_01;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;

/**
 * Created by MIPC on 27/05/2015.
 */
public class Grilla {
    public static final int MARGIN = 6;
    public static final int SIZE = 480;

    private String[][] grilla;
    private Celda[][] celdas;
    private Celda[] celdasSeleccionadas;
    
    private ITextureRegion celdaTextureRegion;
    VertexBufferObjectManager vertexBufferObjectManager;
    private Font myFont;
    
    private ArrayList<Word> wordsHorizontal;
    private ArrayList<Word> wordsVertical;
    private Word selectedWord;
    private Celda selectedCelda;

    public Grilla(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont){
        this.celdaTextureRegion = pTextureRegion;
        this.vertexBufferObjectManager = pVertexBufferObjectManager;
        this.myFont = myFont;

        wordsHorizontal = new ArrayList<Word>();
        wordsVertical = new ArrayList<Word>();
        selectedWord = null;
        selectedCelda = null;

    }

    public void generar(Scene scene){

        Rectangle rectangulo = new Rectangle(
                MainActivity.CAMARA_WIDTH/2,
                MainActivity.CAMARA_HEIGHT/2,
                MainActivity.CAMARA_WIDTH,
                MainActivity.CAMARA_WIDTH, vertexBufferObjectManager);
        rectangulo.setColor(0.0f, 0.0f, 0.0f);
        scene.attachChild(rectangulo);
        //scene.registerTouchArea(rectangulo);
        String [][] unaGrilla =  {//ES AL REVES
                {"A","U","F","E","R"},//COLUMNA 1
                {"P","O","D","E","R"},
                {"H","I","J","O","S"},
                {"P","O","L","O","S"},
                {"Q","U","E","S","O"},
                {"V","I","S","T","Z"},
                };
        grilla = unaGrilla;

        celdas = new Celda[grilla.length][grilla[0].length];
        //wordsHorizontal.add(new Word("AUFER", 0, 0, 0, this, scene));
        //wordsHorizontal.add(new Word("VictorianoOZ", 1, 0, 0, this, scene));
        //wordsHorizontal.add(new Word("CONTRERAS", 3, 0, 0, this, scene));
        //wordsVertical.add(new Word("AVECAHTQWSDX", 0, 0, 1, this, scene));
        wordsHorizontal.add(new Word("AUFER","Hola mundo", 0, 0, 0, this, rectangulo, scene));
        wordsHorizontal.add(new Word("VictorianoOZ", "Este es mi segundo nombre", 1, 0, 0, this, rectangulo,scene));
        wordsHorizontal.add(new Word("CONTRERAS", "Es un apellido que te contradice", 3, 0, 0, this, rectangulo,scene));
        wordsVertical.add(new Word("AVECAHTQWSDX", "seleccion al azar de caracteres", 0, 0, 1, this, rectangulo,scene));
        //recorremos la grilla
        /*
        for(int i = 0; i < grilla.length; i++){
            for(int j = 0; j < grilla[0].length; j++){
                Celda celda = new Celda((i+1)*Celda.SIZE_SIDE, (j+1)*Celda.SIZE_SIDE, celdaTextureRegion,  vertexBufferObjectManager,  myFont);
                celda.setLetra(grilla[i][j]);
                celda.setRowCol(i, j);
                celda.setGrilla(this);
                celda.addToScene(scene);
                celdas[i][j] = celda;
            }
        }
        */
    }

    public void seleccionarCR(int posI,int posJ){//seleccionar Column o Row

        celdasSeleccionadas = new Celda[celdas[0].length];
        for(int i = 0; i < grilla.length; i++){
            celdas[i][posJ].selectCelda();
            celdasSeleccionadas[i] = celdas[i][posJ];
        }

    }
    public void createCeldas(int origenConstant, int origenVariable, String word){
        for(int i = 0; i < word.length(); i++){

        }
    }
    //GETTERS AND SETTERS:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public ITextureRegion getCeldaTextureRegion() {
        return celdaTextureRegion;
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return vertexBufferObjectManager;
    }

    public Font getMyFont() {
        return myFont;
    }

    public ArrayList<Word> getWordsVertical() {
        return wordsVertical;
    }

    public ArrayList<Word> getWordsHorizontal() {
        return wordsHorizontal;
    }

    public Word getSelectedWord() {
        return selectedWord;
    }

    public void setSelectedWord(Word selectedWord) {
        this.selectedWord = selectedWord;
    }

    public Celda getSelectedCelda() {
        return selectedCelda;
    }

    public void setSelectedCelda(Celda selectedCelda) {
        this.selectedCelda = selectedCelda;
    }
}