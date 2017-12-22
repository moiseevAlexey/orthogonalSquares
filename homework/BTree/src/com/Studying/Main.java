package com.Studying;

public class Main {

    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.insert(5, "5");
        tree.insert(8, "8");
        tree.insert(6, "6");
        tree.insert(2, "2");
        tree.insert(1, "1");
        tree.insert(9, "9");
        tree.insert(11, "11");
        tree.insert(7, "7");
        tree.insert(3, "3");
        tree.print();
        tree.remove(5);
        tree.print();
    }
}
