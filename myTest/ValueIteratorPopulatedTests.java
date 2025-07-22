package myTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.ValueIterator;
import myAdapter.MapAdapter.ValueCollection;

/**
 * Test class for verifying the behavior of ValueIterator when the underlying map is populated.
 * This class contains tests to ensure that the ValueIterator behaves correctly
 * when iterating over a map with values. It includes tests for iterating through all values,
 * removing values, and handling edge cases such as calling remove() without a preceding call to next().
 * 
 * @test.design This test class is designed to ensure that the ValueIterator behaves correctly
 * when the underlying map is populated. It verifies that the iterator can traverse all values,
 * remove values as expected, and handle invalid operations gracefully.
 * 
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.ValueIterator
 *
 * @author [Your Name]
 * @version 1.0
 */
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
