package model.Graph;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is a class for a Edge which extends the class Edge.
 * <p>
 * A Edge is a edge which may have more than one start node/end node and has a label.
 *
 * @author Matthias Duerksen
 */
public class Edge extends GraphElement {

    /**
     * the start nodes of the edge with his equivalence classes.
     */
    private LinkedHashMap<Node, Integer> startnodes;

    /**
     * the end nodes of the edge with his equivalence classes.
     */
    private LinkedHashMap<Node, Integer> endnodes;

    public static final String EMPTY_LABEL = "EMPTY_LABEL";


    /**
     * Constructor for the Edge.
     *
     * @param startnodes the start nodes of the edge.
     * @param endnodes   the end nodes of the edge.
     * @param label      the label of the edge.
     */
    public Edge(LinkedHashMap<Node, Integer> startnodes, LinkedHashMap<Node, Integer> endnodes, String label) {
        super(label);
        this.startnodes = startnodes;
        this.endnodes = endnodes;
    }

    /**
     * Constructor for the Edge.
     *
     * @param startnode the start node of the edge.
     * @param endnode   the end node of the edge.
     * @param label     the label of the edge.
     */
    public Edge(Node startnode, int eqStart, Node endnode, int eqEnd, String label) {
        super(label);
        startnodes = new LinkedHashMap<>();
        startnodes.put(startnode, eqStart);
        endnodes = new LinkedHashMap<>();
        endnodes.put(endnode, eqEnd);
    }

    public Edge(Node startnode, int eqStart, Node endnode, int eqEnd) {
        this(startnode, eqStart, endnode, eqEnd, Edge.EMPTY_LABEL);
    }

    /**
     * Getter for the start nodes.
     *
     * @return start nodes of the edge.
     */
    public Map<Node, Integer> getStartnodes() {
        return startnodes;
    }

    /**
     * Getter for the end nodes.
     *
     * @return end nodes of the edge.
     */
    public Map<Node, Integer> getEndnodes() {
        return endnodes;
    }


    public Node getStartNode() {
        if (!isSimpleEdge()) {
            throw new IllegalStateException("Edge is not a simple edge");
        }

        return startnodes.keySet().iterator().next();
    }

    public Node getEndNode() {
        if (!isSimpleEdge()) {
            throw new IllegalStateException("Edge is not a simple edge");
        }

        return endnodes.keySet().iterator().next();
    }

    public boolean isSimpleEdge() {
        return (startnodes.size() == 1 && endnodes.size() == 1);
    }

    public boolean isCircle() {
        return (isSimpleEdge() && getStartnodes().keySet().toArray()[0].equals(getEndnodes().keySet().toArray()[0]));
    }


    public Edge deepCopy() {
        LinkedHashMap<Node, Integer> newStartNodes = new LinkedHashMap<>();
        for (Object obj : startnodes.keySet().toArray()) {
            Node node = (Node) obj;
            newStartNodes.put(node.deepCopy(), startnodes.get(node).intValue());
        }
        LinkedHashMap<Node, Integer> newEndNodes = new LinkedHashMap<>();
        for (Object obj : endnodes.keySet().toArray()) {
            Node node = (Node) obj;
            newStartNodes.put(node.deepCopy(), endnodes.get(node).intValue());
        }
        return new Edge(newStartNodes, newEndNodes, new String(label));
    }

    /**
     * Checks if the edges are the same but not necessary with same id
     *
     * @param edge
     * @return
     */
    public boolean isSameEdge(Edge edge) {
        if (!(startnodes.size() == edge.startnodes.size() && endnodes.size() == edge.endnodes.size())) {
            return false;
        }
        for (int i = 0; i < startnodes.size(); i++) {
            Node node1 = ((Node) startnodes.keySet().toArray()[i]);
            Node node2 = ((Node) edge.startnodes.keySet().toArray()[i]);

            if (!(node1.getLabel().equals(node2.getLabel()) && startnodes.get(node1) == edge.startnodes.get(node2))) {
                return false;
            }
        }
        for (int i = 0; i < endnodes.size(); i++) {
            Node node1 = ((Node) endnodes.keySet().toArray()[i]);
            Node node2 = ((Node) edge.endnodes.keySet().toArray()[i]);

            if (!(node1.getLabel().equals(node2.getLabel()) && endnodes.get(node1) == edge.endnodes.get(node2))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String string = "(" + getLabel() + ", " + getId() + ")";
        string += "Start:";
        for (Node node : getStartnodes().keySet()) {
            string += "[" + node.getLabel() + ", " + node.getId() + "; " + getStartnodes().get(node) + "]";
        }
        string += "End:";
        for (Node node : getEndnodes().keySet()) {
            string += "[" + node.getLabel() + ", " + node.getId() + "; " + getEndnodes().get(node) + "]";
        }
        return string;
    }

    public String shortToString() {
        return super.toString();
    }

    public boolean isIncidentTo(Node node) {
        return startnodes.containsKey(node) || endnodes.containsKey(node);
    }

}
