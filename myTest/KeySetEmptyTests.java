package myTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.KeySet;

/**
 * This test case tests the KeySet class instances linked to an empty map. Every
 * method of the KeySet class is tested to ensure the class implementation
 * serves correctly as a view for empty maps. For each method tests are created
 * to ensure correct behavior in normal and edge cases, including correct
 * throwing of exceptions and correct return values and side effects on the
 * underlying map.
 *
 * @test.design This test case aims to verify the correct behavior of empty
 * keySet instances to ensure it correctly serves as a set view for empty maps
 * and implements correctly the {@link myAdapter.HSet} interface.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 */
public class KeySetEmptyTests {

    public MapAdapter map;
    public KeySet keySet;

    /**
     * Sets up the test environment by initializing an empty MapAdapter through
     * the MapAdapter's default constructor and creating an KeySet instance
     * obtaining its key set view.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        keySet = (KeySet) map.keySet();
    }

    /**
     * Cleans up the test environment by setting the map and keySet references
     * to null, allowing for garbage collection.
     */
    @After
    public void tearDown() {
        map = null;
        keySet = null;
    }

    // KeySet.size()

    /**
     * Tests that the {@link KeySet#size()} method returns 0 for an empty map.
     *
     * @test.design The test aims to verify that the {@link KeySet#size()}
     * method returns 0 for an empty map.
     * @test.description The {@link KeySet#size()} method is called on the set
     * view created by the {@link #setUp()} method. Since the linked map is
     * empty, the method should return 0.
     * @test.precondition The map is correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#size()} method returns 0 for an
     * empty map.
     */
    @Test
    public void testSize() {
        assertEquals("Size of an empty key set should be 0", 0, keySet.size());
    }

    // KeySet.isEmpty()

    /**
     * Tests that the {@link KeySet#isEmpty()} method returns true for an empty
     * map.
     *
     * @test.design The test aims to verify that the {@link KeySet#isEmpty()}
     * method returns true for an empty map.
     * @test.description The {@link KeySet#isEmpty()} method is called on the
     * set view created by the {@link #setUp()} method. Since the linked map is
     * empty, the method should return true.
     * @test.precondition The map is correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#isEmpty()} method returns true
     * for an empty map.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("Key set should be empty", keySet.isEmpty());
    }

    // KeySet.contains(Object)

    /**
     * Tests that the {@link KeySet#contains(Object)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#contains(Object)} method throws a NullPointerException when
     * null is passed as an argument as not supported by the underlying map
     * implementation.
     * @test.description The {@link KeySet#contains(Object)} method is called
     * with null as an argument on the key set. Since the underlying map does
     * not support null keys, it should throw a NullPointerException.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#contains(Object)} method throws
     * NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNull() {
        keySet.contains(null);
    }

    /**
     * Tests that the {@link KeySet#contains(Object)} method returns false when
     * a non-contained element is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#contains(Object)} method returns false when a non-contained
     * element is passed as an argument.
     * @test.description To create a non-contained element, a new MapAdapter
     * instance is created, and a key is added to it. The key is then extracted
     * using the key set view of the new map. The
     * {@link KeySet#contains(Object)} method is called with this key on the
     * original key set. Since the original map is empty, the method should
     * return false.
     * @test.precondition The map and the key set are correctly instantiated.
     * Another MapAdapter instance is created with a key and the key is then
     * extracted from its key set.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#contains(Object)} method returns
     * false when a non-contained element is passed as an argument.
     */
    @Test
    public void testContainsNotContainedElement() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");

        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        Object key = anotherKeySet.iterator().next();

        assertFalse("Key set should not contain non-contained element",
                keySet.contains(key));
    }

    // KeySet.iterator()

    /**
     * Tests that the {@link KeySet#iterator()} method returns a non-null
     * iterator for an empty key set.
     *
     * @test.design The test aims to verify that the {@link KeySet#iterator()}
     * method returns a non-null iterator for an empty key set.
     * @test.description The {@link KeySet#iterator()} method is called on the
     * key set created by the {@link #setUp()} method. Since the key set is
     * empty, the method should return a non-null iterator that iterates over no
     * elements.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#iterator()} method returns a
     * non-null iterator for an empty key set.
     */
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of an empty key set should not be null",
                keySet.iterator());
    }

    // KeySet.toArray()

    /**
     * Tests that the {@link KeySet#toArray()} method returns a non-null array
     * for an empty key set.
     *
     * @test.design The test aims to verify that the {@link KeySet#toArray()}
     * method returns a non-null array for an empty key set.
     * @test.description The {@link KeySet#toArray()} method is called on the
     * key set created by the {@link #setUp()} method. Since the key set is
     * empty, the method should return a non-null array with a length of 0.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#toArray()} method returns a
     * non-null array for an empty key set.
     */
    @Test
    public void testToArray() {
        Object[] array = keySet.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an empty array for an empty key set",
                0, array.length);
    }

    // KeySet.toArray(T[])

    /**
     * Tests that the {@link KeySet#toArray(Object[])} method throws a
     * {@link NullPointerException} when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#toArray(Object[])} method throws a NullPointerException
     * when null is passed as an argument.
     * @test.description The {@link KeySet#toArray(Object[])} method is called
     * with null as an argument on the key set created by the {@link #setUp()}
     * method. Since the method does not support null arrays, it should throw a
     * NullPointerException.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#toArray(Object[])} method throws
     * {@link NullPointerException} when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullArray() {
        keySet.toArray(null);
    }

    /**
     * Tests that the {@link KeySet#toArray(Object[])} method returns the same
     * array when a bigger array is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#toArray(Object[])} method returns the same array when a
     * bigger empty array is passed as an argument.
     * @test.description A new empty array of size 5 is created. Then the array
     * is populated with flags to ensure that the
     * {@link KeySet#toArray(Object[])} method does not overwrite the elements
     * of the array. The format for the flags is {@code "flag-i"} where
     * {@code i} is the index of the element. The
     * {@link KeySet#toArray(Object[])} method is then called with this array as
     * an argument. Since the key set is empty, the method should return the
     * bigger passed array without overwriting its elements.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#toArray(Object[])} method returns
     * the same array passed as argument with a null terminator at position 0
     * and preserves all other elements unchanged.
     */
    @Test
    public void testToArrayWithBiggerArray() {
        Object[] array = new Object[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = keySet.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return an empty array for an empty key set",
                5, result.length);
        assertSame("toArray(T[]) should return the same array passed as argument",
                array, result);
        assertNull("toArray(T[]) should have a null terminator at position 0", result[0]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 1",
                "flag-1", result[1]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 2",
                "flag-2", result[2]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 3",
                "flag-3", result[3]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 4",
                "flag-4", result[4]);
    }

    // KeySet.add(Object)

    /**
     * Tests that the {@link KeySet#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty key set.
     *
     * @test.design The test aims to verify that the {@link KeySet#add(Object)}
     * method throws an UnsupportedOperationException when called on an empty
     * key set.
     * @test.description The {@link KeySet#add(Object)} method is called with a
     * string as an argument on the key set created by the {@link #setUp()}
     * method. Since the key set is a view of an empty map, the method should
     * throw an UnsupportedOperationException as adding elements to a key set is
     * not allowed.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty key set.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        keySet.add("baguette");
    }

    // KeySet.remove(Object)

    /**
     * Tests that the {@link KeySet#remove(Object)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#remove(Object)} method throws a NullPointerException when
     * null is passed as an argument.
     * @test.description The {@link KeySet#remove(Object)} method is called with
     * null as an argument on the key set created by the {@link #setUp()}
     * method. Since the method does not support null keys, it should throw a
     * NullPointerException.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#remove(Object)} method throws
     * NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        keySet.remove(null);
    }

    /**
     * Tests that the {@link KeySet#remove(Object)} method returns false when a
     * non-contained element is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#remove(Object)} method returns false when a non-contained
     * element is passed as an argument since it has no effect on the key set.
     * @test.description The {@link KeySet#remove(Object)} method is called with
     * a string that is not contained in the key set on the key set created by
     * the {@link #setUp()} method. Since the key set is empty, the method
     * should return false, indicating that the element was not contained in the
     * key set. Since the key set is backed by the map, the map should remain
     * empty after the operation.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#remove(Object)} method returns
     * false when a non-contained element is passed as an argument.
     */
    @Test
    public void testRemoveNonContained() {
        assertFalse("remove(Object) should return false for non-contained elements",
                keySet.remove("nonContainedElement"));
        assertEquals("Map should have size 0 after removing non-contained element", 0, map.size());
        assertTrue("Key set should be empty after removing non-contained element",
                keySet.isEmpty());
    }

    // KeySet.containsAll(HCollection)

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with null as an argument on the key set created by the
     * {@link #setUp()} method. Since the method does not accept null
     * collections, it should throw a NullPointerException.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * throws NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNull() {
        keySet.containsAll(null);
    }

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method returns
     * true when an empty collection is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method returns true when an empty
     * collection is passed as an argument.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with an empty collection on the key set created by the
     * {@link #setUp()} method. Since the key set is empty, it should contain
     * all elements of the empty collection, and the method should return true.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * returns true when an empty collection is passed as an argument.
     */
    @Test
    public void testContainsAllSameElements() {
        MapAdapter anotherMap = new MapAdapter();
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertTrue("Key set should contain all elements of the same key set",
                keySet.containsAll(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method returns
     * false when a collection with more elements is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method returns false when a
     * collection with more elements is passed as an argument.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with a collection (for this test another MapAdapter's KeySet
     * containing two entries: "key1"->"value1" and "key2"->"value2") that
     * contains more elements than the key set created by the {@link #setUp()}
     * method. Since the key set is empty, it should not contain all elements of
     * the collection, and the method should return false.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * returns false when a collection with more elements is passed as an
     * argument.
     */
    @Test
    public void testContainsAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key1", "value1");
        anotherMap.put("key2", "value2");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        assertFalse("Key set should not contain all elements of another key set with more elements",
                keySet.containsAll(anotherKeySet));
    }

    // KeySet.addAll(HCollection)

    /**
     * Tests that the {@link KeySet#addAll(HCollection)} method throws an
     * UnsupportedOperationException when called on an HCollection.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#addAll(HCollection)} method throws an
     * UnsupportedOperationException when called on an HCollection.
     * @test.description The {@link KeySet#addAll(HCollection)} method is called
     * with another MapAdapter's key set on the key set created by the
     * {@link #setUp()} method. Since the method is not supported by the key set
     * view it should throw an UnsupportedOperationException.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#addAll(HCollection)} method
     * throws an UnsupportedOperationException when called on an empty key set.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        MapAdapter anotherMap = new MapAdapter();
        keySet.addAll(anotherMap.keySet());
    }

    // KeySet.retainAll(HCollection)

    /**
     * Tests that the {@link KeySet#retainAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#retainAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link KeySet#retainAll(HCollection)} method is
     * called with null as an argument on the key set created by the
     * {@link #setUp()} method. Since the method does not accept null arguments,
     * it should throw a NullPointerException.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#retainAll(HCollection)} method
     * throws a NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNull() {
        keySet.retainAll(null);
    }

    // KeySet.removeAll(HCollection)

    /**
     * Tests that the {@link KeySet#removeAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#removeAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link KeySet#removeAll(HCollection)} method is
     * called with null as an argument on the key set created by the
     * {@link #setUp()} method. Since the method does not accept null arguments,
     * it should throw a NullPointerException.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#removeAll(HCollection)} method
     * throws a NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNull() {
        keySet.removeAll(null);
    }

    // KeySet.clear()

    /**
     * Tests that the {@link KeySet#clear()} method clears the key set and
     * leaves the underlying map empty.
     *
     * @test.design The test aims to verify that the {@link KeySet#clear()}
     * method clears the key set and leaves the underlying map empty.
     * @test.description The {@link KeySet#clear()} method is called on the key
     * set created by the {@link #setUp()} method. Since the key set is backed
     * by the underlying map, clearing the key set should also clear the map.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#clear()} method clears the key
     * set and leaves the underlying map empty.
     */
    @Test
    public void testClear() {
        // Clear the key set
        keySet.clear();

        // Verify that the size is still 0
        assertEquals("Size of key set should be 0 after clear", 0, keySet.size());
        assertTrue("Key set should be empty after clear", keySet.isEmpty());

        // Verify that the map is still empty
        assertEquals("Map should have size 0 after clearing key set", 0, map.size());
        assertTrue("Map should be empty after clearing key set", map.isEmpty());
    }

    // KeySet.equals(Object)

    /**
     * Tests that the {@link KeySet#equals(Object)} method returns true when a
     * key set with the same entries is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#equals(Object)} method returns true when a key set with
     * the same entries is passed as an argument.
     * @test.description A new empty MapAdapter instance is created, and its key
     * set is obtained. The {@link KeySet#equals(Object)} method is called on
     * the key set created by the {@link #setUp()} method with the new one. Since it
     * contains the same entries (none), the method should return true.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#equals(Object)} method returns
     * true when a key set with the same entries is passed as an argument.
     */
    @Test
    public void testEqualsSameSet() {
        MapAdapter anotherMap = new MapAdapter();
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertTrue("Key set should be equal to another key set with the same entries",
                keySet.equals(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#equals(Object)} method returns false when a
     * key set with different entries is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#equals(Object)} method returns false when a key set with
     * different entries is passed as an argument.
     * @test.description A new MapAdapter instance is created with a key
     * ("key", "value"). Its key set is obtained, and the
     * {@link KeySet#equals(Object)} method is called on the key set created by
     * the {@link #setUp()} method and the new one. Since it contains different
     * entries, the method should return false.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#equals(Object)} method returns
     * false when a key set with different entries is passed as an argument.
     */
    @Test
    public void testEqualsDifferentSet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertFalse("Key set should not be equal to another key set with different entries",
                keySet.equals(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#equals(Object)} method returns false when
     * the argument is not an KeySet.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#equals(Object)} method returns false when the argument is
     * not an KeySet.
     * @test.description The {@link KeySet#equals(Object)} method is called with
     * a String as an argument on the key set created by the {@link #setUp()}
     * method. Since the argument is not an KeySet, the method should return
     * false.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#equals(Object)} method returns
     * false when the argument is not an KeySet.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsNotASet() {
        // Test that the equals method returns false when the argument is not an KeySet
        String notASet = "This is not a set";
        assertFalse("Key set should not be equal to a non-KeySet object",
                keySet.equals(notASet));
    }

    // KeySet.hashCode()

    /**
     * Tests that the {@link KeySet#hashCode()} method returns 0 for an empty
     * key set.
     *
     * @test.design The test aims to verify that the {@link KeySet#hashCode()}
     * method returns 0 for an empty key set since it has no entries.
     * @test.description The {@link KeySet#hashCode()} method is called on the
     * key set created by the {@link #setUp()} method. Since the key set is
     * empty, the method should return 0.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#hashCode()} method returns 0 for
     * an empty key set.
     */
    @Test
    public void testHashCode() {
        int expectedHashCode = 0; // Since the key set is empty
        assertEquals("Key set hash code should be " + expectedHashCode,
                expectedHashCode, keySet.hashCode());
    }

    /**
     * Tests that the {@link KeySet#hashCode()} method returns different hash
     * codes for different key sets.
     *
     * @test.design The test aims to verify that the {@link KeySet#hashCode()}
     * method returns different hash codes for different key sets, even if they
     * are both empty.
     * @test.description A new MapAdapter instance is created with a key
     * ("key", "value"). Its key set is obtained, and the
     * {@link KeySet#hashCode()} method is called on the key set created by the
     * {@link #setUp()} method and the new one. Since the key sets are
     * different, the method should return different hash codes.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#hashCode()} method returns
     * different hash codes for different key sets.
     */
    @Test
    public void testHashCodeDifferentSets() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // The hash codes of the two key sets should be different
        assertNotEquals("Hash codes of different key sets should not be equal",
                keySet.hashCode(), anotherKeySet.hashCode());
    }

    /**
     * Tests that the {@link KeySet#hashCode()} method returns the same hash
     * code for the same key set.
     *
     * @test.design The test aims to verify that the {@link KeySet#hashCode()}
     * method returns the same hash code for the same key set.
     * @test.description The {@link KeySet#hashCode()} method is called on the
     * key set created by the {@link #setUp()} method and another instance of
     * the same key set. Since both instances are views of the same empty map,
     * the method should return the same hash code.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#hashCode()} method returns the
     * same hash code for the same key set.
     */
    @Test
    public void testHashCodeSameSet() {
        // Create another instance of the same key set
        KeySet anotherKeySet = (KeySet) map.keySet();
        assertEquals("Hash codes of the same key set should be equal",
                keySet.hashCode(), anotherKeySet.hashCode());
    }

    /**
     * Tests that the {@link KeySet#equals(Object)} method implies that the hash
     * codes of the key sets are equal.
     *
     * @test.design The test aims to verify that if two key sets are equal,
     * their hash codes should also be equal.
     * @test.description The {@link KeySet#equals(Object)} method is called on
     * the key set created by the {@link #setUp()} method with itself as an
     * argument. Since it is the same key set, the method should return true.
     * The {@link KeySet#hashCode()} method is then called on both key sets, and
     * their hash codes should be equal.
     * @test.precondition The map and the key set are correctly instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link KeySet#equals(Object)} method implies
     * that the hash codes of the key sets are equal.
     */
    @Test
    public void testEqualsImpliesHashCode() {
        assertEquals("Key sets with same elements should be equal",
                keySet, keySet);
        assertEquals("Hash codes of equal key sets should be equal",
                keySet.hashCode(), keySet.hashCode());
    }
}