package pairing.ben;

import junit.framework.TestCase;

public class PanagramCheckTest extends TestCase
{
    public void testIsPanagram() throws Exception {
        
        PanagramCheck panagramCheck = new PanagramCheck();
        
        String isPanagram = "The quick brown fox jumps over the lazy dog";
        String isNotPanagram = "The quick brown fox";
        
        assertTrue(panagramCheck.isPanagram(isPanagram));
        assertFalse(panagramCheck.isPanagram(isNotPanagram));
        
        String testCaseSensitivity = "The Quick brown fox jumps over the lazy dog";
        assertTrue(panagramCheck.isPanagram(testCaseSensitivity));

        String testNumeric = "The Quick brown fox jumps over the 2 lazy dogs";
        assertTrue(panagramCheck.isPanagram(testNumeric));

        String testEmpty = "";
        assertFalse(panagramCheck.isPanagram(testEmpty));
        
        panagramCheck.isPanagram2(isPanagram);

    }
}
