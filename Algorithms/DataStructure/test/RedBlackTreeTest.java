import org.junit.Assert;
import org.junit.Test;


public class RedBlackTreeTest extends RedBlackTree {


   @Test(timeout=200)
   public void test_whenAddFewElems_thenValidRedBlackTree()
   {
       RedBlackTree<Integer, String> bst = new RedBlackTree<>();
   
       bst.put(1, "ww1");
       bst.put(2, "ww2");
       bst.put(3, "ww3");
       bst.put(4, "ww4");
       bst.put(-1, "ww-1");
       int v;
       for(int i=5; i < 100; i++) {
           if(i % 3 == 0) v = -i;
           else v = i;
           bst.put(v, "ww" + v);
       }
        
       Assert.assertEquals("ww1", bst.get(1));
       Assert.assertEquals("ww2", bst.get(2));      
       assertValidRedBlackTree(bst);
   }

   @Test(timeout=200)
   public void test_whenSampleData_thenValidRedBlackTree()
   {
       RedBlackTree<Integer, String> bst = new RedBlackTree<>();
       int[] input = new int[] {13,8,17,1,11,15,25,6,22,27};

       for(int k: input) {
          bst.put(k, "w" + k);
       }
       assertValidRedBlackTree(bst);
   }

   private void assertValidRedBlackTree(RedBlackTree bst) {
       Assert.assertTrue("Not symmetric order", bst.isBST());
       Assert.assertTrue("Subtree count not valud", bst.isSizeConsistent());
       Assert.assertTrue("Not a 2-3 tree", bst.is23());
       Assert.assertTrue("Not balanced!", bst.isBalanced());
       Assert.assertTrue("Root is not BLACK", bst.isRootBlack());
   }

}
