package com.shatteringstone.com.samplecode;
import static org.junit.Assert.*;

import org.junit.Test;


public class RoundTest {
    public static double roundTo(double value, int precision){
        double powVal = Math.pow(10, fixRndPos(precision));
        return Math.round(value * powVal) / powVal;
    }
    
    public static double what(double rndPos){
        rndPos *= -1;
        if (rndPos == 0)
            rndPos = -1;
        if (rndPos < 0)
            rndPos += 1;
        return rndPos;
    }
    
    public static double fixRndPos(double rndPos){
        if (rndPos > 0) {
            rndPos = (-rndPos) + 1;
        } else {
            rndPos = -rndPos;
        }
        return rndPos;
    }
    
    @Test
    public void testFixRndPos() {
        for(double testVal = -100; testVal < 100; testVal += 1){
            assertEquals(what(testVal), fixRndPos(testVal), 0);
        }
    }
    
    @Test
    public void testRoundTo() {
        assertEquals(roundTo(0, 0), 0, 0);
    }
}
