package com.Studying;

import java.util.ArrayList;

public class HashTable {
    private int size;
    private ArrayList<Pair>[] table;

    HashTable(int size) {
        this.size = size;
        table = new ArrayList[size];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % size);
    }

    public String put(String key, String value) {
        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new ArrayList();
            table[hash].add(new Pair(key, value));
        }
        else {
            String oldValue;
            for (int i = 0; i < table[hash].size(); i++) {
                Pair tPair = table[hash].get(i);
                if (tPair.key == key) {
                    oldValue = tPair.value;
                    tPair.value = value;
                    return oldValue;
                }
            }
            table[hash].add(new Pair(key, value));
        }
        return null;
    }

    public String get(String key) {
        int hash = hash(key);
        if (table[hash] != null) {
            for (Pair p : table[hash]) {
                if (p.key == key) return p.value;
            }
        }
        return null;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            if (table[i] == null) {
                System.out.println("[" + i + "] null");
            }
            else {
                System.out.print(hash(table[i].get(0).key) + " [" +  i + "] ");
                for (Pair p : table[i]) {
                    System.out.print(p.key + " - " + p.value + "; ");
                }
                System.out.println();
            }
        }
    }

    public String remove(String key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return null;
        }
        else {
            String removedValue;
            for (int i = 0; i < table[hash].size(); i++) {
                if (table[hash].get(i).key == key) {
                    removedValue = table[hash].get(i).value;
                    table[hash].remove(i);
                    return removedValue;
                }
            }
            return null;
        }
    }
}
