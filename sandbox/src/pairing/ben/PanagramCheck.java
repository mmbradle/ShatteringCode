package pairing.ben;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PanagramCheck
{

    public boolean isPanagram(String toCheck) {
        
              
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        Set<String> found = new HashSet<String> ();
        
        for (int i = 0; i < toCheck.length(); i++){
           
            String c = toCheck.toLowerCase().substring(i, i+1);
            if(letters.contains(c)) {
                found.add(c);
            }           
            
        }

        return found.size() == 26 ? true : false;
        
    }
    
    boolean isPanagram2(String toCheck) {
        
       
        
        char [] chars = toCheck.toLowerCase().toCharArray();
        for ( int i = 0; i < chars.length; i ++) {
            
            
            
            System.out.println(Character.getNumericValue(chars[i]));
        }
        
        return false;
    }
}
