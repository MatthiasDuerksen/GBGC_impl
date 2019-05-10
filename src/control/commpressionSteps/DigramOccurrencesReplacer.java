package control.commpressionSteps;

import model.Digram.DigramRule;
import model.DigramOccurrence.DigramOccurrence;
import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.GraphElement;
import model.Graph.Node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DigramOccurrencesReplacer {

    public void replaceOccurrences(DigramRule digramRule, Graph graph) {
        //todo
        System.out.print("Digram to replace: ");
        System.out.println(digramRule);
        System.out.println("End");

        digramRule.setNonterminal();
        for (DigramOccurrence digramOccurrence : digramRule.getOccurrences()) {

            if (digramRule.replacesToANode()) {
                replaceToANode(digramRule, digramOccurrence, graph);
            } else {
                replaceTOAnEdge(digramRule, digramOccurrence, graph);
            }
        }


        digramRule.setBeenApplied();
        //modify getNextDigram, so that applied DigramRules not been in search for next digram, or remove from digramlist
        //currently not necessary because we dont update the digramLists
    }

    private void replaceToANode(DigramRule digramRule, DigramOccurrence digramOccurrence, Graph graph) {
        for (Edge edge : digramOccurrence.getEdges()) {
            graph.delete(edge);
        }
        Node newNode = new Node(digramRule.getNonterminal());
        graph.add(newNode);

        List<Edge> incidentEdges = new ArrayList<>();
        for (Node node : digramOccurrence.getInternalNodes()) {
            for (Edge edge : graph.getAllIncidentEdges(node)) {
                int equiv;
                if (edge.getStartnodes().containsKey(node)) {
                    equiv = edge.getStartnodes().get(node);
                    edge.getStartnodes().remove(node);
                    edge.getStartnodes().put(newNode, digramRule.getEquivOffset(digramRule.getInternalNodes().get(digramOccurrence.getInternalNodes().indexOf(node))) + equiv);
                }
                if (edge.getEndnodes().containsKey(node)) {
                    equiv = edge.getEndnodes().get(node);
                    edge.getEndnodes().remove(node);
                    edge.getEndnodes().put(newNode, digramRule.getEquivOffset(digramRule.getInternalNodes().get(digramOccurrence.getInternalNodes().indexOf(node))) + equiv);
                }
            }
            incidentEdges.addAll(graph.getAllIncidentEdges(node));

            graph.delete(node);
        }
    }

    private void replaceTOAnEdge(DigramRule digramRule, DigramOccurrence digramOccurrence, Graph graph) {
        for (Node node : digramOccurrence.getInternalNodes()) {
            graph.delete(node);
        }
        LinkedHashMap<Node, Integer> startnodes = new LinkedHashMap<>();
        LinkedHashMap<Node, Integer> endnodes = new LinkedHashMap<>();

        for (Edge edge : digramOccurrence.getEdges()) {
            for (Node node : edge.getStartnodes().keySet()) {
                if (!digramOccurrence.getInternalNodes().contains(node)) {
                    startnodes.put(node, edge.getStartnodes().get(node));
                }
            }
            for (Node node : edge.getEndnodes().keySet()) {
                endnodes.put(node, edge.getEndnodes().get(node));
            }
        }
        for (Edge edge : digramOccurrence.getEdges()) {
            for (Node node : edge.getEndnodes().keySet()) {
                if (!digramOccurrence.getInternalNodes().contains(node)) {
                    endnodes.put(node, edge.getEndnodes().get(node));
                }
            }
            graph.delete(edge);
            Edge newEdge = new Edge(startnodes, endnodes, digramRule.getNonterminal());
            graph.add(newEdge);//TODO Bisher wurde die equiv der Kanten nicht berücksichtigt
        }
    }
}
