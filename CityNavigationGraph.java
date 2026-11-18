import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CityNavigationGraph {

    static int V;                // Number of cities
    static String[] cities;      // City names
    static int[][] adjMatrix;    // Adjacency matrix for one-way roads

    // Add a one-way road from source to destination
    static void addRoad(int src, int dest) {
        adjMatrix[src][dest] = 1;
    }

    // 1. Breadth-First Search (BFS) using a Queue
    static void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Reachable Cities from " + cities[start] + ": ");
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(cities[curr] + " ");

            for (int i = 0; i < V; i++) {
                // If there is a one-way road from curr to i and i is unvisited
                if (adjMatrix[curr][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    // 2. Depth-First Search (DFS) using Recursion
    static void dfs(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS Reachable Cities from " + cities[start] + ": ");
        dfsHelper(start, visited);
        System.out.println();
    }

    static void dfsHelper(int curr, boolean[] visited) {
        visited[curr] = true;
        System.out.print(cities[curr] + " ");

        for (int i = 0; i < V; i++) {
            if (adjMatrix[curr][i] == 1 && !visited[i]) {
                dfsHelper(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample network of 5 cities
        V = 5;
        cities = new String[]{"Mumbai", "Pune", "Nashik", "Nagpur", "Goa"};
        adjMatrix = new int[V][V];

        // One-way roads:
        // Mumbai(0) -> Pune(1), Mumbai(0) -> Nashik(2)
        // Pune(1) -> Goa(4), Nashik(2) -> Nagpur(3)
        // Nagpur(3) -> Pune(1)
        addRoad(0, 1);
        addRoad(0, 2);
        addRoad(1, 4);
        addRoad(2, 3);
        addRoad(3, 1);

        System.out.println("Cities: 0-Mumbai, 1-Pune, 2-Nashik, 3-Nagpur, 4-Goa");
        System.out.print("Enter starting city index (0-4): ");
        int startCity = sc.nextInt();

        if (startCity >= 0 && startCity < V) {
            bfs(startCity);
            dfs(startCity);
        } else {
            System.out.println("Invalid city index!");
        }

        sc.close();
    }
}