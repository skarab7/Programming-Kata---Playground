// vim: tabstop=4 expandtab shiftwidth=4

package sorters;

class ShellSort {

    // O(N^(2))
    public static void sort(int[] input) {
        int n = input.length;
        
        for(int h = n/2; h > 0;
                h = h == 2 ? 1 : (int) (h / 2.2)) {
            for(int i = h; i < n; i++) 
            {
                int item = input[i];
                int j = i;
                // insert sort for every gap (h) element
                while(j >= h && input[j-h] > item) {
                   input[j] = input[j - h]; 
                   j = j - h; 
                }
                input[j] = item;
            }    

        }
   }
}
