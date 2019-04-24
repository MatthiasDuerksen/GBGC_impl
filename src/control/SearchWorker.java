package control;

import model.Digram.DigramType;
import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.GraphElement;
import model.Graph.Node;

import java.util.*;


public class SearchWorker extends Thread {

    private Graph graph;


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

                //Adjacency Digram
                if (Config.isDigramTypeInUse(DigramType.ADJACENCY_DIGRAM)) {
                    List<Edge> incidentNonCircleEdges = new ArrayList<>();
                    for (Edge edge : node.getIncedentEdges()) {
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

            } else {
                Edge edge = (Edge) graphElement;

                //Basic Node Digram evertime possible
                if (Config.isDigramTypeInUse(DigramType.BASIC_NODE_DIGRAM)) {
                    //DigramLists.getInstance().getDigramMap(DigramType.BASIC_NODE_DIGRAM).addOccurence(edge);
                    System.out.println("Found Basic Digram" + edge);




                }


                //Circle Digram ----- Only SimpleEdges
                if (Config.isDigramTypeInUse(DigramType.CIRCLE_DIGRAM) && edge.isCircle()) {
                    //DigramLists.getInstance().getDigramMap(DigramType.CIRCLE_DIGRAM).addOccurence(edge);
                    System.out.println("Found Circle Digram" + edge);
                }

            }


        }


    }


//    /**
//     * This method finds all active basic appliedDigrams of a graph and stores them in the DigramList.
//     */
//    private DigramList findAllBaseDigrams() {
//
//        digramListBasicDigrams = new BasicDigramList(labels);
//
//        // compute random permutation
//        List<Edge> edgesCopy = new ArrayList<>(graph.getAllEdges().values());
//        List<Edge> edgesInRandomOrder = new ArrayList<>();
//
//        int numEdges = edgesCopy.size();
//        while (edgesInRandomOrder.size() < numEdges) {
//            int randomIndex = 0;
//            if (edgesCopy.size() > 1) {
//                randomIndex = new Random().nextInt(edgesCopy.size() - 1);
//            }
//
//            edgesInRandomOrder.add(edgesCopy.get(randomIndex));
//            edgesCopy.remove(randomIndex);
//        }
//
//        for (Edge edge : edgesInRandomOrder) {
//            CompressionControl.checkAndAddEdgeToDigram((SimpleEdge) edge, digramListBasicDigrams);
//        }
//
//        return digramListBasicDigrams;
//
//    }
//
//    private DigramList findAllAdjacencyDigrams(){
//        digramListAdjacencyDigrams = new AdjacencyDigramList(labels);
//
//        // compute random permutation
//        List<Node> nodesCopy = new ArrayList<>(graph.getAllNodes().values());
//        List<Node> nodesInRandomOrder = new ArrayList<>();
//
//        int numNodes = nodesCopy.size();
//        while (nodesInRandomOrder.size() < numNodes) {
//            int randomIndex = 0;
//            if (nodesCopy.size() > 1) {
//                randomIndex = new Random().nextInt(nodesCopy.size() - 1);
//            }
//
//            nodesInRandomOrder.add(nodesCopy.get(randomIndex));
//            nodesCopy.remove(randomIndex);
//        }
//
//        for (Node node : nodesInRandomOrder) {
//            digramListAdjacencyDigrams.addDigrams(node, graph);
//        }
//
//        return digramListBasicDigrams;
//    }


}
