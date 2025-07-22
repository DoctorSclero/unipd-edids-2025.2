package myTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.ValueIterator;
import myAdapter.MapAdapter.ValueCollection;

/**
 * Test class for verifying the behavior of ValueIterator when the underlying
 * map is populated. This class contains tests to ensure that the ValueIterator
 * behaves correctly when iterating over a map with values.
 *
 * @test.design This test class is designed to ensure that the ValueIterator
 * behaves correctly when the underlying map is populated. It verifies that the
 * iterator can traverse all values, remove values as expected, and handle
 * invalid operations gracefully. The test setup creates a map with 100 entries
 * where values repeat every 50 keys to test behavior with duplicate values.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.ValueIterator
 * @see myAdapter.MapAdapter.ValueCollection
 */
public class ValueIteratorPopulatedTests {

    public MapAdapter map;
    public ValueCollection values;
    public ValueIterator iter;

    /**
     * Setup method to initialize the map and iterator before each test. This
     * method is called before each test to ensure a fresh state.
     *
     * @test.design This setup method creates a MapAdapter with 100 entries
     * where values repeat every 50 keys, ensuring we have duplicate values
     * to test the ValueIterator behavior with repeated elements.
     * @test.description Creates a map with 100 key-value pairs where keys are
     * "key0" to "key99" and values are "value0" to "value49" (repeating).
     * Then obtains the ValueCollection and its iterator for testing.
     * @test.precondition None - this is a setup method.
     * @test.postcondition The map, values collection, and iterator are
     * properly initialized and ready for testing.
     * @test.expectedresults The map should contain 100 entries with repeating
     * values, and the iterator should be ready to traverse all values.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        values = (ValueCollection) map.values();
        iter = (ValueIterator) values.iterator();
    }

    /**
     * Teardown method to clean up resources after each test.
     *
     * @test.design This teardown method sets all test instance variables to
     * null to ensure proper cleanup and garbage collection.
     * @test.description Sets the map, values collection, and iterator
     * references to null after each test execution.
     * @test.precondition The test method has completed execution.
     * @test.postcondition All test instance variables are set to null.
     * @test.expectedresults Memory resources are properly released.
     */
    @After
    public void tearDown() {
        map = null;
        values = null;
        iter = null;
    }

    // ValueIterator.hasNext() + ValueIterator.next()

    /**
     * Test to verify that the ValueIterator can traverse all values from
     * start to finish.
     *
     * @test.design This test verifies that the ValueIterator correctly
     * implements the iteration protocol by checking that hasNext() and next()
     * work together to traverse exactly the expected number of values.
     * @test.description The test uses a while loop with hasNext() to iterate
     * through all values using next(), counting each iteration. It then
     * verifies that the total count matches the expected number of values
     * in the collection (100).
     * @test.precondition The map must be populated with 100 entries before
     * this test runs.
     * @test.postcondition The iterator should have traversed all values
     * exactly once.
     * @test.expectedresults The test should pass if the iterator traverses
     * exactly 100 values, confirming that hasNext() and next() work correctly
     * for the entire collection.
     */
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

    /**
     * Test to verify that the ValueIterator can remove all values through
     * iteration.
     *
     * @test.design This test verifies that the ValueIterator's remove()
     * method works correctly when called after each next() operation,
     * and that removing all values results in an empty map.
     * @test.description The test iterates through all values using hasNext()
     * and next(), calling remove() after each next() call. It verifies that
     * each returned value is not null and that the map becomes empty after
     * all removals.
     * @test.precondition The map must be populated with 100 entries before
     * this test runs.
     * @test.postcondition The map should be empty after all values are
     * removed through the iterator.
     * @test.expectedresults The test should pass if all values can be
     * removed through the iterator and the map becomes empty.
     */
    @Test
    public void testRemoveAll() {
        while (iter.hasNext()) {
            Object value = iter.next();
            assertNotNull("Object should not be null", value);
            iter.remove();
        }
        assertTrue("Map should be empty after removing all entries", map.isEmpty());
    }

    /**
     * Test to verify that the ValueIterator can remove a single value
     * after calling next().
     *
     * @test.design This test verifies that the ValueIterator's remove()
     * method works correctly when called after a single next() operation,
     * properly reducing the map size by the appropriate amount.
     * @test.description The test calls hasNext() to verify an element exists,
     * then calls next() to get a value, verifies the value is not null,
     * calls remove() to remove the value, and checks that the map size
     * is reduced by the expected amount (considering duplicate values).
     * @test.precondition The map must be populated with 100 entries before
     * this test runs, and the iterator must have at least one element.
     * @test.postcondition The map should have 99 entries after removing
     * one value through the iterator.
     * @test.expectedresults The test should pass if a single value can be
     * removed through the iterator and the map size is reduced to 99.
     */
    @Test
    public void testRemoveWithNext() {
        assertTrue("Should have a next element", iter.hasNext());
        Object value = iter.next();
        assertNotNull("Object should not be null", value);
        iter.remove();
        assertEquals("Map should contain 99 elements after one removal", 99, map.size());
    }

    /**
     * Test to verify that the ValueIterator throws IllegalStateException
     * when remove() is called without a preceding call to next().
     *
     * @test.design This test verifies that the ValueIterator properly
     * enforces the contract that remove() can only be called after next()
     * has been called, throwing IllegalStateException otherwise.
     * @test.description The test attempts to call remove() immediately
     * on a fresh iterator without calling next() first. This should
     * result in an IllegalStateException being thrown.
     * @test.precondition The map must be populated and the iterator must
     * be freshly created (no calls to next() yet).
     * @test.postcondition The iterator and map should remain unchanged
     * after the exception is thrown.
     * @test.expectedresults The test should pass if remove() throws an
     * IllegalStateException when called without a preceding next() call.
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
