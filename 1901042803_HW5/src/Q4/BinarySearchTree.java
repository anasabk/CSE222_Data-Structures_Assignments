package Q4;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> implements SearchTree<E>{
    private ArrayList<E> _data;
    private int _size, _capacity;

    @Override
    public boolean add(E item) {
        int parent = 0;
        return false;
    }

    @Override
    public boolean contains(E target) {
        return false;
    }

    @Override
    public E find(E target) {
        return null;
    }

    @Override
    public E delete(E target) {
        return null;
    }

    @Override
    public boolean remove(E target) {
        return false;
    }
}
