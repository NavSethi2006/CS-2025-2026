package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2025-09-07
 * @param <T> the data structure data type
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node data is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The data to search for. Count is updated to count in matching
     *             node data if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedItem<T> key) {

	// your code here
        if (node == null) {
            return null;
        }

        int cmp = node.getCountedItem().compareTo(key);
        this.comparisons++;

        if (cmp < 0) {
            node.setLeft(retrieveAux(node.getLeft(), key));

            if (node.getLeft() != null &&
                node.getLeft().getCountedItem().getCount() >
                node.getCountedItem().getCount()) {

                node = rotateRight(node);
            }
        }
        else if (cmp > 0) {
            node.setRight(retrieveAux(node.getRight(), key));

            if (node.getRight() != null &&
                node.getRight().getCountedItem().getCount() >
                node.getCountedItem().getCount()) {

                node = rotateLeft(node);
            }
        }
        else {
            node.getCountedItem().incrementCount();
        }

        return node;

    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	// your code here
    	
        TreeNode<T> newRoot = parent.getRight();
        parent.setRight(newRoot.getLeft());
        newRoot.setLeft(parent);

        // heights must be updated
        parent.updateHeight();
        newRoot.updateHeight();

        return newRoot;

    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	// your code here
        TreeNode<T> newRoot = parent.getLeft();
        parent.setLeft(newRoot.getRight());
        newRoot.setRight(parent);

        parent.updateHeight();
        newRoot.updateHeight();

        return newRoot;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> countedItem) {

	// your code here
        if (node == null) {
        	this.size++;
            return new TreeNode<>(countedItem);
        }

        int cmp = node.getCountedItem().compareTo(countedItem);

        if (cmp < 0) {
            node.setLeft(insertAux(node.getLeft(), countedItem));
        }
        else if (cmp > 0) {
            node.setRight(insertAux(node.getRight(), countedItem));
        }


        node.updateHeight();
        return node;

    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here
        if (node == null) {
            return true;
        }

        CountedItem<T> data = node.getCountedItem();

        // Standard BST ordering
        if (minNode != null &&
            data.compareTo(minNode.getCountedItem()) <= 0) {
            return false;
        }

        if (maxNode != null &&
            data.compareTo(maxNode.getCountedItem()) >= 0) {
            return false;
        }

        // Popularity rule
        if (node.getLeft() != null &&
            node.getLeft().getCountedItem().getCount() >
            data.getCount()) {
            return false;
        }

        if (node.getRight() != null &&
            node.getRight().getCountedItem().getCount() >
            data.getCount()) {
            return false;
        }

        return isValidAux(node.getLeft(), minNode, node)
            && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, data, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedItem<T> retrieve(CountedItem<T> key) {

	// your code here
        this.root = retrieveAux(this.root, key);

        // If key was not found, count stays 0 and we don't modify the tree
        return key;

    }

}
