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
    public static HyperGraph transformGraph(HyperGraph untransformedGraph){
        HyperGraph graph = new HyperGraph();
        graph.addAll(untransformedGraph.getAllNodes());
        HashMap<Integer, Edge> oldEdges = untransformedGraph.getAllEdges();

        //Foreach HyperEdge "edge"  in the untransformed HyperGraph
        for (Map.Entry<Integer, Edge> entry : oldEdges.entrySet()) {
            HyperEdge edge = (HyperEdge) entry.getValue();
            Node node = new Node(edge.getLabel());
            graph.add(node);
            for (Node startnode : edge.getStartnodes()) {
                graph.add(new SimpleEdge(startnode, 1, node, 1));
            }
            for (Node endnode : edge.getEndnodes()) {
                graph.add(new SimpleEdge(node, 1, endnode, 1));
            }

        }

        return graph;
    }
}
