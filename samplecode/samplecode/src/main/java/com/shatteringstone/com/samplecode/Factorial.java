package com.shatteringstone.com.samplecode;
import java.util.HashMap;


public class Factorial
{
    static HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    static public long cacheIterations = 0;
    public static Integer factorial(int n)
    {
        if (n == 0) return 1;
        
        Integer ret = cache.get(n);
        if(ret != null) {
            cacheIterations++;
            return ret;
        }
        
        ret = Integer.valueOf(n) * (factorial(n-1));
        cache.put(n, ret);
        return ret;
    }
    
    public static int factorial_iterative(int n)
    {
        int ret = 1;
        for (int i = 1; i<=n; i++)
            ret *= i;
        return ret;
    }

}
