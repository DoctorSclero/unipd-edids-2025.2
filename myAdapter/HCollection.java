package myAdapter;

/**
 * The root interface in the <i>collection hierarchy</i>.  A collection
 * represents a group of objects, known as its <i>elements</i>.  Some
 * collections allow duplicate elements and others do not.  Some are ordered and
 * others unordered.  The SDK does not provide any <i>direct</i> implementations
 * of this interface: it provides implementations of more specific subinterfaces
 * like {@code Set} and {@code List}.  This interface is typically used to pass
 * collections around and manipulate them where maximum generality is desired.
 *
 * <p><i>Bags</i> or <i>multiset</i> (unordered collections that may contain
 * duplicate elements) should implement this interface directly.</p>
 *
 * <p>All general-purpose {@code Collection} implementation classes (which
 * typically implement {@code Collection} indirectly through one of its
 * subinterfaces) should provide two "standard" constructors: a void (no
 * arguments) constructor, which creates an empty collection, and a constructor
 * with a single argument of type {@code Collection}, which creates a new
 * collection with the same elements as its argument.  In effect, the latter
 * constructor allows the user to copy any collection, producing an equivalent
 * collection of the desired implementation types. There is no way to enforce
 * this convention (as interfaces cannot contain constructors) but all the
 * general-purpose {@code Collection} implementations in the Java platform
 * libraries comply.</p>
 *
 * <p>The "destructive" methods contained in this interface, that is, the
 * methods that modify the collection on which they operate, are specified to
 * throw {@code UnsupportedOperationException} if this collection does not
 * support the operation.  If this is the case, these methods may, but are not
 * required to, throw an {@code UnsupportedOperationException} if the invocation
 * would have no effect on the collection.  For example, invoking the
 * {@link #addAll(HCollection)} method on an unmodifiable collection may, but is
 * not required to, throw the exception if the collection to be added is
 * empty.</p>
 *
 * <p>Some collection implementations have restrictions on the elements that
 * they may contain.  For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.  Attempting to add
 * an ineligible element throws an unchecked exception, typically
 * {@code NullPointerException} or {@code ClassCastException}.  Attempting to
 * query the presence of an ineligible element may throw an exception, or it may
 * simply return false; some implementations will exhibit the former behavior,
 * and some will exhibit the latter.  More generally, attempting an operation on
 * an ineligible element whose completion would not result in the insertion of
 * an ineligible element into the collection may throw an exception, or it may
 * succeed, at the option of the implementation. Such exceptions are marked as
 * "optional" in the specification for this interface.</p>
 *
 * <p>This interface is a member of the Java Collections Framework. </p>
 *
 * @see HSet
 * @see HMap
 * @since 1.2
 */

public interface HCollection {
    // Query Operations

    /**
     * Returns the number of elements in this collection.  If this collection
     * contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this collection contains the specified element.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param o element whose presence in this collection is to be tested.
     * @return {@code true} if this collection contains the specified element
     *
     * @throws ClassCastException   if the type of the specified element is
     *                              incompatible with this collection
     *                              (optional).
     * @throws NullPointerException if the specified element is null and this
     *                              collection does not support null elements
     *                              (optional).
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an {@code Iterator} over the elements in this collection
     */
    HIterator iterator();

    /**
     * Returns an array containing all the elements in this collection.  If the
     * collection makes any guarantees as to what order its elements are
     * returned by its iterator, this method must return the elements in the
     * same order.
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection. (In other words, this method must
     * allocate a new array even if this collection is backed by an array). The
     * caller is thus free to modify the returned array.</p>
     *
     * <p>This method acts as a bridge between array-based and collection-based
     * APIs.</p>
     *
     * @return an array containing all the elements in this collection
     */
    Object[] toArray();

    /**
     * Returns an array containing all the elements in this collection; the
     * runtime type of the returned array is that of the specified array. If the
     * collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this collection.
     *
     * <p>If this collection fits in the specified array with room to spare (i.e.,
     * the array has more elements than this collection), the element in the
     * array immediately following the end of the collection is set to
     * {@code null}. This is useful in determining the length of this
     * collection <i>only</i> if the caller knows that this collection does not
     * contain any {@code null} elements.)</p>
     *
     * <p>If this collection makes any guarantees as to what order its elements are
     * returned by its iterator, this method must return the elements in the
     * same order.</p>
     *
     * <p> Like the {@code toArray} method, this method acts as bridge between
     * array-based and collection-based APIs. Further, this method allows
     * precise control over the runtime type of the output array, and may, under
     * certain circumstances, be used to save allocation costs</p>
     *
     * <p> Suppose {@code l} is a {@code List} known to contain only strings. The
     * following code can be used to dump the list into a newly allocated array
     * of {@code String}:</p>
     *
     * <pre>
     *     String[] x = (String[]) v.toArray(new String[0]);
     * </pre>
     *
     * <p> Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.</p>
     *
     * @param a the array into which the elements of this collection are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * @return an array containing the elements of this collection
     *
     * @throws ArrayStoreException  the runtime type of the specified array is
     *                              not a supertype of the runtime type of every
     *                              element in this collection.
     * @throws NullPointerException if the specified array is {@code null}.
     */

    Object[] toArray(Object a[]);

    // Modification Operations

    /**
     * Ensures that this collection contains the specified element (optional
     * operation).  Returns {@code true} if this collection changed as a result
     * of the call.  (Returns {@code false} if this collection does not permit
     * duplicates and already contains the specified element.)
     *
     * <p> Collections that support this operation may place limitations on what
     * elements may be added to this collection.  In particular, some
     * collections will refuse to add {@code null} elements, and others will
     * impose restrictions on the type of elements that may be added. Collection
     * classes should clearly specify in their documentation any restrictions on
     * what elements may be added.</p>
     *
     * <p> If a collection refuses to add a particular element for any reason other
     * than that it already contains the element, it <i>must</i> throw an
     * exception (rather than returning {@code false}).  This preserves the
     * invariant that a collection always contains the specified element after
     * this call returns.</p>
     *
     * @param o element whose presence in this collection is to be ensured.
     * @return {@code true} if this collection changed as a result of the call
     *
     * @throws UnsupportedOperationException {@code add} is not supported by
     *                                       this collection.
     * @throws ClassCastException            if the class of the specified
     *                                       element prevents it from being
     *                                       added to this collection.
     * @throws NullPointerException          if the specified element is null
     *                                       and this collection does not
     *                                       support null elements.
     * @throws IllegalArgumentException      some aspect of this element
     *                                       prevents it from being added to
     *                                       this collection.
     */
    boolean add(Object o);

    /**
     * Removes a single instance of the specified element from this collection
     * if it is present (optional operation). More formally, removes an element
     * {@code e} such that {@code (o==null ? e==null : o.equals(e))}, if this
     * collection contains one or more such elements.  Returns true if this
     * collection contained the specified element (or equivalently, if this
     * collection changed as a result of the call).
     *
     * @param o element to be removed from this collection, if present.
     * @return {@code true} if this collection changed as a result of the call
     *
     * @throws ClassCastException            if the type of the specified
     *                                       element is incompatible with this
     *                                       collection (optional).
     * @throws NullPointerException          if the specified element is null
     *                                       and this collection does not
     *                                       support null elements (optional).
     * @throws UnsupportedOperationException remove is not supported by this
     *                                       collection.
     */
    boolean remove(Object o);


    // Bulk Operations

    /**
     * Returns {@code true} if this collection contains all of the elements in
     * the specified collection.
     *
     * @param c collection to be checked for containment in this collection.
     * @return {@code true} if this collection contains all of the elements in
     * the specified collection
     *
     * @throws ClassCastException   if the types of one or more elements in the
     *                              specified collection are incompatible with
     *                              this collection (optional).
     * @throws NullPointerException if the specified collection contains one or
     *                              more null elements and this collection does
     *                              not support null elements (optional).
     * @throws NullPointerException if the specified collection is
     *                              {@code null}.
     * @see #contains(Object)
     */
    boolean containsAll(HCollection c);

    /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if the
     * specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)
     *
     * @param c elements to be inserted into this collection.
     * @return {@code true} if this collection changed as a result of the call
     *
     * @throws UnsupportedOperationException if this collection does not support
     *                                       the {@code addAll} method.
     * @throws ClassCastException            if the class of an element of the
     *                                       specified collection prevents it
     *                                       from being added to this
     *                                       collection.
     * @throws NullPointerException          if the specified collection
     *                                       contains one or more null elements
     *                                       and this collection does not
     *                                       support null elements, or if the
     *                                       specified collection is
     *                                       {@code null}.
     * @throws IllegalArgumentException      some aspect of an element of the
     *                                       specified collection prevents it
     *                                       from being added to this
     *                                       collection.
     * @see #add(Object)
     */
    boolean addAll(HCollection c);

    /**
     * Removes all this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns, this
     * collection will contain no elements in common with the specified
     * collection.
     *
     * @param c elements to be removed from this collection.
     * @return {@code true} if this collection changed as a result of the call
     *
     * @throws UnsupportedOperationException if the {@code removeAll} method is
     *                                       not supported by this collection.
     * @throws ClassCastException            if the types of one or more
     *                                       elements in this collection are
     *                                       incompatible with the specified
     *                                       collection (optional).
     * @throws NullPointerException          if this collection contains one or
     *                                       more null elements and the
     *                                       specified collection does not
     *                                       support null elements (optional).
     * @throws NullPointerException          if the specified collection is
     *                                       {@code null}.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).  In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     *
     * @param c elements to be retained in this collection.
     * @return {@code true} if this collection changed as a result of the call
     *
     * @throws UnsupportedOperationException if the {@code retainAll} method is
     *                                       not supported by this Collection.
     * @throws ClassCastException            if the types of one or more
     *                                       elements in this collection are
     *                                       incompatible with the specified
     *                                       collection (optional).
     * @throws NullPointerException          if this collection contains one or
     *                                       more null elements and the
     *                                       specified collection does not
     *                                       support null elements (optional).
     * @throws NullPointerException          if the specified collection is
     *                                       {@code null}.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(HCollection c);

    /**
     * Removes all of the elements from this collection (optional operation).
     * This collection will be empty after this method returns unless it throws
     * an exception.
     *
     * @throws UnsupportedOperationException if the {@code clear} method is not
     *                                       supported by this collection.
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this collection for equality.
     *
     * <p> While the {@code Collection} interface adds no stipulations to the
     * general contract for the {@code Object.equals}, programmers who implement
     * the {@code Collection} interface "directly" (in other words, create a
     * class that is a {@code Collection} but is not a {@code Set} or a
     * {@code List}) must exercise care if they choose to override the
     * {@code Object.equals}.  It is not necessary to do so, and the simplest
     * course of action is to rely on {@code Object}'s implementation, but the
     * implementer may wish to implement a "value comparison" in place of the
     * default "reference comparison."  (The {@code List} and {@code Set}
     * interfaces mandate such value comparisons.) </p>
     *
     * <p> The general contract for the {@code Object.equals} method states that
     * equals must be symmetric (in other words, {@code a.equals(b)} if and only
     * if {@code b.equals(a)}).  The contracts for {@code List.equals} and
     * {@code Set.equals} state that lists are only equal to other lists, and
     * sets to other sets.  Thus, a custom {@code equals} method for a
     * collection class that implements neither the {@code List} nor {@code Set}
     * interface must return {@code false} when this collection is compared to
     * any list or set.  (By the same logic, it is not possible to write a class
     * that correctly implements both the {@code Set} and {@code List}
     * interfaces.) </p>
     *
     * @param o Object to be compared for equality with this collection.
     * @return {@code true} if the specified object is equal to this collection
     *
     * @see Object#equals(Object)
     * @see HSet#equals(Object)
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.  While the
     * {@code Collection} interface adds no stipulations to the general contract
     * for the {@code Object.hashCode} method, programmers should take note that
     * any class that overrides the {@code Object.equals} method must also
     * override the {@code Object.hashCode} method in order to satisfy the
     * general contract for the {@code Object.hashCode}method. In particular,
     * {@code c1.equals(c2)} implies that {@code c1.hashCode()==c2.hashCode()}.
     *
     * @return the hash code value for this collection
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    int hashCode();
}