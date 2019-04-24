package model.Digram;

import model.DigramList.DigramLists;
import model.DigramOccurrence.DigramOccurrence;
import model.Graph.Edge;
import model.Graph.Node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RuleGenerator {

    public DigramRule generateRule(DigramType digramType, Node[] nodes, Edge[] edges) {
        //TODO
        return null;
    }

    public void generateTestRule(Edge edge) {
        DigramLists.getInstance().getDigramMap(DigramType.BASIC_NODE_DIGRAM);
        DigramLists.getInstance().getDigramMap(DigramType.BASIC_NODE_DIGRAM).addOccurences(null, null, edge, null);


    }

    public void generateBasicNodeRule(Edge edge) {

        List<String> internalNodes = new ArrayList<>();
        List<String> externalNodes = new ArrayList<>();
        Map<String, Map<String, Integer>> edgeStartnodes = new LinkedHashMap<>();
        Map<String, Map<String, Integer>> edgeEndnodes = new LinkedHashMap<>();
        edgeStartnodes.put(edge.getLabel(), new LinkedHashMap<>());
        edgeEndnodes.put(edge.getLabel(), new LinkedHashMap<>());

        for (Node node : edge.getStartnodes().keySet()) {
            internalNodes.add(node.getLabel());
            edgeStartnodes.get(edge.getLabel()).put(node.getLabel(), edge.getStartnodes().get(node));
        }
        for (Node node : edge.getEndnodes().keySet()) {
            internalNodes.add(node.getLabel());
            edgeEndnodes.get(edge.getLabel()).put(node.getLabel(), edge.getEndnodes().get(node));
        }

        DigramRule tempDigramRule = new DigramRule(DigramType.BASIC_NODE_DIGRAM, internalNodes, externalNodes, edgeStartnodes, edgeEndnodes);

        DigramRule digramRule = DigramLists.getInstance().getDigramMap(DigramType.BASIC_NODE_DIGRAM).getDigramRule(tempDigramRule);

        List<Node> internalNode = new ArrayList<>();
        List<Node> externalNode = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        for (Node node : edge.getStartnodes().keySet()) {
            internalNode.add(node);
        }
        for (Node node : edge.getEndnodes().keySet()) {
            internalNode.add(node);
        }
        edges.add(edge);
        DigramOccurrence digramOccurrence = new DigramOccurrence(digramRule, internalNode, externalNode, edges);
        digramRule.getOccurrences().add(digramOccurrence);
    }

    public void generateM3Rule(Node node1, Node node2, Node node3, Edge edge1, Edge edge2) {

        List<String> internalNodes = new ArrayList<>();
        List<String> externalNodes = new ArrayList<>();
        Map<String, Map<String, Integer>> edgeStartnodes = new LinkedHashMap<>();
        Map<String, Map<String, Integer>> edgeEndnodes = new LinkedHashMap<>();

        externalNodes.add(node1.getLabel());
        internalNodes.add(node2.getLabel());
        internalNodes.add(node3.getLabel());
        edgeStartnodes.put(edge1.getLabel(), new LinkedHashMap<>());
        edgeEndnodes.put(edge1.getLabel(), new LinkedHashMap<>());
        if (edge1.getStartnodes().get(node1) != null) {
            edgeStartnodes.get(edge1.getLabel()).put(node1.getLabel(), -1);
            edgeEndnodes.get(edge1.getLabel()).put(node1.getLabel(), edge1.getEndnodes().get(node1));

        } else {
            edgeEndnodes.get(edge1.getLabel()).put(node1.getLabel(), -1);
            edgeStartnodes.get(edge1.getLabel()).put(node1.getLabel(), edge1.getStartnodes().get(node1));
        }
        edgeStartnodes.get(edge2.getLabel()).put(node2.getLabel(), edge2.getStartnodes().get(node2));
        edgeEndnodes.get(edge2.getLabel()).put(node2.getLabel(), edge2.getEndnodes().get(node2));
        DigramRule tempDigramRule = new DigramRule(DigramType.M3, internalNodes, externalNodes, edgeStartnodes, edgeEndnodes);

        DigramRule digramRule = DigramLists.getInstance().getDigramMap(DigramType.M3).getDigramRule(tempDigramRule);

        List<Node> internalNode = new ArrayList<>();
        List<Node> externalNode = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        externalNode.add(node1);
        internalNode.add(node2);
        internalNode.add(node3);
        edges.add(edge1);
        edges.add(edge2);
        DigramOccurrence digramOccurrence = new DigramOccurrence(digramRule, internalNode, externalNode, edges);
        digramRule.getOccurrences().add(digramOccurrence);
    }

}
