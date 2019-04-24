package control.commpressionSteps;

import model.Graph.*;

import java.util.HashMap;
import java.util.Map;

public class GraphTransformer {


    /**
     * Trasformation of the uncompressed graph to another graph model.
     * <p>
     * The graph is transformed to a graph which has no hyperedge and no edge labels.
     * This is realized by transforming the graph in such a way that all incident elements become adjacent elements.
     *
     * @param untransformedGraph the untransformed graph.
     * @return the transformed graph.
     */
    public static Graph transformGraph(Graph untransformedGraph){
        Graph graph = new Graph();
        graph.addAll(untransformedGraph.getAllNodes());
        HashMap<Integer, Edge> oldEdges = untransformedGraph.getAllEdges();

        //Foreach Edge "edge"  in the untransformed HyperGraph
        for (Map.Entry<Integer, Edge> entry : oldEdges.entrySet()) {
            Edge edge = (Edge) entry.getValue();
            Node node = new Node(edge.getLabel());
            graph.add(node);
            for (Node startnode : edge.getStartnodes().keySet()) {
                graph.add(new Edge(startnode, 1, node, 1,Edge.EMPTY_LABEL));
            }
            for (Node endnode : edge.getEndnodes().keySet()) {
                graph.add(new Edge(node, 1, endnode, 1,Edge.EMPTY_LABEL));
            }

        }

        return graph;
    }
}
