import org.junit.Assert;
import org.junit.Test;


public class BSTTest {

    @Test(timeout=200)
    public void testSampleData() {
         BST<Integer, String> bst = new BST<>();
         bst.add(1, "w1");
         bst.add(2, "w2");

         Assert.assertEquals("w1", bst.get(1));
         Assert.assertEquals("w2", bst.get(2));
    }

    // given: small graph
    @Test(timeout=200) 
    public void test_whenRemoveExistingElement_thenSuccess() 
    {
         BST<Integer, String> bst = new BST<>();    
         bst.add(1, "w1");
         bst.add(2, "w2");
         bst.add(-1, "w3");
         bst.add(4, "w4");

         Assert.assertEquals("w2", bst.get(2));
         bst.remove(2);
         Assert.assertNull(bst.get(2));
    }

    @Test(timeout=200) 
    public void test_whenRemoveRoot_thenSuccess()
    {
        BST<Integer, String> bst = new BST<>();
        bst.add(1, "w1");
        bst.add(-1, "w-1");
        bst.add(3, "w3");
        bst.add(-2, "w-2");
        bst.remove(1);

        Assert.assertNull(bst.get(1));
        Assert.assertEquals("w-1", bst.get(-1));
        Assert.assertEquals("w3", bst.get(3));
    }

    @Test(timeout=200) 
    public void test_whenAddValueTwoTimes_thenSuccess() {
        BST<Integer, String> bst = new BST<>();
        bst.add(1, "w1a");
        Assert.assertEquals("w1a", bst.get(1));
        bst.add(1, "w1b");
        Assert.assertEquals("w1b", bst.get(1));

    }

    // edge cases: k = null, remove from empty BST, remove with non-existing key
    @Test(timeout=200, expected=BST.InvalidKeyValue.class)
    public void test_whenAddWithKeyNull_thenThrowException() {
        BST<Integer, String> bst = new BST<>();
        bst.add(1, "w1");
        bst.remove(null);
    }

    @Test(timeout=200)
    public void test_whenGetKeyAndEmptyGraph_thenReturnNull() {
         BST<Integer, String> bst = new BST<>();
         Assert.assertNull(bst.get(10));
    }

    // given: a bst
    @Test(timeout=100)
    public void test_whenToString_thenStringContainsNodes() {
        BST<Integer, String> bst = new BST<>();
        bst.add(1, "w1");
        bst.add(-1, "w-1");
        bst.add(3, "w3");
        bst.add(-2, "w-2");
        bst.add(5, "w5");
        String s = bst.toString();
        Assert.assertTrue(s.contains("w1"));
        // might be improved with proper schema-based check 
    }

}

