package pairing.mike;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciTest
{
    private static long[] fibSeries = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 75025, 9227465, 1134903170, 
        Long.parseLong("12586269025"), Long.parseLong("2111485077978050")};
    private static int[] fibInput   = {0, 1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11,    25,      35,         45,
        50, 75};    
    
    @Test
    public void testIterative()
    {
        FibonacciAlgorithm a = new IterativeFibAlgorithm();
        for(int i=0; i<fibInput.length; i++){
            assertEquals(fibSeries[i], a.fib(fibInput[i]));
            //System.out.println(a.fib(fibInput[i]));
        }
        
    }
    @Test
    public void testCaching()
    {
        FibonacciAlgorithm a = new CachingFibAlgorithm();
        for(int i=0; i<fibInput.length; i++){
            assertEquals(fibSeries[i], a.fib(fibInput[i]));
            //System.out.println(a.fib(fibInput[i]));
        }
    }
    @Test
    public void testSwapping()
    {
        FibonacciAlgorithm a = new SwappingFibAlgorithm();
        for(int i=0; i<fibInput.length; i++){
            assertEquals(fibSeries[i], a.fib(fibInput[i])); 
            //System.out.println(a.fib(fibInput[i]));
        }
    }
    @Test
    public void testRecursive()
    {
        FibonacciAlgorithm a = new RecursiveFibAlgorithm();
        for(int i=0; i<fibInput.length; i++){
            if (fibInput[i]>45) break;
            assertEquals(fibSeries[i], a.fib(fibInput[i]));
            //System.out.println(a.fib(fibInput[i]));
        }
    }
}
