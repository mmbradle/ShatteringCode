package pairing.mike;

public class IterativeFibAlgorithm implements FibonacciAlgorithm
{

    @Override
    public long fib(int n)
    {
        long previous = -1;
        long result = 1;
        long sum = 0;
        for (int i=0; i<=n; i++){
            sum = result + previous;
            previous = result;
            result = sum;
        }        
        return result;
    }
    
//    public static int fib_j(int n){
//        int cur = 0;
//        int prev = 0;
//        int prev1 = 0;
//        for (int x=1; x<=n; x++){
//            if (x==1)
//                cur = 1;
//            else if (x==2){
//                prev =1;
//                cur = 1;
//            }
//            else{
//                prev1=cur;
//                cur=prev+prev1;
//                prev=prev1;
//            }
//        }
//        return cur;
//    }

}
