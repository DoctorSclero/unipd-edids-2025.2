package myTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import myAdapter.MapAdapter;
import myAdapter.MapAdapter.ValueCollection;

/**
 * Test case for ValueCollection when the underlying map is populated. This
 * class tests the behavior of ValueCollection methods when there are elements
 * in the map.
 *
 * @test.design This test is designed to ensure that the ValueCollection behaves
 * correctly when the underlying map is populated, specifically checking size(),
 * isEmpty(), contains(), iterator(), toArray(), add(), remove(), containsAll(),
 * addAll(), retainAll(), removeAll(), clear(), equals(), and hashCode()
 * methods.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.ValueCollection
 */
public class ValueCollectionPopulatedTests {

    public MapAdapter map;
    public ValueCollection values;

    /**
     * Setup method to initialize the map and values before each test. This
     * method is called before each test to ensure a fresh state.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + (i % 50));
        }
        values = (ValueCollection) map.values();
    }

    // ValueCollection.size()

    /**
     * Test to verify that the size of the ValueCollection is correct.
     *
     * @test.design This test checks that the size method returns the expected
     * number of elements.
     * @test.description This test ensures that the ValueCollection has the
     * correct number of elements based on the number of unique keys in the
     * underlying map.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should have the expected number
     * of elements.
     * @test.expectedresults The test should pass if the size is 100, as there
     * are 100 unique keys.
     */
    @Test
    public void testSize() {
        assertEquals("Size of the value collection should be 100", 100, values.size());
    }

    // ValueCollection.isEmpty()

    /**
     * Test to verify that the ValueCollection is not empty.
     *
     * @test.design This test checks that the isEmpty method returns false when
     * the collection has elements.
     * @test.description This test ensures that the ValueCollection is not empty
     * after being populated.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should not be empty.
     * @test.expectedresults The test should pass if isEmpty() returns false.
     */
    @Test
    public void testIsEmpty() {
        assertFalse("Value collection should not be empty", values.isEmpty());
    }

    // ValueCollection.contains(Object)

    /**
     * Test to verify that the ValueCollection contains specific elements.
     *
     * @test.design This test checks that the contains method returns true for
     * elements that are present in the collection and false for those that are
     * not.
     * @test.description This test ensures that the ValueCollection correctly
     * identifies contained elements.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should contain the expected
     * elements.
     * @test.expectedresults The test should pass if contains() returns true
     * for contained elements and false for non-contained elements.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNull() {
        values.contains(null);
    }

    /**
     * Test to verify that the ValueCollection does not contain an element that
     * was never added.
     *
     * @test.design This test checks that the contains method returns false for
     * elements that are not in the collection.
     * @test.description This test ensures that the ValueCollection does not
     * mistakenly identify non-contained elements as present.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should not contain elements that
     * were never added.
     * @test.expectedresults The test should pass if contains() returns false
     * for non-contained elements.
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

    /**
     * Test to verify that the ValueCollection contains specific elements at
     * different positions. This test checks that the ValueCollection can
     * correctly identify contained elements at the start, middle, and end of
     * the collection.
     *
     * @test.design This test is designed to ensure that the ValueCollection
     * behaves correctly when checking for contained elements at various
     * positions.
     * @test.description This test checks that the ValueCollection has elements
     * to iterate over and that calling contains() returns true for expected
     * values.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should have elements to iterate
     * over.
     * @test.expectedresults The test should pass if contains() returns true
     * for expected values.
     */
    @Test
    public void testContainsContainedElementStart() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object value = anotherValueCollection.iterator().next();
        assertTrue("Value collection should contain the first element",
                values.contains(value));
    }

    /**
     * Test to verify that the ValueCollection contains specific elements at
     * different positions. This test checks that the ValueCollection can
     * correctly identify contained elements at the start, middle, and end of
     * the collection.
     *
     * @test.design This test is designed to ensure that the ValueCollection
     * behaves correctly when checking for contained elements at various
     * positions.
     * @test.description This test checks that the ValueCollection has elements
     * to iterate over and that calling contains() returns true for expected
     * values.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should have elements to iterate
     * over.
     * @test.expectedresults The test should pass if contains() returns true
     * for expected values.
     */
    @Test
    public void testContainsContainedElementMiddle() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key50", "value0");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object value = anotherValueCollection.iterator().next();
        assertTrue("Value collection should contain a middle element",
                values.contains(value));
    }

    /**
     * Test to verify that the ValueCollection contains specific elements at
     * different positions. This test checks that the ValueCollection can
     * correctly identify contained elements at the start, middle, and end of
     * the collection.
     *
     * @test.design This test is designed to ensure that the ValueCollection
     * behaves correctly when checking for contained elements at various
     * positions.
     * @test.description This test checks that the ValueCollection has elements
     * to iterate over and that calling contains() returns true for expected
     * values.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should have elements to iterate
     * over.
     * @test.expectedresults The test should pass if contains() returns true
     * for expected values.
     */
    @Test
    public void testContainsContainedElementEnd() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key99", "value49");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        Object value = anotherValueCollection.iterator().next();
        assertTrue("Value collection should contain the last element",
                values.contains(value));
    }

    // ValueCollection.iterator()

    /**
     * Test to verify that the iterator of the ValueCollection is not null.
     *
     * @test.design This test checks that the iterator method returns a non-null
     * iterator.
     * @test.description This test ensures that the ValueCollection can be
     * iterated over.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should have a valid iterator.
     * @test.expectedresults The test should pass if the iterator is not null.
     */
    @Test
    public void testIteratorNotNull() {
        assertNotNull("Iterator of a value collection should not be null",
                values.iterator());
    }

    // ValueCollection.toArray()

    /**
     * Test to verify that the toArray() method returns an array with the
     * correct number of elements.
     *
     * @test.design This test checks that the toArray method returns an array
     * containing all elements in the ValueCollection.
     * @test.description This test ensures that the ValueCollection can be
     * converted to an array.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should be convertible to an
     * array.
     * @test.expectedresults The test should pass if toArray() returns an array
     * with 100 elements.
     */
    @Test
    public void testToArray() {
        Object[] array = values.toArray();
        assertNotNull("toArray() should return a non-null array", array);
        assertEquals("toArray() should return an array for a populated value collection",
                100, array.length);
    }

    // ValueCollection.toArray(T[])

    /**
     * Test to verify that the toArray(T[]) method returns an array with the
     * correct number of elements.
     *
     * @test.design This test checks that the toArray(T[]) method returns an
     * array containing all elements in the ValueCollection.
     * @test.description This test ensures that the ValueCollection can be
     * converted to an array of a specific type.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should be convertible to an
     * array of a specific type.
     * @test.expectedresults The test should pass if toArray(T[]) returns an
     * array with 100 elements.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullArray() {
        values.toArray(null);
    }

    /**
     * Test to verify that the toArray(T[]) method returns an array with the
     * correct number of elements when a smaller array is passed.
     *
     * @test.design This test checks that the toArray(T[]) method returns a new
     * array containing all elements in the ValueCollection when a smaller array
     * is passed.
     * @test.description This test ensures that the ValueCollection can be
     * converted to an array of a specific type even when a smaller array is
     * provided.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should be convertible to an
     * array of a specific type.
     * @test.expectedresults The test should pass if toArray(T[]) returns a new
     * array with 100 elements.
     */
    @Test
    public void testToArrayWithSmallerArray() {
        Object[] array = new Object[5];
        Object[] result = values.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return a new array containing all the values in the value collection",
                100, result.length);
        assertNotSame("toArray(T[]) should return a new array, not the one passed as argument",
                array, result);
    }

    /**
     * Test to verify that the toArray(T[]) method returns the same array when a
     * larger array is passed.
     *
     * @test.design This test checks that the toArray(T[]) method returns the
     * same array when a larger array is passed.
     * @test.description This test ensures that the ValueCollection can be
     * converted to an array of a specific type and that the original array is
     * not modified.
     * @test.precondition The map must be populated before this test runs.
     * @test.postcondition The ValueCollection should be convertible to an
     * array of a specific type.
     * @test.expectedresults The test should pass if toArray(T[]) returns the
     * same array with null terminators.
     */
    @Test
    public void testToArrayWithLargerArray() {
        Object[] array = new Object[200];
        for (int i = 0; i < array.length; i++) {
            array[i] = "flag-" + i;
        }
        Object[] result = values.toArray(array);
        assertNotNull("toArray(T[]) should return a non-null array", result);
        assertEquals("toArray(T[]) should return an array for a populated value collection",
                200, result.length);
        assertSame("toArray(T[]) should return the same array passed as argument",
                array, result);
        assertNull("toArray(T[]) should have a null terminator at position 100", result[100]);
        assertEquals("The 101st element should not be modified",
                "flag-101", result[101]);
    }

    // ValueCollection.add(Object)

    /**
     * Test to verify that add(Object) throws UnsupportedOperationException.
     *
     * @test.design This test is designed to ensure that the ValueCollection
     * does not allow adding elements.
     * @test.description This test checks that calling add(Object) on the
     * ValueCollection results in an UnsupportedOperationException.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if add(Object) throws an
     * UnsupportedOperationException.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        values.add("baguette");
    }

    // ValueCollection.remove(Object)

    /**
     * Test to verify that remove(Object) throws NullPointerException when null
     * is passed.
     *
     * @test.design This test checks that the remove(Object) method throws a
     * NullPointerException when null is passed.
     * @test.description This test ensures that the ValueCollection does not
     * allow removing null elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if remove(Object) throws a
     * NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        values.remove(null);
    }

    /**
     * Test to verify that remove(Object) returns false for non-contained
     * elements. This test checks that the ValueCollection does not allow
     * removing elements that are not present.
     *
     * @test.design This test is designed to ensure that the ValueCollection
     * behaves correctly when attempting to remove non-contained elements.
     * @test.description This test checks that calling remove(Object) on a
     * non-contained element returns false and does not modify the collection.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if remove(Object) returns
     * false for non-contained elements and the collection size remains
     * unchanged.
     */
    @Test
    public void testRemoveNonContained() {
        assertFalse("remove(Object) should return false for non-contained elements",
                values.remove("nonContainedElement"));
        assertEquals("Map should have size 100 after removing non-contained element", 100, map.size());
        assertFalse("Value collection shouldn't be empty after removing non-contained element",
                values.isEmpty());
    }

    /**
     * Test to verify that remove(Object) works correctly when the element is
     * contained. This test checks that the ValueCollection can remove elements
     * that are present.
     *
     * @test.design This test is designed to ensure that the ValueCollection can
     * remove elements from the underlying map.
     * @test.description This test checks that calling remove(Object) on a
     * contained element removes it from the collection and that the collection
     * size is reduced accordingly.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should be able to remove
     * elements from the map.
     * @test.expectedresults The test should pass if remove(Object) works as
     * expected and the collection size is reduced.
     */
    @Test
    public void testRemoveContainedStart() {
        Object value = "value0";

        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertFalse("Value collection should not contain the removed element",
                values.contains(value));
        assertEquals("Map should have size 98 after removing two elements", 98, map.size());
    }

    /**
     * Test to verify that remove(Object) works correctly when the element is
     * contained in the middle. This test checks that the ValueCollection can
     * remove elements that are present in the middle of the collection.
     *
     * @test.design This test is designed to ensure that the ValueCollection can
     * remove elements from the underlying map.
     * @test.description This test checks that calling remove(Object) on a
     * contained element removes it from the collection and that the collection
     * size is reduced accordingly.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should be able to remove
     * elements from the map.
     * @test.expectedresults The test should pass if remove(Object) works as
     * expected and the collection size is reduced.
     */
    @Test
    public void testRemoveContainedMiddle() {
        Object value = "value35";

        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertFalse("Value collection should not contain the removed element",
                values.contains(value));
        assertEquals("Map should have size 98 after removing two elements", 98, map.size());
    }

    /**
     * Test to verify that remove(Object) works correctly when the element is
     * contained at the end. This test checks that the ValueCollection can
     * remove elements that are present at the end of the collection.
     *
     * @test.design This test is designed to ensure that the ValueCollection can
     * remove elements from the underlying map.
     * @test.description This test checks that calling remove(Object) on a
     * contained element removes it from the collection and that the collection
     * size is reduced accordingly.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should be able to remove
     * elements from the map.
     * @test.expectedresults The test should pass if remove(Object) works as
     * expected and the collection size is reduced.
     */
    @Test
    public void testRemoveContainedEnd() {
        Object value = "value49";

        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertTrue("remove(Object) should return true for contained elements",
                values.remove(value));
        assertFalse("Value collection should not contain the removed element",
                values.contains(value));
        assertEquals("Map should have size 98 after removing two elements", 98, map.size());
    }

    // ValueCollection.containsAll(HCollection)

    /**
     * Test to verify that containsAll(HCollection) throws NullPointerException
     * when null is passed.
     *
     * @test.design This test checks that the containsAll method throws a
     * NullPointerException when null is passed.
     * @test.description This test ensures that the ValueCollection does not
     * allow checking containment against null.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if containsAll(HCollection)
     * throws a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNull() {
        values.containsAll(null);
    }

    /**
     * Test to verify that containsAll(HCollection) works correctly when some
     * elements are contained.
     *
     * @test.design This test checks that the containsAll method returns true
     * when some elements are contained.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify containment of some elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if containsAll(HCollection)
     * works as expected for some elements.
     */
    @Test
    public void testContainsAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value0");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Value collection should contain all elements of another value collection with some elements",
                values.containsAll(anotherValueCollection));
    }

    /**
     * Test to verify that containsAll(HCollection) works correctly when all
     * elements are contained.
     *
     * @test.design This test checks that the containsAll method returns true
     * when all elements are contained.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify containment of all elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if containsAll(HCollection)
     * works as expected for all elements.
     */
    @Test
    public void testContainsAllSameElements() {
        MapAdapter anotherMap = new MapAdapter();
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Value collection should contain all elements of the same value collection",
                values.containsAll(anotherValueCollection));
    }

    /**
     * Test to verify that containsAll(HCollection) works correctly when more
     * elements are contained.
     *
     * @test.design This test checks that the containsAll method returns false
     * when more elements are contained.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify containment when more elements are present.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if containsAll(HCollection)
     * works as expected when more elements are contained.
     */
    @Test
    public void testContainsAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();
        assertFalse("Value collection should not contain all elements of another value collection with more elements",
                values.containsAll(anotherValueCollection));
    }

    /**
     * Test to verify that containsAll(HCollection) works correctly when no
     * common elements are present.
     *
     * @test.design This test checks that the containsAll method returns false
     * when there are no common elements.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify containment when there are no common elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if containsAll(HCollection)
     * works as expected when no common elements are present.
     */
    @Test
    public void testContainsAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertFalse("Value collection should not contain all elements of another value collection with no common elements",
                values.containsAll(anotherValueCollection));
    }

    // ValueCollection.addAll(HCollection)

    /**
     * Test to verify that addAll(HCollection) throws
     * UnsupportedOperationException.
     *
     * @test.design This test checks that the addAll method throws an
     * UnsupportedOperationException when called.
     * @test.description This test ensures that the ValueCollection does not
     * allow adding multiple elements at once.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if addAll(HCollection) throws
     * an UnsupportedOperationException.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        MapAdapter anotherMap = new MapAdapter();
        values.addAll(anotherMap.values());
    }

    // ValueCollection.retainAll(HCollection)

    /**
     * Test to verify that retainAll(HCollection) throws NullPointerException
     * when null is passed.
     *
     * @test.design This test checks that the retainAll method throws a
     * NullPointerException when null is passed.
     * @test.description This test ensures that the ValueCollection does not
     * allow retaining elements against null.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if retainAll(HCollection)
     * throws a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNull() {
        values.retainAll(null);
    }

    /**
     * Test to verify that retainAll(HCollection) works correctly when the same
     * elements are retained.
     *
     * @test.design This test checks that the retainAll method returns false
     * when the same elements are retained.
     * @test.description This test ensures that the ValueCollection does not
     * change when retaining the same elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if retainAll(HCollection)
     * returns false for the same elements.
     */
    @Test
    public void testRetainAllSameElements() {
        MapAdapter anotherMap = new MapAdapter(map);
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain all elements that are in the same value collection
        assertFalse("RetainAll should have no effect with same elements",
                values.retainAll(anotherValueCollection));

        // Verify that the value collection is unchanged
        assertEquals("Size of value collection should remain 100 after retainAll with same elements",
                100, values.size());
    }

    /**
     * Test to verify that retainAll(HCollection) works correctly when some
     * elements are retained.
     *
     * @test.design This test checks that the retainAll method returns true when
     * some elements are retained.
     * @test.description This test ensures that the ValueCollection can
     * correctly retain some elements from another collection.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should retain only the specified
     * elements after this test runs.
     * @test.expectedresults The test should pass if retainAll(HCollection)
     * works as expected for some elements.
     */
    @Test
    public void testRetainAllSomeElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key0", "value0");
        anotherMap.put("key50", "value50");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain only the elements that are in the another value collection
        assertTrue("RetainAll should return true for some elements",
                values.retainAll(anotherValueCollection));

        // Verify that the size is reduced to 2
        assertEquals("Size of value collection should be 2 after retainAll with some elements",
                2, values.size());
    }

    /**
     * Test to verify that retainAll(HCollection) works correctly when more
     * elements are retained.
     *
     * @test.design This test checks that the retainAll method returns false
     * when more elements are retained.
     * @test.description This test ensures that the ValueCollection does not
     * change when retaining more elements than present.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if retainAll(HCollection)
     * returns false for more elements.
     */
    @Test
    public void testRetainAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + i);
        }
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain only the elements that are in the another value collection
        assertFalse("RetainAll should return false for more elements",
                values.retainAll(anotherValueCollection));

        // Verify that the size remains unchanged
        assertEquals("Size of value collection should remain 100 after retainAll with more elements",
                100, values.size());
    }

    /**
     * Test to verify that retainAll(HCollection) works correctly when no common
     * elements are present.
     *
     * @test.design This test checks that the retainAll method returns true when
     * there are no common elements.
     * @test.description This test ensures that the ValueCollection can
     * correctly retain no elements from another collection.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should be empty after this test
     * runs.
     * @test.expectedresults The test should pass if retainAll(HCollection)
     * works as expected when no common elements are present.
     */
    @Test
    public void testRetainAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Retain only the elements that are in the another value collection
        assertTrue("RetainAll should return true for no common elements",
                values.retainAll(anotherValueCollection));

        // Verify that the size is reduced to 0
        assertEquals("Size of value collection should be 0 after retainAll with no common elements",
                0, values.size());
    }

    // ValueCollection.removeAll(HCollection)

    /**
     * Test to verify that removeAll(HCollection) throws NullPointerException
     * when null is passed.
     *
     * @test.design This test checks that the removeAll method throws a
     * NullPointerException when null is passed.
     * @test.description This test ensures that the ValueCollection does not
     * allow removing elements against null.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if removeAll(HCollection)
     * throws a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNull() {
        values.removeAll(null);
    }

    /**
     * Test to verify that removeAll(HCollection) works correctly when the same
     * elements are removed.
     *
     * @test.design This test checks that the removeAll method returns false
     * when the same elements are removed.
     * @test.description This test ensures that the ValueCollection does not
     * change when removing the same elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if removeAll(HCollection)
     * returns false for the same elements.
     */
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

    /**
     * Test to verify that removeAll(HCollection) works correctly when some
     * elements are removed.
     *
     * @test.design This test checks that the removeAll method returns true when
     * some elements are removed.
     * @test.description This test ensures that the ValueCollection can
     * correctly remove some elements from another collection.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should retain only the specified
     * elements after this test runs.
     * @test.expectedresults The test should pass if removeAll(HCollection)
     * works as expected for some elements.
     */
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

    /**
     * Test to verify that removeAll(HCollection) works correctly when more
     * elements are removed.
     *
     * @test.design This test checks that the removeAll method returns true when
     * more elements are removed.
     * @test.description This test ensures that the ValueCollection can
     * correctly remove more elements than present.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should retain only the specified
     * elements after this test runs.
     * @test.expectedresults The test should pass if removeAll(HCollection)
     * works as expected for more elements.
     */
    @Test
    public void testRemoveAllMoreElements() {
        MapAdapter anotherMap = new MapAdapter();
        for (int i = 0; i < 200; i++) {
            anotherMap.put("key" + i, "value" + (i % 50)); // Values repeat every 50 keys
        }
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Remove only the elements that are in the another key set
        assertTrue("RemoveAll should return true for more elements",
                values.removeAll(anotherValueCollection));

        // Verify that the size remains unchanged
        assertEquals("Size of value collection should remain 100 after removeAll with more elements",
                0, values.size());
    }

    /**
     * Test to verify that removeAll(HCollection) works correctly when no common
     * elements are present.
     *
     * @test.design This test checks that the removeAll method returns false
     * when there are no common elements.
     * @test.description This test ensures that the ValueCollection does not
     * change when removing elements with no common elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if removeAll(HCollection)
     * works as expected when no common elements are present.
     */
    @Test
    public void testRemoveAllNoCommonElements() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key200", "value200");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // Remove only the elements that are in the another key set
        assertFalse("RemoveAll should return false for no common elements",
                values.removeAll(anotherValueCollection));

        // Verify that the size is reduced to 0
        assertEquals("Size of value collection should be 0 after removeAll with no common elements",
                100, values.size());
    }

    // ValueCollection.clear()

    /**
     * Test to verify that clear() empties the ValueCollection.
     *
     * @test.design This test checks that the clear method empties the
     * ValueCollection.
     * @test.description This test ensures that calling clear() on the
     * ValueCollection results in an empty collection.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should be empty after this test
     * runs.
     * @test.expectedresults The test should pass if clear() empties the
     * ValueCollection and the underlying map.
     */
    @Test
    public void testClear() {
        // Clear the key set
        values.clear();

        // Verify that the size is still 0
        assertEquals("Size of value collection should be 0 after clear", 0, values.size());
        assertTrue("Value collection should be empty after clear", values.isEmpty());

        // Verify that the map is still empty
        assertEquals("Map should have size 0 after clearing key set", 0, map.size());
        assertTrue("Map should be after clearing key set", map.isEmpty());
    }

    // ValueCollection.equals(Object)

    /**
     * Test to verify that equals(Object) works correctly for the same
     * collection.
     *
     * @test.design This test checks that the equals method returns true when
     * comparing with the same collection.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify equality with another instance of the same
     * collection.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if equals(Object) returns
     * true for the same collection.
     */
    @Test
    public void testEqualsSameCollection() {
        MapAdapter anotherMap = new MapAdapter(map);
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertTrue("Value collection should be equal to another value collection with the same entries",
                values.equals(anotherValueCollection));
    }

    /**
     * Test to verify that equals(Object) works correctly for an empty
     * collection.
     *
     * @test.design This test checks that the equals method returns true when
     * comparing with an empty collection.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify equality with an empty collection.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if equals(Object) returns
     * true for an empty collection.
     */
    @Test
    public void testEqualsDifferentCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        assertFalse("Value collection should not be equal to another value collection with different entries",
                values.equals(anotherValueCollection));
    }

    /**
     * Test to verify that equals(Object) works correctly for a different
     * collection with the same size.
     *
     * @test.design This test checks that the equals method returns false when
     * comparing with a different collection of the same size.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify inequality with another collection of the same size
     * but different elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if equals(Object) returns
     * false for a different collection of the same size.
     */
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

    /**
     * Test to verify that equals(Object) works correctly for a collection with
     * the same elements but different order.
     *
     * @test.design This test checks that the equals method returns true when
     * comparing with a collection of the same elements in a different order.
     * @test.description This test ensures that the ValueCollection can
     * correctly identify equality with another collection of the same elements
     * but in a different order.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if equals(Object) returns
     * true for a collection with the same elements in a different order.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsNotACollection() {
        // Test that the equals method returns false when the argument is not an ValueCollection
        String notACollection = "This is not a set";
        assertFalse("Value collection should not be equal to a non-ValueCollection object",
                values.equals(notACollection));
    }

    /**
     * Test to verify that equals(Object) returns false when a Set is passed.
     *
     * @test.design This test checks that the equals method returns false when
     * comparing with an HSet instance.
     * @test.description This test ensures that the ValueCollection correctly
     * identifies inequality with an HSet, following the general contract that
     * collections are only equal to other collections of the same type.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if equals(Object) returns
     * false when a Set is provided as argument.
     */
    @Test
    public void testEqualsSet() {
        assertFalse("Value collection should not be equal to a Set object",
                values.equals(map.keySet()));
    }

    // ValueCollection.hashCode()

    /**
     * Test to verify that hashCode() returns the same value for the same
     * collection.
     *
     * @test.design This test checks that the hashCode method returns the same
     * value for the same collection.
     * @test.description This test ensures that the ValueCollection can
     * correctly compute its hash code based on its elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if hashCode() returns the
     * same value for the same collection.
     */
    @Test
    public void testHashCodeDifferentCollections() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", "value");
        ValueCollection anotherValueCollection = (ValueCollection) anotherMap.values();

        // The hash codes of the two key sets should be different
        assertNotEquals("Hash codes of different value collections should not be equal",
                values.hashCode(), anotherValueCollection.hashCode());
    }

    /**
     * Test to verify that hashCode() returns the same value for the same
     * collection.
     *
     * @test.design This test checks that the hashCode method returns the same
     * value for the same collection.
     * @test.description This test ensures that the ValueCollection can
     * correctly compute its hash code based on its elements.
     * @test.precondition The ValueCollection must be populated before this
     * test runs.
     * @test.postcondition The ValueCollection should remain unchanged after
     * this test runs.
     * @test.expectedresults The test should pass if hashCode() returns the
     * same value for the same collection.
     */
    @Test
    public void testHashCodeSameCollection() {
        // Create another instance of the same key set
        ValueCollection anotherValueCollection = (ValueCollection) map.values();
        assertEquals("Hash codes of the same value collection should be equal",
                values.hashCode(), anotherValueCollection.hashCode());
    }

    /**
     * Tests that the {@link MapAdapter#values()} {@code retainAll} method
     * handles a collection containing null elements safely (by treating them as not present).
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#values()} {@code retainAll} method does not throw
     * exception when called with a collection containing
     * null elements.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null value. The {@code retainAll} method is then called.
     * Since the null value matches nothing in the map, all elements
     * should be removed.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null values.
     * @test.postcondition The map is empty.
     * @test.expectedresults The {@code retainAll} method completes without exception
     * and empties the map.
     */
    @Test
    public void testValuesRetainAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put("key", null);
        map.values().retainAll(nullableMap.values());
        assertTrue("Map should be empty after retaining only null values", map.isEmpty());
    }

    /**
     * Tests that the {@link MapAdapter#values()} {@code removeAll} returns false
     * when called with a collection containing null elements.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#values()} {@code removeAll} returns false
     * when called with a collection containing null elements.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null value. The {@code removeAll} method is then called
     * on the values collection of the populated map with the values collection
     * of the NullableHMap as argument. The result is asserted to be false,
     * as null elements are ignored or treated as not present.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null values.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@code removeAll} method returns false
     * when called with a collection containing null elements.
     */
    @Test
    public void testValuesRemoveAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put("key", null);
        assertFalse(map.values().removeAll(nullableMap.values()));
    }

    /**
     * Tests that the {@link MapAdapter#values()} {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing null elements.
     *
     * @test.design The test aims to verify that the
     * {@link MapAdapter#values()} {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements, as null elements are not supported by the MapAdapter.
     * @test.description A {@link NullableHMap} instance is created and
     * populated with a null value. The {@code containsAll} method is then called
     * on the values collection of the populated map with the values collection
     * of the NullableHMap as argument. The result is asserted to throw a
     * {@link NullPointerException}, as null elements are not supported.
     * @test.precondition The map is correctly instantiated and populated with
     * 100 items. A NullableHMap is created with null values.
     * @test.postcondition The map is unchanged
     * @test.expectedresults The {@code containsAll} method throws
     * {@link NullPointerException} when called with a collection containing
     * null elements.
     */
    @Test(expected = NullPointerException.class)
    public void testValuesContainsAllWithNullElements() {
        NullableHMap nullableMap = new NullableHMap();
        nullableMap.put("key", null);
        map.values().containsAll(nullableMap.values());
    }
}