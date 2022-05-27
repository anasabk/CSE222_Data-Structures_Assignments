package DataStructures;

import java.util.LinkedList;

public class Stack<E> extends LinkedList<E> {
    public Stack(){
        super();
    }

    public void push(E e){
        addFirst(e);
    }

    public E pop(){
        return removeFirst();
    }

    public E peek(){
        return peekFirst();
    }
}
