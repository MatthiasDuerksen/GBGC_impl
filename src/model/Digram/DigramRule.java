package model.Digram;

import model.DigramOccurrence.DigramOccurrence;
import model.Graph.Edge;
import model.Graph.Node;
import model.Tuple;
import scala.util.parsing.combinator.testing.Str;

import java.util.*;

public class DigramRule {

    private DigramType digramType;

    /**
     * counter for the identifier of a digram.
     */
    private static int digramCounter = 0;
    private List<String> internalNodes = new ArrayList<>();
    private List<String> externalNodes = new ArrayList<>();
    //          Edge-ID   Node-ID Equivalencclass
    private Map<String, Map<String, Integer>> edgeStartnodes = new LinkedHashMap<>();
    private Map<String, Map<String, Integer>> edgeEndNodes = new LinkedHashMap<>();

    protected List<DigramOccurrence> occurrences;
    protected final Map<String, List<Tuple<Integer, Integer>>> mapEquivClasses = new HashMap<>();
    /**
     * the non terminal from the digram.
     */
    protected String nonterminal;

    /**
     * Indicates whether the digram is applied.
     */
    protected boolean beenApplied = false;

    protected int equivClassCounter = 1;


    private DigramRule(DigramType digramType) {
        this.occurrences = new ArrayList<>();
        this.digramType = digramType;
    }

    public DigramRule(DigramType digramType, List<String> internalNodes, List<String> externalNodes, Map<String, Map<String, Integer>> edgeStartnodes, Map<String, Map<String, Integer>> edgeEndNodes) {
        this.digramType = digramType;
        this.internalNodes = internalNodes;
        this.externalNodes = externalNodes;
        this.edgeStartnodes = edgeStartnodes;
        this.edgeEndNodes = edgeEndNodes;
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


    @Override
    public String toString() {
        String string = "\nDigramRule " + nonterminal + ": ";
        for (DigramOccurrence digramOccurrence : occurrences) {
            string += "\n";
            string += "----------" + digramOccurrence + toString();

        }
        return string;
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
            if (!internalNodes.get(i).equals(digramRule.internalNodes.get(i))) {
                return false;
            }
        }
        for (int i = 0; i < externalNodes.size(); i++) {
            if (!externalNodes.get(i).equals(digramRule.externalNodes.get(i))) {
                return false;
            }
        }

        if ((edgeStartnodes.size() == digramRule.edgeStartnodes.size() && edgeEndNodes.size() == digramRule.edgeEndNodes.size())
        {
            return false;
        }
        for (int i = 0; i < edgeStartnodes.size(); i++) {
            //TODO Change Map to Set
        }


    }

}
