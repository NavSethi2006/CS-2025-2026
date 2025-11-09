package cp213;

/**
 * A single linked priority queue structure of {@code T} objects. These data
 * objects must be Comparable - i.e. they must provide the {@code compareTo}
 * method. Only the {@code T} data at the front of the priority queue is visible
 * through the standard priority queue methods. Extends the {@code SingleLink}
 * class.
 *
 * @author your name, id, email here
 * @version 2025-09-07
 * @param <T> the {@code SingleNode} data type
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Combines the contents of {@code left} and {@code right} into {@code this}.
     * Moves nodes only - does not refer to data in any way, or call the high-level
     * methods {@code insert} or {@code remove}. {@code left} and {@code right} are
     * empty when done. Nodes are moved such that they are inserted in order into
     * {@code this}. May call any appropriate {@code SingleLink} helper methods
     * available. {@code left} and {@code right} are not necessarily the same
     * length. {@code this} must be empty at start, or the method throws an
     * {@code AssertionError}. (This significantly simplifies the code.)
     *
     * @param left  the first {@code SingleQueue} to extract nodes from
     * @param right the second {@code SingleQueue} to extract nodes from
     */
    public void combine(final SinglePriorityQueue<T> left, final SinglePriorityQueue<T> right) {
	assert this.front == null : "May combine into an empty Priority Queue only";

	// your code here

    SingleNode<T> nodeL = left.front;
    SingleNode<T> nodeR = right.front;
    SingleNode<T> tail = null; // tail of the combined list

    // Merge nodes from left and right in sorted order
    while (nodeL != null && nodeR != null) {
    	SingleNode<T> nextNode;

        // Choose the smaller node (based on priority, using compareTo)
        if (nodeL.getDatum().compareTo(nodeR.getDatum()) <= 0) {
            nextNode = nodeL;
            nodeL = nodeL.getNext();
        } else {
            nextNode = nodeR;
            nodeR = nodeR.getNext();
        }

        // Detach the node and link it to the combined list
        nextNode.setNext(null);
        if (this.front == null) {
            this.front = nextNode;
        } else {
            tail.setNext(nextNode);
        }
        tail = nextNode;
    }

    // Attach remaining nodes (only one list can still have nodes)
    SingleNode<T> remaining = (nodeL != null) ? nodeL : nodeR;
    while (remaining != null) {
    	SingleNode<T> next = remaining.getNext();
        remaining.setNext(null);
        if (this.front == null) {
            this.front = remaining;
        } else {
            tail.setNext(remaining);
        }
        tail = remaining;
        remaining = next;
    }

    // Update rear pointer
    this.rear = tail;

    // Empty the source queues
    left.front = left.rear = null;
    right.front = right.rear = null;

	return;
    }

    /**
     * Adds object to {@code this}. Data is stored in priority order, with highest
     * priority object at the front of {@code this}, and lowest at the rear.
     * Priority is determined by simple comparison - lower valued objects have
     * higher priority. For example, 1 has a higher priority than 2. (Think of the
     * phrase, "We're number one!" as an indication of priority.)
     * <p>
     * When inserting object int the priority queue, the queue must remain sorted.
     * Hence you need to find the proper location for the inserted object.
     *
     * @param datum object to insert in sorted order in priority queue
     */
    public void insert(final T datum) {

	// your code here
    	SingleNode<T> node = front;
    	SingleNode<T> prev = null;
    	
    	while(node != null && datum.compareTo(node.getDatum()) >= 0) {
	    	prev = node;
	    	node = node.getNext();
    	}
    	
    	SingleNode<T> newNode = new SingleNode<T>(datum, node);
    	
    	if(front == null) {
    		front = newNode;
    	}else if (prev == null) {
    		newNode.setNext(front);
    		front = newNode;
    		
    	}
    	else {
        	prev.setNext(newNode);
    	}
    	
    	length++;
    	

	return;
    }

    /**
     * Removes and returns the highest priority object in {@code this}, which is
     * located at the front node. Decrements the count.
     *
     * @return the highest priority object currently in {@code this} or {@code null}
     *         if {@code this} is empty
     */
    public T remove() {

	// your code here
    	T nodedata = front.getDatum();
    	front = front.getNext();
    	length--;
    	
	return nodedata;
    }

    /**
     * Splits the contents of {@code this} into {@code higher} and {@code lower}.
     * Moves nodes only - does not move data or call the high-level methods
     * {@code insert} or {@code remove}. {@code this} is empty when done. Nodes with
     * priority object higher than {@code key} are moved to {@code higher}. Nodes
     * with a priority object lower than or equal to {@code key} are moved to
     * {@code lower}.
     *
     * @param key    object to compare against node objects in SinglePriorityQueue
     * @param higher an initially empty {@code SinglePriorityQueue} queue that ends
     *               up with all objects with priority higher than {@code key}
     * @param lower  an initially empty {@code SinglePriorityQueue} queue that ends
     *               up with all objects with priority lower than or equal to
     *               {@code key}
     */
    public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {

        while (this.front != null) {
            // Detach the front node
            SingleNode<T> node = front;
            front = front.getNext();
            node.setNext(null);

            // If this becomes empty, clear rear
            if (front == null) {
                rear = null;
            }

            // Choose destination based on key
            SinglePriorityQueue<T> dest = (node.getDatum().compareTo(key) > 0) ? higher : lower;

            // Insert node into dest keeping dest sorted in DESCENDING order:
            // dest.front -> highest (largest) value
            if (dest.front == null) {
                // empty destination
                dest.front = dest.rear = node;
            } else {
                // if node is larger than current front, it becomes new front
                if (dest.front.getDatum().compareTo(node.getDatum()) < 0) {
                    node.setNext(dest.front);
                    dest.front = node;
                } else {
                    // find insertion point: prev such that prev.next.value < node.value
                    SingleNode<T> prev = dest.front;
                    while (prev.getNext() != null && prev.getDatum().compareTo(node.getDatum()) >= 0) {
                        prev = prev.getNext();
                    }
                    // insert after prev
                    node.setNext(prev.getNext());
                    prev.setNext(node);
                    // update rear if inserted at end
                    if (node.getNext() == null) {
                        dest.rear = node;
                    }
                }
            }
        }
    }
}
