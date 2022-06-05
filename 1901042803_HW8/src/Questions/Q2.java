package Questions;

import DataStructures.Graphs.Edge;
import Questions.Q1.MyGraph;
import Questions.Q1.Vertex;

import java.util.*;

public class Q2 {
    /** A reference to the graph being searched. */
    private static MyGraph graph;
    /** A map of all the vertices in the graph with their state. */
    private static Map<Integer, State> listOfVertices;
    /** The queue of vertices to traverse during BFS traversal. */
    private static Queue<Integer> queue;

    /**
     * A class to store the state of a vertex during an iteration.
     */
    protected static class State {
        /** Whether the vertex was visited or not. */
        Boolean visited;
        /** The path from the starting vertex. */
        Double pathLength;
        /** The level in which the vertex was found in. */
        int Level;

        public State () {
            this.pathLength = Double.POSITIVE_INFINITY;
            this.visited = false;
            Level = -1;
        }

        public State (boolean visited, Double pathLength, int level) {
            this.pathLength = pathLength;
            this.visited = visited;
            Level = level;
        }
    }

    /**
     * Calculates the total distance from a starting vertex to all the
     * vertices in the graph using each of BFS and DFS traversals, then
     * returns the difference between them.<P>
     * Only the vertex with the smallest ID is traversed from.<P>
     * If the result was positive, then DFS method found the least total
     * distance, if it was negative, then BFS had the least total distance.
     * @param InputGraph The graph to traverse.
     * @return The difference in the total distance between BFS and DFS.
     */
    public static double BFS_DFS_Dif(MyGraph InputGraph) {
        if (InputGraph == null)
            return 0;

        graph = InputGraph;
        int start = graph.vertexIterator().next().getID();
        return calc_BFS(graph, start) - calc_DFS(graph, start);
    }

    /**
     * Return the total distance from a start vertex to all the vertices
     * in the graph when using BFS traversal.
     * @return The total distance to all the vertices.
     */
    public static double calc_BFS (MyGraph Graph, int start) {
        graph = Graph;
        /* initialize the list of vertices with the default states. */
        listOfVertices = new HashMap<>();
        Iterator<Vertex> vIter = graph.vertexIterator();
        while (vIter.hasNext())
            listOfVertices.put(vIter.next().getID(), new State());

        queue = new LinkedList<>();

        /*
         * Iterate through every vertex in listOfVertices, and add its
         * distance from the starting index to the total result.
         */
        listOfVertices.put(start, new State(true, 0.0, 0));
        calc_BFS(0.0, start, 0);

        double result = 0;
        for (Map.Entry<Integer, State> temp : listOfVertices.entrySet()) {
            if (temp.getValue().visited)
                result += temp.getValue().pathLength;
        }

        return result;
    }

    /**
     * Recursively traverse all the vertices starting from the vertex with
     * the ID current. The distance and level parameters are used to assign
     * the location of the traversed vertex in its state.
     * @param distance The distance of the current vertex from the start.
     * @param current The ID of the current vertex.
     * @param level The level where the current vertex is in.
     */
    private static void calc_BFS(double distance, int current, int level) {
        Iterator<Edge> iter = graph.edgeIterator(current);
        while (iter.hasNext()) {
            Edge edge = iter.next();
            State vertex = listOfVertices.get(edge.getDest());

            if (vertex.visited) {
                if (vertex.Level == level + 1)
                    vertex.pathLength = Math.min(vertex.pathLength, edge.getWeight() + distance);
            }

            else {
                queue.add(edge.getDest());
                vertex.pathLength = edge.getWeight() + distance;
                vertex.Level = level + 1;
                vertex.visited = true;
            }
        }

        if (!queue.isEmpty()) {
            State nextTarget = listOfVertices.get(queue.peek());
            calc_BFS(nextTarget.pathLength, queue.remove(), nextTarget.Level);
        }
    }

    /**
     * Return the total distance from a start vertex to all the vertices
     * in the graph using DFS traversal.
     * @return The total distance to all the vertices.
     */
    public static double calc_DFS (MyGraph Graph, int start) {
        graph = Graph;
        /* initialize the list of vertices with the default states. */
        listOfVertices = new HashMap<>();
        Iterator<Vertex> vIter = graph.vertexIterator();
        while (vIter.hasNext())
            listOfVertices.put(vIter.next().getID(), new State());

        /*
         * Iterate through every vertex in listOfVertices, and add its
         * distance from the starting index to the total result. Call
         * the helper function for each vertex that was not visited in
         * the previous traverse during the iteration.
         */
        listOfVertices.put(start, new State(true, (double) 0, 0));
        calc_DFS((double) 0, start);

        double result = 0;
        for (Map.Entry<Integer, State> temp : listOfVertices.entrySet()) {
            if (temp.getValue().visited)
                result += temp.getValue().pathLength;
        }

        return result;
    }

    /**
     * Recursively traverse all the vertices starting from the vertex with
     * the ID current. The distance parameter is used to assign the distance
     * from the starting vertex to the current vertex in its state.
     * @param distance The distance of the current vertex from the start.
     * @param current The ID of the current vertex.
     */
    private static void calc_DFS (double distance, int current) {
        Queue<Edge> queue = new LinkedList<>();

        Iterator<Edge> iter = graph.edgeIterator(current);
        while (iter.hasNext())
            queue.add(iter.next());

        while (!queue.isEmpty()) {
            Edge edge = queue.remove();
            State vertex = listOfVertices.get(edge.getDest());

            if (!vertex.visited) {
                vertex.pathLength = distance + edge.getWeight();
                vertex.visited = true;
                calc_DFS(distance + edge.getWeight(), edge.getDest());
            }
        }
    }
}