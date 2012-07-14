package pairing.lionel;

import java.util.HashMap;
import java.util.Map;

public class FibRecursive extends Fib {
    private Map<Integer, Long> map = new HashMap<Integer, Long>();
    
    public long calcFib(final int i) {
        if(map.containsKey(i))
            return map.get(i);
        
        long retVal = 0;
        if (i < 2) {
            return i;
        } else{
            retVal = calcFib(i-2) + calcFib(i-1);
            map.put(i, retVal);
            return retVal;
        }
    }
}
