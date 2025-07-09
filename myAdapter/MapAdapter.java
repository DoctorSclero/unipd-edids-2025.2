package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;

import myCompatibilityLayer.MockHashTable;

public class MapAdapter implements HMap {

    private MockHashTable hashTable;
    
    public MapAdapter() {
        this.hashTable = new MockHashTable();
    }

    public MapAdapter(int initialCapacity) {
        this.hashTable = new MockHashTable(initialCapacity);
    }

    public void clear() {
        hashTable.clear();
    }

    public boolean containsValue(Object value) {
        return hashTable.contains(value);
    }

    public boolean containsKey(Object key) {
        return hashTable.containsKey(key);
    }

    public HSet entrySet() {
        return null;
    }

    public boolean equals(Object o) {
        return hashTable.equals(o);
    }

    public Object get(Object key) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public HSet keySet() {
        return null;
    }

    public Object put(Object key, Object value) {
        return null;
    }

    public void putAll(HMap m) {
        
    }

    public Object remove(Object key) {
        return null;
    }

    public int size() {
        return 0;
    }

    public HCollection values() {
        return null;
    }

    /**
     * {@code MapAdapter}'s entry implementation.
     * This class represents a key-value pair in the map.
     */
    public class Entry implements HMap.HEntry {
        private Object key;
        private Object value;

        private Entry() {
            this.key = null;
            this.value = null;
        }

        private Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        private Entry(Entry e) {
            this.key = e.getKey();
            this.value = e.getValue();
        }

        @Override
        public Object getKey() {
            return this.key;
        }

        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public Object setValue(Object value) {
            this.value = value;
            return this.value;
        }

        @Override
        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^
            (this.getValue()== null ? 0 : this.getValue().hashCode());
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof HMap.HEntry)) { return false; }

            HMap.HEntry e = (HMap.HEntry) o;
            return  (this.getKey()==null ? e.getKey()==null : this.getKey().equals(e.getKey())) &&
                    (this.getValue()==null ? e.getValue()==null : this.getValue().equals(e.getValue()));
        }
    }
}
