package shatteringstone.sandbox;
import java.util.HashMap;


public class Fibonacci
{
    private static HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    
    public static int fib(int n){
        int previous = -1;
        int result = 1;
        int sum = 0;
        for (int i=0; i<=n; i++){
            sum = result + previous;
            previous = result;
            result = sum;
        }        
        return result;
    }
    
    public static int fib_r(int n){
        if (n==0) return 0;
        else if (n==1) return 1;
        else return fib(n-1)+fib(n-2);        
    }
    
    public static int fib_rc(int n){
        if (n==0) return 0;
        else if (n==1) return 1;
        else {
            Integer current = cache.get(n);            
            if(current != null) return current;
            
            Integer previous1 = cache.get(n-1);
            if(previous1 == null) previous1 = fib_rc(n-1);
           
            Integer previous2 = cache.get(n-2);
            if(previous2 == null) previous2 = fib_rc(n-2);

            return previous1+previous2;        
        }
    }
    
    public static int fib_j(int n){
        int cur = 0;
        int prev = 0;
        int prev1 = 0;
        for (int x=1; x<=n; x++){
            if (x==1)
                cur = 1;
            else if (x==2){
                prev =1;
                cur = 1;
            }
            else{
                prev1=cur;
                cur=prev+prev1;
                prev=prev1;
            }
        }
        return cur;
    }
    

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        for (int i=0; i<10; i++)
            System.out.println(fib_rc(i));
//        for (int i=0; i<10; i++)
//            System.out.println(fib_r(i));

    }

}
