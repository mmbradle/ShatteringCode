package pairing.chris;

import junit.framework.Assert;

import org.junit.Test;

public class FibCalculatorSingletonTest {
    

    private static int[] fibInput   = {0, 1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 25, 35, 45, 50, 75}; 
    private static long[] fibOutput = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 75025, 9227465, 1134903170, Long.parseLong("12586269025"), Long.parseLong("2111485077978050")};
 

    @Test
    public void test_fibCache() {
        FibCalculatorSingleton fibCalculator = FibCalculatorSingleton.getInstance();
        
        Assert.assertEquals(0, fibCalculator.lookupRcsv(0));
        Assert.assertEquals(1, fibCalculator.lookupRcsv(1));
        
        Assert.assertEquals(3, fibCalculator.lookupRcsv(4));
        
    }

    @Test
    public void tes_Recursion_With_Array() {
        FibCalculatorSingleton fibCalculator = FibCalculatorSingleton.getInstance();
        for(int i = 0; i<fibInput.length; i++){
//        for(int i = firstIndex; i <= lastIndex; i++){
            Assert.assertEquals("Fib("+fibInput[i]+")", fibOutput[i], fibCalculator.lookupRcsv(fibInput[i]));
        }
    }
}
