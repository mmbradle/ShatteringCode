package com.shatteringstone.com.samplecode;
public class FinallyTest {
    public static boolean foo() {
        boolean retVal = false;
        try{
            System.out.println("return");
            return retVal;
        }
        finally{
            System.out.println("finally");
            retVal = true;
        }
    }
    public static void main(String[] args) {
        System.out.println(foo());
    }
}
