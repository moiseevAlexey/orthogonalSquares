package com.Studying;

class Node {
    public int key;
    public String value;
    public Node left;
    public Node right;
    public Node parent;

    Node (int key, String value, Node parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
    }
}
