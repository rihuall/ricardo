package com.example.mipc.andengine_laberinto_01.CrosswordGenerator;

public class Grid {

    private char[][] matrix;
    private int maxRows;
    private int maxColumns;

    public Grid(int maxRows, int maxColumns){
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;

        matrix = new char[maxRows][maxColumns];

        initialize();
    }
    /**
     *
     * @param row
     * @param column
     * @param val
     */
    public void setValue(int row, int column, char val){

        if(row >= maxRows | column >= maxColumns | row < 0 | column < 0)return;

        matrix[row][column] = val;
    }

    public char getValue(int row, int column){

        if(row >= maxRows | column >= maxColumns | row < 0 | column < 0)return 0;

        return matrix[row][column];
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public void initialize(){

        for(int i = 0; i < maxRows; i++)
            for(int j = 0; j < maxColumns; j++)
                matrix[i][j] = ' ';
    }

    public void show() {

        for (int j = 0; j < maxColumns; j++)
            System.out.print("\t" + j);

        System.out.println();

        for (int i = 0; i < maxRows; i++) {
            System.out.print(i + "\t");

            for (int j = 0; j < maxColumns; j++)
                System.out.print((matrix[i][j] == ' ' ? '-' : matrix[i][j] )+ "\t");

            System.out.println();
        }
    }
}
