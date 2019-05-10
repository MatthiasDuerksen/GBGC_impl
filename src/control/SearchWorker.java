package control;

import model.Digram.DigramType;
import control.commpressionSteps.RuleGenerator;
import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.GraphElement;
import model.Graph.Node;

import java.util.*;


public class SearchWorker extends Thread {

    private Graph graph;
    private RuleGenerator ruleGenerator = new RuleGenerator();


    private List<GraphElement> graphElements;


    public SearchWorker(Graph graph, List<GraphElement> graphElements) {
        this.graph = graph;
        this.graphElements = graphElements;
    }

    @Override
    public void run() {
        checkForDigrams();
    }

    private void checkForDigrams() {
        for (GraphElement graphElement : graphElements) {
            if (graphElement instanceof Node) {
                Node node = (Node) graphElement;

                checkForAdjaceny(node);
                checkForM3(node);
            } else {
                Edge edge = (Edge) graphElement;

                //Basic Node Digram evertime possible
                if (Config.isDigramTypeInUse(DigramType.BASIC_NODE_DIGRAM) && !edge.isCircle()) {
//                    System.out.println("Found Basic Digram" + edge);
                    ruleGenerator.generateBasicNodeRule(edge);

                }


                //Circle Digram ----- Only SimpleEdges
                if (Config.isDigramTypeInUse(DigramType.CIRCLE_DIGRAM) && edge.isCircle()) {
//                    System.out.println("Found Circle Digram" + edge);
                    ruleGenerator.generateCircleNodeRule(edge);
                }

            }
        }
    }

    private void checkForM3(Node node) {
        if (!Config.isDigramTypeInUse(DigramType.M3) || graph.getAllIncidentEdges(node).size() != 2) {
            return;
        }
        //System.out.println("Potential M3");
        List<Node> nodes = new ArrayList<>();
        for (Edge edge : graph.getAllIncidentEdges(node)) {
            if (!edge.isSimpleEdge() || edge.isCircle()) {
                return;
            }
            if (edge.getStartNode().equals(node)) {
                nodes.add(edge.getEndNode());
            } else {
                nodes.add(edge.getStartNode());
            }
        }

        if (graph.getAllIncidentEdges(nodes.get(0)).size() != 1 && graph.getAllIncidentEdges(nodes.get(1)).size() != 1) {
            return;
        }

        if (graph.getAllIncidentEdges(nodes.get(0)).size() == 1) {
            //add
            System.out.println("Found M3 Digram " + nodes.get(0) + node + nodes.get(1));
            ruleGenerator.generateM3Rule(nodes.get(0), node, nodes.get(1), graph);
        }
        if (graph.getAllIncidentEdges(nodes.get(1)).size() == 1) {
            //add
            System.out.println("Found M3 Digram " + nodes.get(1) + node + nodes.get(0));
            ruleGenerator.generateM3Rule(nodes.get(0), node, nodes.get(1), graph);
        }

    }


    private void checkForAdjaceny(Node node) {
        //Adjacency Digram
        if (Config.isDigramTypeInUse(DigramType.ADJACENCY_DIGRAM)) {
            List<Edge> incidentNonCircleEdges = new ArrayList<>();
            for (Edge edge : graph.getAllIncidentEdges(node)) {
                if (!edge.isCircle() && edge.isSimpleEdge()) {
                    incidentNonCircleEdges.add(edge);
                }
            }
            //all Combinations
            for (Edge edge : incidentNonCircleEdges) {
                int equiv1;
                if (edge.getStartNode().equals(node)) {
                    equiv1 = edge.getStartnodes().get(edge.getStartNode());
                } else {
                    equiv1 = edge.getStartnodes().get(edge.getStartNode());
                }
                for (Edge edge2 : incidentNonCircleEdges) {
                    if (!edge.equals(edge2) && edge.getId() < edge2.getId()) {
                        //check if equiv class on extern node is the same
                        int equiv2;
                        if (edge.getStartNode().equals(node)) {
                            equiv2 = edge.getStartnodes().get(edge.getStartNode());
                        } else {
                            equiv2 = edge.getStartnodes().get(edge.getStartNode());
                        }
                        if (equiv1 == equiv2) {
                            //add
                            //DigramLists.getInstance().getDigramMap(DigramType.ADJACENCY_DIGRAM).addOccurences(node,edge,edge2);
                            System.out.println("Found Adjacency Digram " + edge + node + edge2);
                        }

                    }
                }
            }
        }
    }
}
