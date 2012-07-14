package pairing.lana;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FibonacciTest
{
    
    private static long[] fibSeries = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 75025, 9227465, 1134903170, 
        Long.parseLong("12586269025"), Long.parseLong("2111485077978050")};
    private static int[] fibInput   = {0, 1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11,    25,      35,         45,
        50, 75};  
    
    Fibonacci fib = null;
    

    @Test
    public void testRecursive()
    {
        Fibonacci fib = new Fibonacci();
        
        for(int index= 0; index < fibInput.length; index ++){
            if(fibInput[index] > 45) break;
            //System.out.println("Test value at: " + fibInput[index] + " "+  fibSeries[index] + " "+  fib.get(fibInput[index]));
            assertEquals("Test value at: " + fibInput[index],  fibSeries[index], fib.get(fibInput[index]));
        }       
        
    }
    
    
    @Test
    public void testItter()
    {
        FibonacciAlgorithm fib = new FibonacciAlgorithmImpl();
        //int index = 4;
        //assertEquals("Test value at: " + fibInput[index],  fibSeries[index], fib.get(fibInput[index]));
        
        for(int index= 0; index < fibInput.length; index ++){
            //if(fibInput[index] > 5) break;
            //System.out.println("Test value at: " + fibInput[index] + " "+  fibSeries[index] + " "+  fib.get(fibInput[index]));
            assertEquals("Test value at: " + fibInput[index],  fibSeries[index], fib.get(fibInput[index]));
        }
    }
    
    
    @Test
    public void testCache()
    {
        FibonacciAlgorithm fib = new CacheImpl();
        
        for(int index= 0; index < fibInput.length; index ++){
            //if(fibInput[index] > 45) break;
            //System.out.println("Test value at: " + fibInput[index] + " "+  fibSeries[index] + " "+  fib.get(fibInput[index]));
            assertEquals("Test value at: " + fibInput[index],  fibSeries[index], fib.get(fibInput[index]));
        }       
        
    }

}
