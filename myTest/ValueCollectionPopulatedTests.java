package myTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.ValueCollection;


public class ValueCollectionPopulatedTests {

    public MapAdapter map;
    public ValueCollection values;

    
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50));
        }
        values = (ValueCollection) map.values();
    }

    // ValueCollection.size()

    @Test
    public void testSize() {
        assertEquals("Size of the key set should be 100", 100, values.size());
    }
    
    // ValueCollection.isEmpty()

    @Test
    public void testIsEmpty() {
        assertFalse("Key set should not be empty", values.isEmpty());
    }

    // ValueCollection.contains(Object)

    @Test (expected = NullPointerException.class)
    public void testContainsNull() {
        values.contains(null);
    }

    @Test
    public void testContainsNotContainedElement() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");

        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object key = anotherValueCollection.iterator().next();

        assertFalse("Key set should not contain non-contained element",
                values.contains(key));
    }

    @Test
    public void testContainsContainedElementStart() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object key = anotherValueCollection.iterator().next();
        assertTrue("Key set should contain the first element",
                values.contains(key));
    }

    @Test
    public void testContainsContainedElementMiddle() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key50", "value0");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object key = anotherValueCollection.iterator().next();
        assertTrue("Key set should contain a middle element",
                values.contains(key));
    }

    @Test
    public void testContainsContainedElementEnd() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key99", "value49");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object key = anotherValueCollection.iterator().next();
        assertTrue("Key set should contain the last element",
                values.contains(key));
    }

    // ValueCollection.iterator()

    
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of an key set should not be null",
                values.iterator());
    }

    // ValueCollection.toArray()

    
    @Test
    public void testToArray() {
        Object[] array = values.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an array for an empty key set",
                100, array.length);
    }

    // ValueCollection.toArray(T[])

    
    @Test (expected = NullPointerException.class)
    public void testToArrayNullArray() {
        values.toArray(null);
    }

    
    @Test
    public void testToArrayWithSmallerArray() {
        Object[] array = new Object[5];
        Object[] result = values.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return a new array containing all the keys in the key set",
                100, result.length);
        assertNotSame("toArray(T[]) should return a new array, not the one passed as argument",
                array, result);
    }

    @Test
    public void testToArrayWithLargerArray() {
        Object[] array = new Object[200];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = values.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return an array for an empty key set",
                200, result.length);
        assertSame("toArray(T[]) should return the same array passed as argument",
                array, result);
        assertNull("toArray(T[]) should have a null terminator at position 100", result[100]);
        assertEquals("The 101st element should not be modified",
                "flag-101", result[101]);
    }

    // ValueCollection.add(Object)
    
    
    @Test (expected = UnsupportedOperationException.class)
    public void testAdd() {
        values.add("baguette");
    }

    // ValueCollection.remove(Object)

    
    @Test (expected = NullPointerException.class)
    public void testRemoveNull() {
        values.remove(null);
    }

    
    @Test
    public void testRemoveNonContained() {
        assertFalse("remove(Object) should return false for non-contained elements",
                values.remove("nonContainedElement"));
        assertEquals("Map should have size 100 after removing non-contained element", 100, map.size());
        assertFalse("Key set shouldn't be empty after removing non-contained element",
                values.isEmpty());
    }

    @Test
    public void testRemoveContainedStart() {
        Object value = "value0";
        
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertFalse("Key set should not contain the removed element",
                values.contains(value));
        assertEquals("Map should have size 98 after removing two elements", 98, map.size());
    }

    @Test
    public void testRemoveContainedMiddle() {
        Object value = "value35";
        
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertFalse("Key set should not contain the removed element",
                values.contains(value));
        assertEquals("Map should have size 98 after removing two elements", 98, map.size());
    }

    @Test
    public void testRemoveContainedEnd() {
        Object value = "value49";
        
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertFalse("Key set should not contain the removed element",
                values.contains(value));
        assertEquals("Map should have size 98 after removing two elements", 98, map.size());
    }

    // ValueCollection.containsAll(HCollection)

    
    @Test (expected = NullPointerException.class)
    public void testContainsAllNull() {
        values.containsAll(null);
    }

    @Test
    public void testContainsAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value0");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Key set should contain all elements of another key set with some elements",
                values.containsAll(anotherValueCollection));
    }
    
    @Test
    public void testContainsAllSameElements() {
        MapAdapter anotherMap = new MapAdapter();
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Key set should contain all elements of the same key set",
                values.containsAll(anotherValueCollection));
    }

    
    @Test
    public void testContainsAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        assertFalse("Key set should not contain all elements of another key set with more elements",
                values.containsAll(anotherValueCollection));
    }

    @Test
    public void testContainsAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertFalse("Key set should not contain all elements of another key set with no common elements",
                values.containsAll(anotherValueCollection));
    }

    // ValueCollection.addAll(HCollection)

    
    @Test (expected = UnsupportedOperationException.class)
    public void testAddAll() {
        MapAdapter anotherMap = new MapAdapter();
        values.addAll(anotherMap.values());
    }

    // ValueCollection.retainAll(HCollection)

    
    @Test (expected = NullPointerException.class)
    public void testRetainAllNull() {
        values.retainAll(null);
    }

    @Test
    public void testRetainAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain all elements that are in the same key set
        assertFalse("RetainAll should have no effect with same elements",
                values.retainAll(anotherValueCollection));
        
        // Verify that the key set is unchanged
        assertEquals("Size of key set should remain 100 after retainAll with same elements",
                100, values.size());
    }

    @Test
    public void testRetainAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value50");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain only the elements that are in the another key set
        assertTrue("RetainAll should return true for some elements",
                values.retainAll(anotherValueCollection));
        
        // Verify that the size is reduced to 2
        assertEquals("Size of key set should be 2 after retainAll with some elements",
                2, values.size());
    }

    @Test
    public void testRetainAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain only the elements that are in the another key set
        assertFalse("RetainAll should return false for more elements",
                values.retainAll(anotherValueCollection));
        
        // Verify that the size remains unchanged
        assertEquals("Size of key set should remain 100 after retainAll with more elements",
                100, values.size());
    }

    @Test
    public void testRetainAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain only the elements that are in the another key set
        assertTrue("RetainAll should return true for no common elements",
                values.retainAll(anotherValueCollection));
        
        // Verify that the size is reduced to 0
        assertEquals("Size of key set should be 0 after retainAll with no common elements",
                0, values.size());
    }

    // ValueCollection.removeAll(HCollection)

    
    @Test (expected = NullPointerException.class)
    public void testRemoveAllNull() {
        values.removeAll(null);
    }

    @Test
    public void testRemoveAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Remove all elements that are in the same key set
        assertTrue("Collection should have changed after removeAll with same elements",
                values.removeAll(anotherValueCollection));
        
        // Verify that the key set is unchanged
        assertEquals("Size should be 0 after removeAll with same elements",
                0, values.size());
    }

    @Test
    public void testRemoveAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key1", "value1");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Remove only the elements that are in the another key set
        assertTrue("RemoveAll should return true for some elements",
                values.removeAll(anotherValueCollection));
        
        // Verify that the size is reduced to 96
        assertEquals("Remove all should remove duplicates inside the map", 96, values.size());
    }

    @Test
    public void testRemoveAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + (i%50)); // Values repeat every 50 keys
        }
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Remove only the elements that are in the another key set
        assertTrue("RemoveAll should return true for more elements",
                values.removeAll(anotherValueCollection));
        
        // Verify that the size remains unchanged
        assertEquals("Size of key set should remain 100 after removeAll with more elements",
                0, values.size());
    }

    @Test
    public void testRemoveAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Remove only the elements that are in the another key set
        assertFalse("RemoveAll should return false for no common elements",
                values.removeAll(anotherValueCollection));

        // Verify that the size is reduced to 0
        assertEquals("Size of key set should be 0 after removeAll with no common elements",
                100, values.size());
    }

    // ValueCollection.clear()

    
    @Test
    public void testClear() {
        // Clear the key set
        values.clear();
        
        // Verify that the size is still 0
        assertEquals("Size of key set should be 0 after clear", 0, values.size());
        assertTrue("Key set should be after clear", values.isEmpty());

        // Verify that the map is still empty
        assertEquals("Map should have size 0 after clearing key set", 0, map.size());
        assertTrue("Map should be after clearing key set", map.isEmpty());
    }

    // ValueCollection.equals(Object)

    
    @Test
    public void testEqualsSameCollection() {
        MapAdapter anotherMap = new MapAdapter(map);
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Key set should be equal to another key set with the same entries",
                values.equals(anotherValueCollection));
    }

    
    @Test
    public void testEqualsDifferentCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertFalse("Key set should not be equal to another key set with different entries",
                values.equals(anotherValueCollection));
    }

    
    @Test
    public void testEqualsDifferentCollectionSameSize() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            anotherMap.put("key" + i, "value" + (i + 1)); // Different values
        }
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertFalse("Value collection should not be equal to another value collection with different entries",
                values.equals(anotherValueCollection));
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsNotACollection() {
        // Test that the equals method returns false when the argument is not an ValueCollection
        String notACollection = "This is not a set";
        assertFalse("Key set should not be equal to a non-ValueCollection object",
                values.equals(notACollection));
    }

    // ValueCollection.hashCode()

    @Test
    public void testHashCodeDifferentCollections() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // The hash codes of the two key sets should be different
        assertNotEquals("Hash codes of different key sets should not be equal",
                values.hashCode(), anotherValueCollection.hashCode());
    }
    
    @Test
    public void testHashCodeSameCollection() {
        // Create another instance of the same key set
        ValueCollection anotherValueCollection = (ValueCollection) map.values();
        assertEquals("Hash codes of the same key set should be equal",
                values.hashCode(), anotherValueCollection.hashCode());
    }

}