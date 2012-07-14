package pairing.lana;

public class FibonacciAlgorithmImpl implements FibonacciAlgorithm
{

    @Override
    public long get(int i)
    {

        long retValue = 1;
        long prev = 1;
        long prev2 = 0; 
        
        if(i == 0){
            return 0;
        }
        else if(i == 1){
            return 1;
        }else{
        
            long temp = 0;
            for(int index = 2; index <= i; index++){
               
                temp = retValue;

                retValue = prev + prev2;
                prev2 = prev;
                prev = retValue;
  
            }
       
            
            return retValue;
        }
        
        
    }

}
