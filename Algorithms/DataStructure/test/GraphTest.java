import org.junit.Assert;
import org.junit.Test;


public class GraphTest {

 
    // given: ...
    @Test(timeout=200)
    public void test_whenAddFewElem_thenValidGraph() {
        Graph g = new Graph(13);
        String[] input = new String[] {
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
        for(String l : input) {
           Integer from = Integer.valueOf(l.split(":")[0]);
           String[] tp = l.split(":")[1].trim().split(" ");
           for(String t : tp) {
                g.addEdge(from, Integer.valueOf(t));
           }
        }
        //System.out.println(g.toString());

    }
}
