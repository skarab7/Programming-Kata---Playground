package sorters;

import org.junit.Test;
import sorters.BubbleSort;
import org.junit.Assert;

public class BubbleSortTest {
   

    @Test
    public void testBubbleSort() {
       int[] input = new int[] {10,40,1000,3,2,43};
       BubbleSort.sort(input);
       Assert.assertArrayEquals(new int[] {2,3,10,40,43,1000}, input); 
      
    }

}
