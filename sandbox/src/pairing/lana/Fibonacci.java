package pairing.lana;

public class Fibonacci
{

    public long get(int i)
    {
        
        if(i == 0){
            return 0;
        }
        else if(i == 1){
            return 1;
        }else{
            return get(i-1)+get(i-2);
        }
        
    }
    
    

}
