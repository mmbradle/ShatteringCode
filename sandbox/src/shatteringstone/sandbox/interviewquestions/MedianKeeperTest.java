package shatteringstone.sandbox.interviewquestions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MedianKeeperTest {
    MedianKeeper median;

    @Before
    public void setup() {
        this.median = new MedianKeeper();
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

    @Test
    public void testGetMedian_ordered() throws Exception {
        this.median.add(1);
        this.median.add(2);
        this.median.add(3);
        this.median.add(4);
        this.median.add(5);
        assertEquals(3, this.median.getMedian());
    }

    @Test
    public void testGetMedian_reverseOrdered() throws Exception {
        this.median.add(5);
        this.median.add(4);
        this.median.add(3);
        this.median.add(2);
        this.median.add(1);
        assertEquals(3, this.median.getMedian());
    }

    @Test
    public void testGetMedian_allSame() throws Exception {
        this.median.add(1);
        this.median.add(1);
        this.median.add(1);
        this.median.add(1);
        this.median.add(1);
        assertEquals(1, this.median.getMedian());
    }
}
