import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


// Excercise after http://algs4.cs.princeton.edu/41undirected/Graph.java.html
public class Graph {
    private final int V;
    private int E;
    private List<Set<Integer>> adj;

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException();
        this.V = V;
        this.E = 0;
        adj = new ArrayList<>(V);
        for(int v=0; v < V; v++) {
           adj.add(v, new HashSet<Integer>());
        }
    }


    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if(adj.get(v).contains(w) && adj.get(w).contains(v)) {
            return;
        }
        E++;
        adj.get(v).add(w);
        adj.get(w).add(v);
    }
   
    public Iterable<Integer> adj(int v) {
       validateVertex(v);
       return adj.get(v);
    } 

    public int degree(int v) {
       validateVertex(v);
       return adj.get(v).size();
    }

    public String toString() {
         StringBuilder s = new StringBuilder();
         String NEWLINE = System.getProperty("line.separator");
         s.append(V + " vertices, " + E + " edges " + NEWLINE);
         for(int v = 0; v < V; v++) {
             s.append( v + ": ");
             for(int w : adj.get(v)) {
                 s.append(w + " ");
             }
             s.append(NEWLINE);
         }
         return s.toString();
    }

}
