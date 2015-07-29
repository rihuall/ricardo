package com.example.mipc.andengine_laberinto_01.CrosswordGenerator;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s2.length() - s1.length();
    }
}