package datastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MedianTest {
    Median median;

    @Before
    public void setup() {
        this.median = new Median(new RandomNumbers());
    }

    @Test
    public void testGetMedian() throws Exception {
        this.median.add(1);
        assertEquals(1, this.median.getMedian());
        this.median.add(2);
        assertEquals(1, this.median.getMedian());
        this.median.add(3);
        assertEquals(2, this.median.getMedian());
    }
}
