package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.Entry;
import myAdapter.MapAdapter.EntryIterator;
import myAdapter.MapAdapter.EntrySet;

/**
 * Tests the functionality for an EntryIterator linked to a populated map.
 * This class contains tests for the EntryIterator methods for which
 * the populated state of the map is relevant.
 * 
 * @test.design This test class is designed to ensure that the EntryIterator and Entry
 * class methods work correctly when the map is populated.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.EntryIterator
 * @see myAdapter.MapAdapter.EntrySet
 * @see myAdapter.MapAdapter.Entry
 */
public class EntryIteratorPopulatedTests {
    
    public MapAdapter map;
    public EntrySet entrySet;
    public EntryIterator iter;
    
    /**
     * Sets up the test environment by initializing a MapAdapter instance,
     * populating it with 100 entries in using {@code "key"+i} as key and
     * {@code "value"+(i % 50)} as value to ensure coverage of edge cases
     * involving duplicate values. After creating the MapAdapter, an
     * EntrySet instance is created obtaining its entry set view. After that,
     * an EntryIterator instance is created from the obtained EntrySet.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        entrySet = (EntrySet) map.entrySet();
        iter = (EntryIterator) entrySet.iterator();
    }

    // EntryIterator.hasNext() + EntryIterator.next()

    /**
     * Tests iterator capability to iterate from start to finish.
     * 
     * @test.design This test is designed to ensure that the EntryIterator can
     * iterate through all entries in the map from start to finish. Ensuring
     * that both hasNext() and next() methods work correctly in a populated state.
     * @test.description The hasNext() is called repeatedly on the iterator
     * created in the {@link #setUp()} method until it returns false,
     * on each iteration next() is called on the iterator to traverse the 
     * entries and a counter is incremented. The test asserts that the
     * counter equals the number of entries in the map, which is 100.
     * @test.preconditions The map must be populated with 100 entries before
     * this test runs.
     * @test.postconditions The iterator is at the end of the entry set, the 
     * map remains unchanged.
     * @test.expectedresults Iterator traverses all 100 entries in the map
     * without any issues.
     */
    @Test
    public void testStartToFinish() {
        int i = 0;
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        assertEquals("Should have next once for each entry", 100, i);
    }

    // EntryIterator.remove()
    
    /**
     * Tests the remove method of the EntryIterator.
     * It checks that all entries can be removed from the map using the iterator.
     * 
     * @test.design This test is designed to ensure that the EntryIterator's
     * remove() method works correctly in a populated state.
     * @test.description The hasNext() is called repeatedly until it returns false,
     * while hasNext() returns true, next() is called to traverse the entries
     * asserting that each entry is not null. Successively, the remove() method
     * is called to remove each entry from the map. After the loop, the map
     * is asserted to be empty.
     * @test.preconditions The map must be populated with 100 entries before
     * this test runs.
     * @test.postconditions The map is empty after all entries are removed.
     * The iterator is at the end of the entry set.
     * @test.expectedresults The test should pass if the remove method can successfully
     * remove all entries from the map without any issues.
     */
    @Test
    public void testRemoveAll() {
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            assertNotNull("Entry should not be null", entry);
            iter.remove();
        }
        assertTrue("Map should be empty after removing all entries", map.isEmpty());
    }

    /**
     * Tests the remove method of the EntryIterator after calling next().
     * It checks that an entry can be removed after it has been accessed.
     * 
     * @test.design This test is designed to ensure that the EntryIterator's
     * remove() method works correctly after calling next().
     * @test.description This test calls the {@link EntryIterator#remove()} method
     * using the EntryIterator created in the {@link #setUp()} method to 
     * an entry after calling next(). It asserts
     * that the entry is no longer present in the map.
     * @test.preconditions The map is correctly instantiated and populated
     * with entries.
     * @test.postconditions The map does not contain the removed entry after
     * the remove operation.
     * @test.expectedresults The removed entry's key should not be present in 
     * the map.
     */
    @Test
    public void testRemoveWithNext() {
        assertTrue("Should have a next element", iter.hasNext());
        Entry entry = (Entry) iter.next();
        assertNotNull("Entry should not be null", entry);
        iter.remove();
        assertFalse("Map should not contain the removed entry", map.containsKey(entry.getKey()));
    }

    /**
     * Tests the remove method of the EntryIterator without calling next().
     * It checks that an IllegalStateException is thrown when remove() is called
     * without a preceding call to next().
     * 
     * @test.design This test is designed to ensure that the EntryIterator's
     * remove() method throws an IllegalStateException when called
     * without a preceding call to next().
     * @test.description This test uses the EntryIterator created in the
     * {@link #setUp()} method to call remove() without calling next().
     * It asserts that an IllegalStateException is thrown.
     * @test.preconditions The EntryIterator is correctly instantiated.
     * @test.postconditions The state of the EntryIterator should remain unchanged
     * after the test.
     * @test.expectedresults The test should pass if an IllegalStateException is thrown
     * when remove() is called without a preceding call to next().
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }
    
    // Entry.setValue()

    /**
     * Tests the setValue method of the Entry class.
     * It checks that the value of an entry can be updated
     * and that the map reflects this change.
     * 
     * @test.design This test is designed to ensure that the Entry class's
     * setValue() method updates the value of an entry and that the map
     * reflects this change correctly.
     * @test.description An Entry is obtained from the EntryIterator created in
     * the {@link #setUp()} method. The setValue() method is called on the
     * Entry instance to update its value. The test asserts that the
     * setValue() method returns the old value and that the map reflects the
     * updated value.
     * @test.preconditions The map must be populated with entries before
     * this test runs.
     * @test.postconditions The map should reflect the updated value
     * after the setValue() operation.
     * @test.expectedresults The test should pass if the setValue() method can successfully
     * update the value of an entry and the map reflects this change.
     */
    @Test
    public void testEntrySetValue() {
        Entry entry = (Entry) iter.next();

        Object old = entry.getValue();
        assertEquals("The old value should be returned", old, entry.setValue("newValue"));
        assertEquals("The value should be updated on the map", "newValue", map.get(entry.getKey()));
    }

    /**
     * Tests the setValue method of the Entry class with a null value.
     * It checks that setting a null value throws a NullPointerException.
     * @test.design This test is designed to ensure that the Entry class's
     * setValue() method throws a NullPointerException
     * when a null value is passed.
     * @test.description This test uses the EntryIterator created in the
     * {@link #setUp()} method to test the setValue() method of the Entry class.
     * It asserts that calling setValue() with a null value throws a NullPointerException.
     * @test.preconditions The map must be populated with entries before
     * this test runs.
     * @test.postconditions The map should remain unchanged after the test runs.
     * @test.expectedresults The test should pass if a NullPointerException is thrown
     */
    @Test(expected = NullPointerException.class)
    public void testEntrySetValueWithNull() {
        Entry entry = (Entry) iter.next();
        entry.setValue(null);
    }

    // Entry.equals()

    /**
     * Tests the equals method of the Entry class.
     * It checks that two entries with different keys are not equal.
     * @test.design This test is designed to ensure that the Entry class's
     * equals() method works correctly in a populated state.
     * @test.description This test uses the EntryIterator created in the
     * {@link #setUp()} method to test the equals() method of the Entry class
     * by comparing two entries with different keys.
     * It asserts that the entries are not equal.
     * @test.preconditions The map must be populated with entries before
     * this test runs.
     * @test.postconditions The map should remain unchanged after the test runs.
     * @test.expectedresults The test should pass if the equals() method correctly
     */
    @Test
    public void testEntryEquals() {
        Entry entry1 = (Entry) iter.next();
        Entry entry2 = (Entry) iter.next();
        
        assertFalse("Entries should not be equal if they have different keys", entry1.equals(entry2));

        // Create another iterator to get an entry with the same key and value
        // as entry1
        Entry entry3 = (Entry) map.entrySet().iterator().next();
        assertTrue("Entries with the same key and value should be equal", entry1.equals(entry3));
    }

    /**
     * Tests the equals method of the Entry class with null.
     * It checks that an entry is not equal to null.
     * @test.design This test is designed to ensure that the Entry class's
     * equals() method returns false when compared to null.
     * @test.description This test uses the EntryIterator created in the
     * {@link #setUp()} method to test the equals() method of the Entry class
     * by comparing an entry to null.
     * It asserts that the entry is not equal to null.
     * @test.preconditions The map must be populated with entries before
     * this test runs.
     * @test.postconditions The map should remain unchanged after the test runs.
     * @test.expectedresults The test should pass if the equals() method returns false
     */
    @Test
    public void testEntryNotEqualsNull() {
        Entry entry = (Entry) iter.next();
        assertFalse("Entry should not be equal to null", entry.equals(null));
    }

    /**
     * Tests the equals method of the Entry class with a different type.
     * It checks that an entry is not equal to an object of a different type.
     * @test.design This test is designed to ensure that the Entry class's
     * equals() method returns false when compared to an object of a different type.
     * @test.description This test uses the EntryIterator created in the
     * {@link #setUp()} method to test the equals() method of the Entry class
     * by comparing an entry to a string.
     * It asserts that the entry is not equal to a string.
     * @test.preconditions The map must be populated with entries before
     * this test runs.
     * @test.postconditions The map should remain unchanged after the test runs.
     * @test.expectedresults The test should pass if the equals() method returns false
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEntryNotEqualsDifferentType() {
        Entry entry = (Entry) iter.next();
        assertFalse("Entry should not be equal to an object of a different type", entry.equals("NotAnEntry"));
    }

    /**
     * Tests the equals method of the Entry class with the same key but different values.
     * It checks that two entries with the same key but different values are not equal.
     * @test.design This test is designed to ensure that the Entry class's
     * equals() method works correctly when comparing entries with the same key
     * but different values.
     * @test.description This test uses the EntryIterator created in the
     * {@link #setUp()} method to test the equals() method of the Entry class
     * by comparing two entries with the same key but different values.
     * It asserts that the entries are not equal.
     * @test.preconditions The map must be populated with entries before
     * this test runs.
     * @test.postconditions The map should remain unchanged after the test runs.
     * @test.expectedresults The test should pass if the equals() method returns false
     */
    @Test
    public void testEntrySameKeyDifferentValue() {
        Entry entry1 = (Entry) iter.next();
        Entry entry2 = (Entry) iter.next();
        
        // Modify entry2 to have the same key but a different value
        entry2.setValue("differentValue");
        
        assertFalse("Entries with the same key but different values should not be equal", entry1.equals(entry2));
    }
}
