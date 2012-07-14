package pairing.mike;

import java.util.HashMap;

public class CachingFibAlgorithm implements FibonacciAlgorithm {
    private static HashMap<Integer, Long> cache = new HashMap<Integer, Long>();

    @Override
    public long fib(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else {
            Long current = cache.get(n);
            if (current != null)
                return current;

            Long previous1 = cache.get(n - 1);
            if (previous1 == null)
                previous1 = fib(n - 1);

            Long previous2 = cache.get(n - 2);
            if (previous2 == null)
                previous2 = fib(n - 2);

            cache.put(n, previous1 + previous2);
            return previous1 + previous2;
        }
    }
}
