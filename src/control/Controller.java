//package control;
//
//import model.*;
//import model.Digram.DigramRule;
//import model.Graph.Edge;
//import model.Graph.Node;
//import model.Graph.Graph;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map.Entry;
//
///**
// * This is a class for the controlling of the compression-process for a
// * Graph.
// * <p>
// * This class manages all necessary things for the compression, like the import
// * of new graphs and manages the step-by-step-simulation of the compression. For
// * the step-by-step-simulation of the compression the entire compression process
// * is executed first and then the all steps are simulated step-by-step.
// *
// * @author Matthias Duerksen
// */
//class Controller extends Thread {
//
//    /**
//     * Indicates whether the compression is started.
//     */
//    private boolean isRunning = true;
//
//    /**
//     * Indicates whether we have the permission to simulate the next compression
//     * step.
//     */
//    private boolean permissionForNextStep = false;
//
//    /**
//     * The graph for which the compression is executed.
//     */
//    private Graph graph;
//
//    /**
//     * The graphical user interface for the graph compression.
//     */
//    private final MyFrame guiController;
//
//    /**
//     * Constructor for Controller.
//     *
//     * @param guiController GUI for displaying the compression of the graph.
//     */
//    public Controller(MyFrame guiController) {
//        this.guiController = guiController;
//    }
//
//    /**
//     * Imports a Graph from a text file via GraphInputControl.
//     *
//     * @param path path where the graph is stored.
//     * @return the imported Graph, if the import failed the old graph is
//     * returned.
//     */
//    public Graph importGraph(String path) {
//        Graph currentGraph;
//        try {
//            GraphInputControl graphInputControl = new GraphInputControl();
//            currentGraph = graphInputControl.getGraph(path);
//        } catch (IOException e) {
//            System.out.println("IllegalGraphInput");
//            return new Graph();
//        }
//
//        if (currentGraph == null) {
//            System.out.println("WrongGraphFormat");
//            return new Graph();
//        }
//        graph = currentGraph;
//        return graph;
//    }
//
//    /**
//     * Executes the compression and step-by-step simulation of the current input
//     * graph.
//     */
//    public void graphCompression() {
//        if (graph != null) {
//            CompressionControl compressionControl = new CompressionControl(graph);
//
//            List<Tuple<Graph, List<DigramRule>>> tuples = compressionControl.graphCompression();
//            guiController.setButtonNextStep();
//            displayCompression(tuples);
//        } else {
//            System.out.println("NoGraphInput");
//        }
//    }
//
//    /**
//     * Performs the whole step-by-step simulation for the executed compression
//     * of the input graph.
//     *
//     * @param tuples compression results of the compression of the graph.
//     */
//    private void displayCompression(List<Tuple<Graph, List<DigramRule>>> tuples) {
//        for (Tuple<Graph, List<DigramRule>> tuple : tuples) {
//            Graph graph = tuple.x;
//            List<DigramRule> digrams = tuple.y;
//
//            String textAreaLine = "";
//            int currentIndex = tuples.indexOf(tuple);
//
//            if (currentIndex == 0) {
//                textAreaLine += "Untransformed Graph " + graph.getAllNodes().size() + " Nodes, "
//                        + graph.getAllEdges().size() + " Edges";
//            } else if (currentIndex == 1) {
//                textAreaLine += "Transformed Graph";
//            } else if (currentIndex == tuples.size() - 1) {
//                textAreaLine += "Compressed Graph\nFinished";
//                guiController.compressionFinished();
//            } else if (currentIndex == tuples.size() - 2) {
//                textAreaLine += "Pruning";
//            } else {
//                //TODO
////				BasicDigram_old lastDigram = (BasicDigram_old) digrams.get(digrams.size()-1);
////				textAreaLine += lastDigram.toStringUnpruned();
//            }
//
//            Tuple<List<Node>, List<Edge>> markedElements = getNodesToMark(tuples, currentIndex + 1);
//            BasicDigram_old currentDigram = null;
//            if (0 < currentIndex && currentIndex < tuples.size() - 3 && tuples.get(currentIndex + 1).y.size() > 0)
//                currentDigram = (BasicDigram_old) tuples.get(currentIndex + 1).y.get(tuples.get(currentIndex + 1).y.size() - 1);
//
//            guiController.refreshGraph(graph, textAreaLine, markedElements, currentDigram);
//
//            if (currentIndex == tuples.size() - 1)
//                guiController.showAllDigrams(digrams);
//            if (tuples.indexOf(tuple) != tuples.size() - 1) {// Not the last
//                // element
//                waitForNextStep();
//
//            }
//        }
//    }
//
//    /**
//     * Selects all nodes and edges that should be marked.
//     *
//     * @param tuples                     all applied digrams and the current Graph from each
//     *                                   compression step.
//     * @param currentSimulationStepIndex the current simulation step from the step-by-step-simulation.
//     * @return all nodes and edges that should be marked.
//     */
//    private Tuple<List<Node>, List<Edge>> getNodesToMark(List<Tuple<Graph, List<DigramRule>>> tuples, int currentSimulationStepIndex) {
//        List<Node> nodes = new ArrayList<>();
//        List<Edge> edges = new ArrayList<>();
//
//        if (0 < currentSimulationStepIndex && currentSimulationStepIndex < tuples.size() - 2) {
//            Tuple<Graph, List<DigramRule>> tuple = tuples.get(currentSimulationStepIndex);
//            Graph graph = tuples.get(currentSimulationStepIndex - 1).x;
//
//            if (tuple.y.size() > 0) {
//                //TODO
////                BasicDigram_old newDigram = (BasicDigram_old) tuple.y.get(tuple.y.size() - 1);
////                for (Entry<Integer, Edge> entry : graph.getAllEdges().entrySet()) {
////                    Edge edgeObject = entry.getValue();
////                    SimpleEdge edge = (SimpleEdge) edgeObject;
////                    if (edge.getStartnode().getLabel().equals(newDigram.getStartNodelabel())
////                            && edge.getEndnode().getLabel().equals(newDigram.getEndNodelabel())) {
////                        if (!nodes.contains(edge.getStartnode()) && !nodes.contains(edge.getEndnode())) {
////                            nodes.add(edge.getStartnode());
////                            nodes.add(edge.getEndnode());
////                            edges.add(edge);
////                        }
////                    }
////
////                }
//            }
//        }
//
//        return new Tuple<>(nodes, edges);
//    }
//
//    /**
//     * Waits for the permission for the simulation of the next step by busy
//     * waiting.
//     */
//    private void waitForNextStep() {
//        while (isRunning) {
//            if (permissionForNextStep) {
//                permissionForNextStep = false;
//                break;
//            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Gives permission for the next simulation-step.
//     */
//    public void givePermissionForNextStep() {
//        permissionForNextStep = true;
//    }
//
//}
