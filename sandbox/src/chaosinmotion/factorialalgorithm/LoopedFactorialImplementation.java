package chaosinmotion.factorialalgorithm;

import java.math.BigInteger;

public class LoopedFactorialImplementation implements FactorialAlgorithm
{
    public BigInteger factorial(int n)
    {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) ret = ret.multiply(BigInteger.valueOf(i));
        return ret;
    }
}