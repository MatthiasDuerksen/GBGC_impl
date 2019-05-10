package control.commpressionSteps;

import model.Digram.DigramRule;
import model.Digram.DigramType;
import model.DigramList.DigramLists;
import model.DigramOccurrence.DigramOccurrence;
import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.Node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RuleGenerator {

    public DigramRule generateRule(DigramType digramType, Node[] nodes, Edge[] edges) {
        //TODO
        return null;
    }



    public void generateBasicNodeRule(Edge edge) {
        Edge edgeNew = edge.deepCopy();

        List<Node> internalNodes = new ArrayList<>();
        List<Node> externalNodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        edges.add(edgeNew);

        for (Node node : edgeNew.getStartnodes().keySet()) {
            internalNodes.add(node.deepCopy());
        }
        for (Node node : edgeNew.getEndnodes().keySet()) {
            internalNodes.add(node.deepCopy());
        }

        DigramRule tempDigramRule = new DigramRule(DigramType.BASIC_NODE_DIGRAM, internalNodes, externalNodes, edges);
        DigramRule digramRule = DigramLists.getInstance().getDigramMap(DigramType.BASIC_NODE_DIGRAM).getDigramRule(tempDigramRule);

        internalNodes = new ArrayList<>();
        externalNodes = new ArrayList<>();
        edges = new ArrayList<>();
        edges.add(edge);
        for (Node node : edge.getStartnodes().keySet()) {
            internalNodes.add(node);
        }
        for (Node node : edge.getEndnodes().keySet()) {
            internalNodes.add(node);
        }

        DigramOccurrence digramOccurrence = new DigramOccurrence(digramRule, internalNodes, externalNodes, edges);
        digramRule.addOccurrence(digramOccurrence);
    }

    public void generateCircleNodeRule(Edge edge) {
        Edge edgeNew = edge.deepCopy();

        List<Node> internalNodes = new ArrayList<>();
        List<Node> externalNodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        edges.add(edgeNew);

        for (Node node : edgeNew.getStartnodes().keySet()) {
            internalNodes.add(node.deepCopy());
        }

        DigramRule tempDigramRule = new DigramRule(DigramType.CIRCLE_DIGRAM, internalNodes, externalNodes, edges);
        DigramRule digramRule = DigramLists.getInstance().getDigramMap(DigramType.CIRCLE_DIGRAM).getDigramRule(tempDigramRule);

        internalNodes = new ArrayList<>();
        externalNodes = new ArrayList<>();
        edges = new ArrayList<>();
        edges.add(edge);
        for (Node node : edge.getStartnodes().keySet()) {
            internalNodes.add(node);
        }

        DigramOccurrence digramOccurrence = new DigramOccurrence(digramRule, internalNodes, externalNodes, edges);
        digramRule.addOccurrence(digramOccurrence);
    }

    public void generateM3Rule(Node nodeExtern, Node nodeCenter, Node nodeSide, Graph graph) {
//TODO do i need deepCopy here
        List<Node> internalNodes = new ArrayList<>();
        List<Node> externalNodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        externalNodes.add(nodeExtern);
        internalNodes.add(nodeCenter);
        internalNodes.add(nodeSide);

        List<Edge> incidentEdgesedges = graph.getAllIncidentEdges(nodeCenter);
        if (incidentEdgesedges.get(0).isIncidentTo(nodeExtern)){
            edges.add(incidentEdgesedges.get(0));
            edges.add(incidentEdgesedges.get(1));
        }else{
            edges.add(incidentEdgesedges.get(1));
            edges.add(incidentEdgesedges.get(0));
        }

        DigramRule tempDigramRule = new DigramRule(DigramType.M3, internalNodes, externalNodes, edges);

        DigramRule digramRule = DigramLists.getInstance().getDigramMap(DigramType.M3).getDigramRule(tempDigramRule);

        DigramOccurrence digramOccurrence = new DigramOccurrence(digramRule, internalNodes, externalNodes, edges);
        digramRule.addOccurrence(digramOccurrence);
    }

}
