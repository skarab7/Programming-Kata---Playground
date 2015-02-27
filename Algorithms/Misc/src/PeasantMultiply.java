public class PeasantMultiply {


    public static int multiply(int x, int y) {
        int prod = 0;
        
        while(x > 0 ) {
            System.out.println(x);
            if(x % 2 != 0) {
                prod =  prod + y;
            }
            x = (int) Math.floor(x / 2);
            y = y + y;
        }
        return prod;
    }
    
    public static void main(String [] args) {
        System.out.println(PeasantMultiply.multiply(5,4));
    
        
    }   
    
}