package cp213;

/**
 * A single linked stack structure of {@code T} objects. Only the top {@code T}
 * object contained in the stack is visible through the standard stack methods.
 * Extends the {@code SingleLink} class. Note that the {@code rear} attribute
 * should be ignored as the {@code rear} is not used in a stack.
 *
 * @author your name, id, email here
 * @version 2025-09-07
 * @param <T> the {@code SingleNode} data type
 */
public class SingleStack<T> extends SingleLink<T> {

    /**
     * Combines the contents of {@code left} and {@code right} into {@code this}.
     * Moves nodes only - does not refer to data in any way, or call the high-level
     * methods {@code pop} or {@code push}. {@code left} and {@code right} are empty
     * when done. Nodes are moved alternately from {@code left} and {@code right}
     * into {@code this}. {@code this} may be empty. May call any appropriate
     * {@code SingleLink} helper methods available. {@code left} and {@code right}
     * are not necessarily the same length.
     *
     * @param left  the first {@code SingleStack} to extract nodes from
     * @param right the second {@code SingleStack} to extract nodes from
     */
    public void combine(final SingleStack<T> left, final SingleStack<T> right) {

	// your code here

	return;
    }

    /**
     * Returns and removes the top data of {@code this}. The next node in the stack
     * becomes the new top node. Decrements the stack length.
     *
     * @return the object at the top of {@code this} or {@code null} if {@code this}
     *         is empty
     */
    public T pop() {

	// your code here

	return null;
    }

    /**
     * Adds data to the top of {@code this}. Increments the stack length.
     *
     * @param datum the object to add to the top of the stack
     */
    public void push(final T datum) {

	// your code here

	return;
    }

    /**
     * Splits the contents of {@code this} into {@code left} and {@code right}.
     * Moves nodes only - does not move data or call the high-level methods
     * {@code pop} or {@code push}. {@code this} is empty when done. Nodes are moved
     * alternately from {@code this} to {@code left} and {@code right}. {@code left}
     * and {@code right} may already contain data.
     * <p>
     * This is the opposite of the combine method.
     *
     * @param left  the first {@code SingleStack} to move nodes to
     * @param right the second {@code SingleStack} to move nodes to
     */
    public void splitAlternate(final SingleStack<T> left, final SingleStack<T> right) {

	// your code here

	return;
    }
}