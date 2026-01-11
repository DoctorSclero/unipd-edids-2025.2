package myTest;

import myAdapter.HCollection;
import myAdapter.HMap;
import myAdapter.HSet;
import myAdapter.HIterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    public HSet entrySet() {
        return new SimpleHEntrySet();
    }
    
    public HSet keySet() {
        return new SimpleHKeySet();
    }

    public HCollection values() {
        return new SimpleHValueCollection();
    }

    public int size() { throw new UnsupportedOperationException("size not supported"); }
    public boolean isEmpty() { throw new UnsupportedOperationException("isEmpty not supported"); }
    public boolean containsKey(Object key) { throw new UnsupportedOperationException("containsKey not supported"); }
    public boolean containsValue(Object value) { throw new UnsupportedOperationException("containsValue not supported"); }
    public Object get(Object key) { throw new UnsupportedOperationException("get not supported"); }
    public Object remove(Object key) { throw new UnsupportedOperationException("remove not supported"); }
    public void putAll(HMap m) { throw new UnsupportedOperationException("putAll not supported"); }
    public void clear() { throw new UnsupportedOperationException("clear not supported"); }
    public boolean equals(Object o) { throw new UnsupportedOperationException("equals not supported"); }
    public int hashCode() { throw new UnsupportedOperationException("hashCode not supported"); }
    public String toString() { throw new UnsupportedOperationException("toString not supported"); }

    public static class SimpleHEntry implements HMap.HEntry {
        private Object key;
        private Object value;

        public SimpleHEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() { return key; }
        public Object getValue() { return value; }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof HMap.HEntry)) return false;
            HMap.HEntry that = (HMap.HEntry) o;
            return (key == null ? that.getKey() == null : key.equals(that.getKey()))
                    && (value == null ? that.getValue() == null : value.equals(that.getValue()));
        }

        public Object setValue(Object value) { throw new UnsupportedOperationException("setValue not supported"); }
        public int hashCode() { throw new UnsupportedOperationException("hashCode not supported"); }
        public String toString() { throw new UnsupportedOperationException("toString not supported"); }
    }

    private class SimpleHEntrySet implements HSet {
        private SimpleHEntrySet() {}

        public HIterator iterator() { return new SimpleEntrySetHIterator(); }

        public boolean contains(Object o) {
             if (!(o instanceof HMap.HEntry)) return false;
             HMap.HEntry e = (HMap.HEntry) o;
             Object key = e.getKey();
             Object value = e.getValue();
             if (!map.containsKey(key)) return false;
             Object mapValue = map.get(key);
             return (value == null ? mapValue == null : value.equals(mapValue));
        }

        public int size() { throw new UnsupportedOperationException("size not supported"); }
        public boolean isEmpty() { throw new UnsupportedOperationException("isEmpty not supported"); }
        public Object[] toArray() { throw new UnsupportedOperationException("toArray not supported"); }
        public Object[] toArray(Object[] a) { throw new UnsupportedOperationException("toArray not supported"); }
        public boolean add(Object e) { throw new UnsupportedOperationException("add not supported"); }
        public boolean remove(Object o) { throw new UnsupportedOperationException("remove not supported"); }
        public boolean containsAll(HCollection c) { throw new UnsupportedOperationException("containsAll not supported"); }
        public boolean addAll(HCollection c) { throw new UnsupportedOperationException("addAll not supported"); }
        public boolean removeAll(HCollection c) { throw new UnsupportedOperationException("removeAll not supported"); }
        public boolean retainAll(HCollection c) { throw new UnsupportedOperationException("retainAll not supported"); }
        public void clear() { throw new UnsupportedOperationException("clear not supported"); }
        public boolean equals(Object o) { throw new UnsupportedOperationException("equals not supported"); }
        public int hashCode() { throw new UnsupportedOperationException("hashCode not supported"); }
        public String toString() { throw new UnsupportedOperationException("toString not supported"); }
    }

    protected class SimpleEntrySetHIterator implements HIterator {
        private Iterator<Map.Entry<Object, Object>> i;
        private SimpleEntrySetHIterator() { i = map.entrySet().iterator(); }
        public boolean hasNext() { return i.hasNext(); }
        public Object next() {
            Map.Entry<Object, Object> entry = i.next();
            return new SimpleHEntry(entry.getKey(), entry.getValue());
        }
        public void remove() { throw new UnsupportedOperationException("remove not supported"); }
    }
    
    private class SimpleHKeySet implements HSet {
        public HIterator iterator() {
            final Iterator<Object> i = map.keySet().iterator();
            return new HIterator() {
                public boolean hasNext() { return i.hasNext(); }
                public Object next() { return i.next(); }
                public void remove() { i.remove(); }
            };
        }
        
        public boolean contains(Object o) { return map.containsKey(o); }

        public int size() { throw new UnsupportedOperationException(); }
        public boolean isEmpty() { throw new UnsupportedOperationException(); }
        public Object[] toArray() { throw new UnsupportedOperationException(); }
        public Object[] toArray(Object[] a) { throw new UnsupportedOperationException(); }
        public boolean add(Object o) { throw new UnsupportedOperationException(); }
        public boolean remove(Object o) { throw new UnsupportedOperationException(); }
        public boolean containsAll(HCollection c) { throw new UnsupportedOperationException(); }
        public boolean addAll(HCollection c) { throw new UnsupportedOperationException(); }
        public boolean retainAll(HCollection c) { throw new UnsupportedOperationException(); }
        public boolean removeAll(HCollection c) { throw new UnsupportedOperationException(); }
        public void clear() { throw new UnsupportedOperationException(); }
        public boolean equals(Object o) { throw new UnsupportedOperationException(); }
        public int hashCode() { throw new UnsupportedOperationException(); }
    }

    private class SimpleHValueCollection implements HCollection {
        public HIterator iterator() {
            final Iterator<Object> i = map.values().iterator();
            return new HIterator() {
                public boolean hasNext() { return i.hasNext(); }
                public Object next() { return i.next(); }
                public void remove() { i.remove(); }
            };
        }

        public boolean contains(Object o) { return map.containsValue(o); }
        
        public int size() { throw new UnsupportedOperationException(); }
        public boolean isEmpty() { throw new UnsupportedOperationException(); }
        public Object[] toArray() { throw new UnsupportedOperationException(); }
        public Object[] toArray(Object[] a) { throw new UnsupportedOperationException(); }
        public boolean add(Object o) { throw new UnsupportedOperationException(); }
        public boolean remove(Object o) { throw new UnsupportedOperationException(); }
        public boolean containsAll(HCollection c) { throw new UnsupportedOperationException(); }
        public boolean addAll(HCollection c) { throw new UnsupportedOperationException(); }
        public boolean retainAll(HCollection c) { throw new UnsupportedOperationException(); }
        public boolean removeAll(HCollection c) { throw new UnsupportedOperationException(); }
        public void clear() { throw new UnsupportedOperationException(); }
        public boolean equals(Object o) { throw new UnsupportedOperationException(); }
        public int hashCode() { throw new UnsupportedOperationException(); }
    }
}