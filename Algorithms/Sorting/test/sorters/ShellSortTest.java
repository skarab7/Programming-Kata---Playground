package sorters;

import org.junit.Test;
import sorters.ShellSort;
import org.junit.Assert;

public class ShellSortTest {
   

    @Test(timeout=1000)
    public void testShellSort() {
       int[] input = new int[] {10,40,1000,3,2,43};
       ShellSort.sort(input);
       Assert.assertArrayEquals(new int[] {2,3,10,40,43,1000}, input); 
    }

}
