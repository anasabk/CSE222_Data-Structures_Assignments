package Questions;

import DataStructures.Graphs.Edge;
import Questions.Q1.MyGraph;
import Questions.Q1.Vertex;
import Questions.Q2.*;

import java.util.*;

public class Q3 {
    /**
     * A simple class to store the references to a vertex and its state.
     */
    private static class Entry {
        private Vertex vertex;
        private State state;

        public Entry (Vertex vertex, State state) {
            this.vertex = vertex;
            this.state = state;
        }
    }

    /** A reference to the graph being searched. */
    private static MyGraph MainGraph;
    /** A map of all the vertices in the graph with their states. */
    private static Map<Integer, Entry> listOfVertices;
    /** The queue of vertices to traverse. */
    private static Queue<Vertex> queue;

    /**
     * Return the total distance from a starting point to all the vertices
     * in the graph when using a modified Dijkstra's traversal method.
     * @return The total distance to all the vertices reachable.
     */
    public static double calc_Dijkstra (MyGraph graph, int targetV) {
        MainGraph = graph;
        /* initialize the list of vertices with the default states. */
        listOfVertices = new HashMap<>();
        Iterator<Vertex> vIter = MainGraph.vertexIterator();
        while (vIter.hasNext()) {
            Vertex vertex = vIter.next();
            listOfVertices.put(vertex.getID(), new Entry(vertex, new State()));
        }

        queue = new LinkedList<>();

        /*
         * Iterate through every vertex in listOfVertices, and add its
         * distance from the starting index to the total result. Call
         * the helper function for each vertex that was not visited in
         * the previous traverse during the iteration.
         */
        Entry entry = listOfVertices.get(targetV);
        entry.state.visited = true;
        entry.state.pathLength = 0.0;
        calc_Dijkstra(0.0, entry.vertex);

        double result = 0.0;
        for (Entry temp : listOfVertices.values())
            if (temp.state.visited)
                result += temp.state.pathLength;

        return result;
    }

    /**
     * Recursively traverse all the vertices starting from the vertex with
     * the ID current. The distance parameter is used to assign the distance
     * to the traversed vertex from the start in its state.
     * @param distance The distance to the current vertex from the start.
     * @param current A reference to the current vertex.
     */
    private static void calc_Dijkstra(double distance, Vertex current) {
        Iterator<Edge> iter = MainGraph.edgeIterator(current.getID());
        while (iter.hasNext()) {
            Edge edge = iter.next();
            Entry entry = listOfVertices.get(edge.getDest());

            if (entry.state.visited) {
                entry.state.pathLength = Math.min(entry.state.pathLength, edge.getWeight() + distance - current.getBoost());
            }

            else {
                queue.add(entry.vertex);
                entry.state.pathLength = edge.getWeight() + distance - current.getBoost();
                entry.state.visited = true;
            }
        }

        if (!queue.isEmpty()) {
            Entry nextTarget = listOfVertices.get(queue.peek().getID());
            calc_Dijkstra(nextTarget.state.pathLength, queue.remove());
        }
    }
}
