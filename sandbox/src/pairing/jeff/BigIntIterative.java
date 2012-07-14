package pairing.jeff;

import java.math.BigInteger;

public class BigIntIterative
{

    public BigInteger createFibonacci(int input)
    {
        BigInteger prev1 = new BigInteger("1");
        BigInteger prev2 = new BigInteger("0");
        BigInteger current = new BigInteger("1");
        
        if(input ==0){
            return prev2;
        }
        if(input ==1){
            return prev1;
        }
        for(int i=2; i<=input; i++){
            current=prev1.add(prev2);
            prev2=prev1;
            prev1=current;
        }
        
        return current;
    }
}
