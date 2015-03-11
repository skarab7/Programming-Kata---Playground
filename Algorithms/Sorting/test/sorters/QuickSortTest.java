package sorters;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;


public class QuickSortTest {

    @Test(timeout=1000)
    public void testSmallSampleSort() {
       Integer[] input = new Integer[] {10,40,1000,3,2,43};
       Integer[] expected = new Integer[] {2,3,10,40,43,1000};
       doTestSortingAlgorithm(input, expected, false);
    }

    @Test(timeout=9000)
    public void testBigSampleSort() {
       int N = 10000;
       Integer[] input = new Integer[N];
       for(int i=0; i<N; i++) 
       {
             if(i%3 == 1) {
                  input[i] = N / i + i;
             } else
             {
                  input[i] = - N + i; 
             }
       }
       Integer[] expected = new Integer[N];
       System.arraycopy(input, 0, expected, 0, input.length );
       Arrays.sort(expected);
       doTestSortingAlgorithm(input, expected); 
    }

    @Test(timeout=100)
    public void test_when_empty_array() {
        doTestSortingAlgorithm(new Integer[] {}, new Integer[] {});
    }

    @Test(timeout=200)
    public void test_when_one_elem_array() {
        doTestSortingAlgorithm(new Integer[] {1}, new Integer[] {1});
    }

    @Test(timeout=100)
    public void test_when_just_two_elems() {
        doTestSortingAlgorithm(new Integer[] {10,2}, new Integer[] {2,10});
    }

    @Test(timeout=100)
    public void test_when_uneven_num_elemens() {
        doTestSortingAlgorithm(new Integer[] {Integer.MIN_VALUE, 2, Integer.MAX_VALUE}, new Integer[] {Integer.MIN_VALUE, 2, Integer.MAX_VALUE });
    }

    @Test(timeout=100)
    public void test_when_two_same_elens() {
        doTestSortingAlgorithm(new Integer[] {1000, 1000, 2}, new Integer[] {2, 1000, 1000});
    }

    private void doTestSortingAlgorithm(Integer[] input, Integer[] expected) 
    {
        doTestSortingAlgorithm(input, expected, false); 
    }

    private void doTestSortingAlgorithm(Integer[] input, Integer[] expected, boolean isVerbose) {
        if(isVerbose) {
             QuickSortTest.printArray(input);
        }
        QuickSort.sort(input);
        if(isVerbose) {
             QuickSortTest.printArray(input);
             QuickSortTest.printArray(expected);
        }
        Assert.assertArrayEquals(expected, input);
    }

    private static void printArray(Integer[] input) {
          StringBuilder sb = new StringBuilder("[");
          for(int i=0; i < input.length; i++) {
             sb.append(input[i]);
             if(i != input.length - 1) {
                  sb.append(",");
             } 
         }
         System.out.println(sb.append("]").toString());
    }
}
