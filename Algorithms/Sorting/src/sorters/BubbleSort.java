package sorters;

public class BubbleSort {


    // O(n2)
    public static void sort(int[] input) {
        boolean swapped; 
        do {
            swapped = false; 
            for(int i=1; i < input.length; i++)
            {
                if(input[i-1] > input[i])
                {
                    swapped = true;
                    swapElements(input, i-1,i);
                }
            }
        } while(swapped == true);
    }
    
    private static void swapElements(int[] input, int idx1, int idx2) {
        int tmp = input[idx1];
        input[idx1] = input[idx2];
        input[idx2] = tmp;
    }

}
