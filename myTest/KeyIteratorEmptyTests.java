package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.KeyIterator;
import myAdapter.MapAdapter.KeySet;

/**
 * Test case for KeyIterator when the underlying map is empty. This class tests
 * the behavior of KeyIterator methods when there are no elements in the map.
 *
 * @test.design This test is designed to ensure that the KeyIterator behaves
 * correctly when the underlying map is empty, specifically checking the
 * hasNext(), next(), and remove() methods.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.KeySet
 * @see myAdapter.MapAdapter.KeyIterator
 */
public class KeyIteratorEmptyTests {

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
        keySet = (KeySet) map.keySet();
        iter = (KeyIterator) keySet.iterator();
    }

    // KeyIterator.hasNext() + KeyIterator.next()

    /**
     * Test to verify that hasNext() returns false when the iterator is empty.
     *
     * @test.design This test is designed to ensure that the KeyIterator behaves
     * correctly when the underlying map is empty.
     * @test.description This test checks that the KeyIterator does not have any
     * elements to iterate over by counting the number of iterations.
     * @test.precondition The map must be empty before this test runs.
     * @test.postcondition The KeyIterator should not have any elements.
     * @test.expectedresults The test should pass if the iteration count equals 0.
     */
    @Test
    public void testStartToFinish() {
        int i = 0;
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        assertEquals("Should have next once for each key", 0, i);
    }

    // KeyIterator.remove()

    /**
     * Test to verify that the empty iterator has no elements to remove.
     *
     * @test.design This test is designed to verify that an empty KeyIterator
     * behaves correctly when attempting to iterate and remove elements.
     * @test.description This test checks that an empty KeyIterator has no
     * elements to iterate over and that the underlying map remains empty.
     * @test.precondition The map must be empty before this test runs.
     * @test.postcondition The map should remain empty after this test runs.
     * @test.expectedresults The test should pass if no iterations occur and
     * the map remains empty.
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
     * Test to verify that remove() throws IllegalStateException when called
     * without a preceding call to next().
     *
     * @test.design This test is designed to ensure that the KeyIterator does
     * not allow removal of elements without first calling next().
     * @test.description This test checks that calling remove() without a prior
     * call to next() results in an IllegalStateException.
     * @test.precondition The map must be empty before this test runs.
     * @test.postcondition The KeyIterator should remain unchanged after this
     * test runs.
     * @test.expectedresults The test should pass if remove() throws an
     * IllegalStateException.
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
