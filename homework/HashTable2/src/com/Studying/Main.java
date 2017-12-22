package com.Studying;

public class Main {

    public static void main(String[] args) {
        HashTable table = new HashTable(4);
        table.put("asd", "фыв");
        table.put("qwe", "йцу");
        table.put("zxc", "ячс");
        table.put("fgh", "апр");
        table.put("vbn", "мит");
        table.print();
        System.out.println(table.put("qwe", "кен"));
        System.out.println(table.get("qwe"));
        System.out.println(table.remove("qwe"));
        table.print();
    }
}
