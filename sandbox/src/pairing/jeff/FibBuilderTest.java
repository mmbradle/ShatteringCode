package pairing.jeff;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class FibBuilderTest
{
    private static long[] fibSeries = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 75025, 9227465, 1134903170, 
        Long.parseLong("12586269025"), Long.parseLong("2111485077978050")};
    private static int[] fibInput   = {0, 1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11,    25,      35,         45,
        50, 75};    

    @Test
    public void testZero()
    {
        CreateFibonacci builder = new FibBuilder();
        assertEquals(0, builder.createFibonacci(0));
    }
    @Test
    public void testOne()
    {
        CreateFibonacci builder = new FibBuilder();
        assertEquals(1, builder.createFibonacci(1));
    }
    
    @Test
    public void testTwo()
    {
        CreateFibonacci builder = new FibBuilder();
        assertEquals(1, builder.createFibonacci(2));
    }
    
    @Test
    public void testFive()
    {
        CreateFibonacci builder = new FibBuilder();
        assertEquals(5, builder.createFibonacci(5));
    }
    
    @Test
    public void testRecursiveLoop()
    {
        CreateFibonacci builder = new FibBuilder();
        for(int i =0; i<fibInput.length; i++){
            int input = fibInput[i];
            if(input>35) break;
            assertEquals("Test with input="+input, fibSeries[i], builder.createFibonacci(input));
        }
    }
    
    @Test
    public void testIterativeLoop()
    {
        CreateFibonacci builder = new IterativeFibBuilder();
        for(int i =0; i<fibInput.length; i++){
            int input = fibInput[i];
            assertEquals("Test with input="+input, fibSeries[i], builder.createFibonacci(input));
        }
    }
    

    @Test
    public void testCachingLoop()
    {
        CreateFibonacci builder = new CachingFibBuilder();
        for(int i =0; i<fibInput.length; i++){
            int input = fibInput[i];
            assertEquals("Test with input="+input, fibSeries[i], builder.createFibonacci(input));
        }
    }
    

    @Test
    public void testSingleton()
    {
        CreateFibonacci builder = CachingSingleton.getFibonacci();
        
        System.out.println(builder.createFibonacci(2));
        for(int i =0; i<fibInput.length; i++){
            int input = fibInput[i];
            assertEquals("Test with input="+input, fibSeries[i], builder.createFibonacci(input));
        }
    }
    
    
    private static long[] fibSeriesBigInt = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 75025, 9227465, 1134903170, 
        Long.parseLong("12586269025"), Long.parseLong("2111485077978050")};
    private static int[] fibInputBigInt   = {0, 1, 2, 3, 4, 5, 6,  7,  8,  9, 10, 11,    25,      35,         45,
        50, 75};    

    
    
    
    @Test
    public void testBigInt(){
        BigIntCaching builder = new BigIntCaching();
        
        
        assertEquals(builder.createFibonacci(1000), new BigInteger("43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"));
        
        System.out.println(builder.createFibonacci(10000));
    }
    @Test
    public void testBigIntAgain(){
        BigIntCaching builder = new BigIntCaching();
        
        
        assertEquals(builder.createFibonacci(1000), new BigInteger("43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"));
        
        System.out.println(builder.createFibonacci(10000));
    }
    
    @Test
    public void testBigInt_Iterative(){
        BigIntIterative builder = new BigIntIterative();
        
        
        assertEquals(builder.createFibonacci(1000), new BigInteger("43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"));
        
        System.out.println(builder.createFibonacci(10000));
    }
    
    


}
