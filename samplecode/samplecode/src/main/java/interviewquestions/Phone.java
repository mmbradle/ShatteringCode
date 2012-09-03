package interviewquestions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;

import org.junit.Test;

public class Phone {
    private static final String[][] numbers = { 
        {"0"}, //0
        {"1"}, //1
        {"a", "b", "c", "2"}, //2
        {"d", "e", "f", "3"}, //3
        {"g", "h", "i", "4"}, //4
        {"j", "k", "l", "5"}, //5
        {"m", "n", "o", "6"}, //6
        {"p", "q", "r", "s", "7"}, //7
        {"t", "u", "v", "8"}, //8
        {"w", "x", "y", "z", "9"} //9
    };
    
    public static SortedSet<String> getWords(int... numbers){
        SortedSet<String> words = new TreeSet<String>();
        words.add("");
        for(int number : numbers){
            words = addDigit(number, words);
        }
        return words;
    }
    
    private static SortedSet<String> addDigit(int digit, SortedSet<String> words){
        SortedSet<String> newWords = new TreeSet<String>();
        
//        //Add digits to ""
//        for (String letter : numbers[digit]){
//            String newWord = letter;
//            newWords.add(newWord);
//        }
        
        //Add digits to existing words
        for (String word : words) {
            for (String letter : numbers[digit]){
                String newWord = word + letter;
                newWords.add(newWord);
            }
        }
        return newWords;
    }
    
    public static SortedSet<String> getWords(String s){
        return getWords(stringToIntArray(s));
    }
    
    private static int[] stringToIntArray(String s){
        int[] intArray = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            int digit = Character.digit(s.charAt(i), 10);
            if (digit < 0){
                throw new IllegalArgumentException("String: \"" + s + "\" contains non digits");
            }
            intArray[i] = digit;
        }
        return intArray;
    }
    
    private static boolean privateTest(){
        return true;
    }
}
