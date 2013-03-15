package shatteringstone.sandbox.interviewquestions;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixReachabilityTest {

    @Test
    public void test3d_1_1() {
        double[][] d = new double[][] {{0,1,1}, {0,0,1}, {0,0,0}};
        MatrixReachability r = new MatrixReachability(d);
        assertEquals(1, r.numUniversallyReachingNodes());
        assertEquals(1, r.numUniversallyReachableNodes());
    }

    @Test
    public void test3d_2_1() {
        double[][] d = new double[][] {{0,1,1}, {1,0,1}, {0,0,0}};
        MatrixReachability r = new MatrixReachability(d);
        assertEquals(2, r.numUniversallyReachingNodes());
        assertEquals(1, r.numUniversallyReachableNodes());
    }

    @Test
    public void test3d_0_0() {
        double[][] d = new double[][] {{0,0,0}, {0,0,0}, {0,0,0}};
        MatrixReachability r = new MatrixReachability(d);
        assertEquals(0, r.numUniversallyReachingNodes());
        assertEquals(0, r.numUniversallyReachableNodes());
    }

    @Test
    public void test4d() {
        double[][] d = new double[][] {{0,0,1,0},{1,0,0,0},{1,1,0,0},{1,0,0,0}};
        MatrixReachability r = new MatrixReachability(d);
        assertEquals(1, r.numUniversallyReachingNodes());
        assertEquals(3, r.numUniversallyReachableNodes());
    }

    @Test
    public void test5d() {
        double[][] d = new double[][] {{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{0,0,0,0,1},{1,0,0,0,0}};
        MatrixReachability r = new MatrixReachability(d);
        assertEquals(5, r.numUniversallyReachingNodes());
        assertEquals(5, r.numUniversallyReachableNodes());
    }

    @Test
    public void test7d() {
        double[][] d = new double[][] {{0,1,1,0,0,0,0},{1,0,0,0,1,0,0},{0,0,0,0,0,0,1},{0,0,1,0,0,0,0},{0,1,1,0,0,0,0},{1,0,0,0,0,1,0},{0,0,0,1,0,0,0}};
        MatrixReachability r = new MatrixReachability(d);
        assertEquals(1, r.numUniversallyReachingNodes());
        assertEquals(3, r.numUniversallyReachableNodes());
    }

}
