# test suite for MapAdapter project
Each test should test different environments. Namely:
 [-] empty
 [ ] few items (5-10)
 [ ] many items (100.000)
 [ ] performance (>MAX_INT)
 [ ] dynamic

## HMap (MapAdapter)

1. `void clear()`
    [x] test [empty, few items, many items, performance, dynamic]
2. `boolean containsValue(Object value)`
    [x] test null value [empty, few items, many items, performance, dynamic]
    [x] test value not present [empty, few items, many items, performance, dynamic]
    [x] test value present start [few items, many items, dynamic]
    [x] test value present middle [few items, many items, dynamic]
    [x] test value present end [few items, many items, dynamic]
    [x] test value present multiple times [few items, many items, dynamic]
3. `boolean containsKey(Object key)`
    [x] test key null [empty, few items, many items, performance, dynamic]
    [x] test key not present [empty, few items, many items, performance, dynamic]
    [x] test key present start [few items, many items, dynamic]
    [x] test key present middle [few items, many items, dynamic]
    [x] test key present end [few items, many items, dynamic]
4. `HSet entrySet()`
    [x] test return not null [empty, few items, many items, dynamic]
5. `boolean equals()`
    [x] test null object [empty, few items, many items, dynamic]
    [x] test not a map object [empty, few items, many items, dynamic]
    [x] test same mappings same order [empty, few items, many items, dynamic]
    [x] test same mappings different order [few items, many items, dynamic]
    [x] test different mappings same order [empty, few items, many items, dynamic]
    [N] test subset mappings [few items, many items, dynamic]
    [N] test superset mappings [few items, many items, dynamic]
6. `Object get()`
    [x] test retrival key null [empty, few items, many items, performance, dynamic]
    [x] test retrival key not present [empty, few items, many items, performance, dynamic]
    [x] test retrival key present start [few items, many items, dynamic]
    [x] test retrival key present middle [few items, many items, dynamic]
    [x] test retrival key present end [few items, many items, dynamic]
7. `int hashCode()`
    [x] test hash code 0 [empty]
    [x] test same map [few items, many items, dynamic]
    [x] test different map [few items, many items, dynamic]
8. `boolean isEmpty()`
    [x] test [empty, few items, many items, performance, dynamic]
9. `HSet keySet()`
    [x] test return not null [empty, few items, many items, dynamic]
10. `Object put(Object key, Object value)`
    [x] test put null key valid value [empty, few items, many items, performance, dynamic]
    [x] test put null key valid value strong guarantee [empty, few items, many items, performance, dynamic]

    [x] test put null value valid key [empty, few items, many items, performance, dynamic]
    [x] test put null value valid key strong guarantee [empty, few items, many items, performance, dynamic]

    [x] test put null key and value [empty, few items, many items, performance, dynamic]
    [x] test put null key and value strong guarantee [empty, few items, many items, performance, dynamic]

    [x] test put present key start [few items, many items, dynamic]
    [x] test put present key middle [few items, many items, dynamic]
    [x] test put present key end [few items, many items, dynamic]
    [x] test put new mapping [few items, many items, dynamic]
    [?] test put map instance as key [few items, many items, dynamic]
    [?] test put map instance as value [few items, many items, dynamic]
11. `void putAll(HMap t)`
    [x] test put null map [empty, few items, many items, performance, dynamic]
    [?] test put map with null key valid value entry [empty, few items, many items, performance, dynamic]
    [?] test put map with null value valid key entry [empty, few items, many items, performance, dynamic]
    [?] test put map with null key and value entry [empty, few items, many items, performance, dynamic]
    [x] test put map with present key start entry [few items, many items, dynamic]
    [x] test put map with present key middle entry [few items, many items, dynamic]
    [x] test put map with present key end entry [few items, many items, dynamic]
    [x] test put map with new key entry [few items, many items, dynamic]
    [?] test put map with map instance as key entry [few items, many items, dynamic]
    [?] test put map with map instance as key and value entry [few items, many items, dynamic]
12. `Object remove(Object key)`
    [x] test null key [empty, few items, many items, performance, dynamic]
    [x] test absent key [empty, few items, many items, performance, dynamic]
    [x] test present key start [few items, many items, dynamic]
    [x] test present key middle [few items, many items, dynamic]
    [x] test present key end [few items, many items, dynamic]
13. `int size()`
    [x] test [empty, few items, many items, performance, dynamic]
14. `HCollection values()`
    [x] test return not null [empty, few items, many items, dynamic]

## HSet (EntrySet & KeySet)
1. `int size()`
    [x] test [empty, few items, many items, dynamic]
2. `boolean isEmpty()`
    [x] test [empty, few items, many items, dynamic]
3. `boolean contains(Object o)`
    [x] test null element [empty, few items, many items, dynamic]
    [x] test not contained element [empty, few items, many items, dynamic]
    [x] test contained element start [few items, many items, dynamic]
    [x] test contained element middle [few items, many items, dynamic]
    [x] test contained element end [few items, many items, dynamic]
4. `HIterator iterator()`
    [x] test return not null [empty, few items, many items, dynamic]
5. `Object[] toArray()`
    [x] test [empty, few items, many items, dynamic]
6. `Object[] toArray(Object a[])`
    [x] test null array [empty, few items, many items, dynamic]
    [x] test array smaller [few items, many items, dynamic]
    [x] test array bigger [empty, few items, many items, dynamic]
7. `boolean add(Object o)`
    [x] test unsupported operation [empty, few items, many items, dynamic]
8. `boolean remove(Object o)`
    [x] test null element [empty, few items, many items, dynamic]
    [x] test not contained element [empty, few items, many items, dynamic]
    [x] test contained element start [few items, many items, dynamic]
    [x] test contained element middle [few items, many items, dynamic]
    [x] test contained element end [few items, many items, dynamic]
9. `boolean containsAll(HCollection c)`
    [x] test null collection [empty, few items, many items, dynamic]
    [?] test collection with null elements [empty, few items, many items, dynamic]
    [x] test collection with same elements [empty, few items, many items, dynamic]
    [x] test collection with some elements [few items, many items, dynamic]
    [x] test collection with more elements [few items, many items, dynamic]
    [x] test collection without common elements [few items, many items, dynamic]
10. `boolean addAll(HCollection c)`
    [x] test unsupported operation [empty, few items, many items, dynamic]
11. `boolean retainAll(HCollection c)`
    [x] test null collection [empty, few items, many items, dynamic]
    [?] test collection with null elements [empty, few items, many items, dynamic]
    [x] test collection with same elements [few items, many items, dynamic]
    [x] test collection with some elements [few items, many items, dynamic]
    [x] test collection with more elements [few items, many items, dynamic]
    [x] test collection without common elements [few items, many items, dynamic]
12. `boolean removeAll(HCollection c)`
    [x] test null collection [empty, few items, many items, dynamic]
    [?] test collection with null elements [empty, few items, many items, dynamic]
    [x] test collection with same elements [few items, many items, dynamic]
    [x] test collection with some elements [few items, many items, dynamic]
    [x] test collection with more elements [few items, many items, dynamic]
    [x] test collection without common elements [few items, many items, dynamic]
13. `void clear()`
    [x] test [empty, few items, many items, dynamic]
14. `boolean equals(Object o)`
    [x] test same set [few items, many items, dynamic]
    [x] test different set [few items, many items, dynamic]
    [x] test not a set [empty, few items, many items, dynamic]
15. `int hashCode()`
    [x] test predictable [empty, few items, many items, dynamic]
    [x] test different set [few items, many items, dynamic]
    [x] test same set [few items, many items, dynamic]

## HCollection

1. `int size()`
    [x] test [empty, few items, many items, dynamic]
2. `boolean isEmpty()`
    [x] test [empty, few items, many items, dynamic]
3. `boolean contains(Object o)`
    [x] test null element [empty, few items, many items, dynamic]
    [x] test not contained element [empty, few items, many items, dynamic]
    [x] test contained element start [few items, many items, dynamic]
    [x] test contained element middle [few items, many items, dynamic]
    [x] test contained element end [few items, many items, dynamic]
4. `HIterator iterator()`
    [x] test return not null [empty, few items, many items, dynamic]
5. `Object[] toArray()`
    [x] test [empty, few items, many items, dynamic]
6. `Object[] toArray(Object a[])`
    [x] test null array [empty, few items, many items, dynamic]
    [x] test array smaller [few items, many items, dynamic]
    [x] test array bigger [few items, many items, dynamic]
7. `boolean add(Object o)`
    [x] test unsupported operation [empty, few items, many items, dynamic]
8. `boolean remove(Object o)`
    [x] test null element [empty, few items, many items, dynamic]
    [x] test not contained element [empty, few items, many items, dynamic]
    [x] test contained element start [few items, many items, dynamic]
    [x] test contained element middle [few items, many items, dynamic]
    [x] test contained element end [few items, many items, dynamic]
9. `boolean containsAll(HCollection c)`
    [x] test null collection [empty, few items, many items, dynamic]
    [x] test collection with null elements [empty, few items, many items, dynamic]
    [x] test collection with same elements [few items, many items, dynamic]
    [x] test collection with some elements [few items, many items, dynamic]
    [x] test collection with more elements [few items, many items, dynamic]
    [x] test collection without common elements [few items, many items, dynamic]
10. `boolean addAll(HCollection c)`
    [x] test unsupported operation [empty, few items, many items, dynamic]
11. `boolean retainAll(HCollection c)`
    [x] test null collection [empty, few items, many items, dynamic]
    [x] test collection with null elements [empty, few items, many items, dynamic]
    [x] test collection with same elements [few items, many items, dynamic]
    [x] test collection with some elements [few items, many items, dynamic]
    [x] test collection with more elements [few items, many items, dynamic]
    [x] test collection without common elements [few items, many items, dynamic]
12. `boolean removeAll(HCollection c)`
    [x] test null collection [empty, few items, many items, dynamic]
    [x] test collection with null elements [empty, few items, many items, dynamic]
    [x] test collection with same elements [few items, many items, dynamic]
    [x] test collection with some elements [few items, many items, dynamic]
    [x] test collection with more elements [few items, many items, dynamic]
    [x] test collection without common elements [few items, many items, dynamic]
13. `void clear()`
    [x] test [empty, few items, many items, dynamic]
14. `boolean equals(Object o)`
    [x] test same collection [few items, many items, dynamic]
    [x] test different collection [few items, many items, dynamic]
    [x] test not a collection [empty, few items, many items, dynamic]
15. `int hashCode()`
    [x] test different collection [few items, many items, dynamic]
    [x] test same collection [few items, many items, dynamic]

## HIterator

1. `boolean hasNext()`
    [x] test start [few items, many items, dynamic]
    [x] test middle [few items, many items, dynamic]
    [x] test end [few items, many items, dynamic]
2. `Object next()`
    [x] test with next element presence [few items, many items, dynamic]
    [x] test without next element presence [empty, few items, many items, dynamic]
3. `void remove()`
    [x] test with next call [few items, many items, dynamic]
    [x] test without next call [empty, few items, many items, dynamic]