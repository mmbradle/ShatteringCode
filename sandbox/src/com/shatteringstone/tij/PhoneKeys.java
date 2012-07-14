package com.shatteringstone.tij;

public enum PhoneKeys {
    ZERO, ONE, TWO;

    public static void main(String... args){
        for(PhoneKeys item:PhoneKeys.values()){
            //System.out.println(item + ", ordinal " + item.ordinal());
            System.out.printf("%s, ordinal %s\n", item, item.ordinal());
//            switch(item){
//                case ZERO: break;
//                case ONE: break;
//                case TWO: break;
//                default: System.out.println("Default");
//            }
        }
        
    }
}
