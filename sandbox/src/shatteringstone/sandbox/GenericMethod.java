package shatteringstone.sandbox;

public class GenericMethod {

    public static <T> void printType(T t, T u, T v){
        System.out.println(t.getClass().getCanonicalName());
        System.out.println(u.getClass().getCanonicalName());
        System.out.println(v.getClass().getCanonicalName());
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        GenericMethod.printType("", 2, 'a');
//        System.out.println(GenericMethod.class.getCanonicalName());
//        GenericMethod.printType(1);
//        GenericMethod.printType(Integer.valueOf(1));
//        GenericMethod.printType(1f);
//        GenericMethod.printType(1l);
//        GenericMethod.printType(1L);
//        GenericMethod.printType(GenericMethod.class);
//        GenericMethod.printType('a');
    }

}
