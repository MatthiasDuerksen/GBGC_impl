package model.Graph;

import org.graphstream.graph.Graph;

public abstract class GraphElement {

    /**
     * counter for the identifiers of the nodes.
     */
    private static int idCounter = 0;

    protected int id;
    protected String label;

    public GraphElement(String label) {
        this.label = label;
        this.id=idCounter++;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((GraphElement) obj).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String toString() {
        return "(" + getLabel() + ", " + getId() + ")";
    }


    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

}
