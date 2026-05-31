import java.util.*;

public class LogisticsGraph {

    private int vertices;
    private LinkedList<Integer>[] adjList;

    LogisticsGraph(int v) {   // Corrected constructor
        vertices = v;
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    void BFS(int start) {
        boolean visited[] = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    void DFSUtil(int node, boolean visited[]) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    void DFS(int start) {
        boolean visited[] = new boolean[vertices];

        System.out.print("DFS Traversal: ");
        DFSUtil(start, visited);
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of locations: ");
        int v = sc.nextInt();

        LogisticsGraph graph = new LogisticsGraph(v); // Corrected

        System.out.print("Enter number of routes: ");
        int e = sc.nextInt();

        System.out.println("Enter routes (source destination):");

        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            graph.addEdge(src, dest);
        }

        int choice;

        do {
            System.out.println("\n1. BFS Traversal");
            System.out.println("2. DFS Traversal");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter starting location: ");
                    int bfsStart = sc.nextInt();
                    graph.BFS(bfsStart);
                    break;

                case 2:
                    System.out.print("Enter starting location: ");
                    int dfsStart = sc.nextInt();
                    graph.DFS(dfsStart);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 3);

        sc.close();
    }
}