package shatteringstone.sandbox;
public class StackTrace {
    public static void main(String args[]) { 
        try {
            a();
        } catch(HighLevelException e) {
            e.printStackTrace();
        }
    }
    static void a() throws HighLevelException {
        try {
            b();
        } catch(MidLevelException e) {
            throw new HighLevelException(e);
        }
    }
    static void b() throws MidLevelException {
        c();
    }   
    static void c() throws MidLevelException {
        try {
            d();
        } catch(LowLevelException e) {
            throw new MidLevelException(e);
        }
    }
    static void d() throws LowLevelException { 
        e();
    }
    static void e() throws LowLevelException {
        throw new LowLevelException();
    }
}

class HighLevelException extends Exception {
	private static final long serialVersionUID = 1L;

	HighLevelException(Throwable cause) { super(cause); }
}

class MidLevelException extends Exception {
	private static final long serialVersionUID = 1L;

	MidLevelException(Throwable cause)  { super(cause); }
}

class LowLevelException extends Exception {
	private static final long serialVersionUID = 1L;
}