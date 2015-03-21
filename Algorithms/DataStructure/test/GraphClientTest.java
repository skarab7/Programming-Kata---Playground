import org.junit.Assert;
import org.junit.Test;


public class GraphClientTest {

    @Test
    public void test_whenSampleNodes_thenExpectedResults() {
       String[] input =  new String[] {
 "0: 6 2 1 5", 
 "1: 0", 
 "2: 0", 
 "3: 5 4", 
 "4: 5 6 3", 
 "5: 3 4 0", 
 "6: 0 4", 
 "7: 8",
 "8: 7",
 "9: 11 10 12", 
 "10: 9", 
 "11: 9 12",
 "12: 11 9"};
      Graph g = new Graph(13);

      int i =0;
      for(String l : input) {
           Integer from = Integer.valueOf(l.split(":")[0]);
           String[] tp = l.split(":")[1].trim().split(" ");
           for(String t : tp) {
                g.addEdge(from, Integer.valueOf(t));
                i++;
           }
      }
      Assert.assertEquals("expected number of Vertex", 13, g.V());
      Assert.assertEquals("expected number of Edges", 13, g.E());

      Assert.assertEquals("vertex of maximum degree", 4, GraphClient.maxDegree(g));
      Assert.assertEquals("average degree", 2, GraphClient.avgDegree(g));
      Assert.assertEquals("number of self loops", 0, GraphClient.numberOfSelfLoops(g));
   }
}
