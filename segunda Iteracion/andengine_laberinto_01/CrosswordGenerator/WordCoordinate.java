package com.example.mipc.andengine_laberinto_01.CrosswordGenerator;

public class WordCoordinate {

    private int x;
    private int y;
    private int score;

    public WordCoordinate(int x, int y, int direction, int score) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.score = score;
    }

    private int direction; // 0 => hor, 1 => ver

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getScore() {
        return score;
    }
}
