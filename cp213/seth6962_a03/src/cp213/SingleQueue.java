package cp213;

/**
 * A single linked queue structure of {@code T} objects. Only the front
 * {@code T} object contained in the queue is visible through the standard queue
 * methods. Extends the {@code SingleLink} class.
 *
 * @author your name, id, email here
 * @version 2025-09-07
 * @param <T> the {@code SingleNode} data type
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of {@code left} and {@code right} into {@code this}.
     * Moves nodes only - does not refer to data in any way, or call the high-level
     * methods {@code insert} or {@code remove}. {@code left} and {@code right} are
     * empty when done. Nodes are moved alternately from {@code left} and
     * {@code right} into the rear of {@code this}. {@code this} may be empty. May
     * call any appropriate {@code SingleLink} helper methods available.
     * {@code left} and {@code right} are not necessarily the same length.
     *
     * @param left  the first {@code SingleQueue} to extract nodes from
     * @param right the second {@code SingleQueue} to extract nodes from
     */
    public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {

	// your code here
    	
    	  while (!left.isEmpty() || !right.isEmpty()) {
    	        if (!left.isEmpty()) {
    	            // Move one node from left to this
    	            this.insert(left.remove());
    	        }
    	        if (!right.isEmpty()) {
    	            // Move one node from right to this
    	            this.insert(right.remove());
    	        }
    	  }
    	length = left.length + right.length;

    }

    /**
     * Adds data to the rear of {@code this}. Increments the queue length.
     *
     * @param datum the data to add to the rear of the queue
     */
    public void insert(final T datum) {

	// your code here
    	
    	SingleNode<T> new_node = new SingleNode<T>(datum, null);
    	
    	if(rear == null) {
    		front = new_node;
    		rear = new_node;
    	} else {
    		rear.setNext(new_node);
    		rear = new_node;
    	}
    	
    	length++;

	return;
    }

    /**
     * Returns and removes the front data of {@code this}. The next node in the
     * queue becomes the new front node. Decrements the queue length.
     *
     * @return the object at the front of the queue or {@code null} if {@code this}
     *         is empty
     */
    public T remove() {

	// your code here
    	T retdata = front.getDatum();
    		
    	front = front.getNext();	
    	length--;

	return retdata;
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
     * @param left  the first {@code SingleQueue} to move nodes to
     * @param right the second {@code SingleQueue} to move nodes to
     */
    public void splitAlternate(final SingleQueue<T> left, final SingleQueue<T> right) {

	// your code here
    	   boolean moveToLeft = true;

    	    // Process until this queue is empty
    	    while (this.front != null) {
    	        // Take the first node from this queue
    	        SingleNode<T> node = front;
    	        front = front.getNext(); // Move the front pointer forward
    	        node.setNext(null);             // Detach the node completely

    	        // If this queue becomes empty, update rear
    	        if (this.front == null) {
    	            this.rear = null;
    	        }

    	        // Move node to left or right alternately
    	        if (moveToLeft) {
    	            if (left.front == null) {
    	                left.front = node;
    	            } else {
    	                left.rear.setNext(node);
    	            }
    	            left.rear = node;
    	        } else {
    	            if (right.front == null) {
    	                right.front = node;
    	            } else {
    	                right.rear.setNext(node);
    	            }
    	            right.rear = node;
    	        }

    	        // Alternate for next iteration
    	        moveToLeft = !moveToLeft;
    	    }
	return;
    }
}
