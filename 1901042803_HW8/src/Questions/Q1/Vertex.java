package Questions.Q1;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Vertex implements Comparable<Vertex>{
    private final int ID;
    private String Label;
    private double Weight;
    private final Map<String ,String> properties;
    private double boost;

    protected Vertex(int id) {
        ID = id;
        Label = null;
        Weight = 0L;
        properties = new TreeMap<>();
        boost = 0;
    }

    protected Vertex(int id, String label) {
        ID = id;
        Label = label;
        Weight = 1;
        properties = new TreeMap<>();
        boost = 0;
    }

    protected Vertex(int id, String label, double weight) {
        ID = id;
        Label = label;
        Weight = weight;
        properties = new TreeMap<>();
        boost = 0;
    }

    protected Vertex(int id, double boost) {
        ID = id;
        Label = null;
        Weight = 0;
        properties = new TreeMap<>();
        this.boost = boost;
    }

    protected Vertex(int id, String label, double weight, double boost) {
        ID = id;
        Label = label;
        Weight = weight;
        properties = new TreeMap<>();
        this.boost = boost;
    }

    public int getID() {
        return ID;
    }

    public String getLabel() {
        return Label;
    }

    public double getBoost () {
        return boost;
    }

    public double getWeight() {
        return Weight;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public void setBoost(double boost) {
        this.boost = boost;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    public void removeProperty(String key, String value) {
        properties.remove(key, value);
    }

    public boolean hasProperty(String key, String value) {
        String target = properties.get(key);
        if (target == null)
            return false;

        return target.equals(value);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        return ID == ((Vertex) o).ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    @Override
    public int compareTo(Vertex v) {
        return ID - v.ID;
    }
}
