package Questions.Q1;

import DataStructures.Graphs.Edge;

import java.util.*;

public class MyGraph implements DynamicGraph {
    /**
     * A node class to create a linked list to store the
     * edges in an adjacency list.
     * @param <E>
     */
    private static class Node<E> {
        // Stored data
        private E data;

        // The next node in the list
        private Node<E> next;

        /**
         * Default constructor.
         * @param data The data in the node.
         */
        public Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }
    }

    /**
     * An iterator class to iterate through edges.
     * @param <E>
     */
    public static class EdgeIterator<E> implements Iterator<E> {
        // The next node in the list.
        private Node<E> next,
        // The node before the current position.
            prev,
        // The last node that was marked as previous.
            prevLast;

        /**
         * Default constructor.
         * @param head The head of the list to be iterated.
         */
        public EdgeIterator (Node<E> head) {
            next = head;
            prev = null;
            prevLast = null;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new IllegalStateException();

            prevLast = prev;
            prev = next;
            next = next.next;
            return prev.data;
        }

        @Override
        public void remove() {
            if (prevLast == null)
                throw new IllegalStateException();

            prevLast.next = next;
            prev = prevLast;
        }
    }

    // Tells whether the graph is directed or not.
    private final boolean directed;

    /**
     * A side effect used by methods after insertion to
     * know if the edge insertion was successful.
     */
    private static boolean added;

    // The last assigned ID to a vertex.
    private static int lastID = -1;

    // The data structure that stores the vertices and the edges.
    private NavigableMap<Vertex, Node<Edge>> vertices;

    /**
     * The default constructor.
     * @param directed Whether the graph is directed or not.
     */
    public MyGraph(boolean directed) {
        this.directed = directed;
        vertices = new TreeMap<>();
        added = false;
    }

    @Override
    public int getNumV() {
        return vertices.size();
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void insert(Edge edge) {
        added = false;
        // Check that the edge is possible.
        if (!vertices.containsKey(new Vertex(edge.getSource())) ||
            !vertices.containsKey(new Vertex(edge.getDest())))
            return;

        Node<Edge> target = vertices.get(new Vertex(edge.getSource()));
        vertices.put(new Vertex(edge.getSource()), insert(edge, target));

        if (!directed) {
            Edge reverse = new Edge(edge.getDest(), edge.getSource(), edge.getWeight());
            target = vertices.get(new Vertex(reverse.getSource()));
            vertices.put(new Vertex(reverse.getSource()), insert(reverse, target));
        }
    }

    /**
     * Recursively insert an edge into the given edge list and
     * return the list's new head. The insertion is done in
     * ascending order.
     * @param edge The edge to be inserted.
     * @param node The head of the edge list.
     * @return The new head of the edge list.
     */
    private Node<Edge> insert (Edge edge, Node<Edge> node) {
        if (node == null) {
            added = true;
            return new Node<>(edge);
        } else if (node.getData().getWeight() > edge.getWeight()) {
            added = true;
            Node<Edge> newNode = new Node<>(edge);
            newNode.next = node;
            return newNode;
        } else if (node.getData().equals(edge)) {
            return node;
        } else {
            node.next = insert(edge, node.next);
            return node;
        }
    }

    @Override
    public boolean isEdge(int source, int dest) {
        // Search the edge list of the source for the given edge.
        Iterator<Edge> iter = edgeIterator(source);
        while (iter.hasNext())
            if (iter.next().getDest() == dest)
                return true;

        return false;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        // Search the edge list of the source for the given edge.
        Iterator<Edge> iter = edgeIterator(source);
        while (iter.hasNext()) {
            Edge target = iter.next();
            if (target.getDest() == dest)
                // The edge was found, return it.
                return target;
        }

        return null;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return new EdgeIterator<>(vertices.get(new Vertex(source)));
    }

    @Override
    public Vertex newVertex(String label, double weight) {
        ++lastID;
        return new Vertex(lastID, label, weight);
    }

    @Override
    public boolean addVertex(Vertex new_vertex) {
        // Do not add if it already exists.
        if (vertices.containsKey(new_vertex))
            return false;

        vertices.put(new_vertex, null);
            return true;
    }

    @Override
    public boolean addEdge(int vertexID1, int vertexID2, double weight) {
        // Do not add if it already exists.
        if (isEdge(vertexID1, vertexID2))
            return false;

        insert(new Edge(vertexID1, vertexID2, weight));
        boolean added1 = added;

        // Insert again to the destination if the graph is directed.
        if (!directed)
            insert(new Edge(vertexID2, vertexID1, weight));

        return added || added1;
    }

    @Override
    public boolean removeEdge(int source, int dest) {
        Iterator<Edge> iter = edgeIterator(source);
        while (iter.hasNext())
        if (iter.next().getDest() == dest) {
            iter.remove();
            return true;
        }

        if (!directed) {
            // The graph is not directed, remove from both sides.
            iter = edgeIterator(dest);
            while (iter.hasNext())
            if (iter.next().getDest() == source) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeVertex(int vertexID) {
        if(vertices.remove(new Vertex(vertexID)) != null) {
            for (Map.Entry<Vertex, Node<Edge>> temp2 : vertices.entrySet())
            if (temp2.getValue() != null) {
                if (temp2.getValue().data.getDest() == vertexID)
                    // An edge pointing to the deleted vertex is the head.
                    temp2.setValue(temp2.getValue().next);

                else {
                    Iterator<Edge> iter = new EdgeIterator<>(temp2.getValue());
                    while (iter.hasNext())
                    if (iter.next().getDest() == vertexID)
                        iter.remove();
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(String label) {
        // Find the target vertex.
        for (Vertex vertex : vertices.keySet())
        if (vertex.getLabel().equals(label)) {
            // The target vertex was found.
            // Remove it.
            vertices.remove(vertex);

            // And remove all the edges pointing to it.
            for (Map.Entry<Vertex, Node<Edge>> temp2 : vertices.entrySet())
            if (temp2.getValue() != null) {
                if (temp2.getValue().data.getDest() == vertex.getID())
                    // An edge pointing to the deleted vertex is the head.
                    temp2.setValue(temp2.getValue().next);

                else {
                    Iterator<Edge> iter = new EdgeIterator<>(temp2.getValue());
                    while (iter.hasNext())
                    if (iter.next().getDest() == vertex.getID())
                        iter.remove();
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public DynamicGraph filterVertices(String key, String filter) {
        DynamicGraph result = new MyGraph(directed);

        // Add the matching vertices.
        for (Vertex temp : vertices.keySet())
        if (temp.hasProperty(key, filter))
        result.addVertex(temp);

        // Add all the edges that are within the chosen vertices.
        for (Map.Entry<Vertex, Node<Edge>> temp : vertices.entrySet())
        if (temp.getKey().hasProperty(key, filter)) {
            Iterator<Edge> iter = edgeIterator(temp.getKey().getID());
            while (iter.hasNext())
            result.insert(iter.next());
        }
        return result;
    }

    /**
     * Return an iterator through the vertices set.
     * @return an iterator for the vertices.
     */
    public Iterator<Vertex> vertexIterator() {
        return vertices.keySet().iterator();
    }

    @Override
    public Double[][] exportMatrix() {
        Double[][] result = new Double[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); ++i)
        for (int j = 0; j < vertices.size(); ++j)
        result[i][j] = Double.POSITIVE_INFINITY;

        // Insert every edge found to the matrix according to it's connections.
        int i = 0, j;
        for (Map.Entry<Vertex, Node<Edge>> temp : vertices.entrySet()) {
            // Iterate through an edge list.
            Iterator<Edge> iter = new EdgeIterator<>(temp.getValue());
            while (iter.hasNext()) {
                Edge target = iter.next();
                j = 0;
                for (Vertex temp2 : vertices.keySet()) {
                    if (temp2.getID() == target.getDest())
                        break;
                    ++j;
                }
                result[i][j] = target.getWeight();
            }
            ++i;
        }
        return result;
    }

    @Override
    public void printGraph() {
        System.out.print("list\n");
        for (Map.Entry<Vertex, Node<Edge>> temp : vertices.entrySet()) {
            Iterator<Edge> iter = new EdgeIterator<>(temp.getValue());
            while (iter.hasNext()) {
                Edge target = iter.next();
                System.out.printf("%d %d %f\n", target.getSource(), target.getDest(), target.getWeight());
            }
        }
    }
}
