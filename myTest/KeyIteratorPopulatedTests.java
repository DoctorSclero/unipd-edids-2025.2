package myTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.KeyIterator;
import myAdapter.MapAdapter.KeySet;

public class KeyIteratorPopulatedTests {
    
    public MapAdapter map;
    public KeySet keySet;
    public KeyIterator iter;
    
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        keySet = (KeySet) map.keySet();
        iter = (KeyIterator) keySet.iterator();
    }

    @After
    public void tearDown() {
        map = null;
        keySet = null;
        iter = null;
    }

    // KeyIterator.hasNext() + KeyIterator.next()

    @Test
    public void testStartToFinish() {
        int i = 0;
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        assertEquals("Should have next once for each key", 100, i);
    }

    // KeyIterator.remove()
    
    @Test
    public void testRemoveAll() {
        while (iter.hasNext()) {
            Object key = iter.next();
            assertNotNull("Object should not be null", key);
            iter.remove();
        }
        assertTrue("Map should be empty after removing all entries", map.isEmpty());
    }

    @Test
    public void testRemoveWithNext() {
        assertTrue("Should have a next element", iter.hasNext());
        Object key = iter.next();
        assertNotNull("Object should not be null", key);
        iter.remove();
        assertFalse("Map should not contain the removed key", map.containsKey(key));
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
