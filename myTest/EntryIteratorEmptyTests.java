package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.Entry;
import myAdapter.MapAdapter.EntryIterator;
import myAdapter.MapAdapter.EntrySet;

/**
 * Tests the functionality for an EntryIterator linked to an empty map.
 * This class contains tests for the EntryIterator methods for which
 * the empty state of the map is relevant.
 *
 * @test.design This test class is designed to ensure that the EntryIterator
 * works correctly when the map is empty. It verifies
 * that the iterator behaves as expected in edge cases and does not allow
 * invalid operations.
 * 
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * 
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.EntryIterator
 * @see myAdapter.MapAdapter.EntrySet
 * @see myAdapter.MapAdapter.Entry
 */
public class EntryIteratorEmptyTests {
    
    public MapAdapter map;
    public EntrySet entrySet;
    public EntryIterator iter;
    
    /**
     * Sets up the test environment by initializing an empty MapAdapter
     * through the MapAdapter's default constructor and creating an EntrySet
     * instance obtaining its entry set view. After that, it an EntryIterator
     * instance is created from the obtained EntrySet.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        entrySet = (EntrySet) map.entrySet();
        iter = (EntryIterator) entrySet.iterator();
    }

    // EntryIterator.hasNext() + EntryIterator.next()

    /**
     * Tests iterator capability to iterate from start to finish.
     * 
     * @test.design This test is designed to ensure that the EntryIterator can
     * iterate through all entries in the map from start to finish. Ensuring
     * that the iterator can handle an empty map correctly.
     * @test.description The hasNext() is called repeatedly on the iterator
     * created in the {@link #setUp()} method until it returns false,
     * on each iteration next() is called on the iterator to traverse the 
     * entries and a counter is incremented. The test asserts that the
     * counter equals the number of entries in the map, which is 0.
     * @test.preconditions The map must be empty before this test runs.
     * @test.postconditions The iterator is at the end of the entry set, the
     * map remains unchanged.
     * @test.expectedresults Iterator traverses all 0 entries in the map
     * without any issues.
     */
    @Test
    public void testStartToFinish() {
        int i = 0;
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        assertEquals("Should have next once for each entry", 0, i);
    }

    // EntryIterator.remove()

    /**
     * Tests the remove method of the EntryIterator without calling next().
     * It checks that an IllegalStateException is thrown when remove() is called
     * without a preceding call to next().
     * 
     * @test.design This test is designed to ensure that the EntryIterator's
     * remove() method throws an IllegalStateException when called
     * without a preceding call to next().
     * @test.description This test uses the EntryIterator created in the
     * {@link #setUp()} method to call remove() without calling next().
     * It asserts that an IllegalStateException is thrown.
     * @test.preconditions The EntryIterator must be initialized and ready for
     * testing.
     * @test.postconditions The state of the EntryIterator should remain unchanged
     * after the test.
     * @test.expectedresults The test should pass if an IllegalStateException is thrown
     * when remove() is called without a preceding call to next().
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
