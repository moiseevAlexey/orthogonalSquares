package com.Studying;

import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Graph {
    public int verticesCount;
    public int edgesCount;
    public ArrayList<Integer>[] adjacencyList;
    public ArrayList<Integer>[] weightList;
    private boolean[] visited;
    private int[] parents;
    private ArrayDeque<Integer> queue;
    private ArrayList<Integer> path;

    private static final int INF = Integer.MAX_VALUE;

    private int getInt(FileReader reader) throws Exception {
        int Int = 0;
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
            Int = Int * 10;
            Int += mas.get(i);
        }
        return Int;
    }

    Graph(String dir) throws Exception{
        FileReader reader = new FileReader(dir);
        verticesCount = getInt(reader);
        edgesCount = getInt(reader);
        adjacencyList = new ArrayList[verticesCount];
        weightList = new ArrayList[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            adjacencyList[i] = new ArrayList();
            weightList[i] = new ArrayList();
        }
        int vertex1;
        int vertex2;
        int weight;
        for (int i = 0; i < edgesCount; i++) {
            vertex1 = getInt(reader);
            vertex2 = getInt(reader);
            weight = getInt(reader);
            adjacencyList[vertex1].add(vertex2);
            adjacencyList[vertex2].add(vertex1);
            weightList[vertex1].add(weight);
            weightList[vertex2].add(weight);
        }
    }

    private void deep(int v) {
        System.out.print(v + " ");
        visited[v] = true;
        for (int i = 0; i < adjacencyList[v].size(); i++) {
            if (visited[adjacencyList[v].get(i)] != true) {
                deep(adjacencyList[v].get(i));
            }
        }
    }

    private int deep(int v, int sv) {
        visited[v] = true;
        if (v == sv) {
            path.add(v);
            return v;
        }
        for (int i = 0; i < adjacencyList[v].size(); i++) {
            if (visited[adjacencyList[v].get(i)] != true) {
                int a = deep(adjacencyList[v].get(i), sv);
                if (a != -1)  {
                    path.add(v);
                    return v;
                }
            }
        }
        return -1;
    }

    public void deepSearch(int v) {
        visited = new boolean[verticesCount];
        deep(v);
    }

    public void deepSearch(int v, int sv) {
        visited = new boolean[verticesCount];
        path = new ArrayList();
        deep(v, sv);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }

    private int breadth(int v, int sv) {
        if (v == sv) {
            path.add(v);
            return v;
        }
        for (int i = 0; i < adjacencyList[v].size(); i++) {
            int currentV = adjacencyList[v].get(i);
            if (!visited[currentV]) {
                visited[currentV] = true;
                parents[currentV] = v;
                queue.add(currentV);
            }
        }
        if ((queue.peek() != null) && (breadth(queue.pop(), sv) != -1)) {
            return v;
        }
        return -1;
    }

    public void breadthSearch(int v, int sv) {
        queue = new ArrayDeque<>();
        parents = new int[verticesCount];
        path = new ArrayList<>();
        visited = new boolean[verticesCount];
        queue.add(v);
        visited[v] = true;
        parents[v] = v;
        breadth(queue.pop(), sv);;
        while (sv != parents[sv]) {
            path.add(parents[sv]);
            sv = parents[sv];
        }
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }

    private void breadth(int v) {
        for (int i = 0; i < adjacencyList[v].size(); i++) {
            int currentV = adjacencyList[v].get(i);
            if (!visited[currentV]) {
                visited[currentV] = true;
                queue.add(currentV);
            }
        }
        System.out.print(v + " ");
        if (queue.peek() != null) {
            breadth(queue.pop());
        }
    }

    public void breadthSearch(int v) {
        queue = new ArrayDeque<>();
        visited = new boolean[verticesCount];
        queue.add(v);
        visited[v] = true;
        breadth(queue.pop());
        System.out.println();
    }

    public void dijkstra(int start, int[] distances, int[] parents) {
        boolean[] visited = new boolean[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            distances[i] = INF;
            parents[i] = INF;
        }
        distances[start] = 0;
        parents[start] = start;

        while (true) {
            int v = -1;
            for (int i = 0; i < verticesCount; i++) {
                if ((visited[i] == false) && (distances[i] < INF) && ((v == -1) || (distances[v] > distances[i]))) {
                    v = i;
                }
            }
            if (v == -1) {
                break;
            }
            visited[v] = true;
            for (int i = 0; i < adjacencyList[v].size(); i++) {
                int curV = adjacencyList[v].get(i);
                int curVDist = weightList[v].get(i);
                if ((visited[curV] == false) && (distances[curV] > distances[v] + curVDist)) {
                    distances[curV] = distances[v] + curVDist;
                    parents[curV] = v;
                }
            }
        }
    }

    public void shortPath(int v2, int[] parents, int[] d) {
        int v1 = 0;
        for (int i = 0; i < verticesCount; i++) {
            if (parents[i] ==i) {
                v1 = i;
            }
        }
        int v = v2;
        int dist = 0;
        int shortPath = 0;
        while (v2 != v1) {
            dist += d[v2];
            path.add(v2);
            shortPath++;
            v2 = parents[v2];
        }
        System.out.print(v2 + " ");
        for (int i = path.size()-1; i >= path.size()  - shortPath; i--) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
        System.out.println(d[v]);
    }
}
