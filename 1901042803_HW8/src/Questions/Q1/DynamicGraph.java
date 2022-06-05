package Questions.Q1;

import DataStructures.Graphs.Graph;

public interface DynamicGraph extends Graph {
    /**
     * Generate a new vertex by given parameters.
     * @param label
     * @param weight
     * @return
     */
    Vertex newVertex (String label, double weight);

    /**
     * Add the given vertex to the graph.
     * @param new_vertex
     * @return
     */
    boolean addVertex (Vertex new_vertex);

    /**
     * Add an edge between the given two vertices in the graph.
     * @param vertexID1
     * @param vertexID2
     * @param weight
     * @return
     */
    boolean addEdge (int vertexID1, int vertexID2, double weight);

    /**
     * Remove the edge between the given two vertices.
     * @param vertexID1
     * @param vertexID2
     * @return
     */
    boolean removeEdge (int vertexID1, int vertexID2);

    /**
     * Remove the vertex from the graph with respect to the given vertex id.
     * @param vertexID
     * @return
     */
    boolean removeVertex (int vertexID);

    /**
     * Remove the vertices that have the given label from the graph.
     * @param label
     * @return
     */
    boolean removeVertex (String label);

    /**
     * Filter the vertices by the given user-defined property and returns a subgraph of the graph.
     * @param key
     * @param filter
     */
    DynamicGraph filterVertices (String key, String filter);

    /**
     * Generate the adjacency matrix representation of the graph and returns the matrix.
     */
    Double[][] exportMatrix();

    /**
     * Print the graph in adjacency list format (You should use the format that can be imported by the method in AbstarctGraph in the book).
     */
    void printGraph();
}
