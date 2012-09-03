package shatteringstone.sandbox;
interface Interface{
    static class Inner {
        private int i;
        public void setI(final int i){this.i = i;}
        public int getI(){return this.i;}
    }
}

class Outer1 implements Interface {
    public Inner inner = new Inner();
}

public class NestedClass {

    public static void main(String[] args) {
        Outer1 outer1a = new Outer1();
        Outer1 outer1b = new Outer1();
        outer1a.inner.setI(1);
        System.out.println(outer1a.inner.getI());
        System.out.println(outer1b.inner.getI());
    }
}
