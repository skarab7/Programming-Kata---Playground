package sorters;

import org.junit.Test;
import sorters.HeapSort;
import org.junit.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;


public class HeapSortTest extends HeapSort {
   

    @Test(timeout=1000)
    public void testHeapSort() {
       int[] input = new int[] {10,40,1000,3,2,43};
       HeapSort.sort(input);
       Assert.assertArrayEquals(new int[] {2,3,10,40,43,1000}, input); 
    }
    
    @Test(timeout=200)
    public void testHeapInsert() {
        int[] input = new int[] {10,40,1000,3,2,43};
        LinkedList<Integer> inList = new LinkedList<Integer>();
        HeapSort.Heap h = new  HeapSort.Heap(input.length);
        for(int i = 0; i< input.length; i++) {
            h.insert(input[i]);
            inList.addLast(input[i]);
        }
        Assert.assertEquals( (int) Collections.min(inList), h.min());
    }
    
    @Test(timeout=200) 
    public void testHeapRemoveMin() {
        int[] input = new int[] {10,40,1000,3,2,43};
        
        HeapSort.Heap h = new  HeapSort.Heap(input.length);
        for(int i = 0; i< input.length; i++) {
            h.insert(input[i]);
        }
        Assert.assertEquals( 2, h.removeMin());
        Assert.assertEquals( input.length - 1, h.size());
        Assert.assertEquals( 3, h.removeMin());
        Assert.assertEquals( input.length - 2, h.size());
    }
}
