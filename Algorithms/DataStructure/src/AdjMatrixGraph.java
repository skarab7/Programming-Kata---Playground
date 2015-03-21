import java.util.Iterator;
import java.util.NoSuchElementException;


public class AdjMatrixGraph {
    private int V;
    private int E;

    private boolean[][] adj;

    public AdjMatrixGraph(int V) {
         this.V = V;
         this.E = 0;
         this.adj = new boolean[V][V];
    }

    public AdjMatrixGraph(int V, int E) {
        this(V);
        if (E < 0) throw new RuntimeException("Number of edges must be nonnegative");
        if (E > V*(V-1) + V) throw new RuntimeException("Too many edges");
        
        while(this.E <= E) {
           int v = (int) (V * Math.random());
           int w = (int) (V * Math.random());
           addEdge(v, w);
        } 
    }

    public int V() { return V; }
    public int E() { return E; }


    public void addEdge(int v, int w) {
        if (!adj[v][w]) E++;
        adj[v][w] = true;
        adj[w][v] = true;
    }

    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }

    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        int v, w = 0;
        AdjIterator(int v) { this.v = v; }

        public Iterator<Integer> iterator() { return this; }
       
        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w]) return true;
                w++;
            }
            return false;
        }

        public Integer next() {
            if (hasNext()) { return w++; }
            else { throw new NoSuchElementException(); }
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }
   
    public String toString() {
            String NEWLINE = System.getProperty("line.separator");
            StringBuilder sb = new StringBuilder();
            sb.append(V + " " + E + NEWLINE);
            for(int v=0; v<V; v++) {
               sb.append(v + " :");
               for(int w : adj(v)) {
                   sb.append(" " + w);
               }
               sb.append(NEWLINE);
            }
            return sb.toString();
    }
}
