/*
 **  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  after http://algs4.cs.princeton.edu/41undirected/DepthFirstSearch.java.html
 */

public class DepthFirstSearch {
    private boolean[] marked;
    private int count; // num of v connected to s 

    public DepthFirstSearch(Graph G, int s) {
       marked = new boolean[G.V()];
       dfs(G, s); 
    } 

    private void dfs(Graph G, int v) {
       count++;
       marked[v] = true;

       for(int w : G.adj(v)) {
           if(!marked[w]) {
               dfs(G, w);
           }
       }
    }

    boolean marked(int v) {
        return marked[v];
    } 

    int count() {
        return count;
    }
}
