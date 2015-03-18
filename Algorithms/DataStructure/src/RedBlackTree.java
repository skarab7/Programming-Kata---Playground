import java.util.NoSuchElementException;


// Based on (all credits go to)
// http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
// It is an excercise
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


    public void delete(Key key)
    {
         if(!contains(key)) {
             throw new NoSuchElementException(); 
         }

         if(isBlack(root.left) && isBlack(root.right)) {
             root.color = RED;
         } 
         root = delete(root, key);
         if(! isEmpty()) root.color = BLACK;
    }

    public boolean contains(Key key) {
        if(root == null) return false;
        Node x = root;
        while(x != null) {
            
            int cmp = compareKey(x, key);
            if(cmp == 0) {
                 return true;
            } else if(cmp > 0) {
                 x = x.left;
            } else {
                 x = x.right;
            }
        }
        return false;
    }


    // I know that an element with the key exists
    private Node delete(Node n, Key key) 
    {
        int cmp = compareKey(n, key);
        if(cmp > 0) {
            if( isBlack(n.left) && isBlack(n.left.left)) {
                n = moveRedLeft(n);
            }
            n.left = delete(n.left, key);
        } else {
            if(isRed(n.left)) {
                 n = rotateRight(n);
            }
            if(compareKey(n, key) == 0 && (n.right == null)) {
                 return null;
            }
          
            if(isBlack(n.right) && isBlack(n.right.left)) {
                 n = moveRedRight(n);
            }
            if(compareKey(n, key) == 0) {
                 Node x = min(n.right);
                 n.key = x.key;
                 n.val = x.val;
                 n.right = deleteMin(n.right);

            }
            else {
                 n.right = delete(n.right, key);
            }
        } 
        return balance(n);
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // delete the key-value pair with the minimum key
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    // is this symbol table empty?
    public boolean isEmpty() {
        return root == null;
    }

   // the smallest key; null if no such key
    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    } 

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    // the largest key; null if no such key
    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    } 

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) { 
        // assert x != null;
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 


    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
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

