package Q3;

public class BinaryHeap<E extends Comparable<E>> extends BinaryTree<E>{
    private int _depth, _size;

    public BinaryHeap(){
        super();
        _depth = 0;
        _size = 0;
    }

    private BinaryHeap(Node<E> node, int depth, int size){
        super(node);
        _depth = depth;
        _size = size;
    }

    public boolean insert(E data) {
        if(root == null){
            root = new Node<>(data);
            ++_depth;
            ++_size;
            return true;
        }

        else if(_size + 1 == Math.pow(2, _depth))
            return insert_perfect(data, root);

        else
            return insert_full(data);
    }

    private boolean insert_full(E data) {
        if(_depth == 2){
            if (root.left == null){
                if(data.compareTo(root.data) > 0)
                    root.left = new Node<>(data);

                else{
                    root.left = new Node<>(root.data);
                    root.data = data;
                }
            }

            else if (root.right == null){
                if(data.compareTo(root.data) > 0)
                    root.right = new Node<>(data);

                else{
                    root.right = new Node<>(root.data);
                    root.data = data;
                }
            }

            else return false;

            ++_size;
            return true;
        }

        else if(new BinaryHeap<E>(
                    root.left,
                    _depth - 1,
                    _size - (int) Math.pow(2, _depth - 1))
                    .insert_full(data))
        {
            if(root.data.compareTo(root.left.data) > 0){
                E temp = root.left.data;
                root.left.data = root.data;
                root.data = temp;
            }

            ++_size;
            return true;
        }

        else if(new BinaryHeap<E>(
                    root.right,
                    _depth - 1,
                    _size - (int) Math.pow(2, _depth - 1))
                    .insert_full(data))
        {
            if(root.data.compareTo(root.right.data) > 0){
                E temp = root.right.data;
                root.right.data = root.data;
                root.data = temp;
            }

            ++_size;
            return true;
        }

        else return false;
    }

    private boolean insert_perfect (E data, Node<E> node){
        if(node.left == null){
            if(node.data.compareTo(data) > 0){
                node.left = new Node<>(node.data);
                node.data = data;
            }

            else
                node.left = new Node<>(node.data);

            ++_depth;
            ++_size;
            return true;
        }

        else if(insert_perfect(data, node.left)){
            if(node.data.compareTo(node.left.data) > 0){
                E temp = node.left.data;
                node.left.data = node.data;
                node.data = temp;
            }

            return true;
        }

        else return false;
    }

    public boolean incrementKey(E data){
        Node<E> parent = searchParent(data, root), target;
        if(parent == null)
            return false;

        else{
            if(parent.left.data == data)
                target = parent.left;

            else
                target = parent.right;

            E tempData = target.data;
            target.data = parent.data;
            parent.data = tempData;
            return true;
        }
    }

    private Node<E> searchParent(E data, Node<E> root) {
        if(root == null
        || root.data == data
        || root.left == null
        || root.right == null)
            return null;

        else if(root.left.data == data
             || root.right.data == data)
            return root;

        else{
            Node<E> target = searchParent(data, root.left);

            if(target == null)
                return searchParent(data, root.right);

            else return target;
        }
    }

    private Node<E> search(E data, Node<E> root) {
        if(root == null)
            return null;

        else if(root.data == data)
            return root;

        else{
            Node<E> target = searchParent(data, root.left);

            if(target == null)
                return searchParent(data, root.right);

            else return target;
        }
    }

    private Node<E> getLast(Node<E> root){
        if(_depth == 1)
            return root;

        else{
            Node<E> target = getLast(root.right);

            if(target == null)
                return getLast(root.left);
            else
                return target;
        }
    }

    private Node<E> getLastParent(Node<E> root){
        if(_depth == 2){
            if(root.right != null
            || root.left != null)
                return root;

            else return null;
        }

        else if(_depth < 2)
            return null;

        else{
            Node<E> target = getLast(root.right);

            if(target == null)
                return getLast(root.left);
            else
                return target;
        }
    }

    public boolean isEmpty(){
        return root == null;
    }

    public Node<E> remove(E data){
        Node<E> parent = searchParent(data, root);

        if(parent != null){
            Node<E> target;
            if(parent.left.data == data){
                target = parent.left;
                parent.left = null;
            }

            else {
                target = parent.right;
                parent.right = null;
            }

            return target;
        }

        else return null;
    }

    public void merge(BinaryHeap<E> heap){
        while(heap.root != null)
            insert(heap.remove(heap.getLast(heap.root).data).data);
    }
}
