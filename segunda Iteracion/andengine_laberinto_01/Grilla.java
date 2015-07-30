package com.example.mipc.andengine_laberinto_01;

import com.example.mipc.andengine_laberinto_01.CrosswordGenerator.Generator;
import com.example.mipc.andengine_laberinto_01.CrosswordGenerator.Grid;
import com.example.mipc.andengine_laberinto_01.CrosswordGenerator.WordInGrid;
import com.example.mipc.andengine_laberinto_01.CrosswordGenerator.WordsDic;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MIPC on 27/05/2015.
 */
public class Grilla {
    public static final int MARGIN = 6;
    public static final int SIZE = 480;

    private ITextureRegion celdaTextureRegion;
    private VertexBufferObjectManager vertexBufferObjectManager;
    private Font myFont;
    private Scene myScene;
    Rectangle rectangulo;
    private ArrayList<Word> wordsHorizontal;
    private ArrayList<Word> wordsVertical;
    private Word selectedWord;
    private Celda selectedCelda;

    private HashMap<String, Celda> celdasUnicas;

    public Grilla(ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Font myFont, Scene scene){
        this.celdaTextureRegion = pTextureRegion;
        this.vertexBufferObjectManager = pVertexBufferObjectManager;
        this.myFont = myFont;
        this.myScene = scene;

        generar();
    }

    public void generar(){
        if(this.celdaTextureRegion == null ||
                this.vertexBufferObjectManager == null ||
                this.myFont == null ||
                this.myScene == null
                ){return;}
        Bridge.numGrilla++;
        wordsHorizontal = new ArrayList<Word>();
        wordsVertical = new ArrayList<Word>();
        selectedWord = null;
        selectedCelda = null;
        if(rectangulo!= null){
            for(Word word:wordsHorizontal)
                word.detachWord(myScene);
            for(Word word:wordsVertical)
                word.detachWord(myScene);
            //rectangulo.detachChildren();
            //myScene.detachChild(rectangulo);
        }
        rectangulo = new Rectangle(
                MainActivity.CAMARA_WIDTH/2,
                MainActivity.CAMARA_HEIGHT/2,
                MainActivity.CAMARA_WIDTH,
                MainActivity.CAMARA_WIDTH, vertexBufferObjectManager){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                return true;
            }
        };
        rectangulo.setColor(0.0f, 0.0f, 0.0f);
        myScene.attachChild(rectangulo);
        //scene.registerTouchArea(rectangulo);
        Grid grid = new Grid(12,12);
        WordsDic dic = new WordsDic();

        List<String> words = new ArrayList<String>(dic.getDictionary().keySet());

        Generator gen = new Generator(grid, words);

        int usedCount = gen.generate();

        HashMap<Integer, WordInGrid> horizontalAnnex = gen.getHorizontalAnnex();
        HashMap<Integer, WordInGrid> verticalAnnex = gen.getVerticalAnnex();

        HashMap<String, Celda> celdasunicas = new HashMap<String, Celda>();

        wordsHorizontal = uploadInformation(horizontalAnnex, dic, 0, celdasunicas);
        wordsVertical = uploadInformation(verticalAnnex, dic, 1, celdasunicas);

        celdasUnicas = celdasunicas;
        /*
        registerTouchAreaInScene(celdasunicas, myScene);
        myScene.registerTouchArea(rectangulo);
        */
        System.out.println("cant horizontal " + wordsHorizontal.size());
        System.out.println("cant vertical "+wordsVertical.size());
        grid.show();
        gen = null;
        words = null;
        dic =null;
        Bridge.numGrilla++;
        if(Bridge.theHeadBoard!=null) Bridge.theHeadBoard.setMyTextClue("Grilla " +Bridge.numGrilla );
        /*
        wordsHorizontal.add(new Word("RIHUALL","Hola mundo", 0, 0, 0, this, rectangulo, myScene));
        wordsHorizontal.add(new Word("RAUL", "Este es mi segundo nombre", 1, 0, 0, this, rectangulo, myScene));
        wordsHorizontal.add(new Word("HUAMAN", "Es un apellido que te contradice", 3, 0, 0, this, rectangulo,myScene));
        wordsVertical.add(new Word("AVECAHTQWSDX", "seleccion al azar de caracteres", 0, 0, 1, this, rectangulo, myScene));
        */
    }

    private ArrayList<Word> uploadInformation(HashMap<Integer, WordInGrid> annex, WordsDic dic, int sentidoHV, HashMap<String, Celda> celdasunicas){
        ArrayList<Word> wordsHV = new ArrayList<Word>();
        List<Integer> list = new ArrayList<Integer>(annex.keySet());
        Collections.sort(list);
        for(Integer key : list){
            System.out.println(" "+sentidoHV+" "+annex.get(key).getRow()+" - "+annex.get(key).getCol()+annex.get(key).getWord()+ "- "+dic.getDictionary().get(annex.get(key).getWord()));
            wordsHV.add(new Word(
                    annex.get(key).getWord(),
                    dic.getDictionary().get(annex.get(key).getWord()),
                    annex.get(key).getCol(),
                    annex.get(key).getRow(),
                    sentidoHV, this, rectangulo, myScene,
                    celdasunicas
            ));
        }
        return wordsHV;
    }

    public void createCeldas(int origenConstant, int origenVariable, String word){
        for(int i = 0; i < word.length(); i++){

        }
    }
    //GETTERS AND SETTERS:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public ITextureRegion getCeldaTextureRegion() {
        return celdaTextureRegion;
    }
    public VertexBufferObjectManager getVertexBufferObjectManager() {return vertexBufferObjectManager;}
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

    public void writeInCell(String aLetter){
        selectedCelda.selectSecondaryCelda();
        selectedCelda.setText(aLetter);
        selectNextCell();
    }
    public void selectNextCell(){
        if(selectedCelda==null)return;
        Celda newSelectCelda;
        newSelectCelda = selectedWord.getNextCeldaTo(selectedCelda.getRow(), selectedCelda.getCol());
        if(newSelectCelda != null){
            selectedCelda = newSelectCelda;
        }

    }
    public void delWriteInCell(){
        if(selectedCelda==null)return;
        selectedCelda.selectSecondaryCelda();
        selectedCelda.setText("");
        selectBackCell();
    }
    public void selectBackCell(){
        Celda newSelectCelda;
        newSelectCelda = selectedWord.getBackCeldaTo(selectedCelda.getRow(), selectedCelda.getCol());
        if(newSelectCelda != null){
            selectedCelda = newSelectCelda;
        }

    }

    public void showGrilla(){
        for(Word word : wordsHorizontal){word.showWord();}
        for(Word word : wordsVertical){word.showWord();}
    }
    public void cleanGrilla(){
        for(Word word : wordsHorizontal){word.hideWord();}
        for(Word word : wordsVertical){word.hideWord();}
    }

    //private void registerTouchAreaInScene(HashMap<String, Celda> celdasunicas, Scene scene){//guarda solo una referencia de cada celda
    public void registerTouchAreaInScene(){//guarda solo una referencia de cada celda

        List<String> list = new ArrayList<String>(celdasUnicas.keySet());
        int i = 0;
        for(String clave : list){
            myScene.registerTouchArea(celdasUnicas.get(clave));
            i++;
        }
        System.out.println("cantidad de celdas total ---- "+i);
        registerArea();
    }
    public void registerArea(){
        myScene.registerTouchArea(rectangulo);
    }
}
