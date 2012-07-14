package pairing.lionel;

public class FibFactory {
    
    public static Fib createFib(long v) {
        if (v <= 50) {
            System.out.println("creating recursive fib.");
            return new FibRecursive();
        } else {
            System.out.println("creating iterative fib.");
            return new FibIterative();
        }
    }
    
    public static void main(String[] args) {
        Fib fib = FibFactory.createFib(51);
        System.out.println(fib.calcFib(200));
        
    }

}
