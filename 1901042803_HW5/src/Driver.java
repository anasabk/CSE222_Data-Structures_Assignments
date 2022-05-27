import Q2.*;
import Q3.*;

import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        int[][] list = {{30,30}, {20,15}, {50,40}, {10,12}, {40,20}, {25,60}, {15,25}};
        QuadTree quadTree = new QuadTree(Arrays.asList(list));
        System.out.printf("\nQ2\n" + quadTree.toString());

        BinaryHeap<Integer> heapTree = new BinaryHeap<Integer>();
        heapTree.insert(6);
        heapTree.insert(4);
        heapTree.insert(7);
        heapTree.insert(5);
        heapTree.insert(9);
        heapTree.insert(2);
        heapTree.insert(8);
        heapTree.insert(3);
        heapTree.incrementKey(4);
        System.out.print("\nQ3\n" + heapTree);
    }
}
