package pairing.mike;

public class SwappingFibAlgorithm implements FibonacciAlgorithm
{
    FibonacciAlgorithm low = new RecursiveFibAlgorithm();
    FibonacciAlgorithm med = new IterativeFibAlgorithm();
    FibonacciAlgorithm high = new CachingFibAlgorithm();

    @Override
    public long fib(int n)
    {
        if(n < 2) return low.fib(n);
        else if(n < 4) return med.fib(n);
        else return high.fib(n);
    }

}
