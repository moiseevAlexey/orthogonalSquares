package com.Studying;

import static org.junit.Assert.*;

public class LatinSquareTest {
    @org.junit.Test
    public void findOrthogonalSquares() throws Exception {
        int test1 = 6;
        int test2 = 2;
        int test3 = 635;
        assertEquals(test1, new LatinSquare("test1.txt").findOrthogonalSquares());
        assertEquals(test2, new LatinSquare("test2.txt").findOrthogonalSquares());
        assertEquals(test3, new LatinSquare("test3.txt").findOrthogonalSquares());
    }

    @org.junit.Test
    public void fileNotFound() throws Exception {
        new LatinSquare("t232xt");
    }

    @org.junit.Test
    public void emptyFile() throws Exception {
        new LatinSquare("testE1.txt");
    }

    @org.junit.Test
    public void fileHaveLetter() throws Exception {
        new LatinSquare("testE2.txt");
    }

    @org.junit.Test
    public void needMoreSigns() throws Exception {
        new LatinSquare("testE3.txt");
    }
}