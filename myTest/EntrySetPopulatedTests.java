package myTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.Entry;
import myAdapter.MapAdapter.EntrySet;


/**
 * This test case tests the EntrySet class instances linked to a populated map.
 * Every method of the EntrySet class is tested to ensure the class
 * implementation serves correctly as a view for maps with multiple entries. For
 * each method, tests are created to ensure correct behavior in normal and edge
 * cases, including correct handling of contained and non-contained elements,
 * correct throwing of exceptions, and correct return values and side effects on
 * the underlying map.
 *
 * @test.design This test case aims to verify the correct behavior of populated
 * entry set instances to ensure it correctly serves as a set view for maps with
 * entries and implements correctly the {@link myAdapter.HSet} interface.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.EntrySet
 * @see myAdapter.MapAdapter.Entry
 */
public class EntrySetPopulatedTests {

    public MapAdapter map;
    public EntrySet entrySet;


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
        entrySet = (EntrySet) map.entrySet();
    }

    // EntrySet.size()

    /**
     * Tests the size method of the EntrySet class.
     *
     * @test.design Verifies that the size method correctly returns the number
     * of entries in the set.
     * @test.description This test ensures that the size method accurately
     * reflects the number of entries in the EntrySet. The test initializes a
     * populated map with 100 entries and checks that the size method returns
     * 100.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with 100 entries.
     * @test.postcondition The size method should return the correct number of
     * entries in the set.
     * @test.expectedresults The size method should return 100.
     */
    @Test
    public void testSize() {
        assertEquals("Size of the entry set should be 100", 100, entrySet.size());
    }

    // EntrySet.isEmpty()

    /**
     * Tests the isEmpty method of the EntrySet class.
     *
     * @test.design Verifies that the isEmpty method correctly identifies
     * whether the set is empty.
     * @test.description This test ensures that the isEmpty method returns false
     * for a populated EntrySet. The test initializes a populated map and checks
     * that the isEmpty method returns false.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The isEmpty method should return false.
     * @test.expectedresults The isEmpty method should return false for a
     * populated EntrySet.
     */
    @Test
    public void testIsEmpty() {
        assertFalse("Entry set should not be empty", entrySet.isEmpty());
    }

    // EntrySet.contains(Object)

    /**
     * Tests the contains method of the EntrySet class with a null argument.
     *
     * @test.design Verifies that the contains method throws a
     * NullPointerException when passed a null argument.
     * @test.description This test ensures that the contains method handles null
     * arguments correctly by throwing a NullPointerException. The test calls
     * the contains method with a null argument and expects an exception.
     * @test.precondition The EntrySet instance must be initialized.
     * @test.postcondition A NullPointerException should be thrown when the
     * contains method is called with a null argument.
     * @test.expectedresults The contains method should throw a
     * NullPointerException when passed a null argument.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNull() {
        entrySet.contains(null);
    }

    /**
     * Tests the contains method of the EntrySet class with a non-contained
     * element.
     *
     * @test.design Verifies that the contains method correctly identifies
     * elements not present in the EntrySet.
     * @test.description This test ensures that the contains method returns
     * false for an element not present in the EntrySet. A new map is created
     * with a single entry, and the contains method is called with this entry on
     * the original EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The contains method should return false for
     * non-contained elements.
     * @test.expectedresults The contains method should return false for an
     * element not present in the EntrySet.
     */
    @Test
    public void testContainsNotContainedElement() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");

        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Entry entry = (Entry) anotherEntrySet.iterator().next();

        assertFalse("Entry set should not contain non-contained element",
                entrySet.contains(entry));
    }

    /**
     * Tests the contains method of the EntrySet class with contained elements.
     *
     * @test.design Verifies that the contains method correctly identifies
     * elements present in the EntrySet.
     * @test.description This test ensures that the contains method returns true
     * for elements present in the EntrySet. It checks for contained elements at
     * the start, middle, and end of the set.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The contains method should return true for contained
     * elements.
     * @test.expectedresults The contains method should return true for elements
     * present in the EntrySet.
     */
    @Test
    public void testContainsContainedElementStart() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        Entry entry = (Entry) anotherEntrySet.iterator().next();
        assertTrue("Entry set should contain the first element",
                entrySet.contains(entry));
    }

    /**
     * Tests the contains method of the EntrySet class with a middle contained
     * element.
     *
     * @test.design Verifies that the contains method correctly identifies a
     * middle element present in the EntrySet.
     * @test.description This test checks that the contains method returns true
     * for a middle element in the EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The contains method should return true for a middle
     * contained element.
     * @test.expectedresults The contains method should return true for a middle
     * element present in the EntrySet.
     */
    @Test
    public void testContainsContainedElementMiddle() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key49", "value49");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Entry entry = (Entry) anotherEntrySet.iterator().next();
        assertTrue("Entry set should contain a middle element",
                entrySet.contains(entry));
    }

    /**
     * Tests the contains method of the EntrySet class with an end contained
     * element.
     *
     * @test.design Verifies that the contains method correctly identifies an
     * end element present in the EntrySet.
     * @test.description This test checks that the contains method returns true
     * for an end element in the EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The contains method should return true for an end
     * contained element.
     * @test.expectedresults The contains method should return true for an end
     * element present in the EntrySet.
     */
    @Test
    public void testContainsContainedElementEnd() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key99", "value49");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Entry entry = (Entry) anotherEntrySet.iterator().next();
        assertTrue("Entry set should contain the last element",
                entrySet.contains(entry));
    }

    /**
     * Tests the contains method of the EntrySet class with an entry that has a
     * contained key but a value not contained in the EntrySet.
     *
     * @test.design Verifies that the contains method correctly identifies
     * entries with contained keys but non-contained values.
     * @test.description This test checks that the contains method returns false
     * for an entry with a key that is contained in the EntrySet but a value
     * that is not contained.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The contains method should return false for entries
     * with contained keys but non-contained values.
     * @test.expectedresults The contains method should return false for an
     * entry with a contained key and a value not contained in the EntrySet.
     */
    @Test
    public void testContainsContainedEntryValueNotContained() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value100"); // Use a value that doesn't exist in the original map
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Entry entry = (Entry) anotherEntrySet.iterator().next();
        assertFalse("Entry set should not contain such entry with key contained and value not contained",
                entrySet.contains(entry));
    }

    /**
     * Tests the contains method of the EntrySet class with an entry that is not
     * an instance of Entry.
     *
     * @test.design Verifies that the contains method returns false for objects
     * that are not instances of Entry.
     * @test.description This test checks that the contains method does not
     * mistakenly identify non-Entry objects as contained.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The contains method should return false for
     * non-Entry objects.
     * @test.expectedresults The contains method should return false for objects
     * that are not instances of Entry.
     */
    @Test
    public void testContainsNotAnEntry() {
        assertFalse("Entry set should not contain a non-entry object",
                entrySet.contains("nonEntryObject"));
    }

    // EntrySet.iterator()

    /**
     * Tests the iterator method of the EntrySet class.
     *
     * @test.design Verifies that the iterator method returns a non-null
     * iterator for the EntrySet.
     * @test.description This test ensures that the iterator method returns a
     * valid iterator for the EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The iterator method should return a non-null
     * iterator.
     * @test.expectedresults The iterator method should return a non-null
     * iterator for the EntrySet.
     */
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of an entry set should not be null",
                entrySet.iterator());
    }

    // EntrySet.toArray()

    /**
     * Tests the toArray method of the EntrySet class. This test checks that the
     * toArray() method returns an array containing all the entries in the
     * EntrySet.
     *
     * @test.design Verifies that the toArray method returns an array
     * representation of the EntrySet.
     * @test.description This test ensures that the toArray method returns an
     * array containing all the entries in the EntrySet, with a default size of
     * 100.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The toArray method should return an array containing
     * all the entries in the EntrySet.
     * @test.expectedresults The toArray method should return an array
     * containing all the entries in the EntrySet.
     */
    @Test
    public void testToArray() {
        Object[] array = entrySet.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an array for a populated entry set",
                100, array.length);
    }

    // EntrySet.toArray(T[])

    /**
     * Tests the toArray(T[]) method of the EntrySet class with a null array.
     * This test checks that the toArray(T[]) method throws a
     * NullPointerException when passed a null array.
     *
     * @test.design Verifies that the toArray(T[]) method handles null arrays
     * correctly.
     * @test.description This test ensures that the toArray(T[]) method throws a
     * NullPointerException when passed a null array.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition A NullPointerException should be thrown when the
     * toArray(T[]) method is called with a null array.
     * @test.expectedresults The toArray(T[]) method should throw a
     * NullPointerException when passed a null array.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullArray() {
        entrySet.toArray(null);
    }

    /**
     * Tests the toArray(T[]) method of the EntrySet class with a smaller array.
     * This test checks that the toArray(T[]) method returns a new array
     * containing all the entries in the EntrySet.
     *
     * @test.design Verifies that the toArray(T[]) method returns a new array
     * containing all entries in the EntrySet.
     * @test.description This test ensures that the toArray(T[]) method returns
     * a new array with a size equal to the number of entries in the EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The toArray(T[]) method should return a new array
     * containing all entries in the EntrySet.
     * @test.expectedresults The toArray(T[]) method should return a new array
     * containing all entries in the EntrySet.
     */
    @Test
    public void testToArrayWithSmallerArray() {
        Object[] array = new Object[5];
        Object[] result = entrySet.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return a new array containing all the entries in the entry set",
                100, result.length);
        assertNotSame("toArray(T[]) should return a new array, not the one passed as argument",
                array, result);
    }

    /**
     * Tests the toArray(T[]) method of the EntrySet class with a larger array.
     * This test checks that the toArray(T[]) method returns the same array
     * passed as an argument, with the first 100 elements filled with entries
     * and the rest set to null.
     *
     * @test.design Verifies that the toArray(T[]) method fills the provided
     * array correctly.
     * @test.description This test ensures that the toArray(T[]) method fills
     * the provided array with entries from the EntrySet and sets any remaining
     * elements to null.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The toArray(T[]) method should fill the provided
     * array with entries and set remaining elements to null.
     * @test.expectedresults The toArray(T[]) method should return the same
     * array passed as an argument, filled with entries and null terminators.
     */
    @Test
    public void testToArrayWithLargerArray() {
        Object[] array = new Object[200];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = entrySet.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return an array for a populated entry set",
                200, result.length);
        assertSame("toArray(T[]) should return the same array passed as argument",
                array, result);
        assertNull("toArray(T[]) should have a null terminator at position 100", result[100]);
        assertEquals("The 101st element should not be modified",
                "flag-101", result[101]);
    }

    // EntrySet.add(Object)

    /**
     * Tests the add method of the EntrySet class. This test checks that the add
     * method throws an UnsupportedOperationException when trying to add an
     * element to the EntrySet.
     *
     * @test.design Verifies that the add method does not allow adding elements
     * to the EntrySet.
     * @test.description This test ensures that the add method throws an
     * UnsupportedOperationException when called on a populated EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The add method should not modify the EntrySet or the
     * underlying map.
     * @test.expectedresults The add method should throw an
     * UnsupportedOperationException.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        entrySet.add("baguette");
    }

    // EntrySet.remove(Object)

    /**
     * Tests the remove method of the EntrySet class with a null argument. This
     * test checks that the remove method throws a NullPointerException when
     * passed a null argument.
     *
     * @test.design Verifies that the remove method handles null arguments
     * correctly.
     * @test.description This test ensures that the remove method throws a
     * NullPointerException when passed a null argument.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition A NullPointerException should be thrown when the
     * remove method is called with a null argument.
     * @test.expectedresults The remove method should throw a
     * NullPointerException when passed a null argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        entrySet.remove(null);
    }

    /**
     * Tests the remove method of the EntrySet class with a non-contained
     * element. This test checks that the remove method returns false when
     * trying to remove an element not present in the EntrySet.
     *
     * @test.design Verifies that the remove method correctly identifies
     * non-contained elements.
     * @test.description This test ensures that the remove method returns false
     * for an element not present in the EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The remove method should return false for
     * non-contained elements.
     * @test.expectedresults The remove method should return false for an
     * element not present in the EntrySet.
     */
    @Test
    public void testRemoveNonContained() {
        assertFalse("remove(Object) should return false for non-contained elements",
                entrySet.remove("nonContainedElement"));
        assertEquals("Map should have size 100 after removing non-contained element", 100, map.size());
        assertFalse("Entry set shouldn't be empty after removing non-contained element",
                entrySet.isEmpty());
    }

    /**
     * Tests the remove method of the EntrySet class with a contained element at
     * the start. This test checks that the remove method returns true and
     * removes the element from the EntrySet.
     *
     * @test.design Verifies that the remove method correctly identifies and
     * removes contained elements.
     * @test.description This test ensures that the remove method returns true
     * for an element present in the EntrySet and that it is removed from both
     * the EntrySet and the underlying map.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The remove method should return true and modify the
     * EntrySet and map accordingly.
     * @test.expectedresults The remove method should return true for a
     * contained element, and the EntrySet size should decrease by 1.
     */
    @Test
    public void testRemoveContainedStart() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Object entry = anotherEntrySet.iterator().next();

        assertTrue("remove(Object) should return true for contained elements",
                entrySet.remove(entry));
        assertFalse("Entry set should not contain the removed element",
                entrySet.contains(entry));
        assertEquals("Map should have size 99 after removing an element", 99, map.size());
    }

    /**
     * Tests the remove method of the EntrySet class with a contained element in
     * the middle. This test checks that the remove method returns true and
     * removes the element from the EntrySet.
     *
     * @test.design Verifies that the remove method correctly identifies and
     * removes contained elements.
     * @test.description This test ensures that the remove method returns true
     * for an element present in the EntrySet and that it is removed from both
     * the EntrySet and the underlying map.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The remove method should return true and modify the
     * EntrySet and map accordingly.
     * @test.expectedresults The remove method should return true for a
     * contained element, and the EntrySet size should decrease by 1.
     */
    @Test
    public void testRemoveContainedMiddle() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key50", "value0"); // key50 should have value0 (50 % 50 = 0)
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Object entry = anotherEntrySet.iterator().next();

        assertTrue("remove(Object) should return true for contained elements",
                entrySet.remove(entry));
        assertFalse("Entry set should not contain the removed element",
                entrySet.contains(entry));
        assertEquals("Map should have size 99 after removing an element", 99, map.size());
    }

    /**
     * Tests the remove method of the EntrySet class with a contained element at
     * the end. This test checks that the remove method returns true and removes
     * the element from the EntrySet.
     *
     * @test.design Verifies that the remove method correctly identifies and
     * removes contained elements.
     * @test.description This test ensures that the remove method returns true
     * for an element present in the EntrySet and that it is removed from both
     * the EntrySet and the underlying map.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The remove method should return true and modify the
     * EntrySet and map accordingly.
     * @test.expectedresults The remove method should return true for a
     * contained element, and the EntrySet size should decrease by 1.
     */
    @Test
    public void testRemoveContainedEnd() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key99", "value49"); // key99 should have value49 (99 % 50 = 49)
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        Object entry = anotherEntrySet.iterator().next();

        assertTrue("remove(Object) should return true for contained elements",
                entrySet.remove(entry));
        assertFalse("Entry set should not contain the removed element",
                entrySet.contains(entry));
        assertEquals("Map should have size 99 after removing an element", 99, map.size());
    }

    // EntrySet.containsAll(HCollection)

    /**
     * Tests the containsAll method of the EntrySet class with a null
     * collection.
     *
     * @test.design Verifies that the containsAll method throws a
     * NullPointerException when passed a null collection.
     * @test.description This test ensures that the containsAll method handles
     * null collections correctly by throwing a NullPointerException. The test
     * calls the containsAll method with a null argument and expects an
     * exception.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition A NullPointerException should be thrown when the
     * containsAll method is called with a null collection.
     * @test.expectedresults The containsAll method should throw a
     * NullPointerException when passed a null collection.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNull() {
        entrySet.containsAll(null);
    }

    /**
     * Tests the containsAll method of the EntrySet class with a collection
     * containing some elements.
     *
     * @test.design Verifies that the containsAll method correctly identifies
     * whether all elements of another collection are contained in the
     * EntrySet.
     * @test.description This test checks that the containsAll method returns
     * true when all elements of another collection are present in the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The containsAll method should return true for
     * collections with some elements.
     * @test.expectedresults The containsAll method should return true for
     * collections with some elements.
     */
    @Test
    public void testContainsAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key49", "value49");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertTrue("Entry set should contain all elements of another entry set with some elements",
                entrySet.containsAll(anotherEntrySet));
    }

    /**
     * Tests the containsAll method of the EntrySet class with a collection
     * containing all elements of the same entry set.
     *
     * @test.design Verifies that the containsAll method correctly identifies
     * whether all elements of another collection are contained in the
     * EntrySet.
     * @test.description This test checks that the containsAll method returns
     * true when all elements of another collection are present in the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The containsAll method should return true for
     * collections with all elements.
     * @test.expectedresults The containsAll method should return true for
     * collections with all elements.
     */
    @Test
    public void testContainsAllSameElements() {
        MapAdapter anotherMap = new MapAdapter();
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertTrue("Entry set should contain all elements of the same entry set",
                entrySet.containsAll(anotherEntrySet));
    }

    /**
     * Tests the containsAll method of the EntrySet class with a collection
     * containing more elements than the EntrySet.
     *
     * @test.design Verifies that the containsAll method correctly identifies
     * whether all elements of another collection are contained in the
     * EntrySet.
     * @test.description This test checks that the containsAll method returns
     * false when another collection contains more elements than the EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The containsAll method should return false for
     * collections with more elements.
     * @test.expectedresults The containsAll method should return false for
     * collections with more elements.
     */
    @Test
    public void testContainsAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();
        assertFalse("Entry set should not contain all elements of another entry set with more elements",
                entrySet.containsAll(anotherEntrySet));
    }

    /**
     * Tests the containsAll method of the EntrySet class with a collection
     * containing no common elements.
     *
     * @test.design Verifies that the containsAll method correctly identifies
     * whether all elements of another collection are contained in the
     * EntrySet.
     * @test.description This test checks that the containsAll method returns
     * false when another collection contains no common elements with the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The containsAll method should return false for
     * collections with no common elements.
     * @test.expectedresults The containsAll method should return false for
     * collections with no common elements.
     */
    @Test
    public void testContainsAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertFalse("Entry set should not contain all elements of another entry set with no common elements",
                entrySet.containsAll(anotherEntrySet));
    }

    // EntrySet.addAll(HCollection)

    /**
     * Tests the addAll method of the EntrySet class. This test checks that the
     * addAll method throws an UnsupportedOperationException when trying to add
     * elements to the EntrySet.
     *
     * @test.design Verifies that the addAll method does not allow adding
     * elements to the EntrySet.
     * @test.description This test ensures that the addAll method throws an
     * UnsupportedOperationException when called on a populated EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The addAll method should not modify the EntrySet or
     * the underlying map.
     * @test.expectedresults The addAll method should throw an
     * UnsupportedOperationException.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        MapAdapter anotherMap = new MapAdapter();
        entrySet.addAll(anotherMap.entrySet());
    }

    // EntrySet.retainAll(HCollection)

    /**
     * Tests the retainAll method of the EntrySet class with a null collection.
     * This test checks that the retainAll method throws a NullPointerException
     * when passed a null collection.
     *
     * @test.design Verifies that the retainAll method handles null collections
     * correctly.
     * @test.description This test ensures that the retainAll method throws a
     * NullPointerException when passed a null collection.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition A NullPointerException should be thrown when the
     * retainAll method is called with a null collection.
     * @test.expectedresults The retainAll method should throw a
     * NullPointerException when passed a null collection.
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNull() {
        entrySet.retainAll(null);
    }

    /**
     * Tests the retainAll method of the EntrySet class with a collection
     * containing the same elements as the EntrySet.
     *
     * @test.design Verifies that the retainAll method correctly retains all
     * elements when passed a collection with the same elements.
     * @test.description This test checks that the retainAll method returns
     * false when called with a collection containing the same elements as the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The retainAll method should not modify the EntrySet
     * or the underlying map.
     * @test.expectedresults The retainAll method should return false for
     * collections with the same elements.
     */
    @Test
    public void testRetainAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Retain all elements that are in the same entry set
        assertFalse("RetainAll should have no effect with same elements",
                entrySet.retainAll(anotherEntrySet));

        // Verify that the entry set is unchanged
        assertEquals("Size of entry set should remain 100 after retainAll with same elements",
                100, entrySet.size());
    }

    /**
     * Tests the retainAll method of the EntrySet class with a collection
     * containing some elements.
     *
     * @test.design Verifies that the retainAll method correctly retains some
     * elements when passed a collection with some elements.
     * @test.description This test checks that the retainAll method returns true
     * when called with a collection containing some elements present in the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The retainAll method should modify the EntrySet and
     * the underlying map accordingly.
     * @test.expectedresults The retainAll method should return true for
     * collections with some elements.
     */
    @Test
    public void testRetainAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key49", "value49");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Retain only the elements that are in another entry set
        assertTrue("RetainAll should return true for some elements",
                entrySet.retainAll(anotherEntrySet));

        // Verify that the size is reduced to 2
        assertEquals("Size of entry set should be 2 after retainAll with some elements",
                2, entrySet.size());
    }

    /**
     * Tests the retainAll method of the EntrySet class with a collection
     * containing more elements than the EntrySet.
     *
     * @test.design Verifies that the retainAll method correctly retains
     * elements when passed a collection with more elements.
     * @test.description This test checks that the retainAll method returns
     * false when called with a collection containing more elements than the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The retainAll method should not modify the EntrySet
     * or the underlying map.
     * @test.expectedresults The retainAll method should return false for
     * collections with more elements.
     */
    @Test
    public void testRetainAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Retain only the elements that are in the another entry set
        assertFalse("RetainAll should return false for more elements",
                entrySet.retainAll(anotherEntrySet));

        // Verify that the size remains unchanged
        assertEquals("Size of entry set should remain 100 after retainAll with more elements",
                100, entrySet.size());
    }

    /**
     * Tests the retainAll method of the EntrySet class with a collection
     * containing no common elements.
     *
     * @test.design Verifies that the retainAll method correctly retains
     * elements when passed a collection with no common elements.
     * @test.description This test checks that the retainAll method returns true
     * when called with a collection containing no common elements with the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The retainAll method should modify the EntrySet and
     * the underlying map accordingly.
     * @test.expectedresults The retainAll method should return true for
     * collections with no common elements.
     */
    @Test
    public void testRetainAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Retain only the elements that are in the another entry set
        assertTrue("RetainAll should return true for no common elements",
                entrySet.retainAll(anotherEntrySet));

        // Verify that the size is reduced to 0
        assertEquals("Size of entry set should be 0 after retainAll with no common elements",
                0, entrySet.size());
    }

    // EntrySet.removeAll(HCollection)

    /**
     * Tests the removeAll method of the EntrySet class with a null collection.
     * This test checks that the removeAll method throws a NullPointerException
     * when passed a null collection.
     *
     * @test.design Verifies that the removeAll method handles null collections
     * correctly.
     * @test.description This test ensures that the removeAll method throws a
     * NullPointerException when passed a null collection.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition A NullPointerException should be thrown when the
     * removeAll method is called with a null collection.
     * @test.expectedresults The removeAll method should throw a
     * NullPointerException when passed a null collection.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNull() {
        entrySet.removeAll(null);
    }

    /**
     * Tests the removeAll method of the EntrySet class with a collection
     * containing the same elements as the EntrySet.
     *
     * @test.design Verifies that the removeAll method correctly removes all
     * elements when passed a collection with the same elements.
     * @test.description This test checks that the removeAll method returns true
     * when called with a collection containing the same elements as the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The removeAll method should modify the EntrySet and
     * the underlying map accordingly.
     * @test.expectedresults The removeAll method should return true for
     * collections with the same elements.
     */
    @Test
    public void testRemoveAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Remove all elements that are in the same entry set
        assertTrue("Set should have changed after removeAll with same elements",
                entrySet.removeAll(anotherEntrySet));

        // Verify that the entry set is now empty
        assertEquals("Size should be 0 after removeAll with same elements",
                0, entrySet.size());
    }

    /**
     * Tests the removeAll method of the EntrySet class with a collection
     * containing some elements.
     *
     * @test.design Verifies that the removeAll method correctly removes
     * elements when passed a collection with some elements.
     * @test.description This test checks that the removeAll method returns true
     * when called with a collection containing some elements that are in the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The removeAll method should modify the EntrySet and
     * the underlying map accordingly.
     * @test.expectedresults The removeAll method should return true for
     * collections with some elements.
     */
    @Test
    public void testRemoveAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value0"); // key50 should have value0 (50 % 50 = 0)
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Remove only the elements that are in the another entry set
        assertTrue("RemoveAll should return true for some elements",
                entrySet.removeAll(anotherEntrySet));

        // Verify that the size is reduced to 98
        assertEquals("Size of entry set should be 98 after removeAll with some elements",
                98, entrySet.size());
    }

    /**
     * Tests the removeAll method of the EntrySet class with a collection
     * containing more elements than the EntrySet.
     *
     * @test.design Verifies that the removeAll method correctly removes
     * elements when passed a collection with more elements.
     * @test.description This test checks that the removeAll method returns true
     * when called with a collection containing more elements than the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The removeAll method should modify the EntrySet and
     * the underlying map accordingly.
     * @test.expectedresults The removeAll method should return true for
     * collections with more elements.
     */
    @Test
    public void testRemoveAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Remove only the elements that are in the another entry set
        assertTrue("RemoveAll should return true for more elements",
                entrySet.removeAll(anotherEntrySet));

        // Verify that all common elements are removed
        assertEquals("Size of entry set should be 0 after removeAll with more elements",
                0, entrySet.size());
    }

    /**
     * Tests the removeAll method of the EntrySet class with a collection
     * containing no common elements.
     *
     * @test.design Verifies that the removeAll method correctly removes
     * elements when passed a collection with no common elements.
     * @test.description This test checks that the removeAll method returns
     * false when called with a collection containing no common elements with
     * the EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The removeAll method should not modify the EntrySet
     * or the underlying map.
     * @test.expectedresults The removeAll method should return false for
     * collections with no common elements.
     */
    @Test
    public void testRemoveAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // Remove only the elements that are in the another entry set
        assertFalse("RemoveAll should return false for no common elements",
                entrySet.removeAll(anotherEntrySet));

        // Verify that the size remains unchanged
        assertEquals("Size of entry set should remain 100 after removeAll with no common elements",
                100, entrySet.size());
    }

    // EntrySet.clear()

    /**
     * Tests the clear method of the EntrySet class.
     *
     * @test.design Verifies that the clear method removes all elements from the
     * EntrySet.
     * @test.description This test checks that the clear method empties the
     * EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The clear method should modify the EntrySet and the
     * underlying map accordingly.
     * @test.expectedresults The clear method should leave the EntrySet and map
     * empty.
     */
    @Test
    public void testClear() {
        // Clear the entry set
        entrySet.clear();

        // Verify that the size is 0
        assertEquals("Size of entry set should be 0 after clear", 0, entrySet.size());
        assertTrue("Entry set should be empty after clear", entrySet.isEmpty());

        // Verify that the map is empty
        assertEquals("Map should have size 0 after clearing entry set", 0, map.size());
        assertTrue("Map should be empty after clearing entry set", map.isEmpty());
    }

    // EntrySet.equals(Object)

    /**
     * Tests the equals method of the EntrySet class with a null argument. This
     * test checks that the equals method returns false when passed a null
     * argument.
     *
     * @test.design Verifies that the equals method handles null arguments
     * correctly.
     * @test.description This test ensures that the equals method returns false
     * when passed a null argument.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The equals method should return false for null
     * arguments.
     * @test.expectedresults The equals method should return false when passed a
     * null argument.
     */
    @Test
    public void testEqualsNull() {
        assertFalse("Entry set should not be equal to null",
                entrySet.equals(null));
    }

    /**
     * Tests the equals method of the EntrySet class with the same set of
     * entries. This test checks that the equals method returns true when
     * comparing to an equivalent EntrySet.
     *
     * @test.design Verifies that the equals method correctly identifies
     * equivalent EntrySet instances.
     * @test.description This test ensures that the equals method returns true
     * when comparing to another EntrySet with the same entries.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The equals method should return true for equivalent
     * EntrySet instances.
     * @test.expectedresults The equals method should return true when comparing
     * to an equivalent EntrySet.
     */
    @Test
    public void testEqualsSameSet() {
        MapAdapter anotherMap = new MapAdapter(map);
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertTrue("Entry set should be equal to another entry set with the same entries",
                entrySet.equals(anotherEntrySet));
    }

    /**
     * Tests the equals method of the EntrySet class with a different set. This
     * test checks that the equals method returns false when comparing to a
     * different set.
     *
     * @test.design Verifies that the equals method returns false for different
     * sets.
     * @test.description This test ensures that the equals method returns false
     * when comparing to a different set with different entries.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The equals method should return false for different
     * sets.
     * @test.expectedresults The equals method should return false when
     * comparing to a different set.
     */
    @Test
    public void testEqualsDifferentSet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertFalse("Entry set should not be equal to another entry set with different entries",
                entrySet.equals(anotherEntrySet));
    }

    /**
     * Tests the equals method of the EntrySet class with a different set of the
     * same size. This test checks that the equals method returns false when
     * comparing to a different set with the same size but different entries.
     *
     * @test.design Verifies that the equals method returns false for different
     * sets of the same size.
     * @test.description This test ensures that the equals method returns false
     * when comparing to a different set with the same size but different
     * entries.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The equals method should return false for different
     * sets of the same size.
     * @test.expectedresults The equals method should return false when
     * comparing to a different set of the same size.
     */
    @Test
    public void testEqualsDifferentSetSameSize() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            anotherMap.put("key" + i, "value" + (i + 1)); // Different values
        }
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        assertFalse("Entry set should not be equal to another entry set with different entries",
                entrySet.equals(anotherEntrySet));
    }

    /**
     * Tests the equals method of the EntrySet class with a non-EntrySet object.
     * This test checks that the equals method returns false when comparing to
     * an object that is not an EntrySet.
     *
     * @test.design Verifies that the equals method returns false for
     * non-EntrySet objects.
     * @test.description This test ensures that the equals method returns false
     * when comparing to an object that is not an EntrySet.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The equals method should return false for
     * non-EntrySet objects.
     * @test.expectedresults The equals method should return false when
     * comparing to a non-EntrySet object.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsNotASet() {
        // Test that the equals method returns false when the argument is not an EntrySet
        String notASet = "This is not a set";
        assertFalse("Entry set should not be equal to a non-EntrySet object",
                entrySet.equals(notASet));
    }

    // EntrySet.hashCode()

    /**
     * Tests the hashCode method of the EntrySet class. This test checks that
     * the hashCode method returns a consistent hash code for the same set of
     * entries.
     *
     * @test.design Verifies that the hashCode method returns a consistent value
     * for the same set of entries.
     * @test.description This test ensures that the hashCode method returns the
     * same value for the same set of entries.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The hashCode method should return a consistent value
     * for the same set of entries.
     * @test.expectedresults The hashCode method should return a consistent
     * value for the same set of entries.
     */
    @Test
    public void testHashCodeDifferentSets() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        EntrySet anotherEntrySet = (EntrySet) anotherMap.entrySet();

        // The hash codes of the two entry sets should be different
        assertNotEquals("Hash codes of different entry sets should not be equal",
                entrySet.hashCode(), anotherEntrySet.hashCode());
    }

    /**
     * Tests the hashCode method of the EntrySet class with the same set of
     * entries. This test checks that the hashCode method returns the same value
     * for the same set of entries.
     *
     * @test.design Verifies that the hashCode method returns a consistent value
     * for the same set of entries.
     * @test.description This test ensures that the hashCode method returns the
     * same value for the same set of entries.
     * @test.precondition The EntrySet instance must be initialized and
     * populated with entries.
     * @test.postcondition The hashCode method should return a consistent value
     * for the same set of entries.
     * @test.expectedresults The hashCode method should return a consistent
     * value for the same set of entries.
     */
    @Test
    public void testHashCodeSameSet() {
        // Create another instance of the same entry set
        EntrySet anotherEntrySet = (EntrySet) map.entrySet();
        assertEquals("Hash codes of the same entry set should be equal",
                entrySet.hashCode(), anotherEntrySet.hashCode());
    }

    /**
     * Tests that the {@link MapAdapter#entrySet()} {@code retainAll} method throws
     * {@link NullPointerException} when called with a collection containing null elements.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#entrySet()} {@code retainAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements, as null elements are not supported by the MapAdapter.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null key-value pair. The {@code retainAll} method is
     * then called on the entry set of the populated map with the entry set of
     * the NullableHMap as argument. The result is asserted to throw a
     * {@link NullPointerException}, as null elements are not supported.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null elements.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@code retainAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements.
     */
    @Test(expected = NullPointerException.class)
    public void testEntrySetRetainAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put(null, "value");
        map.entrySet().retainAll(nullableMap.entrySet());
    }

    /**
     * Tests that the {@link MapAdapter#entrySet()} {@code removeAll} method throws
     * {@link NullPointerException} when called with a collection containing null elements.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#entrySet()} {@code removeAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements, as null elements are not supported by the MapAdapter.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null key-value pair. The {@code removeAll} method is
     * then called on the entry set of the populated map with the entry set of
     * the NullableHMap as argument. The result is asserted to throw a
     * {@link NullPointerException}, as null elements are not supported.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null elements.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@code removeAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements.
     */
    @Test(expected = NullPointerException.class)
    public void testEntrySetRemoveAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put("key", null);
        map.entrySet().removeAll(nullableMap.entrySet());
    }

    /**
     * Tests that the {@link MapAdapter#entrySet()} {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing null elements.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#entrySet()} {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements, as null elements are not supported by the MapAdapter.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null key-value pair. The {@code containsAll} method is
     * then called on the entry set of the populated map with the entry set of
     * the NullableHMap as argument. The result is asserted to throw a
     * {@link NullPointerException}, as null elements are not supported.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null elements.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements.
     */
    @Test(expected = NullPointerException.class)
    public void testEntrySetContainsAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put(null, null);
        map.entrySet().containsAll(nullableMap.entrySet());
    }
}