package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2025-09-07
 * @param <T> the data structure data type
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance data of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

	// your code here
        if (node == null) {
            return 0;
        }
        return this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {

	// your code here
    	  node.updateHeight();
          int bf = balance(node);

          if (bf > 1) {
              if (balance(node.getLeft()) < 0) {
                  node.setLeft(rotateLeft(node.getLeft()));
              }
              return rotateRight(node);
          }

          if (bf < -1) {
              if (balance(node.getRight()) > 0) {
                  node.setRight(rotateRight(node.getRight()));
              }
              return rotateLeft(node);
          }

          return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

	// your code here
    	TreeNode<T> newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        newRoot.setLeft(node);

        node.updateHeight();
        newRoot.updateHeight();

        return newRoot;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	// your code here
        TreeNode<T> newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        newRoot.setRight(node);

        node.updateHeight();
        newRoot.updateHeight();

        return newRoot;

    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL. Same as BST
     * insertion with addition of rebalance of nodes.
     *
     * @param node        The current node (TreeNode).
     * @param countedItem Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> countedItem) {

	// your code here
        if (node == null) {
            node = new TreeNode<T>(countedItem);
            node.getCountedItem().incrementCount();
            this.size++;
            return node;
        }

        int cmp = node.getCountedItem().compareTo(countedItem);

        if (cmp > 0) {
            node.setLeft(insertAux(node.getLeft(), countedItem));
        }
        else if (cmp < 0) {
            node.setRight(insertAux(node.getRight(), countedItem));
        }
        else {
            // duplicate
            node.getCountedItem().incrementCount();
            return node;
        }

        // Rebalance on return
        return rebalance(node);
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
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

        // BST property check
        if (minNode != null && node.getCountedItem().compareTo(minNode.getCountedItem()) <= 0)
            return false;

        if (maxNode != null && node.getCountedItem().compareTo(maxNode.getCountedItem()) >= 0)
            return false;

        // AVL balance property
        int bf = balance(node);
        if (bf < -1 || bf > 1)
            return false;

        // Height validity: node.height = max(left,right) + 1
        int expectedHeight = Math.max(nodeHeight(node.getLeft()), nodeHeight(node.getRight())) + 1;
        if (node.getHeight() != expectedHeight)
            return false;

        // Check subtrees
        return isValidAux(node.getLeft(), minNode, node)
                && isValidAux(node.getRight(), node, maxNode);

    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         data, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

    /**
     * Auxiliary method for remove. Removes data from this BST. Same as BST removal
     * with addition of rebalance of nodes.
     *
     * @param node        The current node (TreeNode).
     * @param countedItem Data removed from the tree.
     * @return The replacement node.
     */
    @Override
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedItem<T> countedItem) {

	// your code here
        if (node == null) {
            return null;
        }

        int cmp = node.getCountedItem().compareTo(countedItem);

        if (cmp > 0) {
            node.setLeft(removeAux(node.getLeft(), countedItem));
        }
        else if (cmp < 0) {
            node.setRight(removeAux(node.getRight(), countedItem));
        }
        else {
            // found node
            if (node.getCountedItem().getCount() > 1) {
                node.getCountedItem().decrementCount();
                return node;
            }

            // leaf
            if (node.getLeft() == null && node.getRight() == null) {
                this.size--;
                return null;
            }
            // one child
            else if (node.getLeft() == null) {
                this.size--;
                return node.getRight();
            }
            else if (node.getRight() == null) {
                this.size--;
                return node.getLeft();
            }
            else {
                // two children → replace node with successor node
                TreeNode<T> succ = node.getRight();
                while (succ.getLeft() != null) {
                    succ = succ.getLeft();
                }

                // Remove successor from its old location
                node.setRight(removeAux(node.getRight(), succ.getCountedItem()));

                // Attach children to successor
                succ.setLeft(node.getLeft());
                succ.setRight(node.getRight());

                node = succ;

                this.size--;
            }
        }

        // Rebalance on way up
        return rebalance(node);
    }

}
