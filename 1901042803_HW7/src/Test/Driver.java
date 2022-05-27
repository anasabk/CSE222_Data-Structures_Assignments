package Test;

import Questions.Q1;
import DataStructures.Trees.*;

public class Driver {
    public static void main(String[] args) {
        System.out.print("Q1 test:\n");
        Integer[] list = new Integer[] {3, 5, 12, 1, 4, 7, 6};
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        BST.add(5);
        BST.add(2);
        BST.add(4);
        BST.add(13);
        BST.add(8);
        BST.add(7);
        BST.add(6);
        System.out.print("Base structure:\n" + BST + "\n");
        BST = Q1.BtoBST(BST, list);
        System.out.print("Result BST:\n" + BST + "\n");

        System.out.print("\nQ2 test:\n");
        BST = new BinarySearchTree<>();
        for (int i = 0; i < 7; ++i)
            BST.add((int) (320 * Math.random()));

        System.out.print("BST test:\n" + BST + "\n");
        BST = Q2.BSTToAVL(BST);
        System.out.print("AVL test:\n" + BST + "\n");
    }
}
