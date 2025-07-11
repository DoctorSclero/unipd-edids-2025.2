package myCompatibilityLayer;

import java.util.Enumeration;

/**
 * Mock implementation of a HashTable for compatibility purposes.
 * This class is not intended for production use and does not implement
 * any real functionality. It serves as a placeholder to satisfy type requirements
 * in the codebase where a HashTable is expected.
 * 
 * It is used to limit the methods available in the MapAdapter class, ensuring
 * that only the methods defined 
 */
public class MockHashTable {
    /**
     * Constructs a new, empty hashtable with a default capacity and load factor.
     * @since JDK1.0
     */
    public MockHashTable() { }

    /**
     * Constructs a new, empty hashtable with the specified initial capacity.
     * @param initialCapacity the initial capacity of the hashtable.
     * @throws IllegalArgumentException if the initial capacity is less than zero.
     * @since JDK1.0
     */
    public MockHashTable(int initialCapacity) { }

    /**
     * Clears this hashtable so that it contains no keys.
     * @since JDK1.0
     */
    public void clear() { }

    /**
     * Tests if some key maps into the specified value in this hashtable.
     * @param value a value to search for.
     * @return true if some key maps to the value argument in this hashtable; false otherwise.
     * @throws NullPointerException if the value is null.
     * @since JDK1.0
     */
    public boolean contains(Object value) { return false; }

    /**
     * Tests if the specified object is a key in this hashtable.
     * @param key possible key.
     * @return true if the specified object is a key in this hashtable; false otherwise.
     * @since JDK1.0
     */
    public boolean containsKey(Object key) { return false; }

    /**
     * Returns an enumeration of the values in this hashtable.
     * @return an enumeration of the values in this hashtable.
     * @since JDK1.0
     */
    public Enumeration<Object> elements() { return null; }

    /**
     * Returns the value to which the specified key is mapped in this hashtable.
     * @param key a key in the hashtable.
     * @return the value to which the key is mapped in this hashtable; null if the key is not mapped to any value in this hashtable.
     * @since JDK1.0
     */
    public Object get(Object key) { return null; }

    /**
     * Tests if this hashtable maps no keys to values.
     * @return true if this hashtable maps no keys to values; false otherwise.
     * @since JDK1.0
     */
    public boolean isEmpty() { return true; }

    /**
     * Returns an enumeration of the keys in this hashtable.
     * @return an enumeration of the keys in this hashtable.
     * @since JDK1.0
     */
    public Enumeration<Object> keys() { return null; }

    /**
     * Maps the specified key to the specified value in this hashtable.
     * @param key the hashtable key.
     * @param value the value.
     * @return the previous value of the specified key in this hashtable, or null if it did not have one.
     * @throws NullPointerException if the key or value is null.
     * @since JDK1.0
     */
    public Object put(Object key, Object value) { return null; }

    /**
     * Rehashes the contents of the hashtable into a hashtable with a larger capacity.
     * This method is called automatically when the number of keys in the hashtable exceeds this hashtable's capacity and load factor.
     * @since JDK1.0
     */
    protected void rehash() { return; }

    /**
     * Removes the key (and its corresponding value) from this hashtable.
     * @param key the key that needs to be removed.
     * @return the value to which the key had been mapped in this hashtable, or null if the key did not have a mapping.
     * @since JDK1.0
     */
    public Object remove(Object key) { return null; }

    /**
     * Returns the number of keys in this hashtable.
     * @return the number of keys in this hashtable.
     * @since JDK1.0
     */
    public int size() { return 0; }

    /**
     * Returns a string representation of this hashtable.
     * @return a string representation of this hashtable.
     * @since JDK1.0
     */
    public String toString() { return "HashTable"; }
}