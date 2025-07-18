package myTest;

import myAdapter.MapAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.junit.Assert.*;

/**
 * This test case tests the MapAdapter class in an empty state. Every method of
 * the MapAdapter class is tested to ensure the class implementation is correct
 * for empty maps. For each method tests are created to ensure correct behavior
 * in normal and edge cases, including correct throw of exceptions and correct
 * return values.
 *
 * @test.design This test case aims to verify the correct behavior of empty
 * MapAdapter instances to ensure it correctly implements the
 * {@link myAdapter.HMap} interface.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 */
public class EmptyMapAdapterTests {

    public MapAdapter map;

    /**
     * Test case setup procedure.
     * Creates an empty MapAdapter instance.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * Test case tear-down procedure.
     */
    @After
    public void tearDown() {
        map = null;
    }

    /**
     * Tests that MapAdapter's default constructor creates a new empty adapter.
     */
    public void testDefaultConstructor() {
        map = new MapAdapter();
        assertNotNull(map);
        assertTrue("Map should be empty with default construction: ", map.isEmpty());
        assertEquals("Map should have size 0 with default construction: ", 0, map.size());
    }

    /**
     * Tests that the {@link MapAdapter#clear()} method call on an empty map
     * leaves the map empty.
     */
    @Test
    public void testClear() {
        map.clear();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }
}
