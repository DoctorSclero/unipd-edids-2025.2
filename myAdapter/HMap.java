package myAdapter;

public interface HMap {

    /**
     * A map entry (key-value pair). The Map.entrySet method returns a
     * collection-view of the map, whose elements are of this class. The only way to
     * obtain a reference to a map entry is from the iterator of this
     * collection-view. These Map.Entry objects are valid only for the duration of
     * the iteration; more formally, the behavior of a map entry is undefined if the
     * backing map has been modified after the entry was returned by the iterator,
     * except through the iterator's own remove operation, or through the setValue
     * operation on a map entry returned by the iterator.
     * @see HMap#entrySet()
     */
    public static interface HEntry {
        public boolean equals(Object o);
        public Object getKey();
        public Object getValue();
        public int hashCode();
        public Object setValue(Object value);
    }
    
    public void clear();

    public boolean containsKey(Object key);

    public boolean containsValue(Object value);

    public HSet entrySet();

    public boolean equals(Object o);

    public Object get(Object key);

    public int hashCode();

    public boolean isEmpty();

    public HSet keySet();

    public Object put(Object key, Object value);

    public void putAll(HMap m);

    public Object remove(Object key);

    public int size();

    public HCollection values();

}
