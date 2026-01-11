package myTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.KeySet;

/**
 * Test case for KeySet when the underlying map is populated. This class tests
 * the behavior of KeySet methods when there are elements in the map.
 *
 * @test.design This test is designed to ensure that the KeySet behaves
 * correctly when the underlying map is populated, specifically checking size(),
 * isEmpty(), contains(), iterator(), toArray(), and remove() methods.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.KeySet
 */
public class KeySetPopulatedTests {

    public MapAdapter map;
    public KeySet keySet;


    /**
     * Sets up the test environment by creating a MapAdapter instance and
     * populating it with 100 entries, where keys are "key0" to "key99" and
     * values are "value0" to "value49" (values repeat every 50 keys) to ensure
     * correct behavior with duplicate values.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        keySet = (KeySet) map.keySet();
    }

    // KeySet.size()

    /**
     * Tests that the {@link KeySet#size()} method returns the correct size of
     * the set.
     *
     * @test.design The test aims to verify that the {@link KeySet#size()}
     * method returns the correct size of the set.
     * @test.description The {@link KeySet#size()} method is called on the key
     * set created by the {@link #setUp()} method. The result is asserted to be
     * equal to 100, as the map linked with key set was populated with 100
     * items.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#size()} method returns the
     * correct size of the set.
     */
    @Test
    public void testSize() {
        assertEquals("Size of the key set should be 100", 100, keySet.size());
    }

    // KeySet.isEmpty()

    /**
     * Tests that the {@link KeySet#isEmpty()} method returns false for a
     * populated key set.
     *
     * @test.design The test aims to verify that the {@link KeySet#isEmpty()}
     * method returns false for a populated key set.
     * @test.description The {@link KeySet#isEmpty()} method is called on the
     * key set created by the {@link #setUp()} method. The result is asserted to
     * be false, as the key set was populated with 100 items.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#isEmpty()} method returns false.
     */
    @Test
    public void testIsEmpty() {
        assertFalse("Key set should not be empty", keySet.isEmpty());
    }

    // KeySet.contains(Object)

    /**
     * Tests that the {@link KeySet#contains(Object)} method throws a
     * NullPointerException when called with null.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#contains(Object)} method throws a NullPointerException
     * when called with a null argument.
     * @test.description The {@link KeySet#contains(Object)} method is called
     * with a null argument. The result is expected to be a NullPointerException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#contains(Object)} method throws
     * a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNull() {
        keySet.contains(null);
    }

    /**
     * Tests that the {@link KeySet#contains(Object)} method returns false for a
     * non-contained element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#contains(Object)} method returns false for a non-contained
     * element.
     * @test.description The {@link KeySet#contains(Object)} method is called
     * with a key that is not present in the key set. The result is asserted to
     * be false.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#contains(Object)} method returns
     * false.
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

    /**
     * Tests that the {@link KeySet#contains(Object)} method returns true for
     * the first element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#contains(Object)} method correctly identifies the presence
     * of the first element.
     * @test.description The {@link KeySet#contains(Object)} method is called
     * with the first key from another key set. The result is asserted to be
     * true.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#contains(Object)} method returns
     * true.
     */
    @Test
    public void testContainsContainedElementStart() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        Object key = anotherKeySet.iterator().next();
        assertTrue("Key set should contain the first element",
                keySet.contains(key));
    }

    /**
     * Tests that the {@link KeySet#contains(Object)} method returns true for a
     * middle element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#contains(Object)} method correctly identifies the presence
     * of a middle element.
     * @test.description The {@link KeySet#contains(Object)} method is called
     * with a middle key from another key set. The result is asserted to be
     * true.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#contains(Object)} method returns
     * true.
     */
    @Test
    public void testContainsContainedElementMiddle() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key50", "value50");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        Object key = anotherKeySet.iterator().next();
        assertTrue("Key set should contain a middle element",
                keySet.contains(key));
    }

    /**
     * Tests that the {@link KeySet#contains(Object)} method returns true for
     * the last element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#contains(Object)} method correctly identifies the presence
     * of the last element.
     * @test.description The {@link KeySet#contains(Object)} method is called
     * with the last key from another key set. The result is asserted to be
     * true.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#contains(Object)} method returns
     * true.
     */
    @Test
    public void testContainsContainedElementEnd() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key99", "value99");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        Object key = anotherKeySet.iterator().next();
        assertTrue("Key set should contain the last element",
                keySet.contains(key));
    }

    // KeySet.iterator()


    /**
     * Tests that the {@link KeySet#iterator()} method does not return null.
     *
     * @test.design The test aims to verify that the {@link KeySet#iterator()}
     * method returns a non-null iterator.
     * @test.description The {@link KeySet#iterator()} method is called on the
     * key set created by the {@link #setUp()} method. The result is asserted to
     * be non-null.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#iterator()} method returns a
     * non-null iterator.
     */
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of an key set should not be null",
                keySet.iterator());
    }

    // KeySet.toArray()


    /**
     * Tests that the {@link KeySet#toArray()} method returns a non-null array
     * of the correct size.
     *
     * @test.design The test aims to verify that the {@link KeySet#toArray()}
     * method returns a non-null array containing all keys.
     * @test.description The {@link KeySet#toArray()} method is called on the
     * key set created by the {@link #setUp()} method. The result is asserted to
     * be non-null and of size 100.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#toArray()} method returns a
     * non-null array of size 100.
     */
    @Test
    public void testToArray() {
        Object[] array = keySet.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an array with all elements from the populated key set",
                100, array.length);
    }

    // KeySet.toArray(T[])


    /**
     * Tests that the {@link KeySet#toArray(T[])} method throws a
     * NullPointerException when passed a null array.
     *
     * @test.design The test aims to verify that the {@link KeySet#toArray(T[])}
     * method throws a NullPointerException when passed a null array.
     * @test.description The {@link KeySet#toArray(T[])} method is called with a
     * null argument. The result is expected to be a NullPointerException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged
     * @test.expectedresults The {@link KeySet#toArray(T[])} method throws a
     * NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullArray() {
        keySet.toArray(null);
    }

    /**
     * Tests that the {@link KeySet#toArray(T[])} method works correctly when
     * passed a smaller array.
     *
     * @test.design The test aims to verify that the {@link KeySet#toArray(T[])}
     * method returns a new array containing all keys when the provided array is
     * smaller.
     * @test.description The {@link KeySet#toArray(T[])} method is called with a
     * smaller array. The result is asserted to be a new array of size 100.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#toArray(T[])} method returns a
     * new array of size 100.
     */
    @Test
    public void testToArrayWithSmallerArray() {
        Object[] array = new Object[5];
        Object[] result = keySet.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return a new array containing all the keys in the key set",
                100, result.length);
        assertNotSame("toArray(T[]) should return a new array, not the one passed as argument",
                array, result);
    }

    /**
     * Tests that the {@link KeySet#toArray(T[])} method works correctly when
     * passed a larger array.
     *
     * @test.design The test aims to verify that the {@link KeySet#toArray(T[])}
     * method reuses the provided array when it is larger than the key set
     * size.
     * @test.description The {@link KeySet#toArray(T[])} method is called with a
     * larger array. The result is asserted to be the same array with a null
     * terminator at position 100.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#toArray(T[])} method reuses the
     * provided array and adds a null terminator.
     */
    @Test
    public void testToArrayWithLargerArray() {
        Object[] array = new Object[200];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = keySet.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return an array with all elements from the populated key set",
                200, result.length);
        assertSame("toArray(T[]) should return the same array passed as argument",
                array, result);
        assertNull("toArray(T[]) should have a null terminator at position 100", result[100]);
        assertEquals("The 101st element should not be modified",
                "flag-101", result[101]);
    }

    // KeySet.add(Object)


    /**
     * Tests that the {@link KeySet#add(Object)} method throws an
     * UnsupportedOperationException.
     *
     * @test.design The test aims to verify that the {@link KeySet#add(Object)}
     * method is not supported and throws an exception.
     * @test.description The {@link KeySet#add(Object)} method is called with a
     * sample object. The result is expected to be an
     * UnsupportedOperationException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#add(Object)} method throws an
     * UnsupportedOperationException.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        keySet.add("baguette");
    }

    // KeySet.remove(Object)


    /**
     * Tests that the {@link KeySet#remove(Object)} method throws a
     * NullPointerException when passed a null argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#remove(Object)} method throws a NullPointerException when
     * called with a null argument.
     * @test.description The {@link KeySet#remove(Object)} method is called with
     * a null argument. The result is expected to be a NullPointerException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#remove(Object)} method throws a
     * NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        keySet.remove(null);
    }

    /**
     * Tests that the {@link KeySet#remove(Object)} method returns false for a
     * non-contained element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#remove(Object)} method behaves correctly when attempting to
     * remove an element that is not present in the key set.
     * @test.description The {@link KeySet#remove(Object)} method is called with
     * a non-contained element. The result is expected to be false.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#remove(Object)} method returns
     * false.
     */
    @Test
    public void testRemoveNonContained() {
        assertFalse("remove(Object) should return false for non-contained elements",
                keySet.remove("nonContainedElement"));
        assertEquals("Map should have size 100 after removing non-contained element", 100, map.size());
        assertFalse("Key set shouldn't be empty after removing non-contained element",
                keySet.isEmpty());
    }

    /**
     * Tests that the {@link KeySet#remove(Object)} method returns true for a
     * contained element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#remove(Object)} method correctly removes an element that is
     * present in the key set.
     * @test.description The {@link KeySet#remove(Object)} method is called with
     * a contained element. The result is expected to be true, and the key set
     * should no longer contain the element.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is changed to no longer contain the removed element.
     * @test.expectedresults The {@link KeySet#remove(Object)} method returns
     * true and the key set no longer contains the removed element.
     */
    @Test
    public void testRemoveContainedStart() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        Object key = anotherKeySet.iterator().next();

        assertTrue("remove(Object) should return true for contained elements",
                keySet.remove(key));
        assertFalse("Key set should not contain the removed element",
                keySet.contains(key));
        assertEquals("Map should have size 99 after removing an element", 99, map.size());
    }

    /**
     * Tests that the {@link KeySet#remove(Object)} method returns true for a
     * middle element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#remove(Object)} method correctly removes a middle element
     * that is present in the key set.
     * @test.description The {@link KeySet#remove(Object)} method is called with
     * a middle element. The result is expected to be true, and the key set
     * should no longer contain the element.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is changed to no longer contain the removed element.
     * @test.expectedresults The {@link KeySet#remove(Object)} method returns
     * true and the key set no longer contains the removed element.
     */
    @Test
    public void testRemoveContainedMiddle() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key50", "value50");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        Object key = anotherKeySet.iterator().next();

        assertTrue("remove(Object) should return true for contained elements",
                keySet.remove(key));
        assertFalse("Key set should not contain the removed element",
                keySet.contains(key));
        assertEquals("Map should have size 99 after removing an element", 99, map.size());
    }

    /**
     * Tests that the {@link KeySet#remove(Object)} method returns true for the
     * last element.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#remove(Object)} method correctly removes the last element
     * that is present in the key set.
     * @test.description The {@link KeySet#remove(Object)} method is called with
     * the last element. The result is expected to be true, and the key set
     * should no longer contain the element.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is changed to no longer contain the removed element.
     * @test.expectedresults The {@link KeySet#remove(Object)} method returns
     * true and the key set no longer contains the removed element.
     */
    @Test
    public void testRemoveContainedEnd() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key99", "value99");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        Object key = anotherKeySet.iterator().next();

        assertTrue("remove(Object) should return true for contained elements",
                keySet.remove(key));
        assertFalse("Key set should not contain the removed element",
                keySet.contains(key));
        assertEquals("Map should have size 99 after removing an element", 99, map.size());
    }

    // KeySet.containsAll(HCollection)

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method throws a
     * NullPointerException when passed a null argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method throws a
     * NullPointerException when called with a null argument.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with a null argument. The result is expected to be a
     * NullPointerException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * throws a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNull() {
        keySet.containsAll(null);
    }

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method returns
     * true for a key set with the same elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method correctly identifies that
     * it contains all elements of another key set with the same elements.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with another key set that has the same elements. The result is
     * expected to be true.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * returns true.
     */
    @Test
    public void testContainsAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value50");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertTrue("Key set should contain all elements of another key set with some elements",
                keySet.containsAll(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method returns
     * true for a key set with the same elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method correctly identifies that
     * it contains all elements of another key set with the same elements.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with an empty key set. The result is expected to be true.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * returns true.
     */
    @Test
    public void testContainsAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertTrue("Key set should contain all elements of the same key set",
                keySet.containsAll(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method returns
     * false for a key set with more elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method correctly identifies that
     * it does not contain all elements of another key set with more elements.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with another key set that has more elements than the original key
     * set. The result is expected to be false.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * returns false.
     */
    @Test
    public void testContainsAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();
        assertFalse("Key set should not contain all elements of another key set with more elements",
                keySet.containsAll(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#containsAll(HCollection)} method returns
     * false for a key set with no common elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#containsAll(HCollection)} method correctly identifies that
     * it does not contain any elements of another key set with no common
     * elements.
     * @test.description The {@link KeySet#containsAll(HCollection)} method is
     * called with another key set that has no common elements. The result is
     * expected to be false.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#containsAll(HCollection)} method
     * returns false.
     */
    @Test
    public void testContainsAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertFalse("Key set should not contain all elements of another key set with no common elements",
                keySet.containsAll(anotherKeySet));
    }

    // KeySet.addAll(HCollection)

    /**
     * Tests that the {@link KeySet#addAll(HCollection)} method throws an
     * UnsupportedOperationException.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#addAll(HCollection)} method is not supported and throws an
     * exception.
     * @test.description The {@link KeySet#addAll(HCollection)} method is called
     * with another key set. The result is expected to be an
     * UnsupportedOperationException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#addAll(HCollection)} method
     * throws an UnsupportedOperationException.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        MapAdapter anotherMap = new MapAdapter();
        keySet.addAll(anotherMap.keySet());
    }

    // KeySet.retainAll(HCollection)

    /**
     * Tests that the {@link KeySet#retainAll(HCollection)} method throws a
     * NullPointerException when passed a null argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#retainAll(HCollection)} method throws a
     * NullPointerException when called with a null argument.
     * @test.description The {@link KeySet#retainAll(HCollection)} method is
     * called with a null argument. The result is expected to be a
     * NullPointerException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#retainAll(HCollection)} method
     * throws a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNull() {
        keySet.retainAll(null);
    }

    /**
     * Tests that the {@link KeySet#retainAll(HCollection)} method returns false
     * for a key set with the same elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#retainAll(HCollection)} method correctly identifies that it
     * retains all elements of another key set with the same elements.
     * @test.description The {@link KeySet#retainAll(HCollection)} method is
     * called with another key set that has the same elements. The result is
     * expected to be false, indicating no change.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#retainAll(HCollection)} method
     * returns false.
     */
    @Test
    public void testRetainAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Retain all elements that are in the same key set
        assertFalse("RetainAll should have no effect with same elements",
                keySet.retainAll(anotherKeySet));

        // Verify that the key set is unchanged
        assertEquals("Size of key set should remain 100 after retainAll with same elements",
                100, keySet.size());
    }

    /**
     * Tests that the {@link KeySet#retainAll(HCollection)} method retains some
     * elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#retainAll(HCollection)} method correctly retains only the
     * elements that are present in another key set with some elements.
     * @test.description The {@link KeySet#retainAll(HCollection)} method is
     * called with another key set that has some common elements. The result is
     * expected to be true, indicating a change.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is changed to retain only the common
     * elements.
     * @test.expectedresults The {@link KeySet#retainAll(HCollection)} method
     * returns true and the size is reduced.
     */
    @Test
    public void testRetainAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value50");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Retain only the elements that are in the another key set
        assertTrue("RetainAll should return true for some elements",
                keySet.retainAll(anotherKeySet));

        // Verify that the size is reduced to 2
        assertEquals("Size of key set should be 2 after retainAll with some elements",
                2, keySet.size());
    }

    /**
     * Tests that the {@link KeySet#retainAll(HCollection)} method retains more
     * elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#retainAll(HCollection)} method correctly retains only the
     * elements that are present in another key set with more elements.
     * @test.description The {@link KeySet#retainAll(HCollection)} method is
     * called with another key set that has more elements than the original key
     * set. The result is expected to be false, indicating no change.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#retainAll(HCollection)} method
     * returns false.
     */
    @Test
    public void testRetainAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Retain only the elements that are in the another key set
        assertFalse("RetainAll should return false for more elements",
                keySet.retainAll(anotherKeySet));

        // Verify that the size remains unchanged
        assertEquals("Size of key set should remain 100 after retainAll with more elements",
                100, keySet.size());
    }

    /**
     * Tests that the {@link KeySet#retainAll(HCollection)} method returns false
     * for a key set with no common elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#retainAll(HCollection)} method correctly identifies that it
     * retains no elements of another key set with no common elements.
     * @test.description The {@link KeySet#retainAll(HCollection)} method is
     * called with another key set that has no common elements. The result is
     * expected to be false, indicating no change.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#retainAll(HCollection)} method
     * returns false.
     */
    @Test
    public void testRetainAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Retain only the elements that are in the another key set
        assertTrue("RetainAll should return true for no common elements",
                keySet.retainAll(anotherKeySet));

        // Verify that the size is reduced to 0
        assertEquals("Size of key set should be 0 after retainAll with no common elements",
                0, keySet.size());
    }

    // KeySet.removeAll(HCollection)

    /**
     * Tests that the {@link KeySet#removeAll(HCollection)} method throws a
     * NullPointerException when passed a null argument.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#removeAll(HCollection)} method throws a
     * NullPointerException when called with a null argument.
     * @test.description The {@link KeySet#removeAll(HCollection)} method is
     * called with a null argument. The result is expected to be a
     * NullPointerException.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#removeAll(HCollection)} method
     * throws a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNull() {
        keySet.removeAll(null);
    }

    /**
     * Tests that the {@link KeySet#removeAll(HCollection)} method returns true
     * for a key set with the same elements and removes all elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#removeAll(HCollection)} method correctly removes all
     * elements when called with another key set with the same elements.
     * @test.description The {@link KeySet#removeAll(HCollection)} method is
     * called with another key set that has the same elements. The result is
     * expected to be true, indicating all elements were removed.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is empty.
     * @test.expectedresults The {@link KeySet#removeAll(HCollection)} method
     * returns true and all elements are removed.
     */
    @Test
    public void testRemoveAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Remove all elements that are in the same key set
        assertTrue("Set should have changed after removeAll with same elements",
                keySet.removeAll(anotherKeySet));

        // Verify that the key set is now empty
        assertEquals("Size should be 0 after removeAll with same elements",
                0, keySet.size());
    }

    /**
     * Tests that the {@link KeySet#removeAll(HCollection)} method removes some
     * elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#removeAll(HCollection)} method correctly removes only the
     * elements that are present in another key set with some elements.
     * @test.description The {@link KeySet#removeAll(HCollection)} method is
     * called with another key set that has some common elements. The result is
     * expected to be true, indicating a change.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is changed to remove only the common
     * elements.
     * @test.expectedresults The {@link KeySet#removeAll(HCollection)} method
     * returns true and the size is reduced.
     */
    @Test
    public void testRemoveAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value50");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Remove only the elements that are in the another key set
        assertTrue("RemoveAll should return true for some elements",
                keySet.removeAll(anotherKeySet));

        // Verify that the size is reduced to 98
        assertEquals("Size of key set should be 98 after removeAll with some elements",
                98, keySet.size());
    }

    /**
     * Tests that the {@link KeySet#removeAll(HCollection)} method removes more
     * elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#removeAll(HCollection)} method correctly removes only the
     * elements that are present in another key set with more elements.
     * @test.description The {@link KeySet#removeAll(HCollection)} method is
     * called with another key set that has more elements than the original key
     * set. The result is expected to be true, indicating a change.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is changed to remove only the common
     * elements.
     * @test.expectedresults The {@link KeySet#removeAll(HCollection)} method
     * returns true and the size is reduced.
     */
    @Test
    public void testRemoveAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Remove only the elements that are in the another key set
        assertTrue("RemoveAll should return true for more elements",
                keySet.removeAll(anotherKeySet));

        // Verify that all elements are removed (since the other set contains all elements from 0-199)
        assertEquals("Size of key set should be 0 after removeAll with more elements",
                0, keySet.size());
    }

    /**
     * Tests that the {@link KeySet#removeAll(HCollection)} method returns false
     * for a key set with no common elements.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#removeAll(HCollection)} method correctly identifies that it
     * does not remove any elements of another key set with no common elements.
     * @test.description The {@link KeySet#removeAll(HCollection)} method is
     * called with another key set that has no common elements. The result is
     * expected to be false, indicating no change.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#removeAll(HCollection)} method
     * returns false.
     */
    @Test
    public void testRemoveAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        // Remove only the elements that are in the another key set
        assertFalse("RemoveAll should return false for no common elements",
                keySet.removeAll(anotherKeySet));

        // Verify that the size remains unchanged (no common elements to remove)
        assertEquals("Size of key set should remain 100 after removeAll with no common elements",
                100, keySet.size());
    }

    // KeySet.clear()


    /**
     * Tests that the {@link KeySet#clear()} method removes all elements from
     * the key set.
     *
     * @test.design The test aims to verify that the {@link KeySet#clear()}
     * method removes all elements from the key set and the associated map.
     * @test.description The {@link KeySet#clear()} method is called on the key
     * set. The result is asserted to be an empty key set and map.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set and map are empty.
     * @test.expectedresults The {@link KeySet#clear()} method clears all
     * elements from the key set and map.
     */
    @Test
    public void testClear() {
        // Clear the key set
        keySet.clear();

        // Verify that the size becomes 0
        assertEquals("Size of key set should be 0 after clear", 0, keySet.size());
        assertTrue("Key set should be empty after clear", keySet.isEmpty());

        // Verify that the map is also empty
        assertEquals("Map should have size 0 after clearing key set", 0, map.size());
        assertTrue("Map should be empty after clearing key set", map.isEmpty());
    }

    // KeySet.equals(Object)

    /**
     * Tests that the {@link KeySet#equals(Object)} method returns true for a
     * key set with the same keys.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#equals(Object)} method correctly identifies that it is
     * equal to another key set with the same keys.
     * @test.description The {@link KeySet#equals(Object)} method is called with
     * another key set that has the same keys. The result is expected to be
     * true.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#equals(Object)} method returns
     * true.
     */
    @Test
    public void testEqualsSameSet() {
        MapAdapter anotherMap = new MapAdapter(map);
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertTrue("Key set should be equal to another key set with the same keys",
                keySet.equals(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#equals(Object)} method returns false for a
     * key set with different keys.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#equals(Object)} method correctly identifies that it is not
     * equal to another key set with different keys.
     * @test.description The {@link KeySet#equals(Object)} method is called with
     * another key set that has different keys. The result is expected to be
     * false.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#equals(Object)} method returns
     * false.
     */
    @Test
    public void testEqualsDifferentSet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertFalse("Key set should not be equal to another key set with different keys",
                keySet.equals(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#equals(Object)} method returns false for a
     * key set with different keys of the same size.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#equals(Object)} method correctly identifies that it is not
     * equal to another key set with different keys of the same size.
     * @test.description The {@link KeySet#equals(Object)} method is called with
     * another key set that has different keys but the same number of elements.
     * The result is expected to be false.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#equals(Object)} method returns
     * false.
     */
    @Test
    public void testEqualsDifferentSetSameSize() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            anotherMap.put("key" + (i + 1), "value" + (i + 1)); // Different values
        }
        KeySet anotherKeySet = (KeySet) anotherMap.keySet();

        assertFalse("Key set should not be equal to another key set with different keys",
                keySet.equals(anotherKeySet));
    }

    /**
     * Tests that the {@link KeySet#equals(Object)} method returns false when
     * the argument is not a KeySet.
     *
     * @test.design The test aims to verify that the
     * {@link KeySet#equals(Object)} method correctly identifies that it is not
     * equal to an object that is not a KeySet.
     * @test.description The {@link KeySet#equals(Object)} method is called with
     * an object that is not a KeySet. The result is expected to be false.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#equals(Object)} method returns
     * false.
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
     * Tests that the {@link KeySet#hashCode()} method returns the same hash
     * code for two key sets with the same keys.
     *
     * @test.design The test aims to verify that the {@link KeySet#hashCode()}
     * method returns the same hash code for two key sets with the same keys.
     * @test.description The {@link KeySet#hashCode()} method is called on two
     * key sets with the same keys. The result is expected to be equal.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#hashCode()} method returns the
     * same hash code for both key sets.
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
     * code for two key sets with the same keys.
     *
     * @test.design The test aims to verify that the {@link KeySet#hashCode()}
     * method returns the same hash code for two key sets with the same keys.
     * @test.description The {@link KeySet#hashCode()} method is called on two
     * key sets with the same keys. The result is expected to be equal.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A key set is correctly instantiated from the map.
     * @test.postcondition The key set is unchanged.
     * @test.expectedresults The {@link KeySet#hashCode()} method returns the
     * same hash code for both key sets.
     */
    @Test
    public void testHashCodeSameSet() {
        // Create another instance of the same key set
        KeySet anotherKeySet = (KeySet) map.keySet();
        assertEquals("Hash codes of the same key set should be equal",
                keySet.hashCode(), anotherKeySet.hashCode());
    }

    /**
     * Tests that the {@link MapAdapter#keySet()} {@code retainAll} method
     * handles a collection containing null elements safely (by treating them as not present).
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#keySet()} {@code retainAll} method does not throw
     * exception when called with a collection containing
     * null elements.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null key. The {@code retainAll} method is then called.
     * Since the null key matches nothing in the map, all elements
     * should be removed.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null keys.
     * @test.postcondition The map is empty.
     * @test.expectedresults The {@code retainAll} method completes without exception
     * and empties the map.
     */
    @Test
    public void testKeySetRetainAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put(null, "value");
        map.keySet().retainAll(nullableMap.keySet());
        assertTrue("Map should be empty after retaining only null keys", map.isEmpty());
    }

    /**
     * Tests that the {@link MapAdapter#keySet()} {@code removeAll} method throws
     * {@link NullPointerException} when called with a collection containing null elements.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#keySet()} {@code removeAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements, as null elements are not supported by the MapAdapter.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null key. The {@code removeAll} method is then called
     * on the key set of the populated map with the key set of the NullableHMap
     * as argument. The result is asserted to throw a {@link NullPointerException},
     * as null elements are not supported.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null keys.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@code removeAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements.
     */
    @Test(expected = NullPointerException.class)
    public void testKeySetRemoveAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put(null, "value");
        map.keySet().removeAll(nullableMap.keySet());
    }

    /**
     * Tests that the {@link MapAdapter#keySet()} {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing null elements.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#keySet()} {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements, as null elements are not supported by the MapAdapter.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null key. The {@code containsAll} method is then called
     * on the key set of the populated map with the key set of the NullableHMap
     * as argument. The result is asserted to throw a {@link NullPointerException},
     * as null elements are not supported.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null keys.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements.
     */
    @Test(expected = NullPointerException.class)
    public void testKeySetContainsAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put(null, "value");
        map.keySet().containsAll(nullableMap.keySet());
    }

}