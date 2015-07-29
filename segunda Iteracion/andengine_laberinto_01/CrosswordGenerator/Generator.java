package com.example.mipc.andengine_laberinto_01.CrosswordGenerator;

import java.util.*;

public class Generator {

    private Grid grid;
    private List<String> words;

    //private Hashtable<Integer, List<String>> horizontalAnnex;
    //private Hashtable<Integer, List<String>> verticalAnnex;
    //private Hashtable<Integer, String> horizontalAnnex;
    //private Hashtable<Integer, String> verticalAnnex;
    //private Hashtable<Integer, WordInGrid> horizontalAnnex;
    private HashMap<Integer, WordInGrid> horizontalAnnex;
    private HashMap<Integer, WordInGrid> verticalAnnex;

    private Random rnd;

    public Generator(Grid grid, List<String> words) {
        this.grid = grid;
        this.words = words;

        rnd = new Random();

        orderWords();

        //horizontalAnnex = new Hashtable<Integer, List<String>>();
        //verticalAnnex = new Hashtable<Integer, List<String>>();
        horizontalAnnex = new HashMap<Integer, WordInGrid>();
        verticalAnnex = new HashMap<Integer, WordInGrid>();
    }

    private void orderWords(){
        Collections.shuffle(words);
        Collections.sort(words, new StringLengthComparator());
    }

    public int generate(){

        List<String> remainingWords = new ArrayList<String>();
        for(String word : words)
            remainingWords.add(word);

        //initialization
        String word = remainingWords.get(0);
        WordCoordinate coordinate = new WordCoordinate(0,0,(byte)rnd.nextInt(2),0);
        /*new WordCoordinate(rnd.nextInt(grid.getMaxRows()-word.length()),
                rnd.nextInt(grid.getMaxColumns()-word.length()),rnd.nextInt(2),0);*/

        while (word != null){

            writeWordToGrid(word, coordinate);
            remainingWords.remove(word);

            addToAnnex(word, coordinate);

            Object[] result = pickupWord(remainingWords);//[:
            word = (String)result[0];
            coordinate = (WordCoordinate)result[1];
        }

        return words.size() - remainingWords.size();
    }

    private Object[] pickupWord(List<String> remainingWords) {
        WordCoordinate maxCoordinate = null;
        String theWord = null;

        for(String word : remainingWords) {

            List<WordCoordinate> coordinates = getPossibleCoordinates(word);

            if (coordinates.size() == 0) continue;

            Collections.sort(coordinates, new WordCoordinateComparator());//ordena de menor a mayor segun el getScore

            if (maxCoordinate == null || (maxCoordinate.getScore() < coordinates.get(0).getScore())) {

                maxCoordinate = coordinates.get(0);
                theWord = word;
            }
        }

        return new Object[]{theWord, maxCoordinate};
    }

    private List<WordCoordinate> getPossibleCoordinates(String word) {

        List<WordCoordinate> coordList = new ArrayList<WordCoordinate>();

        for(int k = 0; k < word.length(); k++){

            for(int i = 0; i < grid.getMaxRows(); i++){

                for(int j = 0; j < grid.getMaxColumns(); j++) {

                    //horizontal
                    if (j - k >= 0 && ((j - k) + word.length() < grid.getMaxColumns()))
                        coordList.add(new WordCoordinate(i, j - k, 0, 0));

                    //vertical
                    if (i - k >= 0 && ((i - k) + word.length() < grid.getMaxRows()))
                        coordList.add(new WordCoordinate(i - k, j, 1, 0));
                }
            }
        }
        
        List<WordCoordinate> toRet = new ArrayList<WordCoordinate>();

        for(WordCoordinate coordinate : coordList) {

            int score = checkScore(word, coordinate);
            if (score == 0) continue;

            toRet.add(new WordCoordinate(coordinate.getX(), coordinate.getY(), coordinate.getDirection(), score));
        }

        return toRet;
    }

    private int checkScore(String word, WordCoordinate coordinate){
        int row = coordinate.getX();
        int col = coordinate.getY();

        if(col < 0 || row < 0)
            return 0;

        int count = 1;
        int score = 1;

        for(Character letter : word.toCharArray()){

            if(!isEmpty(row, col) && grid.getValue(row,col) != letter)
                return 0;

            if(grid.getValue(row,col) == letter)
                score++;

            if(coordinate.getDirection() == 1){
                //vertical
                if(grid.getValue(row,col) != letter && (!isEmpty(row, col + 1) || !isEmpty(row, col - 1)))
                    return 0;

                if(count == 1 && !isEmpty(row-1, col))
                    return 0;

                if(count == word.length() && !isEmpty(row+1,col))
                    return 0;

                row++;

            }else{
                //Horizontal

                if(grid.getValue(row,col) != letter && (!isEmpty(row - 1, col) || !isEmpty(row + 1, col)))
                    return 0;

                if(count == 1 && !isEmpty(row, col-1))
                    return 0;

                if(count == word.length() && !isEmpty(row,col+1))
                    return 0;

                col++;
            }

            count++;
        }

        return score;
    }

    private boolean isEmpty(int row, int column){

        char value = grid.getValue(row, column);

        return  value == ' ' || value == 0;
    }

    private void writeWordToGrid(String word, WordCoordinate coordinate) {

        int k = 0;
        // expresión1 ? expresión2 : expresión3
        // Si expresión1 es cierta entonces se evalúa expresión2
        //Si expresión1 es falsa, se evalúa expresión3
        for (int i = (coordinate.getDirection() == 0) ? coordinate.getY() : coordinate.getX();
             i < word.length() + ((coordinate.getDirection() == 0) ? coordinate.getY() : coordinate.getX());
             i++)
            grid.setValue(coordinate.getDirection() == 0 ? coordinate.getX() : i,
                    coordinate.getDirection() == 0 ? i : coordinate.getY(), word.charAt(k++));

    }

    private void addToAnnex(String word, WordCoordinate coordinate) {
        if (coordinate.getDirection() == 0) {//Horizontal
            //List<String> l = horizontalAnnex.get(coordinate.getX());
            WordInGrid l = horizontalAnnex.get(coordinate.getX());
            //if (l == null) l = new ArrayList<String>();
            //if (l == null) l = new String();
            if (l == null ) l = new WordInGrid(coordinate.getY(), coordinate.getX(), word);
            //l.add(word);
            //l = word;
            //l.setWord(word);
            horizontalAnnex.put(coordinate.getX(), l);
        }else{//vertical

            //List<String> l = verticalAnnex.get(coordinate.getY());
            //String l = verticalAnnex.get(coordinate.getY());
            WordInGrid l = verticalAnnex.get(coordinate.getY());
            //if (l == null) l = new ArrayList<String>();
            if (l == null) l = new WordInGrid(coordinate.getY(), coordinate.getX(), word);
            //l.add(word);
            //l = word;
            verticalAnnex.put(coordinate.getY(), l);
        }
    }

    //Hashtable<Integer, List<String>> getHorizontalAnnex(){
    //Hashtable<Integer, String> getHorizontalAnnex(){
    public HashMap<Integer, WordInGrid> getHorizontalAnnex(){
        return horizontalAnnex;
    }

    //Hashtable<Integer, List<String>> getVerticalAnnex(){
    //Hashtable<Integer, String> getVerticalAnnex(){
    public HashMap<Integer, WordInGrid> getVerticalAnnex(){
        return verticalAnnex;
    }
}
