package chaosinmotion.factorialalgorithm;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.getProperties().setProperty("chaosinmotion.factorialalgorithm", "cachedAlgorithm");
        System.out.println("5! = " + FactorialUtil.factorial(5));
    }

}
