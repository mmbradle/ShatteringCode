package com.shatteringstone.com.samplecode;

public class InnerClassAccess {
    private int outerClassInt = 0;
    InnerClass ic = new InnerClass();
    public void touchInner(){
        ic.innerClassInt++;
    }
    class InnerClass {
        private int innerClassInt = 0;
        public void touchOuter(){
            outerClassInt++;
        }
        public String toString(){return "" + innerClassInt;}
    }
    static class StaticInnerClass{public int b=3;}

    public String toString(){return "" + outerClassInt;}
    /**
     * @param args
     */
    public static void main(String[] args) {
        InnerClassAccess.StaticInnerClass s = new InnerClassAccess.StaticInnerClass();
        System.out.println(s.b);
        InnerClassAccess ica = new InnerClassAccess();
        InnerClass ic = ica.new InnerClass();
        System.out.println(ica);
        ic.touchOuter();
        System.out.println(ica);
        
        System.out.println(ica.ic);
        ica.touchInner();
        System.out.println(ica.ic);
        
        DogImpl dog = new DogImpl();
        dog.howdy();
    }
}

interface ClassInInterface{
    void howdy();
    class DefaultImpl implements ClassInInterface { //Static by default
        public void howdy() {System.out.println("Howdy!");}
    }
}

class DogImpl implements ClassInInterface{
    ClassInInterface.DefaultImpl defaultImpl = new ClassInInterface.DefaultImpl();
    public void howdy() {
        defaultImpl.howdy();
        System.out.println("Dog");
    }
}

