package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.ValueIterator;
import myAdapter.MapAdapter.ValueCollection;

/**
 * Test class for verifying the behavior of ValueIterator when the underlying map is empty.
 * This class contains tests to ensure that the ValueIterator behaves correctly
 * when there are no values to iterate over.
 * @test.design This test is designed to ensure that the ValueIterator behaves correctly
 * when the underlying map is empty. It verifies that the iterator does not have any elements
 * to iterate over and that calling next() throws an exception.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.ValueIterator
 * @see myAdapter.MapAdapter.ValueCollection
 */
public class ValueIteratorEmptyTests {
    
    public MapAdapter map;
    public ValueCollection values;
    public ValueIterator iter;
    
    /**
     * Set up the test environment by initializing an empty MapAdapter and its ValueIterator.
     * This method is called before each test to ensure a fresh state.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        values = (ValueCollection) map.values();
        iter = (ValueIterator) values.iterator();
    }

    // ValueIterator.hasNext() + ValueIterator.next()

    /**
     * Test to verify that hasNext() returns false and next() throws NoSuchElementException
     * when the iterator is empty.
     * @test.design This test is designed to ensure that the ValueIterator behaves correctly
     * when the underlying map is empty.
     * @test.description This test checks that the ValueIterator does not have any elements
     * to iterate over and that calling next() throws an exception.
     * @test.preconditions The map must be empty before this test runs.
     * @test.postconditions The ValueIterator should not have any elements.
     * @test.expectedBehavior The test should pass if all 100 items are iterated
     * over without any exceptions.
     */
    @Test
    public void testStartToFinish() {
        int i = 0;
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        assertEquals("Should have next once for each value", 0, i);
    }

    // ValueIterator.remove()

    /**
     * Test to verify that remove() throws IllegalStateException
     * when called without a preceding call to next().
     * @test.design This test is designed to ensure that the ValueIterator
     * does not allow removal of elements without first calling next().
     * @test.description This test checks that calling remove()
     * without a prior call to next() results in an IllegalStateException.
     * @test.preconditions The ValueIterator must not have any elements
     * to iterate over before this test runs.
     * @test.postconditions The ValueIterator should remain unchanged
     * after this test runs.
     * @test.expectedBehavior The test should pass if remove()
     * throws an IllegalStateException.
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

}
