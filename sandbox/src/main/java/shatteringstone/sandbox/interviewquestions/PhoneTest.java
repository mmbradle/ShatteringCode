package shatteringstone.sandbox.interviewquestions;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class PhoneTest {
	
    @Test
    public void testTemp() {
        Set<String> words = Phone.getWords(5);
        System.out.println(Arrays.toString(words.toArray(new String[words.size()])));
    }
	
    @Test
    public void test123() {
        String [] controlWords = {"1ad", "1ae", "1af", "1bd", "1be", "1bf", "1cd", "1ce", "1cf", "12f"};
        Set<String> testWords = Phone.getWords("123");
        for (int i = 0; i < controlWords.length; i++) {
            assertTrue(testWords.contains(controlWords[i]));
        }
    }
    
    @Test
    public void test22() {
        Set<String> words = Phone.getWords(2, 2);
        assertFalse(words.contains("a"));
        assertFalse(words.contains(""));
        assertTrue(words.contains("ab"));
        assertTrue(!words.contains("aaa"));
    }
    
    @Test
    public void test7763442() {
        Set<String> words = Phone.getWords(7,7,6,3,4,4,2);
        assertTrue(words.contains("prodiga"));
    }
    
    @Test
    public void test7763442AsString() {
        Set<String> words = Phone.getWords("77634425");
        assertTrue(words.contains("prodigal"));
        assertFalse(words.contains("proaigal"));
    }

    @Test
    public void testStringToIntArray() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method method = Phone.class.getDeclaredMethod("stringToIntArray", String.class);
        method.setAccessible(true);
        
        int[] a = {0, 1, 2};
        assertTrue(Arrays.equals(a, (int[])method.invoke(null, "012")));
        assertFalse(Arrays.equals(a, (int[])method.invoke(null, "112")));
    }

    @Test
    public void testStringToIntArrayWiWord() throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = Phone.class.getDeclaredMethod("stringToIntArray", String.class);
        method.setAccessible(true);
        
        boolean caught = false;
        try {
            method.invoke(null, "abc");
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof IllegalArgumentException) {
                caught = true;
            } else {
                throw e;
            }
        }
        assertTrue(caught);
    }

    @Test
    public void testStringToIntArrayWiNeg() throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = Phone.class.getDeclaredMethod("stringToIntArray", String.class);
        method.setAccessible(true);
        
        boolean caught = false;
        try {
            method.invoke(null, "-10");
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof IllegalArgumentException) {
                caught = true;
            } else {
                throw e;
            }
        }
        assertTrue(caught);
    }
    
    @Test
    public void testPrivate() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
        Method method = Phone.class.getDeclaredMethod("privateTest");
        method.setAccessible(true);
        boolean a = (Boolean) method.invoke(null);
        assertTrue(a);
    }
}
