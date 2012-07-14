package pairing.chris;

import java.util.HashMap;
import java.util.Map;

public class FibCalculatorSingleton {

    private Map<Integer, Long> fibCache;
    
    {
        fibCache = new HashMap<Integer, Long>();
    }
    
    private static FibCalculatorSingleton me;
    
    private FibCalculatorSingleton () {
        super();
    }
    
    public static FibCalculatorSingleton getInstance() {
       
        if (me == null) {
            me = new FibCalculatorSingleton();
        }
        
        return me;
    }
    
    
    
    
    public Map<Integer, Long> getFibCache() {
        return fibCache;
    }


    public long lookup(int index) {
        
//        final Long INDEX_0 = 0L;
//        final Long INDEX_1 = 1L;
        
        long curr = 1L;

        long prev1 = 1L;
        long prev2 = 0L;
        
        switch(index) {
            case 0: 
                return 0L;
                
            case 1:
                return 1L;
        }
        
        //parameter index is > 1
        
        for (int i = 2; i <= index; i++) {
            
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
            
        }
        
        return curr;
        
    }
    
    
    
    public long lookupRcsv(int index) {

        if (fibCache.containsKey(index)) {
            return fibCache.get(index);
        }
        
        long retVal = index;
        
        if (index > 1) {
            retVal = this.lookupRcsv(index - 1) + this.lookupRcsv(index - 2);
        }
        
        fibCache.put(index, retVal);
        
        return retVal;
        
        
    }
}
