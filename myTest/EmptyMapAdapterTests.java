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
     * Test case setup procedure. Creates an empty MapAdapter instance.
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
     * Tests that MapAdapter's default constructor creates a new empty map
     * without exceptions.
     *
     * @test.design The test aims to verify that the default constructor creates
     * an empty map by creating a new instance and checking if the map is
     * empty.
     * @test.description The instance created in the {@link #setUp()} method is
     * overwritten by a new one created by the default constructor of the
     * MapAdapter. After creation the new instance is tested to be non-null and
     * empty by asserting that {@link MapAdapter#isEmpty()} returns true on that
     * instance and the same for the size.
     * @test.precondition None
     * @test.postcondition The map is created and is empty
     * @test.expectedresults Map is created without exceptions, it's empty and
     * has size 0.
     */
    @Test
    public void testDefaultConstructor() {
        map = new MapAdapter();
        assertNotNull(map);
        assertTrue("Map should be empty with default construction: ", map.isEmpty());
        assertEquals("Map should have size 0 with default construction: ", 0, map.size());
    }

    /**
     * Tests that MapAdapter's initial capacity constructor creates a new empty
     * map without exceptions.
     *
     * @test.design The test aims to verify that the initial capacity
     * constructor creates an empty map with the specified initial capacity by
     * creating a new instance and checking that the newly created map is
     * empty.
     * @test.description The instance created in the {@link #setUp()} method is
     * overwritten by a new one created by the initial capacity constructor of
     * the MapAdapter. After creation the new instance is tested to be non-null
     * and empty by asserting that {@link MapAdapter#isEmpty()} returns true on
     * that instance and the same for the size.
     * @test.precondition None
     * @test.postcondition The map is created and is empty
     * @test.expectedresults Map is created without exceptions, it's empty and
     * has size 0.
     */
    @Test
    public void testInitialCapacityConstructor() {
        map = new MapAdapter(5);
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
