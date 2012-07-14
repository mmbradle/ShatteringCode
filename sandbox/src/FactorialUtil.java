import java.math.BigInteger;

public class FactorialUtil
{
    private static FactorialUtil singleton;
    private FactorialAlgorithm algorithm;

    /**
     * Default (internal) constructor constructs our default algorithm.
     */
    private FactorialUtil()
    {
        algorithm = new CachedFactorialImplementation();
    }

    /**
     * New initializer which allows selection of the algorithm mechanism
     * @param algorithm
     */
    public FactorialUtil(FactorialAlgorithm a)
    {
        algorithm = a;
    }

    /**
     * Default public interface for handling our factorial algorithm. Uses
     * the old standard established earlier for calling into our utility class.
     * @param n
     * @return
     */
    public static BigInteger factorial(int n)
    {
        if (singleton == null) {
            // Use default constructor which uses default algorithm
            singleton = new FactorialUtil();
        }
        return singleton.doFactorial(n);
    }

    /**
     * New mechanism which allows us to instantiate individual factorial
     * utilitiy classes and invoke customized factorial algorithms directory.
     * @param n
     * @return
     */
    private BigInteger doFactorial(int n)
    {
        // Defer to our algorithm
        return algorithm.factorial(n);
    }
}