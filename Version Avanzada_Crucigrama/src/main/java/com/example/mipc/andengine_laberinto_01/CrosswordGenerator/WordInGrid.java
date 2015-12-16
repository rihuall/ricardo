/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.mipc.andengine_laberinto_01.CrosswordGenerator;

/**
 *
 * @author MIPC
 */
public class WordInGrid {
    private int row = 0;//row origin
    private int col = 0;//column origin
    private String word = "";

    public WordInGrid(int rowOrigin, int colOrigin, String theWord){
        this.row = rowOrigin;
        this.col = colOrigin;
        this.word = theWord;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}