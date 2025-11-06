package cp213;

/**
 * A single linked list structure of {@code T} objects. Extends the
 * {@code SingleLink} class.
 *
 * @author your name, id, email here
 * @version 2025-09-07
 * @param <T> the {@code SingleNode} data type
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of {@code key} in this SingleList. Private
     * helper methods - used only by other ADT methods.
     *
     * @param key the object to look for
     * @return a pointer to the node previous to the node containing {@code key}
     */
    private SingleNode<T> linearSearch(final T key) {

	// your code here
     SingleNode<T> curr = front;

     while(curr != null) {
    	 
    	 if(curr.getDatum().equals(key)) {
    		 return curr;
    	 }
    	 
    	 
    	 curr = curr.getNext();
     }

	return null;
    }

    /**
     * Appends data to the end of {@code this}
     *
     * @param datum the object to append
     */
    public void append(final T datum) {

	// your code here
    	SingleNode<T> new_node = new SingleNode<T>(datum, null);
    	
    	if(front == null) {
    		front = new_node;
    		rear = new_node;
    	} else {
    		rear.setNext(new_node);
    		rear = new_node;
    	}
    	

	return;
    }

    /**
     * Removes duplicates from {@code this}. The list contains one and only one of
     * each object formerly present in {@code this}. The first occurrence of each
     * object is preserved.
     */
    public void clean() {

	// your code here
    	

	return;
    }

    /**
     * Combines the contents of {@code left} and {@code right} into {@code this}.
     * Moves nodes only - does not refer to data in any way, or call the high-level
     * methods {@code insert} or {@code remove}. {@code left} and {@code right} are
     * empty when done. Nodes are moved alternately from {@code left} and
     * {@code right} into {@code this}. {@code this} may be empty. May call any
     * appropriate {@code SingleLink} helper methods available. {@code left} and
     * {@code right} are not necessarily the same length.
     *
     * @param left  the first {@code SingleList} to extract nodes from
     * @param right the second {@code SingleList} to extract nodes from
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	// your code here
    	this.append(left);
    	this.append(right);
    	
	return;
    }

    /**
     * Determines if {@code this} contains {@code key}.
     *
     * @param key the key object to look for
     * @return {@code true} if {@code key} is in {@code this}, {@code false}
     *         otherwise
     */
    public boolean contains(final T key) {

	// your code here
    	SingleNode curr = front;
    	
    	while (curr != null) {
    		
    		if(curr.getDatum().equals(key)) {
    			return true;
    		}
    		
    		curr = curr.getNext();
    	}

	return false;
    }

    /**
     * Finds the number of times {@code key} appears in {@code this}.
     *
     * @param key the object to look for
     * @return the number of times {@code key} appears in {@code this}
     */
    public int count(final T key) {

	// your code here
    	
    	int count = 0;
    	SingleNode curr = front;
    	
    	while (curr != null) {
    		
    		if(curr.getDatum().equals(key)) {
    			count++;
    		}
    		
    		curr = curr.getNext();
    	}

	return count;
    }

    /**
     * Finds and returns the object in {@code this} that matches {@code key}.
     *
     * @param key the object to search for
     * @return the object that matches {@code key}, {@code null} otherwise
     */
    public T find(final T key) {

	// your code here
    	SingleNode curr = front;
    	
    	while (curr != null) {
    		
    		if(curr.getDatum().equals(key)) {
    			return curr.getDatum(T);
    		}
    		
    		curr = curr.getNext();
    	}

    	

	return null;
    }

    /**
     * Get the {@code n}th object in {@code this}.
     *
     * @param n the index of the object to return
     * @return the nth object in {@code this} if {@code n} is a valid index,
     *         {@code null} otherwise
     */
    public T get(final int n) {

	// your code here

	return null;
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source the list to compare against {@code this}
     * @return {@code true} if {@code this} contains the same objects in the same
     *         order as {@code source}, {@code false} otherwise
     */
    public boolean equals(final SingleList<T> source) {

	// your code here

	return false;
    }

    /**
     * Finds the first location of {@code key} in {@code this}.
     *
     * @param key the object to search for
     * @return the index of {@code key} in {@code this}, -1 otherwise
     */
    public int index(final T key) {

	// your code here

	return 0;
    }

    /**
     * Inserts object into {@code this} at index {@code i}. If {@code i} greater
     * than the length of {@code this}, append data to the rear of {@code this}.
     *
     * @param i     The index to insert the new data at.
     * @param datum The new object to insert into this SingleList.
     */
    public void insert(int i, final T datum) {

	// your code here
    	
    	

	return;
    }

    /**
     * Creates an intersection of {@code left} and {@code right} into {@code this}.
     * Copies data to {@code this}. {@code left} and {@code right} are unchanged.
     * Values from {@code left} are copied in order first, then objects from
     * {@code right} are copied in order.
     * <p>
     * In an intersection, data copied to {@code this} must appear in both
     * {@code left} and {@code right}.
     *
     * @param left  the first {@code SingleList} to create an intersection from
     * @param right the second {@code SingleList} to create an intersection from
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	return;
    }

    /**
     * Finds the maximum object in {@code this}.
     *
     * @return the maximum object or {@code null} if {@code this} is empty
     */
    public T max() {

	// your code here

	return null;
    }

    /**
     * Finds the minimum object in {@code this}.
     *
     * @return the minimum object or {@code null} if {@code this} is empty
     */
    public T min() {

	// your code here

	return null;
    }

    /**
     * Inserts object into the front of {@code this}.
     *
     * @param datum the object to insert into the front of {@code this}
     */
    public void prepend(final T datum) {

	// your code here

	return;
    }

    /**
     * Finds, removes, and returns the object in {@code this} that matches
     * {@code key}.
     *
     * @param key the object to search for
     * @return the object matching {@code key}, {@code null} otherwise
     */
    public T remove(final T key) {

	// your code here

	return null;
    }

    /**
     * Removes and returns the object at the front of {@code this}.
     *
     * @return the object at the front of {@code this}, if empty return {@code null}
     */
    public T removeFront() {

	// your code here

	return null;
    }

    /**
     * Finds and removes all objects in {@code this} that match {@code key}.
     *
     * @param key the object to search for
     */
    public void removeMany(final T key) {

	// your code here

	return;
    }

    /**
     * Reverses the order of the objects in {@code this}.
     */
    public void reverse() {

	// your code here

	return;
    }

    /**
     * Splits the contents of {@code this} into {@code left} and {@code right}.
     * Moves nodes only - does not move data or call high-level methods.
     * {@code this} is empty when done.The first half of {@code this} is moved to
     * {@code left}, and the last half of {@code this} is moved to {@code right}. If
     * the resulting lengths are not the same, left should have one more object than
     * right. Order is preserved. {@code left} and {@code right} may already contain
     * data.
     *
     * @param left  the first {@code SingleList} to move nodes to
     * @param right the second {@code SingleList} to move nodes to
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	return;
    }

    /**
     * Splits the contents of {@code this} into {@code left} and {@code right}.
     * Moves nodes only - does not move data or call the high-level methods
     * {@code insert} or {@code remove}. {@code this} is empty when done. Nodes are
     * moved alternately from {@code this} to the rear of {@code left} and
     * {@code right}. {@code left} and {@code right} may already contain data.
     * <p>
     * This is the opposite of the combine method.
     *
     * @param left  the first {@code SingleList} to move nodes to
     * @param right the second {@code SingleList} to move nodes to
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	return;
    }

    /**
     * Creates an union of {@code left} and {@code right} into {@code this}. Copies
     * data to {@code this}. {@code left} and {@code right} are unchanged. Values
     * from {@code left} are copied in order first, then objects from {@code right}
     * are copied in order.
     * <p>
     * In an intersection, data copied to {@code this} must appear in one or both of
     * {@code left} and {@code right}.
     *
     * @param left  the first {@code SingleList} to create an union from
     * @param right the second {@code SingleList} to create an union from
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	// your code here

	return;
    }
}
