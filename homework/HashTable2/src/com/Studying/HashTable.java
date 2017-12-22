package com.Studying;

public class HashTable {
    private int size;
    private Pair[] table;



    HashTable(int size) {
        this.size = size;
        table = new Pair[size];
    }



    private int hash(String key) {
        return Math.abs(key.hashCode() % size);
    }



    public String put(String key, String value) {
        int hash = hash(key);
        for (int i = 0; i < size; i++) {
            int ind = (hash + i) % size;
            if (table[ind] == null) {
                table[ind] = new Pair(key, value);
                return null;
            }
            else if (table[ind].key == key) {
                String oldValue = table[ind].value;
                table[ind].value = value;
                return oldValue;
            }
        }
        return null;
    }



    public String get(String key) {
        int hash = hash(key);
        for (int i = 0; i < size; i++) {
            if (table[(hash + i) % size].key == key) {
                return table[(hash + i) % size].value;
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
                int hash = hash(table[i].key);
                String key = table[i].key;
                String value = table[i].value;
                System.out.println(hash + " [" +  i + "] " + key + " - " + value + "; ");
            }
        }
    }



    public String remove(String key) {
        int hash = hash(key);
        String removedValue;
        for (int i = 0; i < size; i++) {
            if (table[(hash + i) % size].key == key) {
                removedValue = table[hash + i].value;
                table[(hash + i) % size] = null;
                return removedValue;
            }
        }
        return null;
    }
}