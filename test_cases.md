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
    [-] test value present start [few items, many items, dynamic]
    [-] test value present middle [few items, many items, dynamic]
    [-] test value present end [few items, many items, dynamic]
    [-] test value present multiple times [few items, many items, dynamic]
3. `boolean containsKey(Object key)`
    [ ] test key null [empty, few items, many items, performance, dynamic]
    [ ] test key not present [empty, few items, many items, performance, dynamic]
    [ ] test key present start [few items, many items, dynamic]
    [ ] test key present middle [few items, many items, dynamic]
    [ ] test key present end [few items, many items, dynamic]
4. `HSet entrySet()`
    [ ] test return not null [empty, few items, many items, dynamic]
    [ ] [entryset test s] [empty, few items, many items, dynamic]
5. `boolean equals()`
    [ ] test null object [empty, few items, many items, dynamic]
    [ ] test not a map object [empty, few items, many items, dynamic]
    [ ] test different map same mappings same order [few items, many items, dynamic]
    [ ] test different map same mappings different order [few items, many items, dynamic]
    [ ] test different map different mappings [few items, many items, dynamic]
    [ ] test same map same mappings different order [few items, many items, dynamic]
    [ ] test same map same mappings same order [few items, many items, dynamic]
6. `Object get()`
    [ ] test retrival key null [empty, few items, many items, performance, dynamic]
    [ ] test retrival key not present [empty, few items, many items, performance, dynamic]
    [ ] test retrival key present start [few items, many items, dynamic]
    [ ] test retrival key present middle [few items, many items, dynamic]
    [ ] test retrival key present end [few items, many items, dynamic]
7. `int hashCode()`
    [ ] test same map [few items, many items, dynamic]
    [ ] test different map [few items, many items, dynamic]
    [ ] test equals implies same hash code [few items, many items, dynamic]
8. `boolean isEmpty()`
    [ ] test [empty, few items, many items, performance, dynamic]
9. `HSet keySet()`
    [ ] test return not null [empty, few items, many items, dynamic]
    [ ] [keyset test s] [empty, few items, many items, dynamic]
10. `Object put(Object key, Object value)`
    [ ] test put null key valid value [empty, few items, many items, performance, dynamic]
    [ ] test put null value valid key [empty, few items, many items, performance, dynamic]
    [ ] test put null key and value [empty, few items, many items, performance, dynamic]
    [ ] test put present key start [few items, many items, dynamic]
    [ ] test put present key middle [few items, many items, dynamic]
    [ ] test put present key end [few items, many items, dynamic]
    [ ] test put new key [few items, many items, dynamic]
    [ ] test put map instance as key [few items, many items, dynamic]
    [ ] test put map instance as key and value [few items, many items, dynamic]
11. `void putAll(HMap t)`
    [ ] test put null map [empty, few items, many items, performance, dynamic]
    [ ] test put map with null key valid value entry [empty, few items, many items, performance, dynamic]
    [ ] test put map with null value valid key entry [empty, few items, many items, performance, dynamic]
    [ ] test put map with null key and value entry [empty, few items, many items, performance, dynamic]
    [ ] test put map with present key start entry [few items, many items, dynamic]
    [ ] test put map with present key middle entry [few items, many items, dynamic]
    [ ] test put map with present key end entry [few items, many items, dynamic]
    [ ] test put map with new key entry [few items, many items, dynamic]
    [ ] test put map with map instance as key entry [few items, many items, dynamic]
    [ ] test put map with map instance as key and value entry [few items, many items, dynamic]
12. `Object remove(Object key)`
    [ ] test null key [empty, few items, many items, performance, dynamic]
    [ ] test absent key [empty, few items, many items, performance, dynamic]
    [ ] test present key start [few items, many items, dynamic]
    [ ] test present key middle [few items, many items, dynamic]
    [ ] test present key end [few items, many items, dynamic]
13. `int size()`
    [ ] test [empty, few items, many items, performance, dynamic]
14. `HCollection values()`
    [ ] test return not null [empty, few items, many items, dynamic]
    [ ] [value collection test] [empty, few items, many items, dynamic]

## HSet (EntrySet & KeySet)
1. `int size()`
    [ ] test [empty, few items, many items, dynamic]
2. `boolean isEmpty()`
    [ ] test [empty, few items, many items, dynamic]
3. `boolean contains(Object o)`
    [ ] test null element [empty, few items, many items, dynamic]
    [ ] test not contained element [empty, few items, many items, dynamic]
    [ ] test contained element start [few items, many items, dynamic]
    [ ] test contained element middle [few items, many items, dynamic]
    [ ] test contained element end [few items, many items, dynamic]
4. `HIterator iterator()`
    [ ] test return not null [empty, few items, many items, dynamic]
    [ ] [iterator tests] [empty, few items, many items, dynamic]
5. `Object[] toArray()`
    [ ] test [empty, few items, many items, dynamic]
6. `Object[] toArray(Object a[])`
    [ ] test null array [empty, few items, many items, dynamic]
    [ ] test array smaller [few items, many items, dynamic]
    [ ] test array bigger [few items, many items, dynamic]
7. `boolean add(Object o)`
    [ ] test unsupported operation [empty, few items, many items, dynamic]
8. `boolean remove(Object o)`
    [ ] test null element [empty, few items, many items, dynamic]
    [ ] test not contained element [empty, few items, many items, dynamic]
    [ ] test contained element start [few items, many items, dynamic]
    [ ] test contained element middle [few items, many items, dynamic]
    [ ] test contained element end [few items, many items, dynamic]
9. `boolean containsAll(HCollection c)`
    [ ] test null collection [empty, few items, many items, dynamic]
    [ ] test collection with null elements [empty, few items, many items, dynamic]
    [ ] test collection with same elements [few items, many items, dynamic]
    [ ] test collection with some elements [few items, many items, dynamic]
    [ ] test collection with more elements [few items, many items, dynamic]
    [ ] test collection without common elements [few items, many items, dynamic]
10. `boolean addAll(HCollection c)`
    [ ] test unsupported operation [empty, few items, many items, dynamic]
11. `boolean retainAll(HCollection c)`
    [ ] test null collection [empty, few items, many items, dynamic]
    [ ] test collection with null elements [empty, few items, many items, dynamic]
    [ ] test collection with same elements [few items, many items, dynamic]
    [ ] test collection with some elements [few items, many items, dynamic]
    [ ] test collection with more elements [few items, many items, dynamic]
    [ ] test collection without common elements [few items, many items, dynamic]
12. `boolean removeAll(HCollection c)`
    [ ] test null collection [empty, few items, many items, dynamic]
    [ ] test collection with null elements [empty, few items, many items, dynamic]
    [ ] test collection with same elements [few items, many items, dynamic]
    [ ] test collection with some elements [few items, many items, dynamic]
    [ ] test collection with more elements [few items, many items, dynamic]
    [ ] test collection without common elements [few items, many items, dynamic]
13. `void clear()`
    [ ] test [empty, few items, many items, dynamic]
14. `boolean equals(Object o)`
    [ ] test same set [few items, many items, dynamic]
    [ ] test different set [few items, many items, dynamic]
15. `int hashCode()`
    [ ] test different set [few items, many items, dynamic]
    [ ] test same set [few items, many items, dynamic]
    [ ] test equals implies same hash code [few items, many items, dynamic]

## HCollection

1. `int size()`
    [ ] test [empty, few items, many items, dynamic]
2. `boolean isEmpty()`
    [ ] test [empty, few items, many items, dynamic]
3. `boolean contains(Object o)`
    [ ] test null element [empty, few items, many items, dynamic]
    [ ] test not contained element [empty, few items, many items, dynamic]
    [ ] test contained element start [few items, many items, dynamic]
    [ ] test contained element middle [few items, many items, dynamic]
    [ ] test contained element end [few items, many items, dynamic]
4. `HIterator iterator()`
    [ ] test return not null [empty, few items, many items, dynamic]
    [ ] [iterator tests] [empty, few items, many items, dynamic]
5. `Object[] toArray()`
    [ ] test [empty, few items, many items, dynamic]
6. `Object[] toArray(Object a[])`
    [ ] test null array [empty, few items, many items, dynamic]
    [ ] test array smaller [few items, many items, dynamic]
    [ ] test array bigger [few items, many items, dynamic]
7. `boolean add(Object o)`
    [ ] test unsupported operation [empty, few items, many items, dynamic]
8. `boolean remove(Object o)`
    [ ] test null element [empty, few items, many items, dynamic]
    [ ] test not contained element [empty, few items, many items, dynamic]
    [ ] test contained element start [few items, many items, dynamic]
    [ ] test contained element middle [few items, many items, dynamic]
    [ ] test contained element end [few items, many items, dynamic]
9. `boolean containsAll(HCollection c)`
    [ ] test null collection [empty, few items, many items, dynamic]
    [ ] test collection with null elements [empty, few items, many items, dynamic]
    [ ] test collection with same elements [few items, many items, dynamic]
    [ ] test collection with some elements [few items, many items, dynamic]
    [ ] test collection with more elements [few items, many items, dynamic]
    [ ] test collection without common elements [few items, many items, dynamic]
10. `boolean addAll(HCollection c)`
    [ ] test unsupported operation [empty, few items, many items, dynamic]
11. `boolean retainAll(HCollection c)`
    [ ] test null collection [empty, few items, many items, dynamic]
    [ ] test collection with null elements [empty, few items, many items, dynamic]
    [ ] test collection with same elements [few items, many items, dynamic]
    [ ] test collection with some elements [few items, many items, dynamic]
    [ ] test collection with more elements [few items, many items, dynamic]
    [ ] test collection without common elements [few items, many items, dynamic]
12. `boolean removeAll(HCollection c)`
    [ ] test null collection [empty, few items, many items, dynamic]
    [ ] test collection with null elements [empty, few items, many items, dynamic]
    [ ] test collection with same elements [few items, many items, dynamic]
    [ ] test collection with some elements [few items, many items, dynamic]
    [ ] test collection with more elements [few items, many items, dynamic]
    [ ] test collection without common elements [few items, many items, dynamic]
13. `void clear()`
    [ ] test [empty, few items, many items, dynamic]
14. `boolean equals(Object o)`
    [ ] test same collection [few items, many items, dynamic]
    [ ] test different collection [few items, many items, dynamic]
15. `int hashCode()`
    [ ] test different collection [few items, many items, dynamic]
    [ ] test same collection [few items, many items, dynamic]
    [ ] test equals implies same hash code [few items, many items, dynamic]

## HIterator

1. `boolean hasNext()`
    [ ] test start [few items, many items, dynamic]
    [ ] test middle [few items, many items, dynamic]
    [ ] test end [few items, many items, dynamic]
2. `Object next()`
    [ ] test with next element presence [few items, many items, dynamic]
    [ ] test without next element presence [empty, few items, many items, dynamic]
3. `void remove()`
    [ ] test with next call [few items, many items, dynamic]
    [ ] test without next call [empty, few items, many items, dynamic]