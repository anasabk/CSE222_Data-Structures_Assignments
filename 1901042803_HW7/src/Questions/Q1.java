package Questions;

import DataStructures.Stack;
import Sorting.QuickSort;
import DataStructures.Trees.BinarySearchTree;
import DataStructures.Trees.BinaryTree;

/**
 * A class for creating binary search trees with
 * certain structures.
 */
public class Q1 {
    //The last element insrted in the order of insertion.
    private static int lastMinIndex = 0;

    /**
     * Return a binary search tree that contains the elements
     * of an array with n elements, and has the same structure
     * as a given binary tree with n elements.
     * @param binaryTree A binary tree with n nodes
     *                   that has the needed structure.
     * @param array An array with n elements that contains
     *              the data.
     * @return A binary search tree that contains the given data
     *         with the needed structure
     */
    public static <E extends Comparable<E>> BinarySearchTree<E> BtoBST(BinaryTree<E> binaryTree, E[] array) {
        if (array == null || binaryTree == null ||
            BTSize(binaryTree) != array.length)
            throw new IllegalArgumentException();

        //Sort the array
        E[] arraySorted = array.clone();
        QuickSort.sort(arraySorted);

        //Get the order of insertion of the elements
        Stack<E> order = new Stack<>();
        sortInsert(binaryTree, arraySorted, order);

        //Add the elements to the tree according to the order
        BinarySearchTree<E> result = new BinarySearchTree<>();
        while(!order.isEmpty())
            result.add(order.removeFirst());

        lastMinIndex = 0;
        return result;
    }

    /**
     * A helper function that sorts the elements in the
     * array according to their order of insertion in
     * the binary search tree.
     * @param binaryTree A binary tree with n nodes
     *                   that has the needed structure.
     * @param array An array with n elements that contains
     *              the data.
     */
    private static <E extends Comparable<E>> void sortInsert(BinaryTree<E> binaryTree, E[] array, Stack<E> insertOrder) {
        if (binaryTree == null)
            return;

        //Get the order of insertion for the left subtree
        sortInsert(binaryTree.getLeftSubtree(), array, insertOrder);
        //Store the element for the root
        E root = array[lastMinIndex];
        ++lastMinIndex;
        //Get the order of insertion for the left subtree and add it to the total
        sortInsert(binaryTree.getRightSubtree(), array, insertOrder);

        //Insert the element for the root
        insertOrder.push(root);
    }

    /**
     * Return the number of elements in the binary tree.
     * @param binaryTree A binary tree.
     * @return The number of elements.
     */
    private static <E extends Comparable<E>> int BTSize(BinaryTree<E> binaryTree) {
        if (binaryTree == null)
            return 0;

        return 1 + BTSize(binaryTree.getLeftSubtree()) + BTSize(binaryTree.getRightSubtree());
    }
}
