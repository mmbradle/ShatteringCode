package pairing.jeff;

public class CachingSingleton
{
   
    private static CreateFibonacci fib;
    
    
    public static CreateFibonacci getFibonacci(){
        if(fib == null){
            fib = new CachingFibBuilder();
        }
        return fib;
    }
    
    public static void setFibonacciBuidler(CreateFibonacci fib){
        CachingSingleton.fib = fib;
    }

}
