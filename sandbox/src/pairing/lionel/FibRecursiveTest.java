package pairing.lionel;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class FibRecursiveTest {

    @Test
    public void test() {
        FibRecursive fibRecursive = new FibRecursive();
        assertEquals(0, fibRecursive.calcFib(0));
        assertFalse(fibRecursive.calcFib(0)==1);
        assertEquals(1, fibRecursive.calcFib(2));
        assertEquals(2, fibRecursive.calcFib(3));
        assertEquals(3, fibRecursive.calcFib(4));
        assertEquals(5, fibRecursive.calcFib(5));
        assertEquals(8, fibRecursive.calcFib(6));
        //assertEquals(Long.parseLong("354224848179261915075"), fibRecursive.calcFib(100));
        //BigInteger a = new Big
    }
    
    
    @Test
    public void slow() {
        FibRecursive fibRecursive = new FibRecursive();
        System.out.println(fibRecursive.calcFib(100));
    }

}
