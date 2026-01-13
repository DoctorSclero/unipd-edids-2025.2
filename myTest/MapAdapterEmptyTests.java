package myTest;

import myAdapter.HMap;
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
public class MapAdapterEmptyTests {

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

    // MapAdapter()

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

    // MapAdapter(int initialCapacity)

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
        assertNotNull(map);
        assertTrue("Map should be empty with initial capacity constructor: ", map.isEmpty());
        assertEquals("Map should have size 0 with initial capacity constructor: ", 0, map.size());
    }

    /**
     * Tests that the MapAdapter's initial capacity constructor throws
     * {@link IllegalArgumentException} when called with a negative initial
     * capacity.
     *
     * @test.design The test aims to verify that the initial capacity
     * constructor throws {@link IllegalArgumentException} when called with a
     * negative initial capacity.
     * @test.description The instance created in the {@link #setUp()} method is
     * overwritten by a new one created by the initial capacity constructor of
     * the MapAdapter with a negative initial capacity. After creation the new
     * instance is tested to throw {@link IllegalArgumentException}.
     * @test.precondition None
     * @test.postcondition The map is not created
     * @test.expectedresults The {@link MapAdapter#MapAdapter(int)} constructor
     * throws {@link IllegalArgumentException} when called with a negative
     * initial capacity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitialCapacityConstructorNegative() {
        map = new MapAdapter(-1);
    }

    // MapAdapter.clear()

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

    // MapAdapter.containsValue()

    /**
     * Tests that the {@link MapAdapter#containsValue(Object)} throws
     * {@link NullPointerException} when called with null argument.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsValue(Object)} method throws
     * {@link NullPointerException} when called with null argument.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with null argument on the empty map created by the
     * {@link #setUp()} method. Since the map does not accept null values, the
     * method should throw {@link NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * throws {@link NullPointerException} when called with null argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsValueNull() {
        map.containsValue(null);
    }

    /**
     * Tests that the {@link MapAdapter#containsValue(Object)} method returns
     * false when called with a value not present in the map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsValue(Object)} method returns false when called
     * on an empty map with a value not present.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with a value not present (for this test the string "test" is used)
     * on the empty map created by the {@link #setUp()} method. Since the map is
     * empty, the result of the method is asserted to be false.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * returns false when called with a value not present in the map.
     */
    @Test
    public void testContainsValueNotPresent() {
        assertFalse("Map should not contain value 'test' when empty: ", map.containsValue("test"));
    }

    // MapAdapter.containsKey()

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method throws
     * {@link NullPointerException} when called with null argument.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsKey(Object)} method throws
     * {@link NullPointerException} when called with null argument.
     * @test.description The {@link MapAdapter#containsKey(Object)} method is
     * called with null argument on the empty map created by the
     * {@link #setUp()} method. Since the map does not accept null keys, the
     * method should throw {@link NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#containsKey(Object)} method
     * throws {@link NullPointerException} when called with null argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsKeyNull() {
        map.containsKey(null);
    }

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method returns
     * false when called with a key not present in the map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsKey(Object)} method returns false when called
     * on an empty map with a key not present.
     * @test.description The {@link MapAdapter#containsKey(Object)} method is
     * called with a key not present (in the test the string "test" is used) on
     * the empty map created by the {@link #setUp()} method. Since the map is
     * empty, the result of the method is asserted to be false.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#containsKey(Object)} method
     * returns false when called with a key not present in the map.
     */
    @Test
    public void testContainsKeyNotPresent() {
        assertFalse("Map should not contain key 'test' when empty: ", map.containsKey("test"));
    }

    // MapAdapter.entrySet()

    /**
     * Tests that the {@link MapAdapter#entrySet()} method returns a non-null
     * set when called on an empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#entrySet()} method returns a non-null set when called
     * on an empty map.
     * @test.description The {@link MapAdapter#entrySet()} method is called on
     * the empty map created by the {@link #setUp()} method. The result is
     * asserted to be non-null, as the entry set should always be available,
     * even for an empty map.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#entrySet()} method returns a
     * non-null set when called on an empty map.
     */
    @Test
    public void testEntrySetNotNull() {
        assertNotNull("Entry set should not be null when map is empty: ", map.entrySet());
    }

    // MapAdapter.equals()

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method returns false
     * when compared to null.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to
     * null.
     * @test.description The {@link MapAdapter#equals(Object)} method is called
     * on the empty map created by the {@link #setUp()} method with null as
     * argument. The result is asserted to be false, as the map should not be
     * equal to null.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns false when compared to null.
     */
    @Test
    public void testEqualsWithNull() {
        assertFalse("Map should not be equal to null: ", map.equals(null));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method returns false
     * when compared to a non-map object.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to a
     * non-map object.
     * @test.description The {@link MapAdapter#equals(Object)} method is called
     * on the empty map created by the {@link #setUp()} method with a non-map
     * object as argument (for this test, the string "test" is used). The result
     * is asserted to be false, as the two objects are not equal.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns false when compared to a non-map object.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsWithNonMapObject() {
        assertFalse("Map should not be equal to non-map object: ", map.equals("test"));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method returns true when
     * compared to another empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns true when compared to
     * another empty map.
     * @test.description The {@link MapAdapter#equals(Object)} method is called
     * on the empty map created by the {@link #setUp()} method with another
     * empty map created with the default constructor. The result is asserted to
     * be true, as the two maps both contains just the same mappings.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns true when compared to another empty map.
     */
    @Test
    public void testEqualsWithAnotherEmptyMap() {
        MapAdapter anotherEmptyMap = new MapAdapter();
        assertTrue("Two empty maps should be equal: ", map.equals(anotherEmptyMap));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method returns false
     * when compared to a non-empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to a
     * non-empty map.
     * @test.description A new non-empty map is created with a single key-value
     * pair consisting of the string "key" as key and the string "value" as
     * value. The {@link MapAdapter#equals(Object)} method is then called on the
     * empty map with the non-empty map as the argument. The result is asserted
     * to be false as the two maps are not equal.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns false when compared to a non-empty map.
     */
    @Test
    public void testEqualsWithNonEmptyMap() {
        MapAdapter nonEmptyMap = new MapAdapter();
        nonEmptyMap.put("key", "value");
        assertFalse("Empty map should not be equal to non-empty map: ", map.equals(nonEmptyMap));
    }

    // MapAdapter.get()

    /**
     * Tests that the {@link MapAdapter#get(Object)} method throws
     * {@link NullPointerException} when called with null key.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#get(Object)} method throws {@link NullPointerException}
     * when called with null key.
     * @test.description The {@link MapAdapter#get(Object)} method is called
     * with null key on the empty map created by the {@link #setUp()} method.
     * Since the map does not accept null keys, the method should throw
     * {@link NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#get(Object)} method throws
     * {@link NullPointerException} when called with null key.
     */
    @Test(expected = NullPointerException.class)
    public void testGetWithNullKey() {
        map.get(null);
    }

    /**
     * Tests that the {@link MapAdapter#get(Object)} method returns null when
     * called with a key not present in the map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#get(Object)} method returns null when called with a key
     * not present in the map.
     * @test.description The {@link MapAdapter#get(Object)} method is called
     * with a key not present (for this test the string "nonexistentKey" is
     * used) on the empty map created by the {@link #setUp()} method. The result
     * is asserted to be null, as the key is not in the map.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#get(Object)} method returns
     * null when called with a key not present in the map.
     */
    @Test
    public void testGetWithKeyNotPresent() {
        assertNull("Get should return null for a key not present in an empty map: ", map.get("nonexistentKey"));
    }

    // MapAdapter.hashCode()

    /**
     * Tests that the {@link MapAdapter#hashCode()} method returns 0 for an
     * empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#hashCode()} method returns 0 when called on an empty
     * map.
     * @test.description The {@link MapAdapter#hashCode()} method is called on
     * the empty map created by the {@link #setUp()} method. The result is
     * asserted to be 0, as an empty map should have a hash code of 0.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#hashCode()} method returns 0
     * for an empty map.
     */
    @Test
    public void testHashCode() {
        assertEquals("Hash code of an empty map should be 0: ", 0, map.hashCode());
    }

    /**
     * Tests that the {@link MapAdapter#hashCode()} method returns the same
     * value for two empty maps.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#hashCode()} method returns the same value for two empty
     * maps.
     * @test.description A new empty map is created with the default constructor
     * then the {@link MapAdapter#hashCode()} method is called on both the empty
     * map created in the {@link #setUp()} method and the new empty map. The
     * result is asserted to be the same, as both maps are empty they are
     * expected to have the same hash code.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#hashCode()} method returns
     * the same value for two empty maps.
     */
    @Test
    public void testHashCodeWithAnotherEmptyMap() {
        MapAdapter anotherEmptyMap = new MapAdapter();
        assertEquals("Hash code of two empty maps should be the same: ", map.hashCode(), anotherEmptyMap.hashCode());
    }

    /**
     * Tests that the {@link MapAdapter#hashCode()} method returns different
     * values for an empty map and a non-empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#hashCode()} method returns different values for an
     * empty map and a non-empty map assuming the hashing algorithm used in
     * MapAdapter is expected to produce different hash codes for most different
     * maps.
     * @test.description A new non-empty map is created with a single key-value
     * pair consisting of the string "key" as key and the string "value" as
     * value. The {@link MapAdapter#hashCode()} method is then called on the
     * empty map created in the {@link #setUp()} method and on the non-empty
     * map. The result is asserted to be different, as the two maps contain
     * different mappings.
     * @test.precondition The map is correctly instantiated, the hashing
     * algorithm used in MapAdapter is expected to produce different hash codes
     * for most different maps.
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#hashCode()} method returns
     * different values for an empty map and a non-empty map.
     */
    @Test
    public void testHashCodeWithNonEmptyMap() {
        MapAdapter nonEmptyMap = new MapAdapter();
        nonEmptyMap.put("key", "value");
        assertNotEquals("Hash code of an empty map should be different from a non-empty map: ", map.hashCode(), nonEmptyMap.hashCode());
    }

    // MapAdapter.isEmpty()

    /**
     * Tests that the {@link MapAdapter#isEmpty()} method returns true for an
     * empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#isEmpty()} method returns true when called on an empty
     * map.
     * @test.description The {@link MapAdapter#isEmpty()} method is called on
     * the empty map created by the {@link #setUp()} method. The result is
     * asserted to be true, as an empty map should be considered empty.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#isEmpty()} method returns
     * true for an empty map.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("Map should be empty: ", map.isEmpty());
    }

    // MapAdapter.keySet()

    /**
     * Tests that the {@link MapAdapter#keySet()} method returns a non-null set
     * when called on an empty map.
     *
     * @test.design The test aims to verify that the {@link MapAdapter#keySet()}
     * method returns a non-null set when called on an empty map.
     * @test.description The {@link MapAdapter#keySet()} method is called on the
     * empty map created by the {@link #setUp()} method. The result is asserted
     * to be non-null, as the key set should always be available, even for an
     * empty map.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#keySet()} method returns a
     * non-null set when called on an empty map.
     */
    @Test
    public void testKeySetNotNull() {
        assertNotNull("Key set of an empty map should not be null: ", map.keySet());
    }

    // MapAdapter.put()

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method throws
     * {@link NullPointerException} when called with a null key.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method throws
     * {@link NullPointerException} when called with a null key as not supported
     * by the MapAdapter.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with a null key and a valid value on the empty map created by the
     * {@link #setUp()} method. The result is asserted to throw a
     * {@link NullPointerException}, as null keys are not supported.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * throws {@link NullPointerException} when called with a null key.
     */
    @Test(expected = NullPointerException.class)
    public void testPutNullKeyValidValue() {
        map.put(null, "value");
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method does not
     * change the map when called with a null key and a valid value, ensuring
     * strong guarantees.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method does not change the map
     * when called with a null key and a valid value, ensuring strong guarantees
     * that the map remains empty.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with a null key and a valid value on the empty map created by the
     * {@link #setUp()} method. The exception is caught and the map is asserted
     * to have not changed by checking that it's still empty, ensuring strong
     * guarantees.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * does not change the map when called with a null key and a valid value,
     * ensuring strong guarantees that the map remains empty.
     */
    @Test
    public void testPutNullKeyValidValueStrongGuarantee() {
        try {
            map.put(null, "value");
        } catch (NullPointerException e) {
        }

        assertTrue("Map should still be empty after put with null key: ", map.isEmpty());
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method throws
     * {@link NullPointerException} when called with a valid key and a null
     * value.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method throws
     * {@link NullPointerException} when called with a valid key and a null
     * value.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with a valid key and a null value on the empty map created by the
     * {@link #setUp()} method. The result is asserted to throw a
     * {@link NullPointerException}, as null values are not supported.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * throws {@link NullPointerException} when called with a valid key and a
     * null value.
     */
    @Test(expected = NullPointerException.class)
    public void testPutNullValueValidKey() {
        map.put("key", null);
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method does not
     * change the map when called with a valid key and a null value, ensuring
     * strong guarantees.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method does not change the map
     * when called with a valid key and a null value, ensuring strong
     * guarantees.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with a valid key and a null value on the empty map created by the
     * {@link #setUp()} method. Exception is caught and the map is asserted to
     * have not changed by checking that it's still empty, ensuring strong
     * guarantees.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * does not change the map when called with a valid key and a null value,
     * ensuring strong guarantees that the map remains empty.
     */
    @Test
    public void testPutNullValueValidKeyStrongGuarantee() {
        try {
            map.put("key", null);
        } catch (NullPointerException e) {
        }

        assertTrue("Map should still be empty after put with null value: ", map.isEmpty());
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method throws
     * {@link NullPointerException} when called with both key and value as
     * null.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method throws
     * {@link NullPointerException} when called with both key and value as
     * null.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with both key and value as null on the empty map created by the
     * {@link #setUp()} method. The result is asserted to throw a
     * {@link NullPointerException}, as null keys and values are not supported.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * throws {@link NullPointerException} when called with both key and value
     * as null.
     */
    @Test(expected = NullPointerException.class)
    public void testPutNullKeyAndValue() {
        map.put(null, null);
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method does not
     * change the map when called with both key and value as null, ensuring
     * strong guarantees.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method does not change the map
     * when called with both key and value as null, ensuring strong guarantees.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with both key and value as null on the empty map created by the
     * {@link #setUp()} method. The exception is caught and the map is asserted
     * to have not changed by checking that it's still empty, ensuring strong
     * guarantees.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * does not change the map when called with both key and value as null,
     * ensuring strong guarantees.
     */
    @Test
    public void testPutNullKeyAndValueStrongGuarantee() {
        try {
            map.put(null, null);
        } catch (NullPointerException e) {
            // Expected exception
        }

        assertTrue("Map should still be empty after put with null key and value: ", map.isEmpty());
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method adds a new
     * mapping into the map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method adds a valid mapping to the
     * map.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with a valid key and a valid value on the empty map created by the
     * {@link #setUp()} method. For this test the inserted entry has the string
     * "key" as key and "value" as value. The result is asserted to be null, as
     * the key was not previously present in the map. After the null result is
     * asserted, the map is checked to contain the new mapping by asserting that
     * {@link MapAdapter#get(Object)} returns the value for the key "key". The
     * map is also checked to be not empty after the put operation and the size
     * is asserted to be 1.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map contains the new mapping
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * adds a new mapping to the map, and the map is not empty after the put
     * operation.
     */
    @Test
    public void testPutNewMapping() {
        assertEquals("Map should return null when putting a new mapping: ", null, map.put("key", "value"));
        assertEquals("Map should contain the value for the key 'key': ", "value", map.get("key"));
        assertFalse("Map should not be empty after put: ", map.isEmpty());
        assertEquals("Map size should be 1 after put: ", 1, map.size());
    }

    // MapAdapter.putAll()

    /**
     * Tests that the {@link MapAdapter#putAll(Map)} method throws
     * {@link NullPointerException} when called with null argument.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#putAll(Map)} method throws {@link NullPointerException}
     * when called with null argument.
     * @test.description The {@link MapAdapter#putAll(Map)} method is called
     * with null argument on the empty map created by the {@link #setUp()}
     * method. The result is asserted to throw a {@link NullPointerException},
     * as null maps are not supported.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#putAll(Map)} method throws
     * {@link NullPointerException} when called with null argument.
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllNullArgument() {
        map.putAll(null);
    }

    /**
     * Tests that the {@link MapAdapter#putAll(Map)} method adds new mappings
     * from another map to the empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#putAll(Map)} method adds new mappings from another map
     * to the empty map.
     * @test.description A new map is created with the {@link MapAdapter}
     * default constructor then three mappings (for this test: "key1" ->
     * "value1", "key2" -> "value2", "key3" -> "value3") are added to it. The
     * {@link MapAdapter#putAll(Map)} method is then called with this new map as
     * an argument. The map with the new mappings is then checked to contain the
     * new mappings by asserting that all the values are correctly mapped to
     * their respective keys, the map is not empty after the putAll operation
     * and the size is asserted to be 3.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map contains the new mappings: "key1" ->
     * "value1", "key2" -> "value2", "key3" -> "value3".
     * @test.expectedresults The {@link MapAdapter#putAll(Map)} method adds new
     * mappings from another map to the empty map, and the map is not empty
     * after the putAll operation. The size of the map should be 3 after the
     * putAll operation.
     */
    @Test
    public void testPutAllNewMappings() {
        HMap newMappings = new MapAdapter();
        newMappings.put("key1", "value1");
        newMappings.put("key2", "value2");
        newMappings.put("key3", "value3");

        map.putAll(newMappings);

        assertEquals("Map should contain value for key 'key1': ", "value1", map.get("key1"));
        assertEquals("Map should contain value for key 'key2': ", "value2", map.get("key2"));
        assertEquals("Map should contain value for key 'key3': ", "value3", map.get("key3"));
        assertFalse("Map should not be empty after putAll: ", map.isEmpty());
        assertEquals("Map size should be 3 after putAll: ", 3, map.size());
    }

    // MapAdapter.remove()

    /**
     * Tests that the {@link MapAdapter#remove(Object)} method throws
     * {@link NullPointerException} when called with null key.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#remove(Object)} method throws
     * {@link NullPointerException} when called with null key.
     * @test.description The {@link MapAdapter#remove(Object)} method is called
     * with null key on the empty map created by the {@link #setUp()} method.
     * Since the map does not accept null keys, the method should throw
     * {@link NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#remove(Object)} method throws
     * {@link NullPointerException} when called with null key.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNullKey() {
        map.remove(null);
    }

    /**
     * Tests that the {@link MapAdapter#remove(Object)} method returns null when
     * called with a key not present in the map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#remove(Object)} method returns null when called with a
     * key not present in the map.
     * @test.description The {@link MapAdapter#remove(Object)} method is called
     * on the empty map created by the {@link #setUp()} method with a key not
     * present in the map. Since the map does not contain the key, the method
     * should return null.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#remove(Object)} method
     * returns null when called with a key not present in the map.
     */
    @Test
    public void testRemoveKeyNotPresent() {
        // Tests that removing a key not present in the map returns null
        assertNull("Removing a key not present should return null: ", map.remove("nonexistentKey"));
        // The map should still be empty after the remove operation
        assertTrue("Map should still be empty after removing a key not present: ", map.isEmpty());
    }

    // MapAdapter.size()

    /**
     * Tests that the {@link MapAdapter#size()} method returns 0 for an empty
     * map.
     *
     * @test.design The test aims to verify that the {@link MapAdapter#size()}
     * method returns 0 for an empty map.
     * @test.description The {@link MapAdapter#size()} method is called on the
     * empty map created by the {@link #setUp()} method. Since the map is empty,
     * the method should return 0.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#size()} method returns 0 for
     * an empty map.
     */
    @Test
    public void testSize() {
        assertEquals("Size of empty map should be 0: ", 0, map.size());
    }

    // MapAdapter.values()

    /**
     * Tests that the {@link MapAdapter#values()} method returns a non-null
     * collection.
     *
     * @test.design The test aims to verify that the {@link MapAdapter#values()}
     * method returns a non-null collection.
     * @test.description The {@link MapAdapter#values()} method is called on the
     * empty map created by the {@link #setUp()} method. A non-null collection
     * should be always returned, even for an empty map so the return value is
     * asserted to be not null.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#values()} method returns a
     * non-null collection.
     */
    @Test
    public void testValuesNotNull() {
        // Tests that the values collection is not null for an empty map
        assertNotNull("Values collection should not be null for an empty map: ", map.values());
    }

    // MapAdapter.toString()

    /**
     * Tests that the {@link MapAdapter#toString()} method returns "{}" for an
     * empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#toString()} method returns "{}" for an empty map.
     * @test.description The {@link MapAdapter#toString()} method is called on
     * the empty map created by the {@link #setUp()} method. The result is
     * asserted to be "{}", as an empty map should be represented by empty
     * braces.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#toString()} method returns
     * "{}" for an empty map.
     */
    @Test
    public void testToString() {
        assertEquals("String representation of empty map should be '{}': ", "{}", map.toString());
    }

}
