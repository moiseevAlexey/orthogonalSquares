package com.Studying;

public class Main {

    public static void main(String[] args) throws Exception {
        LatinSquare ort = new LatinSquare("test1.txt");
        ort.findOrthogonalSquares();
        ort = new LatinSquare("test2.txt");
        ort.findOrthogonalSquares();
        ort = new LatinSquare("test3.txt");
        ort.findOrthogonalSquares();
    }
}
