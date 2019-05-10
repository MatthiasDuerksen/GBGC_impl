package control;

import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a class to import a Graph out of a text file.
 * <p>
 * The graph in the text file must have a certain syntax. First, the nodes are listed with a new line separated by id':'nodelabel.
 * The id for the node must be unique and a positive integer.
 * The list of nodes and edges is separated by '#' in an extra line and the edges are separated by a new line.
 * An edge is defined by edgelabel':'listOfStartNodes'->'listOfEndnodes.
 * The list for the start  and endnodes are lists of the node id's, separated by ';'.
 *
 * @author Matthias Duerksen
 * @see Graph
 */
class GraphInputControl {


    /**
     * Import of a Graph from the path.
     *
     * @param path path from where the graph should be imported.
     * @return the imported Graph if the import was not successful 'null' will be returned.
     * @throws IOException throws IOException if an error occurs while import the graph form the text file.
     */
    public static Graph getGraph(String path) throws IOException {
        Graph graph = new Graph();
        List<String> lines = Files.readAllLines(Paths.get(path));

        int edgeStartLine = lines.size() - 1;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("#")) {
                edgeStartLine = i + 1;
                break;
            }
            if (line.contains(":")) {
                String[] parts = line.split(":");
                Node node = new Node(parts[1]);
                graph.add(node);
            }
        }

        for (int i = edgeStartLine; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains(":")) {
                String[] edge = line.split(":");
                String[] parts = edge[1].split("->");
                String[] startnodesIDs = parts[0].split(";");
                String[] endnodesIDs = parts[1].split(";");
                LinkedHashMap<Node, Integer> startnodes = convertToNodes(graph, startnodesIDs);
                LinkedHashMap<Node, Integer> endnode = convertToNodes(graph, endnodesIDs);
                graph.add(new Edge(startnodes, endnode, Edge.EMPTY_LABEL));
            }
        }


        return graph;
    }

    /**
     * Converts the id's from the nodes to the references itself.
     *
     * @param graph  graph for which the references are searched.
     * @param labels id's for which the reference are searched.
     * @return the references of the nodes for the id's.
     */
    private static LinkedHashMap<Node, Integer> convertToNodes(Graph graph, String[] labels) {
        LinkedHashMap<Node, Integer> nodes = new LinkedHashMap<>();
        for (int i = 0; i < labels.length; i++) {
            int id = Integer.parseInt(labels[i]);
            nodes.put(graph.getAllNodes().get(id), 1);
        }

        return nodes;
    }

}
