package sorters;

import org.junit.Test;
import sorters.MergeSort;
import org.junit.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;


public class MergeSortTest extends HeapSort {

    @Test(timeout=1000)
    public void testMergeSort() {
       int[] input = new int[] {10,40,1000,3,2,43};
       int[] expected = new int[] {2,3,10,40,43,1000};
       doTestSortingAlgorithm(input, expected);
    }

    @Test(timeout=100)
    public void test_when_empty_array() {
        doTestSortingAlgorithm(new int[] {}, new int[] {});
    }

    @Test(timeout=100)
    public void test_when_just_two_elems() {
        doTestSortingAlgorithm(new int[] {10,2}, new int[] {2,10});
    }

    @Test(timeout=100)
    public void test_when_uneven_num_elemens() {
        doTestSortingAlgorithm(new int[] {Integer.MIN_VALUE, 2, Integer.MAX_VALUE}, new int[] {Integer.MIN_VALUE, 2, Integer.MAX_VALUE });
    }

    @Test(timeout=100)
    public void test_when_two_same_elens() {
        doTestSortingAlgorithm(new int[] {1000, 1000, 2}, new int[] {2, 1000, 1000});
    }

    private void doTestSortingAlgorithm(int[] input, int[] expected) {
        MergeSort.sort(input);
        Assert.assertArrayEquals(expected, input);
    }

}
