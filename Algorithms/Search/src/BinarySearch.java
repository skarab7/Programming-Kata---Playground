class BinarySearch {

    private BinarySearch() {}

    public static int search(int[] input, int needle) {
        return doSearch(input, 0, input.length, needle);
    }
    // see http://stackoverflow.com/questions/4948162/how-can-i-better-understand-the-one-comparison-per-iteration-binary-search
    //  for one-compare for iteration
    private static int doSearch(int[] input, int from, int to, int needle)
    {
        if(to - from < 1) return -1;

        int middle = from + (int) Math.floor((to - from)/2.0);

        int middleValue = input[middle];
        if(needle < middleValue) {
            // go left
            return doSearch(input, from, middle, needle);
        }
        else if(needle > middleValue) {
           // go right
           return doSearch(input, middle +1, to, needle);
        } 
        else {
            return middle;
        } 
    }

   // TODO: experiment more with one comparison version of binary-search
   public static int searchNonRecursive(int[] input, int needle) {
     
     int upperbound=input.length;
     int lowerbound = 0;
     int result = -1;
     int middle;
     while(upperbound > lowerbound) {
         middle = lowerbound + (int) Math.floor( (upperbound - lowerbound) / 2.0 );
         if(needle < input[middle]) {
             upperbound = middle;
         } 
         else if(needle > input[middle]) {
             // needle <= input[middle]
             lowerbound = middle + 1;
         } else {
             result = middle;
             break;
         }
     }
     return result;
   }

   // TODO: find the most left pos to insert an element .. keep in mind
}
