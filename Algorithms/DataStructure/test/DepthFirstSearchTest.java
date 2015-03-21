import org.junit.Test;
import org.junit.Assert;

public class DepthFirstSearchTest {

   @Test(timeout=200)
   public void test_whenTinyG_thenValidResult() {
        Graph g = new Graph(7);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(4, 5);

        DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
        Assert.assertEquals(3, dfs.count());

        dfs = new DepthFirstSearch(g, 4);
        Assert.assertEquals(2, dfs.count());
        

   } 
}
