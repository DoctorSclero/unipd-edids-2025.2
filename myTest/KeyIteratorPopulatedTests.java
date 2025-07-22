package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.KeyIterator;
import myAdapter.MapAdapter.KeySet;

/**
 * Test case for KeyIterator when the underlying map is populated.
 * This class tests the behavior of KeyIterator methods when there are elements in the map.
 * @test.design This test is designed to ensure that the KeyIterator behaves correctly
 * when the underlying map is populated, specifically checking the hasNext(), next(), and remove() methods.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.KeySet
 * @see myAdapter.MapAdapter.KeyIterator
 */
public class KeyIteratorPopulatedTests {
    
    public MapAdapter map;
    public KeySet keySet;
    public KeyIterator iter;
    
    /**
     * Setup method to initialize the map and keySet before each test.
     * This method is called before each test to ensure a fresh state.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        keySet = (KeySet) map.keySet();
        iter = (KeyIterator) keySet.iterator();
    }

    // KeyIterator.hasNext() + KeyIterator.next()

    /**
     * Test to verify that hasNext() returns true and next() returns the correct keys
     * when the iterator is populated.
     * @test.design This test is designed to ensure that the KeyIterator behaves correctly
     * when the underlying map is populated.
     * @test.description This test checks that the KeyIterator has elements to iterate over
     * and that calling next() returns the expected keys.
     * @test.preconditions The map must be populated before this test runs.
     * @test.postconditions The KeyIterator should have elements to iterate over.
     * @test.expectedBehavior The test should pass if hasNext() returns true and next()
     * returns the expected keys.
     */
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
    
    /**
     * Test to verify that remove() works correctly when the iterator is populated.
     * This test checks that elements can be removed from the map using the iterator.
     * @test.design This test is designed to ensure that the KeyIterator can remove elements
     * from the underlying map.
     * @test.description This test checks that calling remove() after next() removes the current key
     * from the map and that the map is empty after removing all entries.
     * @test.preconditions The map must be populated before this test runs.
     * @test.postconditions The KeyIterator should be able to remove elements from the map.
     * @test.expectedBehavior The test should pass if remove() works as expected and the map is empty
     * after all entries are removed.
     */
    @Test
    public void testRemoveAll() {
        while (iter.hasNext()) {
            Object key = iter.next();
            assertNotNull("Object should not be null", key);
            iter.remove();
        }
        assertTrue("Map should be empty after removing all entries", map.isEmpty());
    }

    /**
     * Test to verify that remove() works correctly after calling next().
     * This test checks that the KeyIterator can remove the current key after next() has been called.
     * @test.design This test is designed to ensure that the KeyIterator can remove elements
     * after they have been accessed with next().
     * @test.description This test checks that calling remove() after next() removes the current key
     * from the map and that the map does not contain the removed key.
     * @test.preconditions The KeyIterator must have elements to iterate over before this test runs.
     * @test.postconditions The KeyIterator should be able to remove elements from the map.
     * @test.expectedBehavior The test should pass if remove() works as expected and the map does not
     * contain the removed key.
     */
    @Test
    public void testRemoveWithNext() {
        assertTrue("Should have a next element", iter.hasNext());
        Object key = iter.next();
        assertNotNull("Object should not be null", key);
        iter.remove();
        assertFalse("Map should not contain the removed key", map.containsKey(key));
    }

    /**
     * Test to verify that remove() throws IllegalStateException
     * when called without a preceding call to next().
     * @test.design This test is designed to ensure that the KeyIterator
     * does not allow removal of elements without first calling next().
     * @test.description This test checks that calling remove()
     * without a prior call to next() results in an IllegalStateException.
     * @test.preconditions The KeyIterator must not have any elements
     * to iterate over before this test runs.
     * @test.postconditions The KeyIterator should remain unchanged
     * after this test runs.
     * @test.expectedBehavior The test should pass if remove()
     * throws an IllegalStateException.
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
