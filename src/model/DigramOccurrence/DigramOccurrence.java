package model.DigramOccurrence;

import model.Digram.DigramRule;
import model.Digram.DigramType;
import model.Graph.Edge;
import model.Graph.Node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DigramOccurrence {

    private DigramRule digramRule;
    private List<Node> internalNodes = new ArrayList<>();
    private List<Node> externalNodes = new ArrayList<>();
    //          Edge-ID   Node-ID Equivalencclass
    private List<Edge> edges = new ArrayList<>();


    public DigramOccurrence(DigramRule digramRule, List<Node> internalNodes, List<Node> externalNodes, List<Edge> edges) {
        this.digramRule = digramRule;
        this.internalNodes = internalNodes;
        this.externalNodes = externalNodes;
        this.edges = edges;
    }


    public DigramType getDigramType() {
        return digramRule.getDigramType();
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
}
