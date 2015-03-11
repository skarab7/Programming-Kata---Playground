package sorters;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
// 
public class QuickSort {
    
    public static void sort(Integer[] input) {
        if(input == null) { throw new java.lang.IllegalArgumentException(); }
        if(input.length < 2) { return; }
        final List<Integer> result = doSort(Arrays.asList((Integer[])input));
        
        final Iterator<Integer> iter = result.iterator();
        for(int i=0; i<input.length; i++) { // O(n)
            input[i] = iter.next();  // O(1)
        }
    } 

    private static List<Integer> doSort(final List<Integer> input) {
        if(input.size() < 2) {return input;}
        
        // LinkedList because O(1) inserts
        // and O(1) for the sequentional access
        // with iterator
        // see http://stackoverflow.com/a/322742/410823
        List<Integer> smallerElems = new LinkedList<Integer>();
        List<Integer> biggerElems = new LinkedList<Integer>();
        final List<Integer> pivotElems = new LinkedList<Integer>();

        final int pivot = (int) Math.floor( (input.size()) / 2 ); 
        final int pivotValue = input.get(pivot);  
        
        for(Integer val : input) {
            if(val < pivotValue) {
                smallerElems.add(val);
            } else if(val > pivotValue) {
                biggerElems.add(val);
            } else {
                pivotElems.add(val);
            }
        }
         
        smallerElems = doSort(smallerElems);
        biggerElems = doSort(biggerElems);
        smallerElems.addAll(pivotElems);        
        
        smallerElems.addAll(biggerElems);
        return smallerElems;
    }
}
