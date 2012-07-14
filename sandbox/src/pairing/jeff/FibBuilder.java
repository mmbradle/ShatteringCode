package pairing.jeff;

public class FibBuilder implements CreateFibonacci
{
    
    /* (non-Javadoc)
     * @see pairing.jeff.CreateFibonacci#createFibonacci(int)
     */
    @Override
    public long createFibonacci(int input){
        
        if(input <=0){
            return 0;
        }
        
        if(input ==1){
            return 1;
        }
 
        return createFibonacci(input -1) + createFibonacci(input-2); 
        
    }

}
