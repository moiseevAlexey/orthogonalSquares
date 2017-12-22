package com.Studying;

public class Main {

    public static void main(String[] args) {
        HashTable table = new HashTable(4);
        table.put("asd", "йцу");
        table.put("qwe", "фыв");
        table.put("zxc", "ячс");
        table.put("fgh", "апр");
        table.put("vbn", "мит");
        table.print();
        System.out.println(table.put("asd", "кен"));
        System.out.println(table.get("asd"));
        System.out.println(table.remove("asd"));
        table.print();
    }
}
