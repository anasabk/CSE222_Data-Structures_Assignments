package DataStructures.Graphs;

import java.util.Objects;

public class Edge {
    // The destination vertex for an edge
    private int dest;

    // The source vertex for an edge
    private int source;

    // The weight
    private double Weight;

    /**
     * Constructs an Edge from source to dest. Sets the weight to 1.0
     * @param source
     * @param dest
     */
    public Edge(int source, int dest) {
        if (source < 0 || dest < 0)
            throw new IllegalArgumentException();

        this.source = source;
        this.dest = dest;
        this.Weight = 1;
    }

    /**
     * Constructs an Edge from source to dest. Sets the weight to w.
     * @param source
     * @param dest
     * @param w
     */
    public Edge(int source, int dest, double w){
        if (source < 0 || dest < 0 || w < 0)
            throw new IllegalArgumentException();

        this.source = source;
        this.dest = dest;
        this.Weight = w;
    }

    /**
     * Compares two edges for equality. Edges are equal if their source
     * and destination vertices are the same. The weight is not considered
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return dest == edge.dest && source == edge.source && Double.compare(edge.Weight, Weight) == 0;
    }

    /**
     * Returns the destination vertex
     * @return
     */
    public int getDest() {
        return dest;
    }

    /**
     * Returns the source vertex
     * @return
     */
    public int getSource() {
        return source;
    }

    /**
     * Returns the weight
     * @return
     */
    public double getWeight() {
        return Weight;
    }

    /**
     * Returns the hash code for an edge. The hash code depends only
     * on the source and destination
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(dest, source);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "dest=" + dest +
                ", source=" + source +
                ", Weight=" + Weight +
                '}';
    }
}
