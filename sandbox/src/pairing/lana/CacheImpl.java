package pairing.lana;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl implements FibonacciAlgorithm
{

    public Map<Integer, Long> cache = new HashMap<Integer, Long>();
    
    @Override
    public long get(int i)
    {
        Long retVal = null;
        if(i == 0){
            cache.put(i, 0l);
            return 0;
        }
        else if(i == 1){
            cache.put(i, 1L);
            return 1;
        }else{
            retVal = cache.get(i);
            if(retVal != null){
                return retVal;
            }
            else{
                retVal = get(i-1)+get(i-2);
                cache.put(i, retVal);
                return retVal;
            }
            
        }
        
    }
}
