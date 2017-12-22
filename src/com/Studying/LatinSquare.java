package com.Studying;

import java.io.FileReader;
import java.util.ArrayList;

public class LatinSquare {

    private class Elem {
        int i;
        int j;
        int value;

        Elem(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            Elem e = (Elem) obj;
            if ((value == e.value) && (i == e.i) && (j == e.j)) {
                return true;
            }
            return false;
        }

        public boolean crosses(Elem e) {
            if ((value == e.value) || (i == e.i) || (j == e.j)) {
                return true;
            }
            return false;
        }
    }

    private class Transversal {

        private ArrayList<Elem> transversal;

        Transversal() {
            this.transversal = new ArrayList();
        }

        @Override
        public boolean equals(Object obj) {
            Transversal t = (Transversal) obj;
            for (Elem e : transversal) {
                if (t.transversal.contains(e)) {
                    return true;
                }
            }
            return false;
        }

        private boolean contains(Elem e) {
            for (Elem el : buffer.transversal) {
                if (e.crosses(el)) return true;
            }
            return false;
        }
    }

    private int size;
    private int[][] square;
    private ArrayList<Transversal> transversals;
    private Transversal buffer;
    private ArrayList<Transversal> currentSquare;
    private int ortSquaresNumber;

    private int getInt(FileReader reader) throws Exception {
        int integer = 0;
        int c;
        ArrayList<Integer> mas = new ArrayList();
        while (true) {
            c = reader.read();
            if (c == -1)
            {
                return -1;
            }
            else {
                if ((c >= '0') && (c <= '9'))
                {
                    break;
                }
            }
        }
        while ((c >= '0') && (c <= '9')) {
            mas.add(c - '0');
            c = reader.read();
        }
        for (int i = 0; i < mas.size(); i++) {
            integer = integer * 10;
            integer += mas.get(i);
        }
        return integer;
    }

    LatinSquare(String dir) throws Exception {
        FileReader reader = new FileReader(dir);

        size = getInt(reader);
        square = new int[size][size];
        transversals = new ArrayList<>();
        currentSquare = new ArrayList<>();
        buffer = new Transversal();
        ortSquaresNumber = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                square[i][j] = getInt(reader);
            }
        }
    }

    private void findTransversals(int depth) {
        if (depth == size) {
            Transversal t = new Transversal();
            t.transversal = (ArrayList<Elem>) buffer.transversal.clone();
            transversals.add(t);
            return;
        }

        for (int i = 0; i < size; i++) {
            Elem e = new Elem(i, depth, square[i][depth]);
            if (!buffer.contains(e)) {
                buffer.transversal.add(e);
                findTransversals(depth + 1);
                buffer.transversal.remove(e);
            }
        }
    }

    private void findOrthogonalSquares (int depth, int k) {
        if (depth == size) {
            ortSquaresNumber++;
            int[][] newSquare = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (Elem e : currentSquare.get(i).transversal) {
                    newSquare[e.i][e.j] = i;
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(newSquare[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = k; i < transversals.size(); i++) {
            if ((transversals.get(i).transversal.get(0).i == depth) && (!currentSquare.contains(transversals.get(i)))) {
                currentSquare.add(transversals.get(i));
                findOrthogonalSquares(depth + 1, k + 1);
                currentSquare.remove(transversals.get(i));
            }
        }
    }

    public int findOrthogonalSquares () {
        findTransversals(0);
        findOrthogonalSquares(0, 0);
        return ortSquaresNumber;
    }
}
