package shatteringstone.sandbox;

enum Enum {
	RED, GREED, BLUE
}

public class App 
{
	static int count = 0;
	static int maxCount = 5;
	static int getNext() {
		if ( count > maxCount ) {
			throw new IndexOutOfBoundsException();
		}
		return count++;
	}
	
    public static void main( String[] args )
    {
    	while(true){
    		System.out.println(getNext());
    	}
    	
    	
//    	AtomicInteger aInt = new AtomicInteger();
//    	System.out.println(aInt.addAndGet(1));
//    	System.out.println(aInt);
//        Enum e1 = Enum.BLUE;
//        Pair<String,Integer> pair = Pair.with("hello", Integer.valueOf(23));
//        System.out.println(pair.toString());
//        Map<Pair<Integer, String>, String> map = new TreeMap<Pair<Integer, String>, String>();
//        map.put(Pair.with(2, "hola"), "adios");
//        map.put(Pair.with(1, "hi"), "bye");
//        map.put(Pair.with(1, "aloha"), "aloha");
//        System.out.println(map);
    }
}
