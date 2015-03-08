package sorters;

import java.lang.RuntimeException;

public class HeapSort {


    public static void sort(int[] input) {
        int n = input.length;
        Heap h = new Heap(n);
        for(int i=0; i<input.length; i++) {
            h.insert(input[i]);
        }
        for(int i=0; i<input.length; i++) {
            input[i] = h.removeMin();
        }
    } 

    protected static class Heap {
        private int[] objects; 
        private int capacity;
        private int last; 

        public Heap(int capacity) 
        {
            objects = new int[capacity + 1];
            last = 0;
            this.capacity = capacity;
        }

        // O(1)
        public int size()
        {
            return last;
        }
        
        // O(1)
        public boolean isEmpty()
        {
            return size() == 0;
        }

        // O(logn)
        public void insert(int i)
        {
            if(size() == capacity) {
                throw new RuntimeException("Heap is full. No place to insert new element");
            }
            else {
                last++;
                objects[last] = i;
                bubbleUp();
            }
        }

        private void bubbleUp() {
            int parentIdx;
            int idx = last;
            while(idx > 0)
            {
                parentIdx = (int) Math.floor(idx/2);
                if(objects[parentIdx] > objects[idx])
                {
                    int tmp = objects[idx];
                    objects[idx] = objects[parentIdx];
                    objects[parentIdx] = tmp;
                }   
                else {
                    break;
                }
                idx = parentIdx;
            }    
        }

        // O(log n)   
        public int removeMin()
        {
            if(size() == 0 ) {
                throw new RuntimeException("Operation on empty array");
            }
            else {
                int min = min();
                objects[1] = objects[last];
                last--;
                bubbleDown();
                return min;
            }
        }

        private void bubbleDown() {
            int idx = 1;
            int childIdx;
            while(idx <= last) {
                childIdx = findChildIdx(idx);
                if(childIdx < 0) break;
                
                if( objects[idx] > objects[childIdx]) {
                    swap(idx, childIdx);
                }
                else
                    break;
                idx = childIdx;
            }
        }
        
        private int findChildIdx(int idx) {
            int childIdx;
            int leftChild = 2 * idx;
            int rightChild = 2 * idx+1;
            
            if (leftChild > size()) {
                return -1;
            }
            else {
                childIdx = leftChild;
            }
            
            if (rightChild <= size()) {
                childIdx = findMin(leftChild, rightChild);
            }
            return childIdx;
        
        }
        
        
        private int findMin(int idxLeft, int idxRight) {
            if(objects[idxLeft] < objects[idxRight]) 
            {
                return idxLeft;
            }
            return idxRight;
        }
        
        private void swap(int idx1, int idx2) {
            int tmp = objects[idx1];
            objects[idx1] = objects[idx2];
            objects[idx2] = tmp;  
        }

        // O(1) 
        public int min()
        {
            if(size() == 0 ) {
                throw new RuntimeException("Operation on empty array");
            }
            return objects[1]; 
        }
    }
}
