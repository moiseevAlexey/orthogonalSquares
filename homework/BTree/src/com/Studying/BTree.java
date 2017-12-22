package com.Studying;

import java.util.ArrayList;

public class BTree {
    public Node root;
    private int height;

    private boolean change(Node changeable, Node changer) {
        if (changeable != root) {
            if (changeable.parent.left == changeable) {
                changeable.parent.left = changer;
            }
            else  {
                changeable.parent.right = changer;
            }
            if (changer != null) changer.parent = changeable.parent;
            return true;
        }
        if (changer != null) {
            changer.parent = null;
        }
        root = changer;
        return false;
    }

    public Node find(int key) {
        if (root == null) {
            return null;
        }
        else {
            Node current = root;
            while (true) {
                if (current.key == key) {
                    return current;
                }
                else if (current.key > key) {
                    if (current.left == null) {
                        return current;
                    }
                    else {
                        current = current.left;
                    }
                }
                else {
                    if (current.right == null) {
                        return current;
                    }
                    else {
                        current = current.right;
                    }
                }
            }
        }
    }

    public String insert(int key, String value) {
        String oldValue;
        if ((oldValue = get(key)) != null) {
            find(key).value = value;
            return oldValue;
        }

        if (root == null){
            root = new Node(key, value, null);
            height++;
            return null;
        }
        Node current = root;
        int currentHeight = 2;
        while (true) {
            if (key > current.key) {
                if (current.right == null) {
                    current.right = new Node(key, value, current);
                    break;
                }
                else {
                    current = current.right;
                    currentHeight++;
                }
            }
            if (key < current.key){
                if (current.left == null) {
                    current.left = new Node(key, value, current);
                    break;
                }
                else {
                    current = current.left;
                    currentHeight++;
                }
            }
        }
        if (height < currentHeight) {
            height = currentHeight;
        }
        return null;
    }

    public String get(int key) {
        Node node = find(key);
        if (node != null && node.key == key) {
            return node.value;
        }
        else {
            return null;
        }
    }

    private void remove(Node removable) {
        if (removable.left == null && removable.right == null) {
            change(removable, null);
        }
        else if (removable.right == null || (removable.left != null && removable.left.right == null)) {
            change(removable, removable.left);
            if (removable.right != null) {
                removable.left.right = removable.right;
                removable.right.parent = removable.left;
            }
        }
        else if (removable.left == null || (removable.right != null && removable.right.left == null)) {
            change(removable, removable.right);
            if (removable.left != null) {
                removable.right.left = removable.left;
                removable.left.parent = removable.right;
            }
        }
        else {
            Node replacer = removable.right;
            while (replacer.left != null) {
                replacer = replacer.left;
            }
            remove(replacer);
            change(removable, replacer);
            replacer.right = removable.right;
            removable.right.parent = replacer;
            replacer.left = removable.left;
            removable.left.parent = replacer;
        }
    }

    public String remove(int key) {
        Node removable = find(key);
        if (removable != null && removable.key == key) {
            remove(find(key));
            return removable.value;
        }
        else {
            return null;
        }
    }

    public void print() {
        print(root, 0);
    }

    private void print(Node node, int level) {
        ArrayList arr = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < height; i++) {
            ArrayList<String> a = new ArrayList<String>();
            arr.add(a);
        }

        print(0, root, arr);
        print(arr);
    }

    private void print(int level, Node node, ArrayList<ArrayList<String>> arr) {
        if (level == height ) {
            return;
        }
        if (node == null) {
            arr.get(level).add("n");
            print(level + 1, null, arr);
            print(level + 1, null, arr);
            return;
        }
        arr.get(level).add(node.value);
        print(level + 1, node.left, arr);
        print(level + 1, node.right, arr);
    }

    private void print(ArrayList<ArrayList<String>> arr) {
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < arr.get(i).size(); j++) {
                System.out.print(arr.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
