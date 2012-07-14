import java.math.BigInteger;
import java.util.HashMap;

public class CachedFactorialImplementation implements FactorialAlgorithm
{
    static HashMap<Integer,BigInteger> cache = new HashMap<Integer,BigInteger>();

    @Override
    public BigInteger factorial(int n)
    {
        BigInteger ret;

        if (n == 0) return BigInteger.ONE;
        if (null != (ret = cache.get(n))) return ret;
        ret = BigInteger.valueOf(n).multiply(factorial(n-1));
        cache.put(n, ret);
        return ret;
    }
}