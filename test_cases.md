# test  suite for MapAdapter project
Each test should test different environments. Namely:
empty, few items (5-10), many items/performance (100.000), dynamic.

## HMap (MapAdapter)

1. `void clear()`
    - test
2. `boolean containsValue(Object value)`
    - test null value
    - test value not present
    - test value present start
    - test value present middle
    - test value present end
    - test value present multiple times
3. `boolean containsKey(Object key)`
    - test key null
    - test key not present
    - test key present start
    - test key present middle
    - test key present end
4. `HSet entrySet()`
    - test return not null
    - [entryset test s]
5. `boolean equals()`
    - test null object
    - test not a map object
    - test different map same mappings same order
    - test different map same mappings different order
    - test different map different mappings
    - test same map same mappings different order
    - test same map same mappings same order
6. `Object get()`
    - test retrival key null
    - test retrival key not present
    - test retrival key present start
    - test retrival key present middle
    - test retrival key present end
7. `int hashCode()`
    - test equals implies same hash code
8. `boolean isEmpty()`
    - test 
9. `HSet keySet()`
    - test return not null
    - [keyset test s]
10. `Object put(Object key, Object value)`
    - test put null key valid value
    - test put null value valid key
    - test put null key and value
    - test put present key start
    - test put present key middle
    - test put present key end
    - test put new key
    - test put map instance as key
    - test put map instance as key and value
11. `void putAll(HMap t)`
    - test put null map
    - test put map with null key valid value entry
    - test put map with null value valid key entry
    - test put map with null key and value entry
    - test put map with present key start entry
    - test put map with present key middle entry
    - test put map with present key end entry
    - test put map with new key entry
    - test put map with map instance as key entry
    - test put map with map instance as key and value entry
12. `Object remove(Object key)`
    - test null key
    - test absent key
    - test present key start
    - test present key middle
    - test present key end
13. `int size()`
    - test
    - verificare che se the map contains more than Integer.MAX_VALUE elements, size() returns Integer.MAX_VALUE
14. `HCollection values()`
    - test return not null
    - [value collection test]

## HSet (EntrySet & KeySet)
1. `int size();` 
    - test
2. `boolean isEmpty();`
    - test
3. `boolean contains(Object o);`
    - test null element
    - test not contained element
    - test contained element start
    - test contained element middle
    - test contained element end
4. `HIterator iterator();`
    - test return not null
    - [iterator tests]
5. `Object[] toArray();`
    - test
6. `Object[] toArray(Object a[]);`
    - test null array
    - test array smaller 
    - test array bigger
7. `boolean add(Object o);`
    - test unsupported operation
8. `boolean remove(Object o);`
    - test null element
    - test not contained element
    - test contained element start
    - test contained element middle
    - test contained element end
9. `boolean containsAll(HCollection c);`
    - test null collection
    - test collection with null elements
    - test collection with same elements
    - test collection with some elements
    - test collection with more elements
    - test collection without common elements
10. `boolean addAll(HCollection c);`
    - test unsupported operation
11. `boolean retainAll(HCollection c);`
    - test null collection
    - test collection with null elements
    - test collection with same elements
    - test collection with some elements
    - test collection with more elements
    - test collection without common elements
12. `boolean removeAll(HCollection c);`
    - test null collection
    - test collection with null elements
    - test collection with same elements
    - test collection with some elements
    - test collection with more elements
    - test collection without common elements
13. `void clear();`
    - test
14. `boolean equals(Object o);`
    - test
15. `int hashCode();`
    - test equals implies same hashcode

## HCollection 

## Iterator EntrySet

## Iterator KeySet

## Iterator ValuesCollection
