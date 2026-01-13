package myTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.HMap;
import myAdapter.MapAdapter;
import myAdapter.MapAdapter.Entry;
import myAdapter.MapAdapter.EntrySet;
import myAdapter.MapAdapter.ValueCollection;

/**
 * This test case tests the EntrySet class instances linked to an empty map.
 * Every method of the EntrySet class is tested to ensure the class
 * implementation serves correctly as a view for empty maps. For each method
 * tests are created to ensure correct behavior in normal and edge cases,
 * including correct throwing of exceptions and correct return values and side
 * effects on the underlying map.
 *
 * @test.design This test case aims to verify the correct behavior of empty
 * entry set instances to ensure it correctly serves as a set view for empty maps
 * and implements correctly the {@link myAdapter.HSet} interface.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.EntrySet
 * @see myAdapter.MapAdapter.Entry
 */
public class EntrySetEmptyTests {

    public MapAdapter map;
    public EntrySet entrySet;

    /**
     * Sets up the test environment by initializing an empty MapAdapter through
     * the MapAdapter's default constructor and creating an EntrySet instance
     * obtaining its entry set view.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        entrySet = (EntrySet) map.entrySet();
    }

    /**
     * Cleans up the test environment by setting the map and entrySet references
     * to null, allowing for garbage collection.
     */
    @After
    public void tearDown() {
        map = null;
        entrySet = null;
    }

    // EntrySet.size()

    /**
     * Tests that the {@link EntrySet#size()} method returns 0 for an empty
     * map.
     *
     * @test.design The test aims to verify that the {@link EntrySet#size()}
     * method returns 0 for an empty map.
     * @test.description The {@link EntrySet#size()} method is called on the set
     * view created by the {@link #setUp()} method. Since the linked map is
     * empty, the method should return 0.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#size()} method returns 0 for an
     * empty map.
     */
    @Test
    public void testSize() {
        assertEquals("Size of an empty entry set should be 0", 0, entrySet.size());
    }

    // EntrySet.isEmpty()

    /**
     * Tests that the {@link EntrySet#isEmpty()} method returns true for an
     * empty map.
     *
     * @test.design The test aims to verify that the {@link EntrySet#isEmpty()}
     * method returns true for an empty map.
     * @test.description The {@link EntrySet#isEmpty()} method is called on the
     * set view created by the {@link #setUp()} method. Since the linked map is
     * empty, the method should return true.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#isEmpty()} method returns true
     * for an empty map.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("Entry set should be empty", entrySet.isEmpty());
    }

    // EntrySet.contains(Object)

    /**
     * Tests that the {@link EntrySet#contains(Object)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#contains(Object)} method throws a NullPointerException
     * when null is passed as an argument as not supported by the view.
     * @test.description The {@link EntrySet#contains(Object)} method is called
     * with null as an argument on the entry set. Since the underlying map does
     * not support null keys, it should throw a NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#contains(Object)} method throws
     * NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNull() {
        entrySet.contains(null);
    }

    /**
     * Tests that the {@link EntrySet#contains(Object)} method returns false
     * when a non-contained element is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#contains(Object)} method returns false when a
     * non-contained element is passed as an argument.
     * @test.description To create a non-contained element, a new MapAdapter
     * instance is created, and an entry is added to it. The entry is then
     * extracted using the iterator of the entry set view of the new map. The
     * {@link EntrySet#contains(Object)} method is called with this entry on the
     * original entry set. Since the original map is empty thus the new entry is
     * not contained in it, the method should return false.
     * @test.precondition The map and the entry set are correctly instantiated.
     * @test.postcondition The map is unchanged.
     * @test.expectedresults The {@link EntrySet#contains(Object)} method
     * returns false when a non-contained element is passed as an argument.
     */
    @Test
    public void testContainsNotContainedElement() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");

        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Entry entry = (Entry) anotherEntrySet.iterator().next();

        assertFalse("Entry set should not contain non-contained element", entrySet.contains(entry));
    }

    // EntrySet.iterator()

    /**
     * Tests that the {@link EntrySet#iterator()} method returns a non-null
     * iterator for an empty entry set.
     *
     * @test.design The test aims to verify that the {@link EntrySet#iterator()}
     * method returns a non-null iterator for an empty entry set.
     * @test.description The {@link EntrySet#iterator()} method is called on the
     * entry set created by the {@link #setUp()} method. Since the entry set is
     * empty, the method should return a non-null iterator that iterates over no
     * elements.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#iterator()} method returns a
     * non-null iterator for an empty entry set.
     */
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of an empty entry set should not be null", entrySet.iterator());
    }

    // EntrySet.toArray()

    /**
     * Tests that the {@link EntrySet#toArray()} method returns an empty array
     * for an empty entry set.
     *
     * @test.design The test aims to verify that the {@link EntrySet#toArray()}
     * method returns an empty array for an empty entry set.
     * @test.description The {@link EntrySet#toArray()} method is called on the
     * entry set created by the {@link #setUp()} method. Since the entry set is
     * empty, the method should return an empty array with a length of 0.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#toArray()} method returns an
     * empty array with length 0 for an empty entry set.
     */
    @Test
    public void testToArray() {
        Object[] array = entrySet.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an empty array for an empty entry set", 0, array.length);
    }

    // EntrySet.toArray(T[])

    /**
     * Tests that the {@link EntrySet#toArray(Object[])} method throws a
     * {@link NullPointerException} when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#toArray(Object[])} method throws a NullPointerException
     * when null is passed as an argument.
     * @test.description The {@link EntrySet#toArray(Object[])} method is called
     * with null as an argument on the entry set created by the {@link #setUp()}
     * method. Since the method does not support null arrays, it should throw a
     * NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#toArray(Object[])} method
     * throws {@link NullPointerException} when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullArray() {
        entrySet.toArray(null);
    }

    /**
     * Tests that the {@link EntrySet#toArray(Object[])} method returns the
     * bigger array when a bigger array is passed as an argument. The method
     * should preserve the elements of the passed array except for the one
     * overwritten by the null terminator.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#toArray(Object[])} method returns the bigger array when a
     * bigger array is passed as an argument. The method should preserve the
     * elements of the passed array except for the one overwritten by the null
     * terminator.
     * @test.description The {@link EntrySet#toArray(Object[])} method is called
     * with a bigger array than the entry set size on the entry set created by
     * the {@link #setUp()} method. Since the entry set is empty, the method
     * should return the bigger passed array with a null terminator at position 0
     * and preserve the remaining elements.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#toArray(Object[])} method
     * returns the array of size 5 with a null terminator at position 0 and
     * preserves the elements of the passed array at positions 1 to 4.
     */
    @Test
    public void testToArrayWithBiggerArray() {
        Object[] array = new Object[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = entrySet.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return the same array with size 5", 5, result.length);
        assertSame("toArray(T[]) should return the same array passed as argument", array, result);
        assertNull("toArray(T[]) should have a null terminator at position 0", result[0]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 1", "flag-1", result[1]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 2", "flag-2", result[2]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 3", "flag-3", result[3]);
        assertEquals("toArray(T[]) should not overwrite the array element at index 4", "flag-4", result[4]);
    }

    // EntrySet.add(Object)

    /**
     * Tests that the {@link EntrySet#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty entry set.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty entry set.
     * @test.description The {@link EntrySet#add(Object)} method is called with
     * a string as an argument on the entry set created by the {@link #setUp()}
     * method. Since the entry set is a view of an empty map, the method should
     * throw an UnsupportedOperationException as adding elements to an entry set
     * is not allowed.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty entry set.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        entrySet.add("baguette");
    }

    // EntrySet.remove(Object)

    /**
     * Tests that the {@link EntrySet#remove(Object)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#remove(Object)} method throws a NullPointerException when
     * null is passed as an argument.
     * @test.description The {@link EntrySet#remove(Object)} method is called
     * with null as an argument on the entry set created by the {@link #setUp()}
     * method. Since the set does not support null keys, it should throw a
     * NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#remove(Object)} method throws
     * NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        entrySet.remove(null);
    }

    /**
     * Tests that the {@link EntrySet#remove(Object)} method throws
     * ClassCastException when called on a non HMap.HEntry object.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#remove(Object)} fails with ClassCastException when the
     * passed element is not an entry.
     * @test.description The {@link EntrySet#remove(Object)} method is called
     * with a string on the entry set created by the {@link #setUp()} method.
     * Since the {@link EntrySet} is designed to contain only {@link HMap.HEntry}
     * the {@link EntrySet#remove(Object)} should result in a 
     * {@link ClassCastException} which is asserted to have been thrown.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#remove(Object)} method throws
     * {@link ClassCastException} when called on a non-{@link HMap.HEntry} object.
     */
    @Test(expected = ClassCastException.class)
    public void testRemoveNonContainedNotAnEntry() {
        assertFalse("remove(Object) should return false for non-contained elements", entrySet.remove("nonContainedElement"));
        assertEquals("Map should have size 0 after removing non-contained element", 0, map.size());
        assertTrue("Entry set should be empty after removing non-contained element", entrySet.isEmpty());
    }

    /**
     * Tests that the {@link EntrySet#remove(Object)} method returns false when
     * a non-contained entry is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#remove(Object)} method returns false when a non-contained
     * entry is passed as an argument.
     * @test.description The {@link EntrySet#remove(Object)} method is called
     * with an entry that is not contained in the entry set on the entry set
     * created by the {@link #setUp()} method. Since the entry set is empty, the
     * method should return false, indicating that the entry was not contained
     * in the entry set. Since the entry set is backed by the map, the map
     * should remain empty after the operation.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map and entry set are unchanged
     * @test.expectedresults The {@link EntrySet#remove(Object)} method returns
     * false when a non-contained entry is passed as an argument.
     */
    @Test
    public void testRemoveNonContainedEntry() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");

        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Entry entry = (Entry) anotherEntrySet.iterator().next();

        assertFalse("remove(Object) should return false for non-contained entries", entrySet.remove(entry));
        assertEquals("Map should have size 0 after removing non-contained entry", 0, map.size());
        assertTrue("Entry set should be empty after removing non-contained entry", entrySet.isEmpty());
    }

    // EntrySet.containsAll(HCollection)

    /**
     * Tests that the {@link EntrySet#containsAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#containsAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link EntrySet#containsAll(HCollection)} method is
     * called with null as an argument on the entry set created by the
     * {@link #setUp()} method. Since the method does not accept null
     * collections, it should throw a NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map and the entry set are unchanged
     * @test.expectedresults The {@link EntrySet#containsAll(HCollection)}
     * method throws NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNull() {
        entrySet.containsAll(null);
    }

    /**
     * Tests that the {@link EntrySet#containsAll(HCollection)} method returns
     * true when called on a collection with the same elements as the entry set.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#containsAll(HCollection)} method returns true when called
     * on a collection with the same elements as the entry set.
     * @test.description The {@link EntrySet#containsAll(HCollection)} method is
     * called with another MapAdapter's entry set on the entry set created by
     * the {@link #setUp()} method. Since the entry set is empty, it should
     * contain all elements of the other entry set, and the method should return
     * true.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#containsAll(HCollection)}
     * method returns true when called on a collection with the same elements as the
     * entry set.
     */
    @Test
    public void testContainsAllSameElements() {
        MapAdapter anotherMap = new MapAdapter();
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertTrue("Entry set should contain all elements of the same entry set", entrySet.containsAll(anotherEntrySet));
    }

    /**
     * Tests that the {@link EntrySet#containsAll(HCollection)} method returns
     * false when a collection with more elements is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#containsAll(HCollection)} method returns false when a
     * collection with more elements is passed as an argument.
     * @test.description The {@link EntrySet#containsAll(HCollection)} method is
     * called with a collection (for this test another MapAdapter's EntrySet
     * containing two entries: "key1"->"value1" and "key2"->"value2") that
     * contains more elements than the entry set created by the {@link #setUp()}
     * method. Since the entry set is empty, it should not contain all elements
     * of the collection, and the method should return false.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#containsAll(HCollection)}
     * method returns false when a collection with more elements is passed as an
     * argument.
     */
    @Test
    public void testContainsAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key1", "value1");
        anotherMap.put("key2", "value2");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        assertFalse("Entry set should not contain all elements of another entry set with more elements", entrySet.containsAll(anotherEntrySet));
    }

    // EntrySet.addAll(HCollection)

    /**
     * Tests that the {@link EntrySet#addAll(HCollection)} method throws an
     * UnsupportedOperationException when called on an HCollection.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#addAll(HCollection)} method throws an
     * UnsupportedOperationException when called on an HCollection.
     * @test.description The {@link EntrySet#addAll(HCollection)} method is
     * called with another MapAdapter's entry set on the entry set created by
     * the {@link #setUp()} method. Since the method is not supported by the
     * entry set view it should throw an UnsupportedOperationException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#addAll(HCollection)} method
     * throws an UnsupportedOperationException when called on an empty entry
     * set.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        MapAdapter anotherMap = new MapAdapter();
        entrySet.addAll(anotherMap.entrySet());
    }

    // EntrySet.retainAll(HCollection)

    /**
     * Tests that the {@link EntrySet#retainAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#retainAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link EntrySet#retainAll(HCollection)} method is
     * called with null as an argument on the entry set created by the
     * {@link #setUp()} method. Since the method does not accept null arguments,
     * it should throw a NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#retainAll(HCollection)} method
     * throws a NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNull() {
        entrySet.retainAll(null);
    }

    // EntrySet.removeAll(HCollection)

    /**
     * Tests that the {@link EntrySet#removeAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#removeAll(HCollection)} method throws a
     * NullPointerException when null is passed as an argument.
     * @test.description The {@link EntrySet#removeAll(HCollection)} method is
     * called with null as an argument on the entry set created by the
     * {@link #setUp()} method. Since the method does not accept null arguments,
     * it should throw a NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#removeAll(HCollection)} method
     * throws a NullPointerException when null is passed as an argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNull() {
        entrySet.removeAll(null);
    }

    // EntrySet.clear()

    /**
     * Tests that the {@link EntrySet#clear()} method clears the entry set and
     * leaves the underlying map empty.
     *
     * @test.design The test aims to verify that the {@link EntrySet#clear()}
     * method clears the entry set and leaves the underlying map empty.
     * @test.description The {@link EntrySet#clear()} method is called on the
     * entry set created by the {@link #setUp()} method. Since the entry set is
     * backed by the underlying map, clearing the entry set should also clear
     * the map.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#clear()} method clears the
     * entry set and leaves the underlying map empty.
     */
    @Test
    public void testClear() {
        // Clear the entry set
        entrySet.clear();

        // Verify that the size is still 0
        assertEquals("Size of entry set should be 0 after clear", 0, entrySet.size());
        assertTrue("Entry set should be empty after clear", entrySet.isEmpty());

        // Verify that the map is unchanged
        assertEquals("Map should have size 0 after clearing entry set", 0, map.size());
        assertTrue("Map should be empty after clearing entry set", map.isEmpty());
    }

    // EntrySet.equals(Object)

    /**
     * Tests that the {@link EntrySet#equals(Object)} method returns true when
     * an entry set with the same entries is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#equals(Object)} method returns true when an entry set
     * with the same entries is passed as an argument.
     * @test.description A new empty MapAdapter instance is created, and its
     * entry set is obtained. The {@link EntrySet#equals(Object)} method is
     * called on the entry set created by the {@link #setUp()} method the new
     * one. Since it contains the same entries (none), the method should return
     * true.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#equals(Object)} method returns
     * true when an entry set with the same entries is passed as an argument.
     */
    @Test
    public void testEqualsSameSet() {
        MapAdapter anotherMap = new MapAdapter();
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertTrue("Entry set should be equal to another entry set with the same entries", entrySet.equals(anotherEntrySet));
    }

    /**
     * Tests that the {@link EntrySet#equals(Object)} method returns false when
     * an entry set with different entries is passed as an argument.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#equals(Object)} method returns false when an entry set
     * with different entries is passed as an argument.
     * @test.description A new MapAdapter instance is created with an entry
     * ("key", "value"). Its entry set is obtained, and the
     * {@link EntrySet#equals(Object)} method is called on the entry set created
     * by the {@link #setUp()} method and the new one. Since it contains
     * different entries, the method should return false.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#equals(Object)} method returns
     * false when an entry set with different entries is passed as an argument.
     */
    @Test
    public void testEqualsDifferentSet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertFalse("Entry set should not be equal to another entry set with different entries", entrySet.equals(anotherEntrySet));
    }

    /**
     * Tests that the {@link EntrySet#equals(Object)} method returns false when
     * the argument is not an EntrySet.
     *
     * @test.design The test aims to verify that the
     * {@link EntrySet#equals(Object)} method returns false when the argument is
     * not an EntrySet.
     * @test.description The {@link EntrySet#equals(Object)} method is called
     * with a String and a ValueCollection as an argument on the entry set
     * created by the {@link #setUp()} method. Since the argument is not an
     * EntrySet, the method should return false.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#equals(Object)} method returns
     * false when the argument is not an EntrySet.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsNotASet() {
        // Test that the equals method returns false when the argument is not an EntrySet
        String notASet = "This is not a set";
        ValueCollection vc = (ValueCollection) this.map.values();
        assertFalse("Entry set should not be equal to a String object", entrySet.equals(notASet));
        assertFalse("Entry set should not be equal to a ValueCollection object", entrySet.equals(vc));
    }

    // EntrySet.hashCode()

    /**
     * Tests that the {@link EntrySet#hashCode()} method returns 0 for an empty
     * entry set.
     *
     * @test.design The test aims to verify that the {@link EntrySet#hashCode()}
     * method returns 0 for an empty entry set since it has no entries.
     * @test.description The {@link EntrySet#hashCode()} method is called on the
     * entry set created by the {@link #setUp()} method. Since the entry set is
     * empty, the method should return 0.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#hashCode()} method returns 0
     * for an empty entry set.
     */
    @Test
    public void testHashCode() {
        int expectedHashCode = 0; // Since the entry set is empty
        assertEquals("Entry set hash code should be " + expectedHashCode, expectedHashCode, entrySet.hashCode());
    }

    /**
     * Tests that the {@link EntrySet#hashCode()} method returns different hash
     * codes for different entry sets.
     *
     * @test.design The test aims to verify that the {@link EntrySet#hashCode()}
     * method returns different hash codes for different entry sets when one
     * is empty and the other contains elements.
     * @test.description A new MapAdapter instance is created with an entry
     * ("key", "value"). Its entry set is obtained, and the
     * {@link EntrySet#hashCode()} method is called on the entry set created by
     * the {@link #setUp()} method and the new one. Since the entry sets are
     * different, the method should return different hash codes.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#hashCode()} method returns
     * different hash codes for different entry sets.
     */
    @Test
    public void testHashCodeDifferentSets() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // The hash codes of the two entry sets should be different
        assertNotEquals("Hash codes of different entry sets should not be equal", entrySet.hashCode(), anotherEntrySet.hashCode());
    }

    /**
     * Tests that the {@link EntrySet#hashCode()} method returns the same hash
     * code for the same entry set.
     *
     * @test.design The test aims to verify that the {@link EntrySet#hashCode()}
     * method returns the same hash code for the same entry set.
     * @test.description The {@link EntrySet#hashCode()} method is called on the
     * entry set created by the {@link #setUp()} method and another instance of
     * the same entry set. Since both instances are views of the same empty map,
     * the method should return the same hash code.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#hashCode()} method returns the
     * same hash code for the same entry set.
     */
    @Test
    public void testHashCodeSameSet() {
        // Create another instance of the same entry set
        EntrySet anotherEntrySet = (EntrySet) map.entrySet();
        assertEquals("Hash codes of the same entry set should be equal", entrySet.hashCode(), anotherEntrySet.hashCode());
    }

    /**
     * Tests that the {@link EntrySet#equals(Object)} method implies that the
     * hash codes of the entry sets are equal.
     *
     * @test.design The test aims to verify that if two entry sets are equal,
     * their hash codes should also be equal.
     * @test.description The {@link EntrySet#equals(Object)} method is called on
     * the entry set created by the {@link #setUp()} method with itself as an
     * argument. Since it is the same entry set, the method should return true.
     * The {@link EntrySet#hashCode()} method is then called on both entry sets,
     * and their hash codes should be equal.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@link EntrySet#equals(Object)} method implies
     * that the hash codes of the entry sets are equal.
     */
    @Test
    public void testEqualsImpliesHashCode() {
        assertEquals("Entry sets with same elements should be equal", entrySet, entrySet);
        assertEquals("Hash codes of equal entry sets should be equal", entrySet.hashCode(), entrySet.hashCode());
    }
}