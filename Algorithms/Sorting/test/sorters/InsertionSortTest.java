package sorters;

import org.junit.Test;
import sorters.InsertionSort;
import org.junit.Assert;

public class InsertionSortTest {
   

    @Test(timeout=300)
    public void testInsertionSort() {
       int[] input = new int[] {10,40,1000,3,2,43};
       InsertionSort.sort(input);
       Assert.assertArrayEquals(new int[] {2,3,10,40,43,1000}, input); 
      
    }

}
