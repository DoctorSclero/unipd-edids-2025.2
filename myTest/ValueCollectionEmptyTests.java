package myTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.ValueCollection;

/**
 * This test case tests the ValueCollection class instances linked to an empty
 * map. Every method of the ValueCollection class is tested to ensure the class
 * implementation serves correctly as a view for empty maps. For each method
 * tests are created to ensure correct behavior in normal and edge cases,
 * including correct throwing of exceptions and correct return values and side
 * effects on the underlying map.
 *
 * @test.design This test case aims to verify the correct behavior of empty
 * values instances to ensure it correctly serves as a collection view for empty
 * maps and implements correctly the {@link myAdapter.HCollection} interface.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.ValueCollection
 */
public class ValueCollectionEmptyTests {

    public MapAdapter map;
    public ValueCollection values;

    /**
     * Sets up the test environment by initializing an empty MapAdapter through
     * the MapAdapter's default constructor and creating an ValueCollection
     * instance obtaining its value collection view.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        values = (ValueCollection) map.values();
    }

    /**
     * Cleans up the test environment by setting the map and values references
     * to null, allowing for garbage collection.
     */
    @After
    public void tearDown() {
        map = null;
        values = null;
    }

    // ValueCollection.size()

    /**
     * Tests that the {@link ValueCollection#size()} method returns 0 for an
     * empty map.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#size()} method returns 0 for an empty map.
     * @test.description The {@link ValueCollection#size()} method is called on
     * the collection view created by the {@link #setUp()} method. Since the linked map
     * is empty, the method should return 0.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#size()} method returns 0
     * for an empty map.
     */
    @Test
    public void testSize() {
        assertEquals("Size of an empty value collection should be 0", 0, values.size());
    }

    // ValueCollection.isEmpty()

    /**
     * Tests that the {@link ValueCollection#isEmpty()} method returns true for
     * an empty map.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#isEmpty()} method returns true for an empty map.
     * @test.description The {@link ValueCollection#isEmpty()} method is called
     * on the collection view created by the {@link #setUp()} method. Since the linked
     * map is empty, the method should return true.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#isEmpty()} method
     * returns true for an empty map.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("Value collection should be empty", values.isEmpty());
    }

    // ValueCollection.contains(Object)

    /**
     * Tests that the {@link ValueCollection#contains(Object)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#contains(Object)} method throws a
     * NullPointerException when null is passed as an argument as not supported
     * by the underlying map implementation.
     * @test.description The {@link ValueCollection#contains(Object)} method is
     * called with null as an argument on the value collection. Since the
     * underlying map does not support null keys, it should throw a
     * NullPointerException.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#contains(Object)} method
     * throws NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNull() {
        values.contains(null);
    }

    /**
     * Tests that the {@link ValueCollection#contains(Object)} method returns
     * false when a non-contained element is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#contains(Object)} method returns false when a
     * non-contained element is passed as an argument.
     * @test.description To create a non-contained element, a new MapAdapter
     * instance is created, and an entry is added to it. The value is then extracted
     * using the value collection view of the new map. The
     * {@link ValueCollection#contains(Object)} method is called with this value
     * on the original value collection. Since the original map is empty, the
     * method should return false.
     * @test.precondition The map and the value collection are correctly
     * instantiated. Another MapAdapter instance is created with an entry and the
     * value is then extracted from its value collection.
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#contains(Object)} method
     * returns false when a non-contained element is passed as an argument.
     */
    @Test
    public void testContainsNotContainedElement() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");

        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object value = anotherValueCollection.iterator().next();

        assertFalse("Value collection should not contain non-contained element",
                values.contains(value));
    }

    // ValueCollection.iterator()

    /**
     * Tests that the {@link ValueCollection#iterator()} method returns a
     * non-null iterator for an empty value collection.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#iterator()} method returns a non-null iterator for
     * an empty value collection.
     * @test.description The {@link ValueCollection#iterator()} method is called
     * on the value collection created by the {@link #setUp()} method. Since the
     * value collection is empty, the method should return a non-null iterator
     * that iterates over no elements.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#iterator()} method
     * returns a non-null iterator for an empty value collection.
     */
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of an empty value collection should not be null",
                values.iterator());
    }

    // ValueCollection.toArray()

    /**
     * Tests that the {@link ValueCollection#toArray()} method returns a
     * non-null array for an empty value collection.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#toArray()} method returns a non-null array for an
     * empty value collection.
     * @test.description The {@link ValueCollection#toArray()} method is called
     * on the value collection created by the {@link #setUp()} method. Since the
     * value collection is empty, the method should return a non-null array with
     * a length of 0.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#toArray()} method
     * returns a non-null array for an empty value collection.
     */
    @Test
    public void testToArray() {
        Object[] array = values.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an empty array for an empty value collection",
                0, array.length);
    }

    // ValueCollection.toArray(T[])

    /**
     * Tests that the {@link ValueCollection#toArray(Object[])} method throws a
     * {@link NullPointerException} when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#toArray(Object[])} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link ValueCollection#toArray(Object[])} method is
     * called with null as an argument on the value collection created by the
     * {@link #setUp()} method. Since the method does not support null arrays,
     * it should throw a NullPointerException.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#toArray(Object[])}
     * method throws {@link NullPointerException} when null is passed as an
     * argument.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullArray() {
        values.toArray(null);
    }

    /**
     * Tests that the {@link ValueCollection#toArray(Object[])} method returns
     * an empty array when an empty array is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#toArray(Object[])} method returns an empty array
     * when an bigger empty array is passed as an argument.
     * @test.description A new empty array of size 5 is created. Then the array
     * is populated with flags to ensure that the
     * {@link ValueCollection#toArray(Object[])} method does not overwrite the
     * elements of the array. The format for the flags is {@code "flag-i"} where
     * {@code i} is the index of the element. The
     * {@link ValueCollection#toArray(Object[])} method is then called with this
     * array as an argument. Since the value collection is empty, the method
     * should return the bigger passed array without overwriting its elements.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#toArray(Object[])}
     * method returns the same array passed as argument with the first element
     * set to null as a terminator and the remaining elements unchanged.
     */
    @Test
    public void testToArrayWithBiggerArray() {
        Object[] array = new Object[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = values.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return an empty array for an empty value collection",
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

    // ValueCollection.add(Object)

    /**
     * Tests that the {@link ValueCollection#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty value collection.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty value collection.
     * @test.description The {@link ValueCollection#add(Object)} method is
     * called with a string as an argument on the value collection created by
     * the {@link #setUp()} method. Since the value collection is a view of an
     * empty map, the method should throw an UnsupportedOperationException as
     * adding elements to a value collection is not allowed.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#add(Object)} method
     * throws an UnsupportedOperationException when called on an empty value
     * collection.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        values.add("baguette");
    }

    // ValueCollection.remove(Object)

    /**
     * Tests that the {@link ValueCollection#remove(Object)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#remove(Object)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link ValueCollection#remove(Object)} method is
     * called with null as an argument on the value collection created by the
     * {@link #setUp()} method. Since the method does not support null values, it
     * should throw a NullPointerException.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#remove(Object)} method
     * throws NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        values.remove(null);
    }

    /**
     * Tests that the {@link ValueCollection#remove(Object)} method returns
     * false when a non-contained element is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#remove(Object)} method returns false when a
     * non-contained element is passed as an argument since it has no effect on
     * the value collection.
     * @test.description The {@link ValueCollection#remove(Object)} method is
     * called with a string that is not contained in the value collection on the
     * value collection created by the {@link #setUp()} method. Since the value
     * collection is empty, the method should return false, indicating that the
     * element was not contained in the value collection. Since the value
     * collection is backed by the map, the map should remain empty after the
     * operation.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#remove(Object)} method
     * returns false when a non-contained element is passed as an argument.
     */
    @Test
    public void testRemoveNonContained() {
        assertFalse("remove(Object) should return false for non-contained elements",
                values.remove("nonContainedElement"));
        assertEquals("Map should have size 0 after removing non-contained element", 0, map.size());
        assertTrue("Value collection should be empty after removing non-contained element",
                values.isEmpty());
    }

    // ValueCollection.containsAll(HCollection)

    /**
     * Tests that the {@link ValueCollection#containsAll(HCollection)} method
     * throws a NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#containsAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link ValueCollection#containsAll(HCollection)}
     * method is called with null as an argument on the value collection created
     * by the {@link #setUp()} method. Since the method does not accept null
     * collections, it should throw a NullPointerException.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The
     * {@link ValueCollection#containsAll(HCollection)} method throws
     * NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNull() {
        values.containsAll(null);
    }

    /**
     * Tests that the {@link ValueCollection#containsAll(HCollection)} method
     * returns true when an empty collection is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#containsAll(HCollection)} method returns true when
     * an empty collection is passed as an argument.
     * @test.description The {@link ValueCollection#containsAll(HCollection)}
     * method is called with an empty collection on the value collection created
     * by the {@link #setUp()} method. Since the value collection is empty, it
     * should contain all elements of the empty collection, and the method
     * should return true.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The
     * {@link ValueCollection#containsAll(HCollection)} method returns true when
     * an empty collection is passed as an argument.
     */
    @Test
    public void testContainsAllSameElements() {
        MapAdapter anotherMap = new MapAdapter();
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Value collection should contain all elements of the same value collection",
                values.containsAll(anotherValueCollection));
    }

    /**
     * Tests that the {@link ValueCollection#containsAll(HCollection)} method
     * returns false when a collection with more elements is passed as an
     * argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#containsAll(HCollection)} method returns false
     * when a collection with more elements is passed as an argument.
     * @test.description The {@link ValueCollection#containsAll(HCollection)}
     * method is called with a collection (for this test another MapAdapter's
     * ValueCollection containing two entries: "key1"->"value1" and
     * "key2"->"value2") that contains more elements than the value collection
     * created by the {@link #setUp()} method. Since the value collection is
     * empty, it should not contain all elements of the collection, and the
     * method should return false.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The
     * {@link ValueCollection#containsAll(HCollection)} method returns false
     * when a collection with more elements is passed as an argument.
     */
    @Test
    public void testContainsAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key1", "value1");
        anotherMap.put("key2", "value2");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        assertFalse("Value collection should not contain all elements of another value collection with more elements",
                values.containsAll(anotherValueCollection));
    }

    // ValueCollection.addAll(HCollection)

    /**
     * Tests that the {@link ValueCollection#addAll(HCollection)} method throws
     * an UnsupportedOperationException when called on an HCollection.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#addAll(HCollection)} method throws an
     * UnsupportedOperationException when called on an HCollection.
     * @test.description The {@link ValueCollection#addAll(HCollection)} method
     * is called with another MapAdapter's value collection on the value
     * collection created by the {@link #setUp()} method. Since the method is
     * not supported by the value collection view it should throw an
     * UnsupportedOperationException.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#addAll(HCollection)}
     * method throws an UnsupportedOperationException when called on an empty
     * value collection.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        MapAdapter anotherMap = new MapAdapter();
        values.addAll(anotherMap.values());
    }

    // ValueCollection.retainAll(HCollection)

    /**
     * Tests that the {@link ValueCollection#retainAll(HCollection)} method
     * throws a NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#retainAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link ValueCollection#retainAll(HCollection)}
     * method is called with null as an argument on the value collection created
     * by the {@link #setUp()} method. Since the method does not accept null
     * arguments, it should throw a NullPointerException.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#retainAll(HCollection)}
     * method throws a NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNull() {
        values.retainAll(null);
    }

    // ValueCollection.removeAll(HCollection)

    /**
     * Tests that the {@link ValueCollection#removeAll(HCollection)} method
     * throws a NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#removeAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link ValueCollection#removeAll(HCollection)}
     * method is called with null as an argument on the value collection created
     * by the {@link #setUp()} method. Since the method does not accept null
     * arguments, it should throw a NullPointerException.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#removeAll(HCollection)}
     * method throws a NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNull() {
        values.removeAll(null);
    }

    // ValueCollection.clear()

    /**
     * Tests that the {@link ValueCollection#clear()} method clears the value
     * collection and leaves the underlying map empty.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#clear()} method clears the value collection and
     * leaves the underlying map empty.
     * @test.description The {@link ValueCollection#clear()} method is called on
     * the value collection created by the {@link #setUp()} method. Since the
     * value collection is backed by the underlying map, clearing the value
     * collection should also clear the map.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#clear()} method clears
     * the value collection and leaves the underlying map empty.
     */
    @Test
    public void testClear() {
        // Clear the value collection
        values.clear();

        // Verify that the size is still 0
        assertEquals("Size of value collection should be 0 after clear", 0, values.size());
        assertTrue("Value collection should be empty after clear", values.isEmpty());

        // Verify that the map is still empty
        assertEquals("Map should have size 0 after clearing value collection", 0, map.size());
        assertTrue("Map should be empty after clearing value collection", map.isEmpty());
    }

    // ValueCollection.equals(Object)

    /**
     * Tests that the {@link ValueCollection#equals(Object)} method returns true
     * when an value collection with the same entries is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#equals(Object)} method returns true when an value
     * collection with the same entries is passed as an argument.
     * @test.description A new empty MapAdapter instance is created, and its
     * value collection is obtained. The {@link ValueCollection#equals(Object)}
     * method is called on the value collection created by the {@link #setUp()}
     * method the new one. Since it contains the same entries (none), the method
     * should return true.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#equals(Object)} method
     * returns true when an value collection with the same entries is passed as
     * an argument.
     */
    @Test
    public void testEqualsSameSet() {
        MapAdapter anotherMap = new MapAdapter();
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Value collection should be equal to another value collection with the same entries",
                values.equals(anotherValueCollection));
    }

    /**
     * Tests that the {@link ValueCollection#equals(Object)} method returns
     * false when an value collection with different entries is passed as an
     * argument.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#equals(Object)} method returns false when an value
     * collection with different entries is passed as an argument.
     * @test.description A new MapAdapter instance is created with an entry
     * ("key", "value"). Its value collection is obtained, and the
     * {@link ValueCollection#equals(Object)} method is called on the value
     * collection created by the {@link #setUp()} method and the new one. Since
     * it contains different entries, the method should return false.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#equals(Object)} method
     * returns false when an value collection with different entries is passed
     * as an argument.
     */
    @Test
    public void testEqualsDifferentSet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertFalse("Value collection should not be equal to another value collection with different entries",
                values.equals(anotherValueCollection));
    }

    /**
     * Tests that the {@link ValueCollection#equals(Object)} method returns
     * false when the argument is not an ValueCollection.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#equals(Object)} method returns false when the
     * argument is not an ValueCollection.
     * @test.description The {@link ValueCollection#equals(Object)} method is
     * called with a String as an argument on the value collection created by
     * the {@link #setUp()} method. Since the argument is not an
     * ValueCollection, the method should return false.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#equals(Object)} method
     * returns false when the argument is not an ValueCollection.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsNotACollection() {
        // Test that the equals method returns false when the argument is not an ValueCollection
        String notACollection = "This is not a collection";
        assertFalse("Value collection should not be equal to a non-ValueCollection object",
                values.equals(notACollection));
    }

    /**
     * Tests that the {@link ValueCollection#equals(Object)} method returns
     * false when the argument is an {@link myAdapter.HSet}.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#equals(Object)} method returns false when the
     * argument is an HSet, as collections and sets are not compatible for
     * equality.
     * @test.description The {@link ValueCollection#equals(Object)} method is
     * called with an {@link myAdapter.MapAdapter.KeySet} as an argument on the
     * value collection created by the {@link #setUp()} method. Since the
     * argument is a set, the method should return false.
     * @test.precondition The map and the value collection are correctly
     * instantiated.
     * @test.postcondition The map is still empty.
     * @test.expectedresults The {@link ValueCollection#equals(Object)} method
     * returns false when the argument is an HSet.
     */
    @Test
    public void testEqualsSet() {
        assertFalse("Value collection should not be equal to a Set object",
                values.equals(map.keySet()));
    }

    // ValueCollection.hashCode()

    /**
     * Tests that the {@link ValueCollection#hashCode()} method returns 0 for an
     * empty value collection.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#hashCode()} method returns 0 for an empty value
     * collection since it has no entries.
     * @test.description The {@link ValueCollection#hashCode()} method is called
     * on the value collection created by the {@link #setUp()} method. Since the
     * value collection is empty, the method should return 0.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#hashCode()} method
     * returns 0 for an empty value collection.
     */
    @Test
    public void testHashCode() {
        int expectedHashCode = 0; // Since the value collection is empty
        assertEquals("Value collection hash code should be " + expectedHashCode,
                expectedHashCode, values.hashCode());
    }

    /**
     * Tests that the {@link ValueCollection#hashCode()} method returns
     * different hash codes for different value collections.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#hashCode()} method returns different hash codes
     * for different value collections when one is empty and the other contains elements.
     * @test.description A new MapAdapter instance is created with an entry
     * ("key", "value"). Its value collection is obtained, and the
     * {@link ValueCollection#hashCode()} method is called on the value
     * collection created by the {@link #setUp()} method and the new one. Since
     * the value collections are different (one empty, one with elements), the 
     * method should return different hash codes.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#hashCode()} method
     * returns different hash codes for different value collections.
     */
    @Test
    public void testHashCodeDifferentSets() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // The hash codes of the two value collections should be different
        assertNotEquals("Hash codes of different value collections should not be equal",
                values.hashCode(), anotherValueCollection.hashCode());
    }

    /**
     * Tests that the {@link ValueCollection#hashCode()} method returns the same
     * hash code for the same value collection.
     *
     * @test.design The test aims to verify that the
     * {@link ValueCollection#hashCode()} method returns the same hash code for
     * the same value collection.
     * @test.description The {@link ValueCollection#hashCode()} method is called
     * on the value collection created by the {@link #setUp()} method and
     * another instance of the same value collection. Since both instances are
     * views of the same empty map, the method should return the same hash
     * code.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#hashCode()} method
     * returns the same hash code for the same value collection.
     */
    @Test
    public void testHashCodeSameSet() {
        // Create another instance of the same value collection
        ValueCollection anotherValueCollection = (ValueCollection) map.values();
        assertEquals("Hash codes of the same value collection should be equal",
                values.hashCode(), anotherValueCollection.hashCode());
    }

    /**
     * Tests that the {@link ValueCollection#equals(Object)} method implies that
     * the hash codes of the value collections are equal.
     *
     * @test.design The test aims to verify that if two value collections are
     * equal, their hash codes should also be equal.
     * @test.description The {@link ValueCollection#equals(Object)} method is
     * called on the value collection created by the {@link #setUp()} method
     * with itself as an argument. Since it is the same value collection, the
     * method should return true. The {@link ValueCollection#hashCode()} method
     * is then called on both value collections, and their hash codes should be
     * equal.
     * @test.precondition The map and the value collection are correctly
     * instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link ValueCollection#equals(Object)} method
     * implies that the hash codes of the value collections are equal.
     */
    @Test
    public void testEqualsImpliesHashCode() {
        assertEquals("Value collections with same elements should be equal",
                values, values);
        assertEquals("Hash codes of equal value collections should be equal",
                values.hashCode(), values.hashCode());
    }
}