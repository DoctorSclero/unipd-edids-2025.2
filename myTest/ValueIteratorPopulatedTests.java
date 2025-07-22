package myTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.ValueIterator;
import myAdapter.MapAdapter.ValueCollection;

public class ValueIteratorPopulatedTests {
    
    public MapAdapter map;
    public ValueCollection values;
    public ValueIterator iter;
    
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        values = (ValueCollection) map.values();
        iter = (ValueIterator) values.iterator();
    }

    @After
    public void tearDown() {
        map = null;
        values = null;
        iter = null;
    }

    // ValueIterator.hasNext() + ValueIterator.next()

    @Test
    public void testStartToFinish() {
        int i = 0;
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        assertEquals("Should have next once for each value", 100, i);
    }

    // ValueIterator.remove()
    
    @Test
    public void testRemoveAll() {
        while (iter.hasNext()) {
            Object value = iter.next();
            assertNotNull("Object should not be null", value);
            iter.remove();
        }
        assertTrue("Map should be empty after removing all entries", map.isEmpty());
    }

    @Test
    public void testRemoveWithNext() {
        assertTrue("Should have a next element", iter.hasNext());
        Object value = iter.next();
        assertNotNull("Object should not be null", value);
        iter.remove();
        assertEquals("Map should contain 99 elements after one removal", 99, map.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
