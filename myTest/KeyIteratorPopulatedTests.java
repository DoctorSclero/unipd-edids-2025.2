package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.KeyIterator;
import myAdapter.MapAdapter.KeySet;

/**
 * Test case for KeyIterator when the underlying map is populated. This class
 * tests the behavior of KeyIterator methods when there are elements in the
 * map.
 *
 * @test.design This test is designed to ensure that the KeyIterator behaves
 * correctly when the underlying map is populated, specifically checking the
 * hasNext(), next(), and remove() methods.
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
     * Setup method to initialize the map and keySet before each test. This
     * method is called before each test to ensure a fresh state.
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
     * Test to verify that hasNext() returns true and next() returns the correct
     * keys when the iterator is populated.
     *
     * @test.design This test is designed to ensure that the KeyIterator behaves
     * correctly when the underlying map is populated, specifically testing the
     * complete iteration through all elements.
     * @test.description This test iterates through all keys in the populated map
     * using hasNext() and next() methods, counting the total number of iterations
     * to verify that exactly 100 keys are iterated over (matching the setup).
     * @test.precondition The map must be populated with 100 key-value pairs
     * before this test runs.
     * @test.postcondition The KeyIterator should have completed iteration through
     * all elements and hasNext() should return false.
     * @test.expectedresults The test should pass if exactly 100 iterations are
     * performed, confirming that all keys in the map are accessible through the iterator.
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
     * Test to verify that remove() works correctly when the iterator is
     * populated. This test checks that elements can be removed from the map
     * using the iterator.
     *
     * @test.design This test is designed to ensure that the KeyIterator can
     * remove elements from the underlying map by iterating through all elements
     * and removing each one.
     * @test.description This test iterates through all keys in the populated map,
     * calling next() followed by remove() for each element. It verifies that each
     * key returned by next() is not null and that the map becomes empty after
     * all elements are removed.
     * @test.precondition The map must be populated with 100 key-value pairs
     * before this test runs.
     * @test.postcondition The map should be empty and the KeyIterator should
     * have no more elements to iterate over.
     * @test.expectedresults The test should pass if all keys are successfully
     * removed and the map is empty after the removal process.
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
     * Test to verify that remove() works correctly after calling next(). This
     * test checks that the KeyIterator can remove the current key after next()
     * has been called.
     *
     * @test.design This test is designed to ensure that the KeyIterator can
     * remove a single element after it has been accessed with next(), testing
     * the basic remove functionality.
     * @test.description This test calls hasNext() to verify an element exists,
     * then calls next() to retrieve a key, verifies the key is not null, calls
     * remove() to delete the key from the map, and finally verifies that the
     * map no longer contains the removed key.
     * @test.precondition The KeyIterator must have at least one element to
     * iterate over before this test runs.
     * @test.postcondition The specific key that was removed should no longer
     * exist in the map, and the map size should be reduced by one.
     * @test.expectedresults The test should pass if the key returned by next()
     * is not null, remove() executes without exception, and the map no longer
     * contains the removed key.
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
     * Test to verify that remove() throws IllegalStateException when called
     * without a preceding call to next().
     *
     * @test.design This test is designed to ensure that the KeyIterator properly
     * enforces the contract that remove() can only be called after next() has
     * been called, testing the error handling behavior.
     * @test.description This test attempts to call remove() immediately without
     * first calling next() on a freshly created iterator, expecting an
     * IllegalStateException to be thrown as per the Iterator contract.
     * @test.precondition The KeyIterator must be in its initial state (no calls
     * to next() have been made) before this test runs.
     * @test.postcondition The KeyIterator and underlying map should remain
     * unchanged, and an IllegalStateException should be thrown.
     * @test.expectedresults The test should pass if remove() throws an
     * IllegalStateException when called without a preceding next() call.
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
