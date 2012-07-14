package pairing.mike;

public class Main
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        //RecursiveFibAlgorithm fib = new RecursiveFibAlgorithm();
        //FibonacciUtil.setAlgorithm(fib);
        
        FibonacciAlgorithm fib = new SwappingFibAlgorithm();
        
        try{        
            for (int i=0; i<10; i++){
                System.out.println(fib.fib(i));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
