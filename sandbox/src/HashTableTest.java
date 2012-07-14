import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HashTableTest
{
    HashTable hashTable = null;

    @Before
    public void setUp() throws Exception
    {
        hashTable = new HashTable();
        hashTable.put(1, 11);
        hashTable.put(2, 22);
        hashTable.put(3, 33);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testGet()
    {
        assertEquals(hashTable.get(1), 11);
        assertEquals(hashTable.get(2), 22);
        assertEquals(hashTable.get(3), 33);
    }

    @Test
    public void testRemove()
    {
        fail("Not yet implemented");
    }

}
