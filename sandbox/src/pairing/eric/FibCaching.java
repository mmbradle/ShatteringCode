package pairing.eric;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibCaching implements Fib {
    public Map<Integer, BigInteger> cache = new HashMap<Integer, BigInteger>();
    
    @Override
    public BigInteger getFibonacci(int i) {
        if (i == 0)
            return BigInteger.ZERO;
        else if (i == 1)
            return BigInteger.ONE;
        else{
            BigInteger cacheResult = cache.get(i);
            if(cacheResult != null){
                return cacheResult;
            }
            BigInteger result = getFibonacci(i-2).add(getFibonacci(i-1));
            cache.put(i, result);
                
            return result;
        }
    }

}
