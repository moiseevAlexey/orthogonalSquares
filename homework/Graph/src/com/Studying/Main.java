package com.Studying;

public class Main {

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph("TGraph1.txt");
        //graph.deepSearch(0);
        //graph.deepSearch(0, 7);
        System.out.println();
        graph.deepSearch(6);
        System.out.println();
        graph.deepSearch(6, 4);
        System.out.println();
        int[] distances = new int[graph.verticesCount];
        int[] parents = new int[graph.verticesCount];
        graph.dijkstra(5, distances, parents);
        for (int i = 0; i < graph.verticesCount; i++) {
            System.out.print(distances[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < graph.verticesCount; i++) {
            System.out.print(parents[i] + " ");
        }
        System.out.println();
        graph.shortPath(0, parents, distances);
    }
}
