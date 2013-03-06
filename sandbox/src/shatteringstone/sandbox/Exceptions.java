package shatteringstone.sandbox;
class AwesomeException extends Exception {
}
class DerivedException extends Exceptions {
    public void fun() throws AwesomeException{
    }
}
public class Exceptions {
    public static void foo() throws AwesomeException {
        throw new AwesomeException();
    }
    public static void bar() throws Exception{
        foo();
    }
    public void fun() throws AwesomeException {
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
//        bar();
        try{
            bar();
        } catch(AwesomeException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
