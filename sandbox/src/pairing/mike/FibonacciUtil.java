package pairing.mike;

public class FibonacciUtil
{
    private static FibonacciUtil instance = null;
    private FibonacciAlgorithm algorithm = null;
    
    /**
     * Constructor which allows selection of the Fibonacci Algorithm
     * @param a
     */
    protected FibonacciUtil(){        
    }
    
    public static FibonacciUtil getInstance(){
        if (instance == null)
            instance = new FibonacciUtil();
        return instance;
    }
    
    public static void setAlgorithm(FibonacciAlgorithm a){
        getInstance().algorithm=a;
    }
    
    public static long fib(int n) throws Exception{        
        return getInstance().doFib(n);
    }
    
    private long doFib(int n) throws Exception{
        if(algorithm==null) throw new Exception("Algorithm has not yet been set");
        return algorithm.fib(n);
    }

}
