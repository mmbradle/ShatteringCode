package pairing.jeff;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BigIntCaching
{

    private List<BigInteger> cache = new ArrayList<BigInteger>();
    
    public BigInteger createFibonacci(int input)
    {
        
        if(cache.size() ==0){
            cache.add(new BigInteger("0"));
        }
        
        if(cache.size() ==1){
            cache.add(new BigInteger("1"));
        }

        if(cache.size() >input){
            return cache.get(input);
        }
        
        BigInteger retVal = createFibonacci(input-2).add(createFibonacci(input-1));
        cache.add(retVal);
        return retVal;
    }

}
