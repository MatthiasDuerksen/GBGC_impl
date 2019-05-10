package model.Graph;

import java.util.*;

public class Graph {

    private final HashMap<Integer, Node> nodes;
    private final HashMap<Integer, Edge> edges;

    /**
     * Empty constructor of HyperGraph.
     */
    public Graph() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
    }

    /**
     * Constructor of HyperGraph.
     *
     * @param nodes the set of nodes.
     * @param edges the set of edges.
     */
    private Graph(HashMap<Integer, Node> nodes, HashMap<Integer, Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }


    /**
     * Checks whether the graph contains a node with the label 'l'.
     *
     * @param l the label for which we perform the check.
     * @return true if the graph contains a node with label 'l', else false.
     */
    public boolean containsNode(String l) {
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            if (node.getLabel().equals(l)) return true;

        }
        return false;
    }

    /**
     * Add an element to the graph.
     *
     * @param graphElement which is to be added.
     */
    public void add(GraphElement graphElement) {
        if (graphElement instanceof Node) {
            nodes.put(graphElement.getId(), (Node) graphElement);
        } else {
            edges.put(graphElement.getId(), (Edge) graphElement);
        }
    }


    /**
     * Add all new nodes to the graph.
     *
     * @param newNodes nodes which are be added.
     */
    public void addAll(Map<Integer, Node> newNodes) {
        nodes.putAll(newNodes);
    }

    /**
     * Delete a node from the graph.
     *
     * @param graphElement which should be deleted.
     */
    public void delete(GraphElement graphElement) {
        if (graphElement instanceof Node) {
            nodes.remove(graphElement.getId());
        } else {
            edges.remove(graphElement.getId());
        }
    }

    /**
     * Deletes an edge from the graph.
     *
     * @param graphElementList which should be deleted.
     */
    public void delete(List<GraphElement> graphElementList) {
        for (GraphElement graphElement : graphElementList) {
            delete(graphElement);
        }
    }

    /**
     * Getter for all nodes.
     *
     * @return all nodes of the graph.
     */
    public HashMap<Integer, Node> getAllNodes() {
        return nodes;
    }

    /**
     * Getter for all edges.
     *
     * @return all edges of the graph.
     */
    public HashMap<Integer, Edge> getAllEdges() {
        return edges;
    }

    public String toString() {
        String string = "HyperGraph: NodeSize: " + nodes.size() + ", EdgeSize: " + edges.size() + "\n";
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            string += "Node " + node.toString() + ": ";
            for (Edge edge : getAllIncidentEdges(node)) {
                string += "Edge";
                if (edge.getEndnodes().containsKey(node)) {
                    string += "<";
                }
                if (edge.getStartnodes().containsKey(node)) {
                    string += ">";
                }
                string += edge.shortToString() + ":";

            }
            string += "\n";
        }
        for (Edge edge : edges.values()) {
            string += "Edge:" + edge + "\n";
        }
        return string;

    }

    /**
     * Clones the current graph.
     *
     * @return cloned graph of the current graph.
     */
    public Graph clone() {
        return new Graph((HashMap<Integer, Node>) getAllNodes().clone(), (HashMap<Integer, Edge>) getAllEdges().clone());
    }

    /**
     * Getter for all incident edges of a node.
     *
     * @param node the node for which the request is to be executed.
     * @return all incident edges of the node.
     */
    public LinkedList<Edge> getAllIncidentEdges(Node node) {
        LinkedList<Edge> edges = new LinkedList<>();
        for (Map.Entry<Integer, Edge> entry : getAllEdges().entrySet()) {
            Edge edge = (Edge) entry.getValue();
            if (edge.getStartnodes().containsKey(node) || edge.getEndnodes().containsKey(node)) {
                edges.add(edge);
            }
        }
        return edges;
    }

    public int size() {
        return getAllEdges().size()+getAllNodes().size();
    }
}
