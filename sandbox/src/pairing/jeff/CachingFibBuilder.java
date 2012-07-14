package pairing.jeff;

import java.util.ArrayList;
import java.util.List;

public class CachingFibBuilder implements CreateFibonacci
{

    List<Long> cache = new ArrayList<Long>();
    
    @Override
    public long createFibonacci(int input)
    {
        
        if(cache.size() ==0){
            cache.add(0L);
        }
        
        if(cache.size() ==1){
            cache.add(1L);
        }

        if(cache.size() >input){
            return cache.get(input);
        }
        
        long retVal = createFibonacci(input -2) + createFibonacci(input-1);
        cache.add(retVal);
        return retVal;
    }

}
