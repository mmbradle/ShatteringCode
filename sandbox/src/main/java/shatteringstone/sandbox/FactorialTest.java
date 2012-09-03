package shatteringstone.sandbox;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FactorialTest
{
    private final static int[][] factorials={
        //{-45.0, Integer.NaN},
        {0, 1},
        {1, 1},
        {2, 2},
        {3, 6},
        {4, 24},
        {5, 120},
        {6, 720},
        {7, 5040},
        {8, 40320},
        {9, 362880},
        {10, 3628800}};

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testFactorial()
    {
        //fail("Not yet implemented");
        for(int i=0; i<factorials.length; i++){
            assertEquals(factorials[i][1], Factorial.factorial(factorials[i][0]).intValue());            
        }
    }
    
    @Test
    public void testFactorial_iterative()
    {
        //fail("Not yet implemented");
        for(int i=0; i<factorials.length; i++){
            assertEquals(factorials[i][1], Factorial.factorial_iterative(factorials[i][0]));
        }
    }

}
