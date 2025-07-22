package myTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.Entry;
import myAdapter.MapAdapter.EntryIterator;
import myAdapter.MapAdapter.EntrySet;

public class EntryIteratorPopulatedTests {
    
    public MapAdapter map;
    public EntrySet entrySet;
    public EntryIterator iter;
    
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        entrySet = (EntrySet) map.entrySet();
        iter = (EntryIterator) entrySet.iterator();
    }

    @After
    public void tearDown() {
        map = null;
        entrySet = null;
        iter = null;
    }

    // EntryIterator.hasNext() + EntryIterator.next()

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
    
    @Test
    public void testRemoveAll() {
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            assertNotNull("Entry should not be null", entry);
            iter.remove();
        }
        assertTrue("Map should be empty after removing all entries", map.isEmpty());
    }

    @Test
    public void testRemoveWithNext() {
        assertTrue("Should have a next element", iter.hasNext());
        Entry entry = (Entry) iter.next();
        assertNotNull("Entry should not be null", entry);
        iter.remove();
        assertFalse("Map should not contain the removed entry", map.containsKey(entry.getKey()));
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNext() {
        iter.remove();
    }

    // Entry.setValue()

    @Test
    public void testEntrySetValue() {
        Entry entry = (Entry) iter.next();

        Object old = entry.getValue();
        assertEquals("The old value should be returned", old, entry.setValue("newValue"));
        assertEquals("The value should be updated on the map", "newValue", map.get(entry.getKey()));
    }

    
    @Test(expected = NullPointerException.class)
    public void testEntrySetValueWithNull() {
        Entry entry = (Entry) iter.next();
        entry.setValue(null);
    }

    // Entry.equals()

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

    @Test
    public void testEntryNotEqualsNull() {
        Entry entry = (Entry) iter.next();
        assertFalse("Entry should not be equal to null", entry.equals(null));
    }

    @Test
    public void testEntryNotEqualsDifferentType() {
        Entry entry = (Entry) iter.next();
        assertFalse("Entry should not be equal to an object of a different type", entry.equals("NotAnEntry"));
    }

    @Test
    public void testEntrySameKeyDifferentValue() {
        Entry entry1 = (Entry) iter.next();
        Entry entry2 = (Entry) iter.next();
        
        // Modify entry2 to have the same key but a different value
        entry2.setValue("differentValue");
        
        assertFalse("Entries with the same key but different values should not be equal", entry1.equals(entry2));
    }
}
