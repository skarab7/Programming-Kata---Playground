import org.junit.Assert;
import org.junit.Test;

// part of Alg repetition
// based on http://algs4.cs.princeton.edu/41undirected/AdjMatrixGraph.java.html
public class AdjMatrixGraphTest {

    @Test(timeout=200)
    public void test_whenAddSampleData_thenValidTOString() {
          int V = 10;
          int E = 9;
          AdjMatrixGraph g = new AdjMatrixGraph(V, E);
          String s = g.toString();
          // We could do better then that
          Assert.assertTrue(s.contains("10 10"));
    }
}


