package pairing.lionel;

public class FibIterative extends Fib {
    
    
   public long calcFib(int i) {
       int retVal = 0;
       if (i < 2) {
           return i;
       } else {
           int temp1 = 1;
           int temp2 = 0;
           retVal = 0;
           for (int count = 2; count <= i; count++) {
               retVal = temp1+temp2;
               temp2 = temp1;
               temp1 = retVal;
           }
       }
       return retVal;
   }

}
