
public class Round {

    public static double roundTo(double value, int precision){
        double powVal = Math.pow(10, -precision);
        return Math.round(value * powVal) / powVal;
    }
    
    public static double what(double rndPos){
        rndPos *= -1;
        if (rndPos == 0)
            rndPos = -1;
        if (rndPos < 0)
            rndPos += 1;
        return rndPos;
    }
    
    public static double fixRndPos(double rndPos){
        if (rndPos > 0) {
            rndPos = (-rndPos) + 1;
        } else {
            rndPos = -rndPos;
        }
        return rndPos;
    }
    /**
     * @param args
     */
    public static void main(String... args) {
        double value = -123.456;
        int precision = 0; 
        System.out.println("Rounding " + value + " to " + precision + " = " + roundTo(value, precision));
        
        System.out.println("-2: " + what(-2));
        System.out.println("-1: " + what(-1));
        System.out.println("0: " + what(0));
        System.out.println("1: " + what(1));
        System.out.println("2: " + what(2));
        System.out.println("3: " + what(3));
        for(double testVal = -100; testVal < 100; testVal += 1){
            assert fixRndPos(testVal) == what(testVal) : "Error at: " + testVal;
        }
        
    }

}
