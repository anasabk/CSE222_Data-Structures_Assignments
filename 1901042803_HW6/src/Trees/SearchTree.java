package Trees;

public interface SearchTree<E extends Comparable<E>> {
    /**
     * Inserts item where it belongs in the tree.
     * Returns true if item is inserted; false
     * if it isn’t (already in tree).
     * @param item Insertion target
     * @return
     */
    boolean add(E item);

    /**
     * Returns true if target is found in the tree
     * @param target
     * @return
     */
    boolean contains(E target);

    /**
     * Returns a reference to the data in the node
     * that is equal to target. If no such node is
     * found, returns null.
     * @param target
     * @return
     */
    E find(E target);

    /**
     * Removes target (if found) from tree and returns
     * it; otherwise, returns null.
     * @param target
     * @return
     */
    E delete(E target);

    /**
     * Removes target (if found) from tree and returns
     * true; otherwise, returns false.
     * @param target
     * @return
     */
    boolean remove(E target);
}
