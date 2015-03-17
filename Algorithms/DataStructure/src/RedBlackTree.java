import java.lang.Comparable;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


// Based on (all credits go to)
// http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean BLACK=false;
    private static final boolean RED=true;
    private Node root;

    private class Node 
    {
        public Key key;
        public Value val;
        public boolean color;
        public Node left;
        public Node right;
        public int N;

        public Node(Key k, Value v, boolean c, int n)
        {
            key = k;
            val = v;
            color = c;
            N = n;
        }
    }

    public RedBlackTree() {}


    public void put(Key key, Value value) 
    {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node n, Key key, Value val) {
       // all the new nodes are RED
       if(n == null) { return new Node(key, val, RED, 1); }
      
       int cmp = compareKey(n, key);
       if(cmp == 0) {
           n.val = val;
       } else if(cmp > 0) {
           n.left = put(n.left, key, val);
       } else {
           n.right = put(n.right, key, val);
       }

       if(isRed(n.right) && isBlack(n.left) )  { n = rotateLeft(n); }
       if(isRed(n.left)  && isRed(n.left.left)) { n = rotateRight(n);}
       if(isRed(n.left)  && isRed(n.right))     { flipColors(n);}
       n.N = size(n.left) + size(n.right) + 1;
       return n;
    }

    private boolean isRed(Node n) {
       return n == null ? false : n.color == RED;
    }

    private boolean isBlack(Node n) {
       return ! isRed(n);
    }

    private int size(Node n) {
        return n == null ? 0 : n.N;
    }

    // child_lef = node.right, child_right -> parent_node
    // parent_node -> child_left.right
    private Node rotateLeft(Node n) {
       Node new_parent = n.right;
       n.right = new_parent.left;
       new_parent.left = n;
       new_parent.color = new_parent.left.color;
       new_parent.left.color = RED;
       new_parent.N = n.N;
       n.N = size(n.left) + size(n.right) + 1;
       return new_parent;
    }

    private Node rotateRight(Node n) {
       Node new_parent = n.left;
       n.left = new_parent.right;
       new_parent.right = n;
       new_parent.color = new_parent.right.color;
       new_parent.right.color = RED;
       new_parent.N = n.N;
       n.N = size(n.left) + size(n.right) + 1; 
       return new_parent;
    }

    private void flipColors(Node n) { 
        n.color = !n.color;
        n.left.color = !n.left.color;
        n.right.color = !n.right.color;
    }

    public Value get(Key key) 
    {
        return get(root, key);
    }

    private Value get(Node n, Key key) {
        if(n == null) {return null;}

        int cmp = compareKey(n, key);
        if(cmp == 0) {
            return n.val;
        } else if(cmp > 0) {
           return get(n.left, key);
        } else {
           return get(n.right, key);
        } 
    }

    private int compareKey(Node n, Key key)
    {
        // 1 compareTo 2 -> -1
        // 3 comapareTo 3 -> 1
        return n.key.compareTo(key);
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node n, Key min, Key max) {
        if(n == null) return true;
        
        /* String ch_keys= "";
        if(n.left != null) {
             ch_keys = ch_keys + " " + n.left.key; 
        }
        else {
           ch_keys = "null";
        }

        if(n.right != null) {
             ch_keys = ch_keys + ", " + n.right.key; 
        }
        else {
           ch_keys = ch_keys + ",null";
        }
 
        System.out.println(n.key + " (" + ch_keys +  ") {" + n.color + "} " + min + " " + max); */
        if(min != null && compareKey(n, min) <= 0) { return false; }
        if(max != null && compareKey(n, max) >= 0) { return false; }
        return isBST(n.left, min, n.key) && isBST(n.right, n.key, max);
    }

    public boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node n) {
         if(n == null) return true; 
         if(size(n) == size(n.left) + size(n.right) + 1) {
             return isSizeConsistent(n.left) && isSizeConsistent(n.right); 
         }
         return false;
    }

    public boolean is23() {return is23(root); }

    private boolean is23(Node x) {
       if (x == null) { return true; }
       if (isRed(x.right)) { return false; }

       if( x != root && isRed(x) && isRed(x.left)) {
           return false; 
       }
       return is23(x.left) && is23(x.right);
    } 
   
    public boolean isBalanced() {
        int black = 0;

        Node x = root;
        while(x != null) {
             if(isBlack(x)) black++;
             x = x.left;
        }
        return isBalanced(root, black); 
    }

   // preorder
   private boolean isBalanced(Node x, int black) {
      if(x == null) return black == 0;
      if(isBlack(x)) black--;
      return isBalanced(x.left, black) && isBalanced(x.right, black);     

   }

   public boolean isRootBlack() {
     return root == null ? true : isBlack(root);
   }
}

