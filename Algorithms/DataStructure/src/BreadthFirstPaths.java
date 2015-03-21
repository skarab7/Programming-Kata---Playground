import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/*
 *  Run breadth first search on an undirected graph.
 *  Runs in O(E + V) time
 *  after http://algs4.cs.princeton.edu/41undirected/BreadthFirstPaths.java.html *
 */
public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(Graph G, int s) {
       marked = new boolean[G.V()];
       distTo = new int[G.V()];
       edgeTo = new int[G.V()];
       bfs(G, s);
    }

    public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        bfs(G, sources);
    } 
   
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        for(int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while(!q.isEmpty())  {
             int v = q.dequeue();
             for(int w : G.adj(v)) {
                 if(!marked[v]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                 }
             }
        }
    }

    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new ArrayDeque<Integer>();

        for(int s : sources) {
              marked[s] = true;
              distTo[s] = 0;
              q.enqueue(s);
        }

        while(!q.isEmpty()) {
             int v = q.dequeue();
             for(int w : G.adj(v)) {
                 if(!marked[w]) {
                     edgeTo[w] = v;
                     distTo[w] = distTo[v] + 1;
                     marked[w] = true;
                     q.enqueue(w);
                 }
             }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

}
