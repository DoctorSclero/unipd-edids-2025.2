package myTest;

import myAdapter.HMap;
import myAdapter.MapAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test case tests the MapAdapter class in a populated state. Every method of
 * the MapAdapter class is tested to ensure the class implementation is correct
 * for populated maps. For each method tests are created to ensure correct behavior
 * in normal and edge cases, including correct throw of exceptions and correct
 * return values.
 *
 * @test.design This test case aims to verify the correct behavior of populated
 * MapAdapter instances to ensure it correctly implements the
 * {@link myAdapter.HMap} interface.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 */
public class MapAdapterPopulatedTests {

    public MapAdapter map;

    /**
     * Test case setup procedure. Creates an empty MapAdapter instance.
     * Then populates it with 100 items, each with a unique key and value.
     * The format for the inserted key-value pairs is {@code "key" + k} for
     * {@code 0 <= k < 100} as key and {@code "value" + v} for
     * {@code 0 <= v < 100} as value.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + i);
        }
    }

    /**
     * Test case tear-down procedure.
     */
    @After
    public void tearDown() {
        map = null;
    }

    // MapAdapter.clear()

    /**
     * Tests that the {@link MapAdapter#clear()} method call on a populated map
     * leaves the map empty.
     *
     * @test.design The test aims to verify that the {@link MapAdapter#clear()}
     * method call on an populated map empties the map when called on an
     * populated map.
     * @test.description The {@link MapAdapter#clear()} method is called on the
     * populated map created in the {@link #setUp()} method. After the call the map
     * is tested to be empty by asserting that {@link MapAdapter#isEmpty()}
     * returns true and the size is 0.
     * @test.precondition The map is correctly instantiated and populated
     * @test.postcondition The map is empty
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
     * {@code NullPointerException} when called with null argument.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with null argument on the map created by the
     * {@link #setUp()} method. Since the map does not accept null values, the
     * method should throw {@code NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is unchanged
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
     * on a populated map with a value not present.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with a value not present (for this test the string "test" is used)
     * on the populated map created by the {@link #setUp()} method. Since the map is
     * populated with values "value0" to "value99", the result of the method is
     * asserted to be false.
     * @test.precondition The map is correctly instantiated and populated;
     * "test" is not a value in the map.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * returns false when called with a value not present in the map.
     */
    @Test
    public void testContainsValueNotPresent() {
        assertFalse("Map should not contain value 'test': ", map.containsValue("test"));
    }

    /**
     * Tests that the {@link MapAdapter#containsValue(Object)} method
     * returns true when called with a value present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsValue(Object)} method returns true when called
     * on a populated map with a value present.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with a value present at start (for this test case the starting value
     * is "value0") on the populated map created by the {@link #setUp()} method.
     * Since the map is populated with values "value0" to "value99", the result
     * of the method is asserted to be true.
     * @test.precondition The map is correctly instantiated and populated
     * including "value0" at the start.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * returns true when called with a value present in the map.
     */
    @Test
    public void testContainsValuePresentStart() {
        assertTrue("Map should contain value 'value0': ", map.containsValue("value0"));
    }

    /**
     * Tests that the {@link MapAdapter#containsValue(Object)} method
     * returns true when called with a value present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsValue(Object)} method returns true when called
     * on a populated map with a value present.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with a value present in the middle (for this test case the middle
     * value is "value50") on the populated map created by the {@link #setUp()}
     * method. Since the map is populated with values "value0" to "value99", the
     * result of the method is asserted to be true.
     * @test.precondition The map is correctly instantiated and populated
     * including "value50" in the middle.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * returns true when called with a value present in the map.
     */
    @Test
    public void testContainsValuePresentMiddle() {
        assertTrue("Map should contain value 'value50': ", map.containsValue("value50"));
    }

    /**
     * Tests that the {@link MapAdapter#containsValue(Object)} method
     * returns true when called with a value present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsValue(Object)} method returns true when called
     * on a populated map with a value present.
     * @test.description The {@link MapAdapter#containsValue(Object)} method is
     * called with a value present at the end (for this test case the ending
     * value is "value99") on the populated map created by the {@link #setUp()}
     * method. Since the map is populated with values "value0" to "value99", the
     * result of the method is asserted to be true.
     * @test.precondition The map is correctly instantiated and populated
     * including "value99" at the end.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#containsValue(Object)} method
     * returns true when called with a value present in the map.
     */
    @Test
    public void testContainsValuePresentEnd() {
        assertTrue("Map should contain value 'value99': ", map.containsValue("value99"));
    }

    // MapAdapter.containsKey()

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method
     * throws {@link NullPointerException} when called with null argument.
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsKey(Object)} method throws 
     * {@link NullPointerException} when called with null argument.
     * @test.description The {@link MapAdapter#containsKey(Object)} method is
     * called with null argument on the map created by the
     * {@link #setUp()} method. Since the map does not accept null keys,
     * the method should throw {@link NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still populated
     * @test.expectedresults The {@link MapAdapter#containsKey(Object)} method
     * throws {@code NullPointerException} when called with null argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsKeyNull() {
        map.containsKey(null);
    }

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method
     * returns false when called with a key not present in the map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsKey(Object)} method returns false when called
     * on a map with a key not present.
     * @test.description The {@link MapAdapter#containsKey(Object)} method is
     * called with a key not present (in the test the string "test" is used)
     * on the map created by the {@link #setUp()} method. Since the map
     * is populated with keys "key0" to "key99", the result of the method is 
     * asserted to be false.
     * @test.precondition The map is correctly instantiated and populated;
     * the key "test" is not present.
     * @test.postcondition The map is unchanged
     */
    @Test
    public void testContainsKeyNotPresent() {
        assertFalse("Map should not contain key 'test': ", map.containsKey("test"));
    }

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method
     * returns true when called with a key present in the map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsKey(Object)} method returns true when called
     * with a key present in the map.
     * @test.description The {@link MapAdapter#containsKey(Object)} method is
     * called with a key present (in the test the string "key0" is used)
     * on the map created by the {@link #setUp()} method. Since the map
     * is populated with keys "key0" to "key99", the result of the method is
     * asserted to be true.
     * @test.precondition The map is correctly instantiated and populated
     * including "key0" at the start.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#containsKey(Object)} method
     * returns true when called with a key present in the map.
     */
    @Test
    public void testContainsKeyPresentStart() {
        assertTrue("Map should contain key 'key0': ", map.containsKey("key0"));
    }

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method
     * returns true when called with a key present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsKey(Object)} method returns true when called
     * with a key present in the map.
     * @test.description The {@link MapAdapter#containsKey(Object)} method is
     * called with a key present in the middle (for this test case the middle
     * key "key50" is used).
     * Since the map is populated with keys "key0" to "key99", the result of the
     * method is asserted to be true.
     * @test.precondition The map is correctly instantiated and populated
     * including "key50" in the middle.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#containsKey(Object)} method
     * returns true when called with a key present in the map.
     */
    @Test
    public void testContainsKeyPresentMiddle() {
        assertTrue("Map should contain key 'key50': ", map.containsKey("key50"));
    }

    /**
     * Tests that the {@link MapAdapter#containsKey(Object)} method
     * returns true when called with a key present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#containsKey(Object)} method returns true when called
     * with a key present in the map.
     * @test.description The {@link MapAdapter#containsKey(Object)} method is
     * called with a key present at the end (for this test case the ending
     * key "key99" is used). Since the map is populated with keys "key0" to
     * "key99", the result of the method is asserted to be true.
     * @test.precondition The map is correctly instantiated and populated 
     * including "key99" at the end.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#containsKey(Object)} method
     * returns true when called with a key present in the map.
     */
    @Test
    public void testContainsKeyPresentEnd() {
        assertTrue("Map should contain key 'key99': ", map.containsKey("key99"));
    }

    // MapAdapter.entrySet()

    /**
     * Tests that the {@link MapAdapter#entrySet()} method
     * returns a non-null set when called on a map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#entrySet()} method returns a non-null set when called
     * on a map.
     * @test.description The {@link MapAdapter#entrySet()} method is called on
     * the map created by the {@link #setUp()} method. The result is
     * asserted to be non-null, as the entry set should always be available.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link MapAdapter#entrySet()} method
     * returns a non-null set when called on a populated map.
     */
    @Test
    public void testEntrySetNotNull() {
        assertNotNull("Entry set should not be null: ", map.entrySet());
    }

    // MapAdapter.equals()

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method
     * returns false when compared to null.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to
     * null.
     * @test.description The {@link MapAdapter#equals(Object)} method is called
     * on the map created by the {@link #setUp()} method with null as the
     * argument. The result is asserted to be false, as the two are not
     * equal.
     * @test.precondition The map is correctly instantiated.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns false when compared to null.
     */
    @Test
    public void testEqualsWithNull() {
        assertFalse("Map should not be equal to null: ", map.equals(null));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method
     * returns false when compared to a non-map object.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to a
     * non-map object.
     * @test.description The {@link MapAdapter#equals(Object)} method is called
     * on the map created by the {@link #setUp()} method with a non-map
     * object as argument (for this test, the string "test" is used).
     * The result is asserted to be false, as the two objects are not equal.
     * @test.precondition The map is correctly instantiated.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns false when compared to a non-map object.
     */
    @Test
    public void testEqualsWithNonMapObject() {
        assertFalse("Map should not be equal to non-map object: ", map.equals("test"));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method
     * returns true when compared to another map with the same mappings
     * in the same order.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns true when compared to
     * another map with the same mappings in the same order.
     * @test.description The {@link MapAdapter#equals(Object)} method is called
     * on the map created by the {@link #setUp()} method with another
     * map created with the default constructor and populated with the same
     * mappings. The result is asserted to be 
     * true, as the two maps both contains just the same mappings.
     * @test.precondition The map is correctly instantiated, populated with
     * mappings with key "key" + i and value "value" + i for
     * {@code 0 <= i < 100}.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns true when compared to another map with the same mappings.
     */
    @Test
    public void testEqualsWithAnotherEmptyMapSameMappingsSameOrder() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        assertTrue("Two maps with the same mappings should be equal: ", map.equals(anotherMap));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method
     * returns true when compared to another map with the same mappings
     * in a different order.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns true when compared to
     * another map with the same mappings in a different order.
     * @test.description The {@link MapAdapter#equals(Object)} method is called
     * on the map created by the {@link #setUp()} method with another
     * map created with the default constructor and populated with the same
     * mappings. The result is asserted to be 
     * true, as the two maps both contains just the same mappings.
     * @test.precondition The map is correctly instantiated, populated with
     * mappings with key "key" + i and value "value" + i for
     * {@code 0 <= i < 100}.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns true when compared to another map with the same mappings.
     */
    @Test
    public void testEqualsWithAnotherEmptyMapSameMappingsDifferentOrder() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 99; i >= 0; i--) {
            anotherMap.put("key" + i, "value" + i);
        }
        assertTrue("Two maps with the same mappings should be equal: ", map.equals(anotherMap));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method
     * returns false when compared to a non-empty map with the same mappings.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to
     * a non-empty map.
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
    public void testEqualsWithDifferentMap() {
        MapAdapter nonEmptyMap = new MapAdapter();
        nonEmptyMap.put("key", "value");
        assertFalse("Maps should not be equal: ", map.equals(nonEmptyMap));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method
     * returns false when compared to a map with a subset of mappings.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to a
     * map with a subset of mappings.
     * @test.description A new map is created with 50 key-value pairs, each with
     * a unique key and value. The {@link MapAdapter#equals(Object)} method
     * is then called on the populated map with the new map as the argument.
     * The result is asserted to be false, as the two maps have 
     * mappings.
     * @test.precondition The map is correctly instantiated and populated
     * with 100 mappings.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns false when compared to a map with a subset of mappings.
     */
    @Test
    public void testEqualsWithDifferentMapSubset() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 50; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        assertFalse("Maps with different mappings should not be equal: ", map.equals(anotherMap));
    }

    /**
     * Tests that the {@link MapAdapter#equals(Object)} method
     * returns false when compared to a map with a superset of mappings.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#equals(Object)} method returns false when compared to a
     * map with a superset of mappings.
     * @test.description A new map is created with 150 key-value pairs, each with
     * a unique key and value. The {@link MapAdapter#equals(Object)} method
     * is then called on the populated map with the new map as the argument.
     * The result is asserted to be false, as the two maps have different
     * mappings.
     * @test.precondition The map is correctly instantiated and populated
     * with 100 mappings.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#equals(Object)} method
     * returns false when compared to a map with a superset of mappings.
     */
    @Test
    public void testEqualsWithDifferentMapSuperset() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 150; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        assertFalse("Maps with different mappings should not be equal: ", map.equals(anotherMap));
    }

    // MapAdapter.get()

    /**
     * Tests that the {@link MapAdapter#get(Object)} method throws
     * {@link NullPointerException} when called with null key.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#get(Object)} method throws {@link NullPointerException}
     * when called with null key.
     * @test.description The {@link MapAdapter#get(Object)} method is called with
     * null key on the map created by the {@link #setUp()} method.
     * Since the map does not accept null keys, the method should throw
     * {@link NullPointerException}.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#get(Object)} method
     * throws {@link NullPointerException} when called with null key.
     */
    @Test (expected = NullPointerException.class)
    public void testGetWithNullKey() {
        map.get(null);
    }

    /**
     * Tests that the {@link MapAdapter#get(Object)} method
     * returns null when called with a key not present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#get(Object)} method returns null when called with a key
     * not present in the map.
     * @test.description The {@link MapAdapter#get(Object)} method is
     * called with a key not present (for this test the string "nonexistentKey"
     * is used) on the map created by the {@link #setUp()} method.
     * The result is asserted to be null, as the key is not in the map.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#get(Object)} method
     * returns null when called with a key not present in the map.
     */
    @Test
    public void testGetWithKeyNotPresent() {
        assertNull("Get should return null for a key not present in the map: ", map.get("nonexistentKey"));
    }

    /**
     * Tests that the {@link MapAdapter#get(Object)} method
     * returns the value associated with a key present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#get(Object)} method returns the value associated with
     * the key when it is present at the start of the map.
     * @test.description The {@link MapAdapter#get(Object)} method is called with
     * a key present at start (for this test the starting key "key0" is used)
     * on the map created by the {@link #setUp()} method. Since the map is
     * populated with keys "key0" to "key99" and keyX is associated with
     * valueX, the result of the method is asserted to be "value0".
     * @test.precondition The map is correctly instantiated and populated
     * including "key0" at the start.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#get(Object)} method
     * returns the value associated with a key present in the map.
     */
    @Test
    public void testGetWithKeyPresentStart() {
        assertEquals("Get should return 'value0' for key 'key0': ", "value0", map.get("key0"));
    }

    /**
     * Tests that the {@link MapAdapter#get(Object)} method
     * returns the value associated with a key present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#get(Object)} method returns the value associated with
     * the key when it is present in the middle of the map.
     * @test.description The {@link MapAdapter#get(Object)} method is called with
     * a key present in the middle (for this test the middle key "key50" is used)
     * on the map created by the {@link #setUp()} method. Since the map is
     * populated with keys "key0" to "key99" and keyX is associated with
     * valueX, the result of the method is asserted to be "value50".
     * @test.precondition The map is correctly instantiated and populated
     * including "key50" in the middle.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#get(Object)} method
     * returns the value associated with a key present in the map.
     */
    @Test
    public void testGetWithKeyPresentMiddle() {
        assertEquals("Get should return 'value50' for key 'key50': ", "value50", map.get("key50"));
    }

    /**
     * Tests that the {@link MapAdapter#get(Object)} method
     * returns the value associated with a key present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#get(Object)} method returns the value associated with
     * the key when it is present at the end of the map.
     * @test.description The {@link MapAdapter#get(Object)} method is called with
     * a key present at the end (for this test the ending key "key99" is used)
     * on the map created by the {@link #setUp()} method. Since the map is
     * populated with keys "key0" to "key99" and keyX is associated with
     * valueX, the result of the method is asserted to be "value99".
     * @test.precondition The map is correctly instantiated and populated
     * including "key99" at the end.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link MapAdapter#get(Object)} method
     * returns the value associated with a key present in the map.
     */
    @Test
    public void testGetWithKeyPresentEnd() {
        assertEquals("Get should return 'value99' for key 'key99': ", "value99", map.get("key99"));
    }

    // MapAdapter.hashCode()

    /**
     * Tests that the {@link MapAdapter#hashCode()} method returns the same value
     * for two empty maps.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#hashCode()} method returns the same value for two empty
     * maps.
     * @test.description A new empty map is created with the default constructor
     * then the {@link MapAdapter#hashCode()} method is called on both the
     * map created in the {@link #setUp()} method and the new empty map.
     * The result is asserted to be the same, as both maps are empty they are
     * expected to have the same hash code.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#hashCode()} method
     * returns the same value for two empty maps.
     */
    @Test
    public void testHashCodeWithAnotherEmptyMap() {
        MapAdapter anotherEmptyMap = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            anotherEmptyMap.put("key" + i, "value" + i);
        }
        assertEquals("Hash code of two empty maps should be the same: ", map.hashCode(), anotherEmptyMap.hashCode());
    }

    /**
     * Tests that the {@link MapAdapter#hashCode()} method returns different 
     * values for an empty map and a non-empty map.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#hashCode()} method returns different values for an empty
     * map and a non-empty map assuming the hashing algorithm used in
     * MapAdapter is expected to produce different hash codes for most different
     * maps.
     * @test.description A new non-empty map is created with a single key-value
     * pair consisting of the string "key" as key and the string "value" as
     * value. The {@link MapAdapter#hashCode()} method is then called on the
     * empty map created in the {@link #setUp()} method and on the non-empty map.
     * @test.precondition The map is correctly instantiated, the hashing algorithm
     * used in MapAdapter is expected to produce different hash codes for most
     * different maps.
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#hashCode()} method
     * returns different values for an empty map and a non-empty map.
     */
    @Test
    public void testHashCodeWithNonEmptyMap() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        assertNotEquals("Hash code of an empty map should be different from a non-empty map: ", map.hashCode(), anotherMap.hashCode());
    }

    // MapAdapter.isEmpty()

    /**
     * Tests that the {@link MapAdapter#isEmpty()} method returns true for an empty map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#isEmpty()} method returns true when called on an empty map.
     * @test.description The {@link MapAdapter#isEmpty()} method is called on
     * the empty map created by the {@link #setUp()} method. The result is
     * asserted to be true, as an empty map should be considered empty.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#isEmpty()} method
     * returns true for an empty map.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("Map shouldn't be empty: ", !map.isEmpty());
    }

    // MapAdapter.keySet()

    /**
     * Tests that the {@link MapAdapter#keySet()} method returns a non-null set
     * when called on an empty map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#keySet()} method returns a non-null set when called
     * on an empty map.
     * @test.description The {@link MapAdapter#keySet()} method is called on
     * the empty map created by the {@link #setUp()} method. The result is
     * asserted to be non-null, as the key set should always be available,
     * even for an empty map.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#keySet()} method
     * returns a non-null set when called on an empty map.
     */
    @Test
    public void testKeySetNotNull() {
        assertNotNull("Key set should not be null: ", map.keySet());
    }
    
    // MapAdapter.put()

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method
     * throws {@link NullPointerException} when called with a null key.
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
     * Tests that the {@link MapAdapter#put(Object, Object)} method
     * does not change the map when called with a null key and a valid value,
     * ensuring strong guarantees.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method does not change the map
     * when called with a null key and a valid value, ensuring strong
     * guarantees that the map remains empty.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with a null key and a valid value on the empty map created by the
     * {@link #setUp()} method. The exception is caught and the map is
     * asserted to have not changed by checking that it's still empty, ensuring
     * strong guarantees.
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
        } catch (NullPointerException e) { }

        assertEquals("Map should be unchanged after put with null key: ", 100, map.size()); 
        assertTrue("Map should not be empty after put with null key: ", !map.isEmpty());
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method
     * throws {@link NullPointerException} when called with a valid key and a
     * null value.
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
     * throws {@link NullPointerException} when called with a valid key and a null
     * value.
     */
    @Test(expected = NullPointerException.class)
    public void testPutNullValueValidKey() {
        map.put("key", null);
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method
     * does not change the map when called with a valid key and a null value,
     * ensuring strong guarantees.
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
        } catch (NullPointerException e) { }

        assertEquals("Map should be unchanged after put with null value: ", 100, map.size());
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method
     * throws {@link NullPointerException} when called with both key and value
     * as null.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method throws
     * {@link NullPointerException} when called with both key and value
     * as null.
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
     * Tests that the {@link MapAdapter#put(Object, Object)} method
     * does not change the map when called with both key and value as null,
     * ensuring strong guarantees.
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

        assertEquals("Map should be unchanged after put with null key and value: ", 100, map.size());
        for (int i = 0; i < 100; i++) {
            assertEquals("Map should still contain value for key 'key" + i + "': ", "value" + i, map.get("key" + i));
        }
    }

    /**
     * Tests that the {@link MapAdapter#put(Object, Object)} method
     * adds a new mapping to the map when called with a new key and value.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#put(Object, Object)} method adds a new mapping to the
     * map when called with a new key and value.
     * @test.description The {@link MapAdapter#put(Object, Object)} method is
     * called with a new key (for this test the string "key") and a new value
     * (for this test the string "value") on the map created by the
     * {@link #setUp()} method. The result is asserted to be null, as the
     * mapping is new and was not previously present in the map. The map is then
     * checked to contain the new mapping by asserting that the value is correctly
     * mapped to the key.
     * @test.precondition The map is correctly instantiated and populated with 100 items
     * @test.postcondition The map contains the new mapping: "key" -> "value"
     * @test.expectedresults The {@link MapAdapter#put(Object, Object)} method
     * adds a new mapping to the map when called with a new key and value, and
     * the map size is 101 (1 more than the initial size).
     */
    @Test
    public void testPutNewMapping() {
        assertEquals("Map should return null when putting a new mapping: ", null, map.put("key", "value"));
        assertEquals("Map should contain the value for the key 'key': ", "value", map.get("key"));
        assertFalse("Map should not be empty after put: ", map.isEmpty());
        assertEquals("Map size should be 101 after put: ", 101, map.size());
    }

    // MapAdapter.putAll()

    /**
     * Tests that the {@link MapAdapter#putAll(Map)} method throws
     * {@link NullPointerException} when called with null argument.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#putAll(Map)} method throws
     * {@link NullPointerException} when called with null argument.
     * @test.description The {@link MapAdapter#putAll(Map)} method is called
     * with null argument on the empty map created by the {@link #setUp()} method.
     * The result is asserted to throw a {@link NullPointerException}, as null
     * maps are not supported.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#putAll(Map)} method
     * throws {@link NullPointerException} when called with null argument.
     */
    @Test (expected = NullPointerException.class)
    public void testPutAllNullArgument() {
        map.putAll(null);
    }

    /**
     * Tests that the {@link MapAdapter#putAll(Map)} method
     * adds new mappings to the map when called with a non-empty map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#putAll(Map)} method adds new mappings to the map when
     * called with a non-empty map.
     * @test.description The {@link MapAdapter#putAll(Map)} method is
     * called with a new map containing three key-value pairs on the map created
     * by the {@link #setUp()} method. The result is asserted to be null, as the
     * mappings are new and were not previously present in the map. The map is then
     * checked to contain the new mappings by asserting that the values are correctly
     * mapped to the keys.
     * @test.precondition The map is correctly instantiated and populated with 100 items
     * @test.postcondition The map contains the new mappings: "test1" -> "value1",
     * "test2" -> "value2", "test3" -> "value3"
     * @test.expectedresults The {@link MapAdapter#putAll(Map)} method
     * adds new mappings to the map when called with a non-empty map, and
     * the map size is 103 (3 more than the initial size).
     */
    @Test
    public void testPutAllNewMappings() {
        HMap newMappings = new MapAdapter();
        assertNull("Put all should not have overwritten any mappings: ", newMappings.put("test1", "value1"));
        assertNull("Put all should not have overwritten any mappings: ", newMappings.put("test2", "value2"));
        assertNull("Put all should not have overwritten any mappings: ", newMappings.put("test3", "value3"));

        map.putAll(newMappings);

        assertEquals("Map should contain value for key 'test1': ", "value1", map.get("test1"));
        assertEquals("Map should contain value for key 'test2': ", "value2", map.get("test2"));
        assertEquals("Map should contain value for key 'test3': ", "value3", map.get("test3"));
        assertFalse("Map should not be empty after putAll: ", map.isEmpty());
        assertEquals("Map size should be 3 after putAll: ", 103, map.size());
    }

    // MapAdapter.remove()

    /**
     * Tests that the {@link MapAdapter#remove(Object)} method
     * throws {@link NullPointerException} when called with null key.
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
     * @test.expectedresults The {@link MapAdapter#remove(Object)} method
     * throws {@link NullPointerException} when called with null key.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNullKey() {
        map.remove(null);
    }

    /**
     * Tests that the {@link MapAdapter#remove(Object)} method
     * returns null when called with a key not present in the map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#remove(Object)} method returns null when called with a
     * key not present in the map.
     * @test.description The {@link MapAdapter#remove(Object)} method is called
     * on the empty map created by the {@link #setUp()} method with a key not
     * present in the map. Since the map does not contain the key,
     * the method should return null.
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
        assertEquals("Map should be unchanged after removing a key not present: ", 100, map.size());
    }

    // MapAdapter.size()

    /**
     * Tests that the {@link MapAdapter#size()} method returns 0 for an empty map.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#size()} method returns 0 for an empty map.
     * @test.description The {@link MapAdapter#size()} method is called on the
     * empty map created by the {@link #setUp()} method. Since the map is empty,
     * the method should return 0.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link MapAdapter#size()} method returns 0
     * for an empty map.
     */
    @Test
    public void testSize() {
        assertEquals("Size of map should be 100: ", 100, map.size());
    }

    // MapAdapter.values()

    /**
     * Tests that the {@link MapAdapter#values()} method returns a non-null collection.
     * 
     * @test.design The test aims to verify that the
     * {@link MapAdapter#values()} method returns a non-null collection.
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

}
