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

	return null;
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

	// your code here

	return;
    }
}
