package cp213;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The abstract base class for singly-linked data structures. Provides
 * attributes and implementations for {@code getLength}, {@code peek},
 * {@code isEmpty}, {@code append}, {@code moveFrontToRear},
 * {@code moveFrontToFront}, and iterator methods.
 *
 * @author David Brown
 * @version 2025-09-07
 * @param <T> the {@code SingleNode} data type
 */
public abstract class SingleLink<T> implements Iterable<T> {

    /**
     * Creates an Iterator for the outer class. An iterator allows a program to walk
     * through the objects in a data structure by using the {@code hasNext} and
     * {@code next} methods. Typical code:
     *
     * <pre>
    Iterator&lt;T&gt; iter = dataStructure.iterator();
    
    while(iter.hasNext()){
        T data = iter.next();
        ...
    }
     * </pre>
     *
     * It also allows the user of the enhanced for loop:
     *
     * <pre>
    for(T data : dataStructure){
        ...
    }
     * </pre>
     *
     * (Replace T with a concrete class such as String or Integer.)
     */
    private class SingleLinkIterator implements Iterator<T> {
	/**
	 * {@code current} is initialized to beginning of the linked structure.
	 */
	private SingleNode<T> current = SingleLink.this.front;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
	    return this.current != null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
	    T result = null;

	    if (this.current == null) {
		throw new NoSuchElementException();
	    } else {
		result = this.current.getDatum();
		this.current = this.current.getNext();
	    }
	    return result;
	}
    }

    /**
     * First node of linked structure.
     */
    protected SingleNode<T> front = null;
    /**
     * Number of objects currently stored in linked structure. Must be updated as
     * nodes are added and removed.
     */
    protected int length = 0;
    /**
     * Last node of linked structure.
     */
    protected SingleNode<T> rear = null;

    /**
     * Protected helper method to append the entire source to the rear of this.
     * {@code source} becomes empty. O(1) operation - must not use loops. if
     * {@code source} is empty, {@code this} is unchanged.
     *
     * @param source the nodes to append to the end of {@code this}
     */
    protected void append(final SingleLink<T> source) {
	if (source.front != null) {

	    // Update this.
	    if (this.front == null) {
		// this is empty.
		this.front = source.front;
	    } else {
		// Add source to rear of this.
		this.rear.setNext(source.front);
	    }
	    this.rear = source.rear;
	    this.length += source.length;
	    // Empty source.
	    source.front = null;
	    source.rear = null;
	    source.length = 0;
	}
	return;
    }

    /**
     * Protected helper method to move the front node of {@code source} to the front
     * of {@code this}. All front, rear, and length attributes are appropriately
     * updated in both {@code SingleLink} objects. O(1) operation - must not use
     * loops. if {@code source} is empty, {@code this} is unchanged.
     *
     * @param source the {@code SingleLink} to extract the front node from
     */
    protected void moveFrontToFront(final SingleLink<T> source) {
	if (source.front != null) {

	    final SingleNode<T> node = source.front;
	    // Update source.
	    source.length--;
	    source.front = source.front.getNext();

	    if (source.front == null) {
		// Clean up source if empty.
		source.rear = null;
	    }
	    node.setNext(this.front);

	    // Update this.
	    if (this.rear == null) {
		this.rear = node;
	    }
	    this.front = node;
	    this.length++;
	}
	return;
    }

    /**
     * Protected helper method to move the front node of {@code source} to the rear
     * of {@code this}. All front, rear, and length attributes are appropriately
     * updated in both {@code SingleLink} objects. O(1) operation - must not use
     * loops. if {@code source} is empty, {@code this} is unchanged.
     *
     * @param source the {@code SingleLink} to extract the front node from
     */
    protected void moveFrontToRear(final SingleLink<T> source) {
	if (source.front != null) {
	    final SingleNode<T> node = source.front;
	    // Update source.
	    source.length--;
	    source.front = source.front.getNext();

	    if (source.front == null) {
		// Clean up source if empty.
		source.rear = null;
	    }
	    node.setNext(null);

	    // Update this.
	    if (this.rear == null) {
		this.front = node;
	    } else {
		this.rear.setNext(node);
	    }
	    this.rear = node;
	    this.length++;
	}
	return;
    }

    /**
     * Returns the current number of objects in the linked structure.
     *
     * @return the number of objects in this data structure.
     */
    public final int getLength() {
	return this.length;
    }

    /**
     * Determines whether the linked data structure is empty or not.
     *
     * @return {@code true} if {@code this} is empty, {@code false} otherwise
     */
    public final boolean isEmpty() {
	return this.front == null;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public final Iterator<T> iterator() {
	return new SingleLinkIterator();
    }

    /**
     * Returns a reference to the first object of {@code this} without removing that
     * object from the structure.
     *
     * @return the object at the front of {@code this} or {@code null} if
     *         {@code this} is empty
     */
    public final T peek() {
	T datum = null;

	if (this.front != null) {
	    datum = this.front.getDatum();
	}
	return datum;
    }

    /**
     * Returns a string version of the data structure in the format:
     *
     * <pre>
     * front > ... > null
     * </pre>
     */
    @Override
    public String toString() {
	String string = "front > ";
	SingleNode<T> curr = this.front;

	while (curr != null) {
	    string += curr.getDatum() + " > ";
	    curr = curr.getNext();
	}
	string += "null";
	return string;
    }

}
