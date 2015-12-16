//Palabras
package com.example.mipc.andengine_laberinto_01;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by EBERCILLO on 11/06/2015.
 */
public class Word {
    private Grilla grilla;//Coordenadas
    private String word;//palabra
    private String clue;//pista o indicio
    private ArrayList<Celda> celdas;

    public Word(String theWord, String theClue, int row, int  col, int sentidoHV,
                Grilla theGrilla, Rectangle rectangulo, Scene scene, HashMap<String, Celda> celdasunicas){//ITextureRegion celTRegion, VertexBufferObjectManager vertBuffObjMngr, Font myFont, Scene scene){
        this.grilla = theGrilla;
        this.word = theWord;
        this.clue = theClue;
        this.celdas = new ArrayList<Celda>();

        if(sentidoHV == 0){//Horizontal == true
            //debe aumentar en column (EJE X)
            for(int i = 0; i < word.length(); i++ ){
                Celda celda = null;
                /*
                //compruevo si esa celda ya fue creada en las palabras verticales
                */
                celda = new Celda(
                        (col+i+1)*Celda.SIZE_SIDE -(Celda.SIZE_SIDE/2)+Grilla.MARGIN,
                        Grilla.SIZE-(row+1)*Celda.SIZE_SIDE +(Celda.SIZE_SIDE/2)-Grilla.MARGIN,
                        word.substring(i, i + 1),//EXTRAEMOS UNA LETRA
                        grilla.getCeldaTextureRegion(),
                        grilla.getVertexBufferObjectManager(),
                        grilla.getMyFont());
                
                //extraigo una letra
                celda.setRowCol(row, col + i);
                celda.addWord(this);//guardo pertenencia de la celda
                celda.setGrilla(this.grilla);
                rectangulo.attachChild(celda);
                celdas.add(celda);//OJO con el object

                celdasunicas.put(""+celda.getRow()+""+celda.getCol(), celda);
            }
        }else{//Vertical (False) verifica si la celda ya esta
            //debe aumentar en row (EJE Y)
            for(int i = 0; i < word.length(); i++ ){
                Celda celda = null;
                //comruebo si la celda fue creada en las palabras horizontales
                boolean  exists = false;
                if(grilla.getWordsHorizontal()!= null){
                    if(grilla.getWordsHorizontal().size()>0){

                        for(Word aWord : grilla.getWordsHorizontal()){

                            celda = aWord.getIsCelda(row + i, col);
                            if(celda != null){
                                exists = true;
                                break;
                            }
                        }
                    }
                }
                if(celda == null){
                    celda = new Celda(
                            (col+1)*Celda.SIZE_SIDE -(Celda.SIZE_SIDE/2)+Grilla.MARGIN,
                            Grilla.SIZE-(row+i+1)*Celda.SIZE_SIDE +(Celda.SIZE_SIDE/2)-Grilla.MARGIN,
                            word.substring(i, i + 1),
                            grilla.getCeldaTextureRegion(),
                            grilla.getVertexBufferObjectManager(),
                            grilla.getMyFont());
                }
                //extraigo una letra
                celda.setRowCol(row + i, col);
                celda.addWord(this);//guardo pertenencia de la celda
                celda.setGrilla(this.grilla);
                if(!exists){
                    rectangulo.attachChild(celda);
                }
                celdas.add(celda);//OJO con el object

                celdasunicas.put(""+celda.getRow()+""+celda.getCol(), celda);
            }
        }
    }

    public void selectWord(){
        for(Celda celda : celdas){
            celda.selectSecondaryCelda();
        }
    }
    public void deselectWord(){
        for(Celda celda : celdas){
            celda.deselectCelda();
        }
    }
    public Celda getIsCelda(int aRow, int aCol){
        for(Celda celda : celdas){
            if(celda.isEqualsRowCol(aRow, aCol)){
                return celda;
            }
        }
        return null;
    }

    public String getTheWord(){return word;}
    public String getClue(){
        return clue;
    }

    public Celda getNextCeldaTo(int rowBackCell, int colBackCell){
        int next = 0;
        for(Celda celda : celdas){
            next++;
            if(celda.isEqualsRowCol(rowBackCell, colBackCell)){
                if(next>=celdas.size()){
                    next=celdas.size()-1;
                }
                celdas.get(next).selectMainCelda();
                return  celdas.get(next);
            }
        }
        return null;
    }
    public Celda getBackCeldaTo(int rowBackCell, int colBackCell){
        int current = 0;
        for(Celda celda : celdas){
            if(celda.isEqualsRowCol(rowBackCell, colBackCell)){
                if(current<=0){
                    current = 1;
                }
                celdas.get(current-1).selectMainCelda();
                return  celdas.get(current-1);
            }
            current++;
        }
        return null;
    }

    public void showWord(){
        for(Celda celda : celdas){
            celda.showLetra();
        }
    }
    public void hideWord(){
        for(Celda celda : celdas){
            celda.hideLetra();
        }
    }
    public void detachWord(Scene scene){//para que no sea detectado por ele evento touch
        for(Celda celda : celdas){
            celda.setVisible(false);
        }
    }
}

