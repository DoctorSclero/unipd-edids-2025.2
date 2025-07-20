package myTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.Entry;
import myAdapter.MapAdapter.EntrySet;

/**
 * This test case tests the EntrySet class instances linked to an empty map.
 * Every method of the EntrySet class is tested to ensure the class
 * implementation serves correctly as a view for empty maps. For each method
 * tests are created to ensure correct behavior in normal and edge cases,
 * including correct throwing of exceptions and correct return values and
 * side effects on the underlying map.
 *
 * @test.design This test case aims to verify the correct behavior of empty
 * MapAdapter instances to ensure it correctly serves as a set view for empty
 * maps and implements correctly the {@link myAdapter.HSet} interface.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 */
public class EntrySetEmptyTests {

    public MapAdapter map;
    public EntrySet entrySet;

    /**
     * Sets up the test environment by initializing an empty MapAdapter
     * through the MapAdapter's default constructor and creating an EntrySet
     * instance obtaining its entry set view.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        entrySet = (EntrySet) map.entrySet();
    }

    /**
     * Cleans up the test environment by setting the map and entrySet
     * references to null, allowing for garbage collection.
     */
    @After
    public void tearDown() {
        map = null;
        entrySet = null;
    }

    // EntrySet.size()

    /**
     * Tests that the {@link EntrySet#size()} method returns 0 for an empty map.
     * 
     * @test.design The test aims to verify that the
     * {@link EntrySet#size()} method returns 0 for an empty map.
     * @test.description The {@link EntrySet#size()} method is called on the set
     * view created by the {@link #setUp()} method. Since the linked map is
     * empty, the method should return 0.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#size()} method returns 0
     * for an empty map.
     */
    @Test
    public void testSize() {
        assertEquals("Size of an empty entry set should be 0", 0, entrySet.size());
    }
    
    // EntrySet.isEmpty()

    /**
     * Tests that the {@link EntrySet#isEmpty()} method returns true for an empty map.
     * 
     * @test.design The test aims to verify that the
     * {@link EntrySet#isEmpty()} method returns true for an empty map.
     * @test.description The {@link EntrySet#isEmpty()} method is called on the set
     * view created by the {@link #setUp()} method. Since the linked map is
     * empty, the method should return true.
     * @test.precondition The map is correctly instantiated
     * @test.postcondition The map is still empty
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
     * when null is passed as an argument as not supported by the underlying
     * map implementation.
     * @test.description The {@link EntrySet#contains(Object)} method is called
     * with null as an argument on the entry set. Since the underlying map does
     * not support null keys, it should throw a NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#contains(Object)} method throws
     * NullPointerException when null is passed as an argument.
     */
    @Test (expected = NullPointerException.class)
    public void testContainsNull() {
        entrySet.contains(null);
    }

    /**
     * Tests that the {@link EntrySet#contains(Object)} method returns false
     * when a non-contained element is passed as an argument.
     * 
     * @test.design The test aims to verify that the
     * {@link EntrySet#contains(Object)} method returns false when a non-contained
     * element is passed as an argument.
     * @test.description To create a non-contained element, a new
     * MapAdapter instance is created, and an entry is added to it. The entry is
     * then extracted using the entry set view of the new map. The
     * {@link EntrySet#contains(Object)} method is called with this entry on the
     * original entry set. Since the original map is empty, the method should
     * return false.
     * @test.precondition The map and the entry set are correctly instantiated.
     * Another MapAdapter instance is created with an entry and the entry is then
     * extracted from its entry set.
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#contains(Object)} method returns false
     * when a non-contained element is passed as an argument.
     */
    @Test
    public void testContainsNotContainedElement() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");

        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Entry entry = (Entry)anotherEntrySet.iterator().next();

        assertFalse("Entry set should not contain non-contained element",
                entrySet.contains(entry));
    }

    // EntrySet.iterator()

    /**
     * Tests that the {@link EntrySet#iterator()} method returns a non-null
     * iterator for an empty entry set.
     * 
     * @test.design The test aims to verify that the
     * {@link EntrySet#iterator()} method returns a non-null iterator for an
     * empty entry set.
     * @test.description The {@link EntrySet#iterator()} method is called on the
     * entry set created by the {@link #setUp()} method. Since the entry set
     * is empty, the method should return a non-null iterator that
     * iterates over no elements.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#iterator()} method returns a
     * non-null iterator for an empty entry set.
     */
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of an empty entry set should not be null",
                entrySet.iterator());
    }

    // EntrySet.toArray()

    /**
     * Tests that the {@link EntrySet#toArray()} method returns a non-null
     * array for an empty entry set.
     * 
     * @test.design The test aims to verify that the
     * {@link EntrySet#toArray()} method returns a non-null array for an empty
     * entry set.
     * @test.description The {@link EntrySet#toArray()} method is called on the
     * entry set created by the {@link #setUp()} method. Since the entry set
     * is empty, the method should return a non-null array with a length of 0.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#toArray()} method returns a
     * non-null array for an empty entry set.
     */
    @Test
    public void testToArray() {
        Object[] array = entrySet.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an empty array for an empty entry set",
                0, array.length);
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
     * method. Since the method does not support null arrays, it should throw
     * a NullPointerException.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#toArray(Object[])} method throws
     * {@link NullPointerException} when null is passed as an argument.
     */
    @Test (expected = NullPointerException.class)
    public void testToArrayNullArray() {
        entrySet.toArray(null);
    }

    /**
     * Tests that the {@link EntrySet#toArray(Object[])} method returns an
     * empty array when an empty array is passed as an argument.
     * 
     * @test.design The test aims to verify that the
     * {@link EntrySet#toArray(Object[])} method returns an empty array when an
     * bigger empty array is passed as an argument.
     * @test.description A new empty array of size 5 is created. Then the array
     * is populated with flags to ensure that the {@link EntrySet#toArray(Object[])}
     * method does not overwrite the elements of the array. The format for the
     * flags is {@code "flag-i"} where {@code i} is the index of the element.
     * The {@link EntrySet#toArray(Object[])} method is then called with this array
     * as an argument. Since the entry set is empty, the method should return
     * the bigger passed array without overwriting its elements.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#toArray(Object[])} method 
     * returns an array of 
     */
    @Test
    public void testToArrayWithBiggerArray() {
        Object[] array = new Object[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = entrySet.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return an empty array for an empty entry set",
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

    // EntrySet.add(Object)
    
    /**
     * Tests that the {@link EntrySet#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty entry set.
     * 
     * @test.design The test aims to verify that the
     * {@link EntrySet#add(Object)} method throws an UnsupportedOperationException
     * when called on an empty entry set.
     * @test.description The {@link EntrySet#add(Object)} method is called with
     * a string as an argument on the entry set created by the {@link #setUp()}
     * method. Since the entry set is a view of an empty map, the method should
     * throw an UnsupportedOperationException as adding elements to an empty
     * entry set is not allowed.
     * @test.precondition The map and the entry set are correctly instantiated
     * @test.postcondition The map is still empty
     * @test.expectedresults The {@link EntrySet#add(Object)} method throws an
     * UnsupportedOperationException when called on an empty entry set.
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testAdd() {
        entrySet.add("baguette");
    }
}
