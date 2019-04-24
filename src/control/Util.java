package control;

import model.DigramList.DigramLists;
import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.Node;
import model.Graph.Graph;
import model.Graph.Edge;

/**
 * This class is a class for standard tasks.
 *
 * @author Matthias Duerksen
 */
class Util {

    public static void main(String[] a) {
        createTempGraph();
        System.out.println(DigramLists.getInstance().toString());
    }

    public static Graph createTempGraph() {
        Graph graph = new Graph();

        Node[] nodes = new Node[4];
        nodes[0] = new Node("u");
        nodes[1] = new Node("v");
        nodes[2] = new Node("u");
        nodes[3] = new Node("v");


        Edge[] edges = new Edge[2];
        edges[0] = new Edge(nodes[0], 1, nodes[1], 1);
        edges[1] = new Edge(nodes[2], 1, nodes[3], 1);

        for (Node node : nodes) {
            graph.add(node);
        }
        for (Edge edge : edges) {
            graph.add(edge);
        }
        CompressionControl control = new CompressionControl(graph);
        control.graphCompression(false);

        return graph;
    }


    public static Graph createDebuggingGraph() {
        Graph graph = new Graph();

        Node[] nodes = new Node[8];
        nodes[0] = new Node("u");
        nodes[1] = new Node("v1");
        nodes[2] = new Node("w");
        nodes[3] = new Node("u");
        nodes[4] = new Node("w");
        nodes[5] = new Node("v2");
        nodes[6] = new Node("w");
        nodes[7] = new Node("u");


        Edge[] edges = new Edge[7];
        edges[0] = new Edge(nodes[0], 1, nodes[1], 1);
        edges[1] = new Edge(nodes[1], 1, nodes[2], 1);
        edges[2] = new Edge(nodes[3], 1, nodes[1], 1);
        edges[3] = new Edge(nodes[3], 1, nodes[5], 1);
        edges[4] = new Edge(nodes[5], 1, nodes[4], 1);
        edges[5] = new Edge(nodes[5], 1, nodes[6], 1);
        edges[6] = new Edge(nodes[7], 1, nodes[5], 1);

        for (Node node : nodes) {
            graph.add(node);
        }
        for (Edge edge : edges) {
            graph.add(edge);
        }
        CompressionControl control = new CompressionControl(graph);
        control.graphCompression(false);

        return graph;
    }


    public static Graph createAdjacencyGraphFromThesis() {
        Graph graph = new Graph();

        Node[] nodes = new Node[8];
        nodes[0] = new Node("u");
        nodes[1] = new Node("v1");
        nodes[2] = new Node("w");
        nodes[3] = new Node("u");
        nodes[4] = new Node("w");
        nodes[5] = new Node("v2");
        nodes[6] = new Node("w");
        nodes[7] = new Node("u");


        Edge[] edges = new Edge[7];
        edges[0] = new Edge(nodes[0], 1, nodes[1], 1);
        edges[1] = new Edge(nodes[1], 1, nodes[2], 1);
        edges[2] = new Edge(nodes[3], 1, nodes[1], 1);
        edges[3] = new Edge(nodes[3], 1, nodes[5], 1);
        edges[4] = new Edge(nodes[5], 1, nodes[4], 1);
        edges[5] = new Edge(nodes[5], 1, nodes[6], 1);
        edges[6] = new Edge(nodes[7], 1, nodes[5], 1);

        for (Node node : nodes) {
            graph.add(node);
        }
        for (Edge edge : edges) {
            graph.add(edge);
        }
        CompressionControl control = new CompressionControl(graph);
        control.graphCompression(false);

        return graph;
    }

    public static Graph createOtherGraphFromThesis() {
        Graph graph = new Graph();

        Node[] nodes = new Node[4];
        nodes[0] = new Node("n2");
        nodes[1] = new Node("n1");
        nodes[2] = new Node("n2");
        nodes[3] = new Node("n1");

        Edge[] edges = new Edge[3];
        edges[0] = new Edge(nodes[1], 1, nodes[0], 1);
        edges[1] = new Edge(nodes[1], 1, nodes[2], 1);
        edges[2] = new Edge(nodes[3], 1, nodes[2], 1);

        for (Node node : nodes) {
            graph.add(node);
        }
        for (Edge edge : edges) {
            graph.add(edge);
        }
        CompressionControl control = new CompressionControl(graph);
        control.graphCompression(false);

        return graph;
    }

//    public static Graph createGraphFromThesis() {
//        Graph graph = new Graph();
//
//        Node[] nodes = new Node[7];
//        nodes[0] = new Node("x");
//        nodes[1] = new Node("x");
//        nodes[2] = new Node("z");
//        nodes[3] = new Node("y");
//        nodes[4] = new Node("y");
//        nodes[5] = new Node("z");
//        nodes[6] = new Node("w");
//
//        Edge[] edges = new Edge[7];
//        edges[0] = new Edge(nodes[0], nodes[1]);
//        edges[1] = new Edge(nodes[1], nodes[2]);
//        edges[2] = new Edge(nodes[2], nodes[3]);
//        edges[3] = new Edge(nodes[3], nodes[4]);
//        edges[4] = new Edge(nodes[5], nodes[4]);
//        edges[5] = new Edge(nodes[0], nodes[5]);
//        edges[6] = new Edge(nodes[5], nodes[6]);
//
//        for (Node node : nodes) {
//            graph.add(node);
//        }
//        for (Edge edge : edges) {
//            graph.add(edge);
//        }
//        CompressionControl control = new CompressionControl(graph);
//        control.graphCompression(false);
//
//        return graph;
//    }
//
//
//    /**
//     * Create a control Graph.
//     *
//     * @return a Graph.
//     */
//    public static Graph createSampleGraph() {
//        Graph graph = new Graph();
//
//        Node n6 = new Node("n6");
//        graph.add(n6);
//
//        //Left Side
//        Node n1 = new Node("n1");
//        graph.add(n1);
//        Node n2 = new Node("n2");
//        graph.add(n2);
//        Node n3 = new Node("n3");
//        graph.add(n3);
//        Node n4 = new Node("n4");
//        graph.add(n4);
//        Node n5 = new Node("n5");
//        graph.add(n5);
//
//
//        Edge e1 = new Edge(new Node[]{n2}, new Node[]{n1}, "e1");
//        graph.add(e1);
//        Edge e2 = new Edge(new Node[]{n2}, new Node[]{n3}, "e2");
//        graph.add(e2);
//        Edge e3 = new Edge(new Node[]{n4}, new Node[]{n3}, "e3");
//        graph.add(e3);
//        Edge e5 = new Edge(new Node[]{n1}, new Node[]{n5}, "e5");
//        graph.add(e5);
//        Edge e7 = new Edge(new Node[]{n5}, new Node[]{n6}, "e7");
//        graph.add(e7);
//        Edge e8 = new Edge(new Node[]{n4}, new Node[]{n6}, "e3");
//        graph.add(e8);
//
//        for (int i = 0; i < 4; i++) {
//            Node n7 = new Node("n7");
//            graph.add(n7);
//            Node n8 = new Node("n8");
//            graph.add(n8);
//
//            Edge e10 = new Edge(new Node[]{n4}, new Node[]{n7}, "e10");
//            graph.add(e10);
//            Edge e11 = new Edge(new Node[]{n7}, new Node[]{n8}, "e11");
//            graph.add(e11);
//            Edge e12 = new Edge(new Node[]{n8}, new Node[]{n8}, "e12");
//            graph.add(e12);
//        }
//
//
//        //Right Side
//        n1 = new Node("n1");
//        graph.add(n1);
//        n2 = new Node("n2");
//        graph.add(n2);
//        n3 = new Node("n3");
//        graph.add(n3);
//        n4 = new Node("n4");
//        graph.add(n4);
//        n5 = new Node("n5");
//        graph.add(n5);
//
//
//        e1 = new Edge(new Node[]{n2}, new Node[]{n1}, "e1");
//        graph.add(e1);
//
//        e3 = new Edge(new Node[]{n4}, new Node[]{n3}, "e3");
//        graph.add(e3);
//        Edge e4 = new Edge(new Node[]{n2}, new Node[]{n3}, "e4");
//        graph.add(e4);
//        Edge e6 = new Edge(new Node[]{n1}, new Node[]{n5}, "e6");
//        graph.add(e6);
//        e7 = new Edge(new Node[]{n5}, new Node[]{n6}, "e7");
//        graph.add(e7);
//        Edge e9 = new Edge(new Node[]{n4}, new Node[]{n6}, "e9");
//        graph.add(e9);
//
//        for (int i = 0; i < 4; i++) {
//            Node n7 = new Node("n7");
//            graph.add(n7);
//            Node n8 = new Node("n8");
//            graph.add(n8);
//
//            Edge e10 = new Edge(new Node[]{n4}, new Node[]{n7}, "e10");
//            graph.add(e10);
//            Edge e11 = new Edge(new Node[]{n7}, new Node[]{n8}, "e11");
//            graph.add(e11);
//            Edge e12 = new Edge(new Node[]{n8}, new Node[]{n8}, "e12");
//            graph.add(e12);
//        }
//
//
//        return graph;
//    }

}
