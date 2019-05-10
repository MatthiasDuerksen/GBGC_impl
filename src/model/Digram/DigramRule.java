package model.Digram;

import control.Config;
import model.DigramOccurrence.DigramOccurrence;
import model.Graph.Edge;
import model.Graph.Node;
import model.Tuple;
import scala.Int;
import scala.util.parsing.combinator.testing.Str;

import java.util.*;

public class DigramRule {

    private DigramType digramType;

    /**
     * counter for the identifier of a digram.
     */
    private static int digramCounter = 0;
    private List<Node> internalNodes = new ArrayList<>();
    private List<Node> externalNodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();


    protected List<DigramOccurrence> occurrences = new ArrayList<>();
    //foreach internalNode an offset
    protected final Map<Node, Integer> mapEquivOffset = new HashMap<>();
    /**
     * the non terminal from the digram.
     */
    protected String nonterminal;

    /**
     * Indicates whether the digram is applied.
     */
    protected boolean beenApplied = false;

    public DigramRule(DigramType digramType, List<Node> internalNodes, List<Node> externalNodes, List<Edge> edges) {
        this.digramType = digramType;
        this.internalNodes = internalNodes;
        this.externalNodes = externalNodes;
        this.edges = edges;
        initEquivOffset();
    }

    private void initEquivOffset() {
        int currentOffset = 0;
        for (Node node : internalNodes) {
            mapEquivOffset.put(node, currentOffset);
            currentOffset += node.getNumEquivClasses();
        }
    }

    /**
     * Setter for the Non terminal of the digram.
     */
    public void setNonterminal() {
        nonterminal = "A_" + digramCounter++;

    }

    public List<DigramOccurrence> getOccurrences() {
        return occurrences;
    }

    /**
     * Gets the number of associated occurrences.
     *
     * @return the number of associate occurrences of the digram.
     */
    public int getNumOccurences() {
        return occurrences.size();
    }


    /**
     * Getter for the Non terminal of the digram.
     *
     * @return the Non terminal of the digram.
     */
    public String getNonterminal() {
        return nonterminal;
    }

    /**
     * Getter that indicates that digram was applied.
     *
     * @return true if the digram was applied, else false.
     */
    public boolean hasBeenAplied() {
        return beenApplied;
    }

    /**
     * Sets the digram to applied.
     */
    public void setBeenApplied() {
        beenApplied = true;
    }

    public DigramType getDigramType() {
        return digramType;
    }


    public boolean replacesToANode() {
        return Config.digramTypesWichReplacesToNodes.get(digramType.toString()).equals(Node.class);
    }

    @Override
    public boolean equals(Object obj) {
        DigramRule digramRule = (DigramRule) obj;
        if (!digramType.equals(digramRule.digramType)) {
            return false;
        }

        //check nodes
        if (!(internalNodes.size() == digramRule.internalNodes.size() && externalNodes.size() == digramRule.externalNodes.size())) {
            return false;
        }
        for (int i = 0; i < internalNodes.size(); i++) {
            if (!internalNodes.get(i).getLabel().equals(digramRule.internalNodes.get(i).getLabel())) {
                return false;
            }
        }


        if (edges.size() != digramRule.edges.size()) {
            return false;
        }

        for (int i = 0; i < edges.size(); i++) {
            if (!edges.get(i).getLabel().equals(digramRule.edges.get(i).getLabel())) {
                return false;
            }

            //Check equiv from startnodes
            for (int j = 0; j < edges.get(i).getStartnodes().size(); j++) {
                Node node1 = (Node) edges.get(i).getStartnodes().keySet().toArray()[j];
                Node node2 =(Node) digramRule.edges.get(i).getStartnodes().keySet().toArray()[j];
                if (internalNodes.contains(node1)) {
                    if (!(edges.get(i).getStartnodes().get(node1).equals( digramRule.edges.get(i).getStartnodes().get(node2)))){
                        return false;
                    }
                }

            }

            //Check equiv from endnodes
            for (int j = 0; j < edges.get(i).getEndnodes().size(); j++) {
                Node node1 = (Node) edges.get(i).getEndnodes().keySet().toArray()[j];
                Node node2 =(Node) digramRule.edges.get(i).getEndnodes().keySet().toArray()[j];
                if (internalNodes.contains(node1)) {
                    if (!edges.get(i).getEndnodes().get(node1).equals(digramRule.edges.get(i).getEndnodes().get(node2))){
                        return false;
                    }
                }

            }

        }

        return true;
    }

    public int getEquivOffset(Node node) {
        return mapEquivOffset.get(node);
    }

    public List<Node> getInternalNodes() {
        return internalNodes;
    }

    public List<Node> getExternalNodes() {
        return externalNodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        String string = "DigramRule " + nonterminal + " Type " + digramType + " NumOcc:" + occurrences.size() + " InternalNodes: ";
        for (Node node : internalNodes) {
            string += node + "; ";
        }
        string += "# Edges: ";
        for (Edge edge : edges) {
            string += edge.shortToString() + "; ";
        }
        return string;
    }


    public synchronized void addOccurrence(DigramOccurrence digramOccurrence) {
        occurrences.add(digramOccurrence);
    }
}
