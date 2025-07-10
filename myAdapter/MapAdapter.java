package myAdapter;

import myCompatibilityLayer.MockHashTable;

/**
 * Note: this implementation does not support null values and keys
 * since the underlying hash table does not support them.
 */
public class MapAdapter implements HMap {

    // Methods //

    private MockHashTable hashTable;

    // Constructors //

    /**
     * MapAdapter default constructor.
     * Creates a new instance of the hash table for storing.
     */
    public MapAdapter() {
        this.hashTable = new MockHashTable();
    }

    /**
     * MapAdapter constructor.
     * Creates a new instance of the hash table given an initial capacity.
     * @param initialCapacity The initial capacity of the hash table
     */
    public MapAdapter(int initialCapacity) {
        this.hashTable = new MockHashTable(initialCapacity);
    }

    /**
     * MapAdapter copy constructor.
     * Creates a new instance of the hash table given another HMap object.
     * The content of the passed map is shallow copied inside the underlying
     * structure, changes in the passed HMap's values and keys will then
     * result in changes to the new created copied instance.
     * @param map The map to copy the data from.
     */
    public MapAdapter(HMap map) {
        this.hashTable = new MockHashTable();
        putAll(map);
    }

    // Methods //

    public void clear() {
        hashTable.clear();
    }

    public boolean containsValue(Object value) {
        return hashTable.contains(value);
    }

    /**
     * @throws NullPointerException if key is null since null values are
     * not supported from the underlying hash table.
     */
    public boolean containsKey(Object key) {
        if (key == null) throw new NullPointerException();
        return hashTable.containsKey(key);
    }

    public HSet entrySet() {
        // TODO: implementation
        return null;
    }

    public boolean equals(Object o) {
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
        if (key == this) throw new IllegalArgumentException();
        return hashTable.put(key, value);
    }

    public void putAll(HMap m) {
        if (m == null) throw new NullPointerException();

        HSet entrySet = m.entrySet();
        HIterator iter = entrySet.iterator();
        while(iter.hasNext()) {
            Entry current = (Entry)iter.next();
            put(current.getKey(), current.getValue());
        }
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
     * Provides a set like view for map entries, values and keys.
     * for all view methods (listed in the 'see also' section
     * of the documentation for this class).
     * @see MapAdapter#entrySet()
     * @see MapAdapter#keySet()
     * @see MapAdapter#values()
     */
    public class SetView implements HSet {

        @Override
        public boolean add(Object o) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }

        @Override
        public boolean addAll(HCollection c) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addAll'");
        }

        @Override
        public void clear() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'clear'");
        }

        @Override
        public boolean contains(Object o) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'contains'");
        }

        @Override
        public boolean containsAll(HCollection c) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
        }

        @Override
        public boolean isEmpty() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
        }

        @Override
        public HIterator iterator() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'iterator'");
        }

        @Override
        public boolean remove(Object o) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'remove'");
        }

        @Override
        public boolean removeAll(HCollection c) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
        }

        @Override
        public boolean retainAll(HCollection c) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
        }

        @Override
        public int size() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'size'");
        }

        @Override
        public Object[] toArray() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'toArray'");
        }

        @Override
        public Object[] toArray(Object[] a) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'toArray'");
        }

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
