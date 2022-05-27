package DataStructures.Trees;

import DataStructures.Trees.AVLTree.AVLNode;

/**
 * A class for converting binary search trees
 * to AVL trees.
 */
public class Q2 {
    //The height of the current root.
    private static int  height = 0;

    /**
     * Takes a binary search tree and rearranges it to make
     * an AVL tree, then returns the resulting tree as an
     * AVL tree. <P></P>
     * Warning: The resulting tree will contain the same
     * nodes inside the given binary search tree, so
     * any modifications done on either tree will affect
     * the other one.
     * @param bst The binary search tree to be rearranged.
     * @return  An AVL tree version of the rearranged
     * binary search tree.
     */
    public static <E extends Comparable<E>> AVLTree<E> BSTToAVL(BinarySearchTree<E> bst) {
        if (bst == null)
            return null;
        bst.root = balance(getAVLNode(bst.root));
        AVLTree<E> result = new AVLTree<>();
        result.root = bst.root;
        return result;
    }

    /**
     * A recursive function that balances every node that
     * is under the given root of a binary search tree
     * using rotations.
     * @param root  The root of a binary search tree.
     * @return The new root of the binary search tree.
     */
    private static <E extends Comparable<E>> AVLNode<E> balance(AVLNode<E> root) {
        if (root != null) {
            //The root is not null
            //Increment the height and store the current height
            ++height;
            int baseHeight = height;

            //Balance the left side and store its height, then reset the current height
            AVLNode<E> left = balance(getAVLNode(root.left));
            int lheight = height;
            height = baseHeight;

            //Balance the right side and store its height, then reset the current height
            AVLNode<E> right = balance(getAVLNode(root.right));
            int rheight = height;
            height = baseHeight;

            //Update the balance and the subtrees of the root
            root.left = left;
            root.right = right;
            root.balance = rheight - lheight;

            if (root.balance < -1){
                //There is a violation on the left side
                if(left.balance > 0) {
                    /*The violation is Left-Right, rotate the left
                      subtree to the left then update the balances.
                     */
                    root.left = rotateLeft(left);
                    --((AVLNode<E>) root.left).balance;
                    --root.balance;
                }

                /*Rotate the root to the right, then balance
                  the right side and update the heights.
                 */
                root = rotateRight(root);
                root.right = balance((AVLNode<E>) root.right);
                rheight = height;
                lheight--;
            }

            else if (root.balance > 1) {
                //There is a violation on the right side
                if(right.balance < 0) {
                    /*The violation is Right-Left, rotate the right
                      subtree to the right then update the balances.
                     */
                    root.right = rotateRight(right);
                    ++((AVLNode<E>) root.right).balance;
                    ++root.balance;
                }

                /*Rotate the root to the left, then balance
                  the left side and update the heights.
                 */
                root = rotateLeft(root);
                root.left = balance((AVLNode<E>) root.left);
                lheight = height;
                rheight--;
            }

            //Update the roots balance and the current height
            root.balance = rheight - lheight;
            height = (lheight + rheight + 1)/2;
        }

        return root;
    }

    /**
     * Performs a right rotation to the given root.
     * @param root The root of a binary search tree.
     * @return The new root of the rotated tree.
     */
    private static <E extends Comparable<E>> AVLNode<E> rotateRight(AVLNode<E> root) {
        if(root.left != null){
            //The left side is not null, can be rotated
            root.balance = 0;
            AVLNode<E> temp = (AVLNode<E>) root.left;
            root.left = temp.right;
            temp.right = root;
            return temp;
        }

        return root;
    }

    /**
     * Performs a left rotation to the given root.
     * @param root The root of a binary search tree.
     * @return The new root of the rotated tree.
     */
    private static <E extends Comparable<E>> AVLNode<E> rotateLeft(AVLNode<E> root) {
        if(root.right != null){
            //The right side is not null, can be rotated
            root.balance = 0;
            AVLNode<E> temp = (AVLNode<E>) root.right;
            root.right = temp.left;
            temp.left = root;
            return temp;
        }

        return root;
    }

    /**
     * Takes a normal binary tree and returns an AVL node
     * version of it.
     * @param node The node to be converted.
     * @return An AVL node version of the node.
     */
    private static <E extends Comparable<E>> AVLNode<E> getAVLNode(BinaryTree.Node<E> node) {
        AVLNode<E> avlNode;

        if(node == null)
            avlNode = null;

        else {
            avlNode = new AVLNode<>(node.data);
            avlNode.left = node.left;
            avlNode.right = node.right;
        }

        return avlNode;
    }
}
