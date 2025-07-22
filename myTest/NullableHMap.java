package myTest;

import myAdapter.HCollection;
import myAdapter.HMap;
import myAdapter.HSet;
import myAdapter.HIterator;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A custom implementation of the {@link HMap} interface that allows null keys and values.
 * This implementation wraps a {@link HashMap} and provides additional functionality
 * through nested classes for entry sets and iterators all for testing purposes.
 * 
 * Note: Many methods throw {@link UnsupportedOperationException} as they are not supported
 * in this implementation.
 */
public class NullableHMap implements HMap {
    private HashMap<Object, Object> map;

    /**
     * Constructs an empty {@code NullableHMap}.
     */
    public NullableHMap() {
        map = new HashMap<Object, Object>();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * 
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or {@code null} if there was no mapping
     */
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    /**
     * Returns a set view of the mappings contained in this map.
     * 
     * @return a set view of the mappings contained in this map
     */
    public HSet entrySet() {
        return new SimpleHEntrySet();
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public int size() {
        throw new UnsupportedOperationException("size not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @param key the key whose presence in this map is to be tested
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("containsKey not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @param value the value whose presence in this map is to be tested
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("containsValue not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @param key the key whose associated value is to be returned
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public Object get(Object key) {
        throw new UnsupportedOperationException("get not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @param key the key whose mapping is to be removed from the map
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public Object remove(Object key) {
        throw new UnsupportedOperationException("remove not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @param m the map whose mappings are to be copied to this map
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public void putAll(HMap m) {
        throw new UnsupportedOperationException("putAll not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public void clear() {
        throw new UnsupportedOperationException("clear not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public HSet keySet() {
        throw new UnsupportedOperationException("keySet not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public HCollection values() {
        throw new UnsupportedOperationException("values not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @param o the object to be compared with this map for equality
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("equals not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode not supported for SimpleHMapWithNulls");
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     * 
     * @return nothing, as this method is unsupported
     * @throws UnsupportedOperationException always
     */
    public String toString() {
        throw new UnsupportedOperationException("toString not supported for SimpleHMapWithNulls");
    }

    /**
     * A simple implementation of the {@link HMap.HEntry} interface.
     * Represents a key-value pair in the map.
     */
    public static class SimpleHEntry implements HMap.HEntry {
        private Object key;
        private Object value;

        /**
         * Constructs a new entry with the specified key and value.
         * 
         * @param key   the key of the entry
         * @param value the value of the entry
         */
        public SimpleHEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns the key corresponding to this entry.
         * 
         * @return the key corresponding to this entry
         */
        public Object getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.
         * 
         * @return the value corresponding to this entry
         */
        public Object getValue() {
            return value;
        }

        /**
         * Compares the specified object with this entry for equality.
         * 
         * @param o the object to be compared for equality with this entry
         * @return {@code true} if the specified object is equal to this entry
         */
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof HMap.HEntry))
                return false;
            HMap.HEntry that = (HMap.HEntry) o;
            return (key == null ? that.getKey() == null : key.equals(that.getKey()))
                    && (value == null ? that.getValue() == null : value.equals(that.getValue()));
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param value the new value to be stored in this entry
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public Object setValue(Object value) {
            throw new UnsupportedOperationException("setValue not supported for SimpleHEntry");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for SimpleHEntry");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public String toString() {
            throw new UnsupportedOperationException("toString not supported for SimpleHEntry");
        }
    }

    /**
     * A simple implementation of the {@link HSet} interface for the entry set view.
     */
    private class SimpleHEntrySet implements HSet {

        private SimpleHEntrySet() {
        }

        /**
         * Returns an iterator over the elements in this set.
         * 
         * @return an iterator over the elements in this set
         */
        public HIterator iterator() {
            return new SimpleEntrySetHIterator();
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public int size() {
            throw new UnsupportedOperationException("size not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param o the object to be tested for containment in this set
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean contains(Object o) {
            throw new UnsupportedOperationException("contains not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param a the array into which the elements of this set are to be stored
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param e the element to be added to this set
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean add(Object e) {
            throw new UnsupportedOperationException("add not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param o the element to be removed from this set
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param c the collection containing elements to be tested for containment in this set
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param c the collection containing elements to be added to this set
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param c the collection containing elements to be removed from this set
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param c the collection containing elements to be retained in this set
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public void clear() {
            throw new UnsupportedOperationException("clear not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @param o the object to be compared with this set for equality
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for SimpleHEntrySet view");
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @return nothing, as this method is unsupported
         * @throws UnsupportedOperationException always
         */
        public String toString() {
            throw new UnsupportedOperationException("toString not supported for SimpleHEntrySet view");
        }
    }

    /**
     * A simple implementation of the {@link HIterator} interface for iterating over the entry set.
     */
    protected class SimpleEntrySetHIterator implements HIterator {
        private Iterator<Map.Entry<Object, Object>> i;

        private SimpleEntrySetHIterator() {
            i = map.entrySet().iterator();
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * 
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            return i.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         * 
         * @return the next element in the iteration
         */
        public Object next() {
            Map.Entry<Object, Object> entry = i.next();
            return new SimpleHEntry(entry.getKey(), entry.getValue());
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         * 
         * @throws UnsupportedOperationException always
         */
        public void remove() {
            throw new UnsupportedOperationException("remove not supported for SimpleHIterator");
        }
    }
}
