package pairing.eric;

import java.math.BigInteger;

public class FibIterative implements Fib {

    @Override
    public BigInteger getFibonacci(int i) {
        
        BigInteger result = BigInteger.ZERO;
        BigInteger prevResult = BigInteger.ONE;
        BigInteger oldPrevResult = BigInteger.ZERO;
        
        if (i == 0)
            return BigInteger.ZERO;
        else if (i == 1)
            return BigInteger.ONE;
        else 
            for (int j = 2; j <= i; j++)
            {
                result = oldPrevResult.add(prevResult);
                oldPrevResult = prevResult;
                prevResult = result;
            }
        
       return result;
    }

}
