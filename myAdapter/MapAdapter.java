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
        //! Must check if it's a proper set
        if (!(o instanceof MapAdapter)) {
            return false;
        }
        return this.entrySet().equals(((MapAdapter)o).entrySet());
    }

    public Object get(Object key) {
        return hashTable.get(key);
    }

    public int hashCode() {
        // TODO: implementation
        return 0;
    }

    public boolean isEmpty() {
        return hashTable.isEmpty();
    }

    public HSet keySet() {
        // TODO: implementation
        return null;
    }

    public Object put(Object key, Object value) {
        return hashTable.put(key, value);
    }

    public void putAll(HMap m) {
        // TODO: implementation
    }

    public Object remove(Object key) {
        return hashTable.remove(key);
    }

    public int size() {
        return hashTable.size();
    }

    public HCollection values() {
        // TODO: implementation
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

        /**
         * Replaces the value corresponding to this entry with the specified value.
         * @throws NullPointerException if the value is null, due to hash table incompatibility
         * for null values.
         */
        @Override
        public Object setValue(Object value) {
            return hashTable.put(this.key, value);
        }

        @Override
        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^
            (this.getValue() == null ? 0 : this.getValue().hashCode());
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof HMap.HEntry)) { return false; }

            HMap.HEntry e = (HMap.HEntry) o;
            return this.getKey().equals(e.getKey()) && this.getValue().equals(e.getValue());
        }
    }
}
