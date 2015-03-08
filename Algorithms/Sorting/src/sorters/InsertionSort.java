package sorters;

public class InsertionSort {


    // O(n2)
    public static void sort(int[] input) {
        
        int j;
        
        for(int i=1; i < input.length; i++) 
        {
            for(j=i; j >0; j--) 
            {
                if(input[j-1] > input[j])
                {
                    swap(input, j-1, j);
                }
                else {
                    break;
                } 
            }
        }
    }
    
    private static void swap(int[] input, int idx1, int idx2) {
        int tmp = input[idx1];
        input[idx1] = input[idx2];
        input[idx2] = tmp;
    }
    
}