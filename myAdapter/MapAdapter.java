package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * A class that provides map functionality using Java ME CLDC 1.1 hash table. As
 * a result of the underlying supporting structure, this implementation of the
 * map does not support null keys nor null values so
 * {@code NullPointerException} will be thrown on all methods for which that is
 * expected. All the optional operations are implemented thus no methods of this
 * class will throw {@code UnsupportedOperationException}
 * 
 * <p>
 * Note: This implementation does not check for insertion of the map instance,
 * subclasses instances or views as keys or values in the map.
 * Extreme caution is advised: if the key or value is an instance of this class,
 * any of its subclasses or views, calls to methods that do calculations based
 * on the contents of the map may lead to infinite recursion and consequently
 * a {@code StackOverflowError}. Some examples of such a method are
 * {@link #toString()}, {@link #hashCode()}, {@link #equals(Object)}.
 * </p>
 *
 * @see HMap
 * @see HSet
 * @see HCollection
 * @see EntrySet
 * @see KeySet
 * @see ValueCollection
 */
public class MapAdapter implements HMap {

    // Methods //

    private Hashtable<Object,Object> hashTable;

    // Constructors //

    /**
     * Creates a new instance of the hash table with no additional parameters
     * for data storing.
     */
    public MapAdapter() {
        this.hashTable = new Hashtable<>();
    }

    /**
     * Creates a new instance of the hash table given an initial capacity.
     *
     * @param initialCapacity The initial capacity of the underlying hash table
     */
    public MapAdapter(int initialCapacity) {
        this.hashTable = new Hashtable<>(initialCapacity);
    }

    /**
     * Creates a new instance of the hash table given another HMap object. The
     * content of the passed map is shallowly copied inside the underlying
     * structure, changes in the past HMap's values and keys will then cord
     * result in changes to the new created copied instance.
     *
     * @param map The map to copy the data from.
     */
    public MapAdapter(HMap map) {
        this.hashTable = new Hashtable<>();
        putAll(map);
    }

    // Methods //

    /**
     * Removes all mappings from this map.
     */
    public void clear() {
        hashTable.clear();
    }


    /**
     * Returns {@code true} if this map maps one or more keys to the specified
     * value. More formally, returns {@code true} if and only if this map
     * contains at least one mapping to a value {@code v} such that
     * {@code (value==null ? v==null : value.equals(v))}. This operation will
     * probably require time linear in the map size for most implementations of
     * the {@code Map} interface.
     *
     * @param value value whose presence in this map is to be tested.
     * @return {@code true} if this map maps one or more keys to the specified
     * value.
     *
     * @throws NullPointerException if the value is {@code null}.
     */
    public boolean containsValue(Object value) {
        return hashTable.contains(value);
    }

    /**
     * Returns {@code true} if this map contains a mapping for the specified
     * key. More formally, returns {@code true} if and only if this map contains
     * at a mapping for a key {@code k} such that
     * {@code (key==null ? k==null : key.equals(k))}. (There can be at most one
     * such mapping.)
     *
     * @param key key whose presence in this map is to be tested.
     * @return {@code true} if this map contains a mapping for the specified
     * key.
     *
     * @throws NullPointerException if the key is {@code null}.
     */
    public boolean containsKey(Object key) {
        if (key == null) throw new NullPointerException();
        return hashTable.containsKey(key);
    }

    /**
     * Returns a set view of the mappings contained in this map. Each element
     * in the returned set is a {@link HMap.HEntry}. The set is backed by the
     * map, so changes to the map are reflected in the set, and vice versa. If
     * the map is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined. The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * {@code Iterator.remove}, {@code Set.remove}, {@code removeAll},
     * {@code retainAll} and {@code clear} operations. It does not support the
     * {@code add} or {@code addAll} operations.
     *
     * @return a set view of the mappings contained in this map.
     */
    public HSet entrySet() {
        return new EntrySet();
    }

    /**
     * Compares the specified object with this map for equality. Returns
     * {@code true} if the given object is also a map and the two Maps represent
     * the same mappings.  More formally, two maps {@code t1} and {@code t2}
     * represent the same mappings if
     * {@code t1.entrySet().equals(t2.entrySet())}. This ensures that the
     * {@code equals} method works properly across different implementations of
     * the {@code Map} interface.
     *
     * @param o object to be compared for equality with this map.
     * @return {@code true} if the specified object is equal to this map.
     */
    public boolean equals(Object o) {
        if (!(o instanceof HMap)) return false;
        return this.entrySet().equals(((HMap) o).entrySet());
    }

    /**
     * Returns the value to which this map maps the specified key. Returns
     * {@code null} if the map contains no mapping for this key.
     * 
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that
     * {@code (key==null ? k==null : key.equals(k))}, then this method returns
     * {@code v}; otherwise it returns {@code null}. (There can be at most one
     * such mapping.)</p>
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or
     * {@code null} if the map contains no mapping for this key.
     *
     * @throws NullPointerException if key is {@code null}.
     * @see #containsKey(Object)
     */
    public Object get(Object key) {
        return hashTable.get(key);
    }

    /**
     * Returns the hash code value for this map. The hash code of a map is
     * defined to be the sum of the hashCodes of each entry in the map's
     * entrySet view. This ensures that {@code t1.equals(t2)} implies that
     * {@code t1.hashCode()==t2.hashCode()} for any two maps {@code t1} and
     * {@code t2}, as required by the general contract of Object.hashCode.
     *
     * @return the hash code value for this map.
     *
     * @see HMap.HEntry#hashCode()
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    public int hashCode() {
        return entrySet().hashCode();
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings.
     */
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }

    /**
     * Returns a set view of the keys contained in this map. The set is backed
     * by the map, so changes to the map are reflected in the set, and vice
     * versa. If the map is modified while an iteration over the set is in
     * progress, the results of the iteration are undefined. The set supports
     * element removal, which removes the corresponding mapping from the map,
     * via the {@code Iterator.remove}, {@code Set.remove}, {@code removeAll}
     * {@code retainAll}, and {@code clear} operations. It does not support the
     * add or {@code addAll} operations.
     *
     * @return a set view of the keys contained in this map.
     */
    public HSet keySet() {
        return new KeySet();
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation). If the map previously contained a mapping for this
     * key, the old value is replaced by the specified value. (A map {@code m}
     * is said to contain a mapping for a key {@code k} if and only if
     * {@link #containsKey(Object) m.containsKey(k)} would return
     * {@code true}.)
     *
     * @param key   key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with the specified key, or {@code null}
     * if there was no mapping for key.
     *
     * @throws NullPointerException if {@code key} or {@code value} is null.
     */
    public Object put(Object key, Object value) {
        if (key == null || value == null) {
            throw new NullPointerException("Key and value must not be null.");
        }
        return hashTable.put(key, value);
    }

    /**
     * Copies all of the mappings from the specified map to this map (optional
     * operation). The effect of this call is equivalent to that of calling
     * {@link #put(Object, Object) put(k, v)} on this map once for each mapping
     * from key {@code k} to value {@code v} in the specified map. The behavior
     * of this operation is unspecified if the specified map is modified while
     * the operation is in progress.
     *
     * @param t Mappings to be stored in this map.
     * @throws NullPointerException          the specified map is {@code null},
     *                                       or the specified map contains
     *                                       entries with {@code null} keys or 
     *                                       values.
     */
    public void putAll(HMap t) {
        if (t == null) throw new NullPointerException();

        HSet entrySet = t.entrySet();
        HIterator iter = entrySet.iterator();
        while (iter.hasNext()) {
            HEntry current = (HEntry) iter.next();
            if (current.getKey() == null || current.getValue() == null) {
                throw new NullPointerException("Key and value must not be null.");
            }
            put(current.getKey(), current.getValue());
        }
    }

    /**
     * Removes the mapping for this key from this map if it is present (optional
     * operation). More formally, if this map contains a mapping from key
     * {@code k} to value {@code v} such that
     * {@code (key==null ? k==null : key.equals(k))}, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which the map previously associated the key, or
     * {@code null} if the map contained no mapping for this key.
     * The map will not contain a mapping for the
     * specified key once the call returns.</p>
     *
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with a specified key, or {@code null}
     * if there was no mapping for key.
     *
     * @throws NullPointerException          if the key is {@code null}
     */
    public Object remove(Object key) {
        return hashTable.remove(key);
    }

    /**
     * Returns the number of key-value mappings in this map.  If the map
     * contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return hashTable.size();
    }

    /**
     * Returns a collection view of the values contained in this map. The
     * collection is backed by the map, so changes to the map are reflected in
     * the collection, and vice versa. If the map is modified while an iteration
     * over the collection is in progress, the results of the iteration are
     * undefined.  The collection supports element removal, which removes the
     * corresponding mapping from the map, via the {@code Iterator.remove},
     * {@code Collection.remove}, {@code removeAll}, {@code retainAll} and
     * {@code clear} operations. It does not support the add or {@code addAll}
     * operations.
     *
     * @return a collection view of the values contained in this map.
     */
    public HCollection values() {
        return new ValueCollection();
    }

    // Inner classes //

    public abstract class AbstractView implements HCollection {

        /**
         * This method is not supported by any hash table map adapter view
         *
         * @throws UnsupportedOperationException {@code add} is not supported by
         *                                       this collection.
         */
        @Override
        public boolean add(Object o) {
            throw new UnsupportedOperationException("Method 'add' is not supported by this set view.");
        }

        /**
         * This method is not supported by any hash table map adapter view
         *
         * @throws UnsupportedOperationException {@code addAll} is not supported
         *                                       by this collection.
         */
        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("Method 'addAll' is not supported by this set view.");
        }

        /**
         * Removes all of the elements from this collection (optional
         * operation). This collection will be empty after this method returns
         * unless it throws an exception.
         */
        @Override
        public void clear() {
            hashTable.clear();
        }
        /**
         * Returns {@code true} if this collection contains no elements.
         *
         * @return {@code true} if this collection contains no elements
         */
        @Override
        public boolean isEmpty() {
            return hashTable.isEmpty();
        }

        /**
         * Returns the number of elements in this collection.  If this
         * collection contains more than {@code Integer.MAX_VALUE} elements,
         * returns {@code Integer.MAX_VALUE}.
         *
         * @return the number of elements in this collection
         */
        @Override
        public int size() {
            return hashTable.size();
        }

        /**
         * Returns {@code true} if this collection contains all of the elements
         * in the specified collection.
         *
         * @param c collection to be checked for containment in this
         *          collection.
         * @return {@code true} if this collection contains all of the elements
         * in the specified collection
         *
         * @throws NullPointerException if the specified collection contains one
         *                              or more null elements
         * @throws NullPointerException if the specified collection is
         *                              {@code null}.
         * @see #contains(Object)
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
         * Removes all this collection's elements that are also contained in the
         * specified collection (optional operation). After this call returns,
         * this collection will contain no elements in common with the specified
         * collection.
         *
         * @param c elements to be removed from this collection.
         * @return {@code true} if this collection changed as a result of the
         * call
         *
         * @throws NullPointerException          if the specified collection is
         *                                       {@code null}.
         * @see #remove(Object)
         * @see #contains(Object)
         */
        @Override
        public boolean removeAll(HCollection c) {
            boolean res = false;
            HIterator iter = c.iterator();
            while (iter.hasNext()) {
                Object current = iter.next();
                try {
                    // Remove all occurrences of current
                    while(remove(current)) res |= true;
                } catch (Exception e) { }
            }
            return res;
        }

        /**
         * Retains only the elements in this collection that are contained in
         * the specified collection (optional operation).  In other words,
         * removes from this collection all of its elements that are not
         * contained in the specified collection.
         *
         * @param c elements to be retained in this collection.
         * @return {@code true} if this collection changed as a result of the
         * call
         *
         * @throws NullPointerException          if this collection contains one
         *                                       or more null elements
         * @throws NullPointerException          if the specified collection is
         *                                       {@code null}.
         * @see #remove(Object)
         * @see #contains(Object)
         */
        @Override
        public boolean retainAll(HCollection c) {
            if (c == null) throw new NullPointerException();
            boolean res = false;
            HIterator iter = iterator();
            while (iter.hasNext()) {
                Object current = iter.next();
                try {
                    if (!c.contains(current)) res |= remove(current);
                } catch (NullPointerException npe) { }
            }
            return res;
        }

        /**
         * Returns an array containing all the elements in this collection.  If
         * the collection makes any guarantees as to what order its elements are
         * returned by its iterator, this method must return the elements in the
         * same order.
         *
         * <p>The returned array will be "safe" in that no references to it are
         * maintained by this collection. (In other words, this method must
         * allocate a new array even if this collection is backed by an array).
         * The caller is thus free to modify the returned array.</p>
         *
         * <p>This method acts as a bridge between array-based and
         * collection-based APIs.</p>
         *
         * @return an array containing all the elements in this collection
         */
        @Override
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        /**
         * Returns an array containing all the elements in this collection; the
         * runtime type of the returned array is that of the specified array. If
         * the collection fits in the specified array, it is returned therein.
         * Otherwise, a new array is allocated with the runtime type of the
         * specified array and the size of this collection.
         *
         * <p>If this collection fits in the specified array with room to spare
         * (i.e., the array has more elements than this collection), the element
         * in the array immediately following the end of the collection is set
         * to {@code null}. This is useful in determining the length of this
         * collection <i>only</i> if the caller knows that this collection does
         * not contain any {@code null} elements.)</p>
         *
         * <p>If this collection makes any guarantees as to what order its
         * elements are returned by its iterator, this method must return the
         * elements in the same order.</p>
         *
         * <p> Like the {@code toArray} method, this method acts as bridge
         * between array-based and collection-based APIs. Further, this method
         * allows precise control over the runtime type of the output array, and
         * may, under certain circumstances, be used to save allocation
         * costs</p>
         *
         * <p> Suppose {@code l} is a {@code List} known to contain only
         * strings. The following code can be used to dump the list into a newly
         * allocated array of {@code String}:</p>
         *
         * <pre>
         *     String[] x = (String[]) v.toArray(new String[0]);
         * </pre>
         *
         * <p> Note that {@code toArray(new Object[0])} is identical in
         * function to {@code toArray()}.</p>
         *
         * @param a the array into which the elements of this collection are to
         *          be stored, if it is big enough; otherwise, a new array of
         *          the same runtime type is allocated for this purpose.
         * @return an array containing the elements of this collection
         *
         * @throws NullPointerException if the specified array is {@code null}.
         */
        @Override
        public Object[] toArray(Object[] a) {
            if (a == null) throw new NullPointerException();
            Object[] res = a.length >= size() ? a : new Object[size()];
            HIterator iter = iterator();

            int i = 0;
            while (iter.hasNext()) {
                res[i++] = iter.next();
            }
            if (i < res.length) res[i] = null;

            return res;
        }

        /**
         * Returns the hash code value for this set.  The hash code of a set is
         * defined to be the sum of the hash codes of the elements in the set,
         * where the hashcode of a {@code null} element is defined to be zero.
         * This ensures that <code>s1.equals(s2)</code> implies that
         * <code>s1.hashCode()==s2.hashCode()</code> for any two sets
         * <code>s1</code> and <code>s2</code>, as required by the general
         * contract of the {@code Object.hashCode} method.
         *
         * @return the hash code value for this set.
         *
         * @see Object#hashCode()
         * @see Object#equals(Object)
         * @see HSet#equals(Object)
         */
        @Override
        public int hashCode() {
            int hash = 0;
            HIterator i = iterator();
            while (i.hasNext()) {
                hash += i.next().hashCode();
            }
            return hash;
        }

    }

    /**
     * Provides a set like view for map entries, values, and keys. for all view
     * methods (listed in the 'see also' section of the documentation for this
     * class).
     *
     * @see MapAdapter#entrySet()
     * @see MapAdapter#keySet()
     * @see MapAdapter#values()
     */
    public class EntrySet extends AbstractView implements HSet {

        /**
         * Returns {@code true} if this set contains the specified element. More
         * formally, returns {@code true} if and only if this set contains an
         * element <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>.
         *
         * @param o element whose presence in this set is to be tested.
         * @return {@code true} if this set contains the specified element.
         *
         * @throws NullPointerException if the specified element is null
         * @throws ClassCastException if o is not instance of HMap.HEntry
         */
        @Override
        public boolean contains(Object o) {
            if (o == null) throw new NullPointerException();
            if (!(o instanceof HEntry)) throw new ClassCastException();
            HEntry entry = (HEntry) o;

            if (entry.getKey()==null || entry.getValue()==null) throw new NullPointerException();

            try {
                return MapAdapter.this.containsKey(entry.getKey()) &&
                        MapAdapter.this.get(entry.getKey()).equals(entry.getValue());
            } catch (NullPointerException e) {
                // Since MapAdapter does not support null keys or values
                // null values or keys cannot be present in the map
                return false;
            }
        }

        /**
         * Removes the specified element from this set if it is present
         * (optional operation). More formally, removes an element
         * <code>e</code> such that
         * <code>(o==null ? e==null : o.equals(e))</code>, if the set contains
         * such an element. Returns {@code true} if the set contained the
         * specified element (or equivalently, if the set changed as a result of
         * the call). (The set will not contain the specified element once the
         * call returns.)
         * 
         * @param o object to be removed from this set, if present.
         * @return true if the set contained the specified element.
         * 
         * @throws NullPointerException if the specified element is null
         * @throws ClassCastException if the specified element is not a HEntry instance
         */
        @Override
        public boolean remove(Object o) {
            if (o == null) throw new NullPointerException();
            if (!(o instanceof HEntry)) throw new ClassCastException();
            HEntry entry = (HEntry) o;
            
            if (entry.getKey() == null || entry.getValue() == null) throw new NullPointerException();

            Object key = entry.getKey();
            Object value = MapAdapter.this.get(key);

            if (!MapAdapter.this.containsKey(key)) return false;
            if (!value.equals(entry.getValue())) return false;

            return MapAdapter.this.remove(key) != null;
        }

        /**
         * Returns an iterator over the elements in this set.  The elements are
         * returned in no particular order (unless this set is an instance of
         * some class that provides a guarantee).
         *
         * @return an iterator over the elements in this set.
         */
        @Override
        public HIterator iterator() {
            return new EntryIterator();
        }

        /**
         * Compares the specified object with this set for equality. Returns
         * {@code true} if the specified object is also a set, the two sets have the
         * same size, and every member of the specified set is contained in this set
         * (or equivalently, every member of this set is contained in the specified
         * set). This definition ensures that the equals method works properly
         * across different implementations of the set interface.
         *
         * @param o Object to be compared for equality with this set.
         * @return {@code true} if the specified Object is equal to this set.
         */
        public boolean equals(Object o) {
            if (!(o instanceof HSet)) return false;
            HSet other = (HSet) o;

            // Must check if the other view has the same size
            // otherwise one or the other could be a subset
            return containsAll(other) && other.size() == size();
        }

    }

    public class KeySet extends AbstractView implements HSet {

        // Methods //

        /**
         * Returns {@code true} if this set contains the specified element. More
         * formally, returns {@code true} if and only if this set contains an
         * element <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>.
         *
         * @param o element whose presence in this set is to be tested.
         * @return {@code true} if this set contains the specified element.
         *
         * @throws NullPointerException if the specified element is null and
         *                              this set does not support null elements
         *                              (optional).
         */
        @Override
        public boolean contains(Object o) {
            if (o == null) throw new NullPointerException();
            return MapAdapter.this.containsKey(o);
        }

        /**
         * Removes the specified element from this set if it is present
         * (optional operation).  More formally, removes an element
         * <code>e</code> such that
         * <code>(o==null ? e==null : o.equals(e))</code>, if the set contains
         * such an element.  Returns {@code true} if the set contained the
         * specified element (or equivalently, if the set changed as a result of
         * the call). (The set will not contain the specified element once the
         * call returns.)
         *
         * @param o object to be removed from this set, if present.
         * @return true if the set contained the specified element.
         *
         * @throws NullPointerException          if the specified element is
         *                                       null and this set does not
         *                                       support null elements
         *                                       (optional).
         */
        public boolean remove(Object o) {
            if (o == null) throw new NullPointerException();
            return MapAdapter.this.remove(o) != null;
        }

        /**
         * Returns an iterator over the elements in this set. The elements are
         * returned in no particular order (unless this set is an instance of
         * some class that provides a guarantee).
         *
         * @return an iterator over the elements in this set.
         */
        public HIterator iterator() {
            return new KeyIterator();
        }

        /**
         * Compares the specified object with this set for equality. Returns
         * {@code true} if the specified object is also a set, the two sets have the
         * same size, and every member of the specified set is contained in this set
         * (or equivalently, every member of this set is contained in the specified
         * set). This definition ensures that the equals method works properly
         * across different implementations of the set interface.
         *
         * @param o Object to be compared for equality with this set.
         * @return {@code true} if the specified Object is equal to this set.
         */
        public boolean equals(Object o) {
            if (!(o instanceof HSet)) return false;
            HSet other = (HSet) o;

            // Must check if the other view has the same size
            // otherwise one or the other could be a subset
            return containsAll(other) && other.size() == size();
        }
    }

    public class ValueCollection extends AbstractView {

        // Methods //

        /**
         * Returns {@code true} if this collection contains the specified
         * element. More formally, returns {@code true} if and only if this
         * collection contains at least one element {@code e} such that
         * {@code (o==null ? e==null : o.equals(e))}.
         *
         * @param o element whose presence in this collection is to be tested.
         * @return {@code true} if this collection contains the specified
         * element
         *
         * @throws NullPointerException if the specified element is null and
         *                              this collection does not support null
         *                              elements (optional).
         */
        @Override
        public boolean contains(Object o) {
            if (o == null) throw new NullPointerException();
            return hashTable.contains(o);
        }

        /**
         * Removes a single instance of the specified element from this
         * collection if it is present (optional operation). More formally,
         * removes an element {@code e} such that
         * {@code (o==null ? e==null : o.equals(e))}, if this collection
         * contains one or more such elements.  Returns true if this collection
         * contained the specified element (or equivalently, if this collection
         * changed as a result of the call).
         *
         * @param o element to be removed from this collection, if present.
         * @return {@code true} if this collection changed as a result of the
         * call
         *
         * @throws NullPointerException          if the specified element is
         *                                       null and this collection does
         *                                       not support null elements
         *                                       (optional).
         */
        @Override
        public boolean remove(Object o) {
            if (o == null) throw new NullPointerException();
            HIterator iter = iterator();
            while (iter.hasNext()) {
                Object current = iter.next();
                if (current.equals(o)) {
                    iter.remove();
                    return true;
                }
            }
            return false;
        }

        /**
         * Compares the specified object with this collection for equality.
         *
         * <p>
         * While the {@code Collection} interface adds no stipulations to the
         * general contract for the {@code Object.equals}, programmers who implement
         * the {@code Collection} interface "directly" (in other words, create a
         * class that is a {@code Collection} but is not a {@code Set} or a
         * {@code List}) must exercise care if they choose to override the
         * {@code Object.equals}. It is not necessary to do so, and the simplest
         * course of action is to rely on {@code Object}'s implementation, but the
         * implementer may wish to implement a "value comparison" in place of the
         * default "reference comparison." (The {@code List} and {@code Set}
         * interfaces mandate such value comparisons.)
         * </p>
         *
         * <p>
         * The general contract for the {@code Object.equals} method states that
         * equals must be symmetric (in other words, {@code a.equals(b)} if and only
         * if {@code b.equals(a)}). The contracts for {@code List.equals} and
         * {@code Set.equals} state that lists are only equal to other lists, and
         * sets to other sets. Thus, a custom {@code equals} method for a
         * collection class that implements neither the {@code List} nor {@code Set}
         * interface must return {@code false} when this collection is compared to
         * any list or set. (By the same logic, it is not possible to write a class
         * that correctly implements both the {@code Set} and {@code List}
         * interfaces.)
         * </p>
         *
         * @param o Object to be compared for equality with this collection.
         * @return {@code true} if the specified object is equal to this collection
         *
         * @see Object#equals(Object)
         * @see HSet#equals(Object)
         */
        public boolean equals(Object o) {
            if (!(o instanceof HCollection)) return false;
            if (o instanceof HSet) return false;

            HCollection other = (HCollection) o;
            if (other.size() != size()) return false;
            HCollection clone = new MapAdapter(MapAdapter.this).values();
            HIterator iter = other.iterator();
            while (iter.hasNext()) {
                clone.remove(iter.next());
            }
            return clone.isEmpty();
        }

        /**
         * Returns an iterator over the elements in this collection.  There are
         * no guarantees concerning the order in which the elements are returned
         * (unless this collection is an instance of some class that provides a
         * guarantee).
         *
         * @return an {@code Iterator} over the elements in this collection
         */
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

        // Methods //

        /**
         * Returns {@code true} if the iteration has more elements. (In other
         * words, returns {@code true} if {@code next} would return an element
         * rather than throwing an exception.)
         *
         * @return {@code true} if the iterator has more elements.
         */
        @Override
        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         *
         * @throws NoSuchElementException iteration has no more elements.
         */
        @Override
        public Object next() {
            current = new MapAdapter.Entry(keys.nextElement());
            return current;
        }

        /**
         * Removes from the underlying collection the last element returned by
         * the iterator (optional operation). This method can be called only
         * once per call to {@code next}. The behavior of an iterator is
         * unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws IllegalStateException         if the {@code next} method has
         *                                       not yet been called, or the
         *                                       {@code remove} method has
         *                                       already been called after the
         *                                       last call to the {@code next}
         *                                       method.
         */
        @Override
        public void remove() {
            if (current == null) throw new IllegalStateException();
            MapAdapter.this.remove(current.getKey());
            current = null;
        }

    }

    public class KeyIterator extends EntryIterator {

        // Methods //

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         *
         * @throws NoSuchElementException iteration has no more elements.
         */
        @Override
        public Object next() {
            return ((Entry) super.next()).getKey();
        }

    }

    public class ValueIterator extends EntryIterator {

        // Methods //

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         *
         * @throws NoSuchElementException iteration has no more elements.
         */
        @Override
        public Object next() {
            return ((Entry) super.next()).getValue();
        }

    }

    /**
     * {@code MapAdapter}'s entry implementation. This class represents a
     * key-value pair in the map.
     */
    public class Entry implements HEntry {

        // Attributes //

        private final Object key;

        // Constructors //

        /**
         * Creates an entry with the given key and value. The copy of the key
         * and the value inside the entry are shallow.
         *
         * @param key   The key to create the entry instance with
         * @param value The value to create the entry instance with
         */
        private Entry(Object key) {
            this.key = key;
        }

        // Methods //

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry.
         */
        @Override
        public Object getKey() {
            return this.key;
        }

        /**
         * Returns the value corresponding to this entry.  If the mapping has
         * been removed from the backing map (by the iterator's {@code remove}
         * operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry.
         */
        @Override
        public Object getValue() {
            return MapAdapter.this.get(this.key);
        }

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.) The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's {@code remove} operation).
         *
         * @param value new value to be stored in this entry.
         * @return old value corresponding to the entry.
         *
         * @throws NullPointerException          the backing map does not permit
         *                                       {@code null} values, and the
         *                                       specified value is
         *                                       {@code null}.
         */
        @Override
        public Object setValue(Object value) {
            return MapAdapter.this.put(this.key, value);
        }

        /**
         * Returns the hash code value for this map entry.  The hash code
         * of a map entry {@code e} is defined to be: <pre>
         *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *     (e.getValue()==null ? 0 : e.getValue().hashCode())
         * </pre>
         * This ensures that {@code e1.equals(e2)} implies that
         * {@code e1.hashCode()==e2.hashCode()} for any two Entries {@code e1}
         * and {@code e2}, as required by the general contract of
         * {@code Object.hashCode}.
         *
         * @return the hash code value for this map entry.
         *
         * @see Object#hashCode()
         * @see Object#equals(Object)
         * @see #equals(Object)
         */
        @Override
        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^
                    (this.getValue() == null ? 0 : this.getValue().hashCode());
        }

        /**
         * Compares the specified object with this entry for equality. Returns
         * {@code true} if the given object is also a map entry and the two
         * entries represent the same mapping.  More formally, two entries
         * {@code e1} and {@code e2} represent the same mapping if
         * <pre>
         *     (e1.getKey()==null ?
         *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
         *     (e1.getValue()==null ?
         *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
         * </pre>
         * This ensures that the {@code equals} method works properly across
         * different implementations of the {@code Map.Entry} interface.
         *
         * @param o object to be compared for equality with this map entry.
         * @return {@code true} if the specified object is equal to this map
         * entry.
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof HEntry)) return false;

            HEntry e = (HEntry) o;
            return this.getKey().equals(e.getKey()) && this.getValue().equals(e.getValue());
        }

        /**
         * Creates a string representation of the Map.Entry
         * following the {@code key=value} representation
         */
        @Override
        public String toString() {
            return this.getKey().toString() + "=" + this.getValue().toString();
        }
    }
}