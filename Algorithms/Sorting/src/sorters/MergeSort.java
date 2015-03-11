package sorters;

// Playground
class MergeSort {
  
   public static void sort(final int[] input) {
       if(input.length < 2) return; 

       final int[] buffor = new int[input.length];
       mergesort(input, 0, input.length, buffor);
   } 

   public static void mergesort(final int[] input, final int from, 
                                final int to, final int[] buffor)  {
        final int len = to - from;
        if (len < 2) return;
        
        final int splitPos = from + (int) Math.floor(len/ 2);
        mergesort(input, from, splitPos, buffor);
        mergesort(input, splitPos, to, buffor);
       
        merge(input, from, splitPos, to, buffor);
   }

   private static void merge(final int[] input, final int from, final int splitPos, final int to,
                             final int[] buffor) {
        int leftIdx = from;
        int rightIdx = splitPos;
        int mergedIdx = from;

        while(leftIdx < splitPos || rightIdx < to) {
              if( leftIdx < splitPos && (rightIdx >= to || input[leftIdx] < input[rightIdx])) 
              {
                 buffor[mergedIdx] = input[leftIdx];
                 leftIdx++;
              }   
              else {
                 buffor[mergedIdx] = input[rightIdx];
                 rightIdx++;
              }
              mergedIdx++;
         }
         System.arraycopy(buffor, from, input, from, to - from);
  }
}
