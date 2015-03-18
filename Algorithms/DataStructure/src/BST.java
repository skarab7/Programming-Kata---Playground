import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        Key key;
        Value val;
        int N; // number of destends
       
        Node left;
        Node right; 
        
        public Node(Key k, Value v, int N)
        {
            this.key = k;
            this.val = v;
            this.N = N;
        }
          
    }

    public void add(Key k, Value v) 
    {
        if(k == null) {
            throw new InvalidKeyValue();
        }
        root = add(root, k, v);
    }

    private Node add(Node n, Key k, Value v) {
        if(n == null) {
            n = new Node(k, v, 0);
        }
        else {        
           n = moveDownOrSetupValue(n, k, v); 
        }
       return n;
    }

    private Node moveDownOrSetupValue(Node n, Key k, Value v) {
        int cmp = compareKeys(n, k);

        if(cmp == 0) {
           n.val = v;
        }
        else if(cmp > 0) {
           n.left = add(n.left, k, v);
        }
        else {
           n.right = add(n.right, k, v);
        }
        return n;
    }

    private int compareKeys(Node n, Key k)
    {
        return n.key.compareTo(k);
    } 
   
    public Value get(Key k) {
        if(k == null) {return null; } 
        return get(root, k);
    } 

    private Value get(Node n, Key k) {
        if(n == null) {return null;}

        int cmp = compareKeys(n, k);

        if(cmp == 0) {
             return n.val;
        } else if(cmp > 0) {
             return get(n.left, k);
        }
        else {
             return get(n.right, k);
        }
    }

    public void remove(Key k) {
         if(k == null) throw new InvalidKeyValue();
         root = remove(root, k); 
    }

    private Node remove(Node n, Key k) 
    {
       if(n == null) {throw new RuntimeException("Unknown key");}

       int cmp = compareKeys(n, k);

       if(cmp > 0) {
           n.left = remove(n.left, k);
       } else if (cmp < 0) {
           n.right = remove(n.right, k);
       }
       else {
          if (n.right == null) { return n.left;}
          if (n.left == null) {return n.right; }

          Node x = min(n.right);
          n.right = remove(n.right, x.key); x.right = n.right;
          x.left = n.left;
          n = x;
       }
       return n;
    }

    private Node min(Node n) {
        if(n.left == null) return n;
        return(n.left);
    }

    public static class InvalidKeyValue extends RuntimeException 
    {

    }


    public String toString() {
        int depth = 0;
        Map<Integer, List<String>> printLines = new HashMap<>();        
        
        collectNodesAsStrings(root, depth, printLines);
        
        StringBuilder sb = new StringBuilder();
        List<String> line;
        for(int i=0; i<printLines.size(); i++) // O(depth)
        {
             line = printLines.get(i);
             sb.append(i);
             sb.append(" ");
             for(String p: line) {     // logn
                 sb.append(p);
                 sb.append(" ");
             }
             sb.append("\n");
        }
        return sb.toString();
    }

    //https://www.youtube.com/watch?v=vSkb0kDacjs
    public void collectNodesAsStrings(Node n, int dep, Map<Integer, List<String>> result) {
        if(n.left != null) {
             collectNodesAsStrings(n.left, dep+1, result);
        }
        if(n.right != null) {
             collectNodesAsStrings(n.right, dep+1, result); 
        }
        String r = "(" + n.key + " [ " + n.val  + "])"; 
        
        List<String> l; 
        if(! result.containsKey(dep)) {
            // avoid resizing cost in case if we exceed the default size of ArrayList
            l = new ArrayList<>(dep == 0 ? 1 : dep *2);     
            result.put(dep, l);
        }
        else {
            l = result.get(dep);
        }
        l.add(r); 
    }
}
