package CPS.LDLinkedList;

import java.util.AbstractList;

public class LDLinkedList<E> extends AbstractList<E>{
    private Node<E> _head, _tail, _headDeleted;
    private int _size;

    private static class Node<E> {
        private Node<E> _next;
        private E _data;

        public Node(E data){
            _next = null;
            _data = data;
        }

        public void setData(E data) {
            _data = data;
        }

        private void setNext(Node<E> next) {
            _next = next;
        }

        public E getData() {
            return _data;
        }

        public Node<E> getNext() {
            return _next;
        }
    }

    public LDLinkedList(){
        _head = null;
        _tail = null;
        _headDeleted = null;
        _size = 0;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        if(_head == null){
            if(_headDeleted != null){
                _head = _headDeleted;
                _headDeleted = _headDeleted.getNext();
                _head.setNext(null);
                _head.setData(element);
            }

            else
                _head = new Node<E>(element);
            
            _tail = _head;
            ++_size;
        }

        else if(index == size()){
            add(element);
        }

        else if(index == 0){
            Node<E> target;
            if(_headDeleted != null){
                target = _headDeleted;
                _headDeleted = _headDeleted.getNext();
            }

            else
                target = new Node<E>(element);

            target.setNext(_head);
            _head = target;
            _head.setData(element);
            ++_size;
        }

        else{
            Node<E> target = new Node<E>(element);
            if(_headDeleted != null){
                target = _headDeleted;
                _headDeleted = _headDeleted.getNext();
                target.setData(element);
            }

            else
                target = new Node<E>(element);

            Node<E> beforeTarget = getNode(index - 1);
            target.setNext(beforeTarget.getNext());
            beforeTarget.setNext(target);
            ++_size;
        }
    }

    @Override
    public boolean add(E element) {
        if(_head == null){  /**Empty list */
            if(_headDeleted != null){
                _head = _headDeleted;
                _headDeleted = _headDeleted.getNext();
                _head.setNext(null);
                _head.setData(element);
            }

            else
                _head = new Node<E>(element);

            _tail = _head;
            ++_size;
            return true;
        }

        else{
            if(_headDeleted != null){
                Node<E> target = _headDeleted;
                _headDeleted = _headDeleted.getNext();
                _tail.setNext(target);
                _tail = _tail.getNext();
                _tail.setNext(null);
                _tail.setData(element);
            }

            else{
                _tail.setNext(new Node<E>(element));
                _tail = _tail.getNext();
            }

            ++_size;
            return true;
        }
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        Node<E> target;
        if(index == 0){
            target = _head;
            _head = target.getNext();
        }

        else if(index == size() -1){
            target = _tail;
            _tail = null;
        }

        else{
            Node<E> beforeTarget = getNode(index-1);
            target = beforeTarget.getNext();
            beforeTarget.setNext(target.getNext());
        }
        
        if(_headDeleted == null){
            _headDeleted = target;
            _headDeleted.setNext(null);
        }

        else{
            target.setNext(_headDeleted);
            _headDeleted = target;
        }

        --_size;
        return target.getData();
    }

    private Node<E> getNode(int index){
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        Node<E> targeNode = _head;

        for(int i = 0; i < index; ++i)
            targeNode = targeNode.getNext();

        return targeNode;
    }

    @Override
    public E set(int index, E element) {
        Node<E> targeNode = getNode(index);

        E overWritten = targeNode.getData();
        targeNode.setData(element);
        return overWritten;
    }

    @Override
    public E get(int index) {
        return getNode(index).getData();
    }

    @Override
    public int size() {
        return _size;
    }
}