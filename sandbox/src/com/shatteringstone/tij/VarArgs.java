package com.shatteringstone.tij;

public class VarArgs {
    
    public static void one(int... args){
        for(int item:args){
            System.out.println(item);
        }
    }
    public static void two(Object... args){
        for(Object item:args)
            System.out.println(item + ", " +item.getClass());
    }
    
    @Override
    public String toString() {
        return "hi";
    }

    /**
     * @param args
     */
    public static void main(String... args) {
        VarArgs.one(1, 2, 3, 4);
        VarArgs.two("1", 2, '3', new Integer('8'), new VarArgs());
    }

}
