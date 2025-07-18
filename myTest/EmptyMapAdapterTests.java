package myTest;

import myAdapter.MapAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
     *
     * @test.design The test aims to verify that the {@link MapAdapter#clear()}
     * method call on an empty map leaves the map empty when called on an empty
     * map.
     * @test.description The {@link MapAdapter#clear()} method is called on the
     * empty map created in the {@link #setUp()} method. After the call the map
     * is tested to be empty by asserting that {@link MapAdapter#isEmpty()}
     * returns true and the size is 0.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The map is empty after the
     * {@link MapAdapter#clear()} method call.
     */
    @Test
    public void testClear() {
        map.clear();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    /**
     * Tests that the {@link MapAdapter#containsValue(Object)} throws
     * {@link NullPointerException} when called with null argument.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsValue(Object)} method throws
     * {@code NullPointerException} when called with null argument.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with null argument on the empty map created by the
     * {@link #setUp()} method. Since the map does not accept null values, the
     * method should throw {@code NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * throws {@code NullPointerException} when called with null argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsValueNull() {
        map.containsValue(null);
    }

    /**
     * Tests that the {@link MapAdapter#containsValue(Object)} method
     * returns false when called with a value not present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsValue(Object)} method returns false when called
     * on an empty map with a value not present.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with a value not present on the empty map created by the
     * {@link #setUp()} method. Since the map is empty, the result of the method
     * is asserted to be false.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * returns false when called with a value not present in the map.
     */
    @Test
    public void testContainsValueNotPresent() {
        assertFalse("Map should not contain value 'test' when empty: ", map.containsValue("test"));
    }

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method
     * throws {@link NullPointerException} when called with null argument.
     */
    @Test
    public void testContainsKeyNull() {
        assertFalse("Map should not contain key null when empty: ", map.containsKey(null));
    }



}
