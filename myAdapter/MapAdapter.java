package myAdapter;

import myCompatibilityLayer.MockHashTable;

import java.util.Enumeration;

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
     * The content of the passed map is shallowly copied inside the underlying
     * structure, changes in the past HMap's values and keys will then cord
     * result in changes to the new created copied instance.
     * 
     * @param map The map to copy the data from.
     */
    public MapAdapter(HMap map) {
        this.hashTable = new MockHashTable();
        putAll(map);
    }

    // Methods //

    /**
     * Removes all mappings from this map. (optional operation)
     * The method clears the underlying hash table using its clear method.
     * After this call, the map will be empty.
     */
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
        return new EntrySet();
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
        return entrySet().hashCode();
    }

    public boolean isEmpty() {
        return hashTable.isEmpty();
    }

    public HSet keySet() {
        return new KeySet();
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
        return new ValueCollection();
    }

    // Inner classes //

    public abstract class AbstractView implements HCollection {

        /**
         * This method is not supported by this hashmap set view.
         * @throws UnsupportedOperationException if called.
         */
        @Override
        public boolean add(Object o) {
            throw new UnsupportedOperationException("Method 'add' is not supported by this set view.");
        }

        /**
         * This method is not supported by this hashmap set view.
         * @throws UnsupportedOperationException if called.
         */
        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("Method 'addAll' is not supported by this set view.");
        }

        /**
         * Removes all entries from the set view. Since this set
         * is backed by {@link MapAdapter} removing all entries from the set view
         * results in the removal of all entries from the map.
         */
        @Override
        public void clear() {
            hashTable.clear();
        }

        /**
         * This method checks if the {@link MapAdapter.EntrySet} is
         * empty. Since it's backed by the {@link MapAdapter} the view
         * is empty if the underlying hash table is empty.
         */
        @Override
        public boolean isEmpty() {
            return hashTable.isEmpty();
        }

        /**
         * This method returns the number of elements in the underlying
         * hash table.
         * @return The number of elements in the underlying hash table.
         */
        @Override
        public int size() {
            return hashTable.size();
        }

        /**
         * This method checks whether all the elements of the collection are
         * included in the underlying hash table.
         * @param c The collection to check elements presence from.
         * @return {@code true} if all elements of the passed collection are
         * contained {@code false} otherwise
         */
        @Override
        public boolean containsAll(HCollection c) {
            HIterator iter = c.iterator();
            while (iter.hasNext()) {
                if (!contains(iter.next())) return false;
            }
            return true;
        }

        /**
         * This method removes all the elements that are both contained in the
         * view and the collection. Since the view is backed by {@link MapAdapter}
         * element removal in this view results in removal in the underlying hash table.
         * @param c The collection of elements to be removed from the view
         * @return {@code true} if some elements were removed as a result of the
         * call of this method {@code false} otherwise.
         */
        @Override
        public boolean removeAll(HCollection c) {
            boolean res = false;
            HIterator iter = c.iterator();
            while (iter.hasNext()) {
                Object current = iter.next();
                res |= remove(current);
            }
            return res;
        }

        /**
         * This method removes all the elements of the view that are not contained 
         * in the passed collection. Since the view is backed by {@link MapAdapter}
         * element removal in this view results in removal in the underlying hash table.
         * @param c The collection of elements to be retained in the underlying hash table.
         * @return {@code true} if some elements were removed as a result of the call
         * to this method {@code false} otherwise.
         */
        @Override
        public boolean retainAll(HCollection c) {
            boolean res = false;
            HIterator iter = iterator();
            while (iter.hasNext()) {
                Object current = iter.next();
                res |= remove(current);
            }
            return res;
        }

        @Override
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override
        public Object[] toArray(Object[] a) {
            if (a == null) throw new NullPointerException();
            Object[] res = a.length >= size() ? a : new Object[size()];
            HIterator iter = iterator();
            
            int i = 0;
            while (iter.hasNext()) {
                res[i++] = iter.next();
            }
            res[i] = null;

            return res;
        }

    }

    /**
     * Provides a set like view for map entries, values and keys.
     * for all view methods (listed in the 'see also' section
     * of the documentation for this class).
     * @see MapAdapter#entrySet()
     * @see MapAdapter#keySet()
     * @see MapAdapter#values()
     */
    public class EntrySet extends AbstractView implements HSet {

        /**
         * This method checks if a {@link MapAdapter.Entry} is contained
         * in the set. Search is complete only the entry key is contained
         * inside the underlying hash table and associated with it.
         * @param o The entry to check the presence to.
         * @return {@code true} if a entry with the same key of the passed
         * one is contained in the map, {@code false} otherwise
         */
        @Override
        public boolean contains(Object o) {
            if (o == null) throw new NullPointerException();
            if (!(o instanceof HEntry)) return false;
            HEntry entry = (HEntry) o;
            return  hashTable.containsKey(entry.getKey()) && 
                    hashTable.get(entry.getKey()).equals(entry.getValue());
        }

        @Override
        public boolean remove(Object o) {
            if (o == null) throw new NullPointerException();
            if (!(o instanceof HEntry)) return false;
            HEntry entry = (HEntry) o;
            if (contains(entry)) return hashTable.remove(entry) != null;
            return false;
        }

        @Override
		public int hashCode() {
			int hash = 0;
			HIterator i = iterator();
			while (i.hasNext()) {
				hash += i.next().hashCode();
            }
			return hash;
		}

        @Override
        public HIterator iterator() {
            return new EntryIterator();
        }

    }

	public class KeySet extends AbstractView implements HSet {

        // Attributes //

        private EntrySet entrySet;
        
        // Constructors //

        public KeySet() {
            this.entrySet = (EntrySet) entrySet();
        }

        // Methods //

		public boolean contains(Object o) {
			if (o == null) throw new NullPointerException();
			return hashTable.containsKey(o);
		}

		public boolean remove(Object o) {
            if (o == null) throw new NullPointerException();
            return this.entrySet.remove(o);
		}

		public HIterator iterator() {
            return new KeyIterator();
		}

	}

    public class ValueCollection extends AbstractView {
        
        // Methods //

        @Override
        public boolean contains(Object o) {
            if (o == null) throw new NullPointerException();
            return hashTable.contains(o);
        }

        /**
         * Removes the first encountered entry containing the passed value.
         * @param o The value to remove
         * @return {@code true} if the 
         */
        @Override
        public boolean remove(Object o) {
            if (o == null) throw new NullPointerException();
            HIterator iter = iterator();
            while (iter.hasNext()) {
                if (iter.next().equals(o)) {
                    iter.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        public HIterator iterator() {
            return new ValueIterator();
        }


    }

    // Iterator

    @SuppressWarnings("rawtypes")
    public class EntryIterator implements HIterator {
        
        // Attributes //

        private Entry current = null;
        private Enumeration keys = hashTable.keys();
        private Enumeration values = hashTable.elements();

        // Methods //

        @Override
        public boolean hasNext() {
            return keys.hasMoreElements() && values.hasMoreElements();
        }

        @Override
        public Object next() {
            current = new MapAdapter.Entry(keys.nextElement(), values.nextElement());
            return current;
        }

        @Override
        public void remove() {
            if (current == null) throw new IllegalStateException();
            hashTable.remove(current);
            current = null;
        }
        
    }

    public class KeyIterator extends EntryIterator {

        // Methods //

        @Override
        public Object next() {
            return ((Entry)super.next()).getKey();
        }

    }
    public class ValueIterator extends EntryIterator {

        // Methods //

        @Override
        public Object next() {
            return ((Entry)super.next()).getValue();
        }

    }
    /**
     * {@code MapAdapter}'s entry implementation.
     * This class represents a key-value pair in the map.
     */
    public class Entry implements HEntry {
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
            if (!(o instanceof HEntry)) { return false; }

            HEntry e = (HEntry) o;
            return this.getKey().equals(e.getKey()) && this.getValue().equals(e.getValue());
        }
    }
}