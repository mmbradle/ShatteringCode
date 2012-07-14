package pairing.jeff;

public class IterativeFibBuilder implements CreateFibonacci
{

    
    @Override
    public long createFibonacci(int input)
    {
        long prev1, prev2, current;
        
        if(input ==0){
            return 0;
        }
        if(input ==1){
            return 1;
        }
        prev2 = 0;
        prev1 = 1;
        current = 1;
        for(int i=2; i<=input; i++){
            current=prev1+prev2;
            prev2=prev1;
            prev1=current;
        }
        
        return current;
    }

}
