package test;


import control.CompressionControl;
import control.Config;
import control.commpressionSteps.NewDigramFinder;
import model.Digram.DigramType;
import model.DigramList.DigramLists;
import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class SearchTest {

    private Graph graph;
    private NewDigramFinder digramFinder = new NewDigramFinder();

    @BeforeEach
    public void init() {
        DigramLists.getInstance().resetDigramLists();
        graph = initGraph1();
    }

    @Test
    public void basicDigram() {
        digramFinder.seachForDigrams(graph);

        Assertions.assertEquals(3, DigramLists.getInstance().getDigramMap(DigramType.BASIC_NODE_DIGRAM).getNumDigramRules());
        Assertions.assertEquals(2, DigramLists.getInstance().getDigramMap(DigramType.BASIC_NODE_DIGRAM).getNextDigramRule().getNumOccurences());
    }

    @Test
    public void circleDigram() {
        digramFinder.seachForDigrams(graph);

        Assertions.assertEquals(1, DigramLists.getInstance().getDigramMap(DigramType.CIRCLE_DIGRAM).getNumDigramRules());
        Assertions.assertEquals(2, DigramLists.getInstance().getDigramMap(DigramType.CIRCLE_DIGRAM).getNextDigramRule().getNumOccurences());
    }


    public Graph initGraph1() {

        Graph graph = new Graph();

        Node[] nodes = new Node[4];
        nodes[0] = new Node("u");
        nodes[1] = new Node("v");
        nodes[2] = new Node("u");
        nodes[3] = new Node("v");


        Edge[] edges = new Edge[6];
        edges[0] = new Edge(nodes[0], 1, nodes[1], 1);
        edges[1] = new Edge(nodes[2], 1, nodes[3], 1);
        edges[2] = new Edge(nodes[0], 1, nodes[0], 1);
        edges[3] = new Edge(nodes[2], 1, nodes[2], 1);
        edges[4] = new Edge(nodes[0], 1, nodes[2], 1);
        edges[5] = new Edge(nodes[1], 1, nodes[3], 1);

        for (Node node : nodes) {
            graph.add(node);
        }
        for (Edge edge : edges) {
            graph.add(edge);
        }

        return graph;
    }

}
