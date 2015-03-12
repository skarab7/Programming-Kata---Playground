import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;


public class BinarySearchTest {

  @Test(timeout=100)
  public void test_GivenArray_WhenNeedleExist_ThenFound() {
    int[] input  = new int[] {10, 20, 20, 40, 50, 60};
    int expected = 1;
    int needle = 20;    
    int result = BinarySearch.search(input, needle);
    Assert.assertEquals(expected, result); 
  }

  @Test(timeout=100)
  public void test_GivenNonRecursiveVerAndSmallSample_WhenNeedleExist_ThenFound() {
    int[] input  = new int[] {10, 20, 20, 40, 50, 60};
    int expected = 1;
    int needle = 20;    
    int result = BinarySearch.searchNonRecursive(input, needle);
    Assert.assertEquals(expected, result); 
  }

  @Test(timeout=100)
  public void test_GivenArrayWithSameValues_WhenNeedleExist_ThenAnyFoundIdx() {
    int[] input  = new int[] {20, 20, 20, 20};
    int expected = 2;
    int needle = 20;    
    int result = BinarySearch.searchNonRecursive(input, needle);
    Assert.assertEquals(expected, result); 
  }

  @Test(timeout=100)
  public void test_GivenEmptyTable_WhenNoNeedle_ThenReturnNotFound() {
      int result = BinarySearch.searchNonRecursive(new int[] {}, 20);
      Assert.assertEquals(-1, result);
  }

  @Test(timeout=100)
  public void test_GivenOneElem_WhenNeedleExists_ThenFound() {
      int r = BinarySearch.searchNonRecursive(new int[] {33}, 33);
      Assert.assertEquals(0, r);
  }

  private void run_test(int[] input, int needle, int expected) {
    Assert.assertEquals(expected, BinarySearch.search(input, needle)); 
  }
}
