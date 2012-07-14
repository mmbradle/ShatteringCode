package pairing.lionel;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibIterativeTest {

    @Test
    public void test() {
        FibIterative fibIterative = new FibIterative();
        assertEquals(0, fibIterative.calcFib(0));
        assertFalse(fibIterative.calcFib(0)==1);
        assertEquals(1, fibIterative.calcFib(2));
        assertEquals(2, fibIterative.calcFib(3));
        assertEquals(3, fibIterative.calcFib(4));
        assertEquals(5, fibIterative.calcFib(5));
        assertEquals(8, fibIterative.calcFib(6));
       // fail("Not yet implemented");
    }

}
