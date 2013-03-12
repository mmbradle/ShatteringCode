package shatteringstone.sandbox.io;

import org.junit.Assert;
import org.junit.Test;

public class CreatorGenericTest {

    @Test
    public void testConstructor() {
        Creator c = new Creator();
        c.f();
        Assert.assertTrue(c.element != null);
    }

}
