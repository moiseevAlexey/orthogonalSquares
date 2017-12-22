package com.Studying;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

        public boolean crosses(Elem e) {    //e пересекается с данным элементом
            if ((value == e.value) || (i == e.i) || (j == e.j)) {
                return true;
            }
            return false;
        }
    }

    private class Transversal extends ArrayList<Elem>{
        @Override
        public boolean equals(Object obj) {
            Transversal t = (Transversal) obj;
            for (Elem e : this) {
                int k = 0;
                if (t.contains(e)) {
                    return true;
                }
            }
            return false;
        }

        private boolean crosses(Elem e) {   //e пересекается с одним из элементов из данной трансверсали
            for (Elem el : this) {
                if (e.crosses(el)) return true;
            }
            return false;
        }
    }

    private int size;
    public int[][] square; //Латинский квадрат
    private ArrayList<Transversal> transversals;    //Все трансверсали
    private Transversal buffer; //Строющеяся трансверсаль
    private ArrayList<Transversal> currentSquare;   //Строящейся квадрат
    private int ortSquaresNumber;   //Кол-во квадратов

    private int getInt(FileReader reader) throws IOException {
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
                else if ((c != ' ' && c != '\n')) {
                    throw new IOException();
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

    LatinSquare(String dir) throws IOException {
        try (FileReader reader = new FileReader(dir)) {
            size = getInt(reader);
            if (size == -1) {
                throw new IOException();
            }
            square = new int[size][size];
            transversals = new ArrayList<>();
            currentSquare = new ArrayList<>();
            buffer = new Transversal();
            ortSquaresNumber = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int t = getInt(reader);
                    if (t == -1) {
                        IOException e = new IOException();
                        throw  e;
                    }
                    square[i][j] = t;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
            throw e;
        }
    }

    private void findTransversals(int depth) {  //Найти все трансверсали данного квадрата
        if (depth == size) {    //Найдена трансверсаль
            Transversal t = (Transversal) buffer.clone();
            transversals.add(t);
            return;
        }

        for (int i = 0; i < size; i++) {
            Elem e = new Elem(i, depth, square[i][depth]);
            if (!buffer.crosses(e)) {   //e не пересекается с содержимым строящейся трансверсали
                buffer.add(e); //Добавляем e в строющуюся трансверсаль
                findTransversals(depth + 1);
                buffer.remove(e);   //Убераем e из строящейся трансверсали
            }
        }
    }

    private void findOrthogonalSquares (int depth, int k) { //Поиск всех ортогональных квадратов
        if (depth == size) {  //Найден квадрат
            ortSquaresNumber++;
            int[][] newSquare = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (Elem e : currentSquare.get(i)) {
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
            if ((transversals.get(i).get(0).i == depth) && (!currentSquare.contains(transversals.get(i)))) {    //Трансверсаль не пересекаются с трансверсалями из currentTransversals
                currentSquare.add(transversals.get(i)); //Добавляем трансверсаль в строящийся квадрат
                findOrthogonalSquares(depth + 1, k + 1);
                currentSquare.remove(transversals.get(i));  //Удаляем трансверсаль из строящегося квадрата
            }
        }
    }

    public int findOrthogonalSquares () {
        findTransversals(0);
        findOrthogonalSquares(0, 0);
        return ortSquaresNumber;
    }
}
