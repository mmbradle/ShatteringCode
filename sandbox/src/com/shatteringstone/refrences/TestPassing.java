package com.shatteringstone.refrences;

class C{
    public C(){
        this.member =55;
    }
    
    int member;
}

public class TestPassing {
    
    public static void foo(String param){
        param = "d";
    }
    public static void bar(C param){
        param = null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        C c = new C();
        TestPassing.bar(c);
        System.out.println(c.member);
        String i = "bye";
        TestPassing.foo(i);
        System.out.println(i);

    }

}
