package shatteringstone.sandbox;

import org.javatuples.Pair;

/**
 * Hello world!
 *
 */

enum Enum {
	RED, GREED, BLUE
}

public class App 
{
    public static void main( String[] args )
    {
        Enum e1 = Enum.BLUE;
        Pair<String,Integer> pair = Pair.with("hello", Integer.valueOf(23));
        System.out.println(pair.toString());
     
    }
}
