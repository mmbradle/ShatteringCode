package pairing.chris;

import junit.framework.Assert;

import org.junit.Test;

public class FibCalculatorTest {

    private static int[] fibInput   = {0, 1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 25, 35, 45, 50, 75}; 
    private static long[] fibOutput = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 75025, 9227465, 1134903170, Long.parseLong("12586269025"), Long.parseLong("2111485077978050")};
    
    private static int firstIndex = 12;
    private static int lastIndex = 15;

    @Test
    public void test() {
//        fail("Not yet implemented");
        
        FibCalculator fibCalculator = new FibCalculator();
        
        Assert.assertEquals(0, fibCalculator.lookup(0));
        Assert.assertEquals(1, fibCalculator.lookup(1));
        Assert.assertEquals(1, fibCalculator.lookup(2));
        Assert.assertEquals(2, fibCalculator.lookup(3));

        Assert.assertEquals(3, fibCalculator.lookup(4));
        
    }
    @Test
    public void loopLookupTest() {
        FibCalculator fibCalculator = new FibCalculator();
        for(int i = 12; i<fibInput.length-2; i++){
            Assert.assertEquals("Fib("+fibInput[i]+")", fibOutput[i], fibCalculator.lookup(fibInput[i]));
            
            
        }
    }
    
    @Test
    public void test_Recursion() {
        
        FibCalculator fibCalculator = new FibCalculator();
        
        Assert.assertEquals(0, fibCalculator.lookupRcsv(0));
        Assert.assertEquals(1, fibCalculator.lookupRcsv(1));
        
        Assert.assertEquals(3, fibCalculator.lookupRcsv(4));
    }

    @Test
    public void tes_Recursion_With_Array() {
        FibCalculator fibCalculator = new FibCalculator();
        for(int i = 0; i<fibInput.length; i++){
//        for(int i = firstIndex; i <= lastIndex; i++){
            Assert.assertEquals("Fib("+fibInput[i]+")", fibOutput[i], fibCalculator.lookupRcsv(fibInput[i]));
        }
    }
    

    @Test
    public void tes_fibCache() {
        FibCalculator fibCalculator = new FibCalculator();
        
        Assert.assertTrue(fibCalculator.getFibCache().size() == 0);
        
        FibCalculator fibCalculator2 = new FibCalculator();
        
        Assert.assertTrue(fibCalculator.getFibCache() == fibCalculator2.getFibCache());
        
    }

}
