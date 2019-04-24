package model.DigramList;

import model.Digram.DigramRule;
import model.Digram.DigramType;
import model.DigramOccurrence.DigramOccurrence;
import model.Graph.Edge;
import model.Graph.Node;
import scala.collection.parallel.ParIterableLike;
import sun.swing.BakedArrayList;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DigramMap {
    private List<DigramRule> digramRuleList = new ArrayList<DigramRule>();

    private DigramType digramType;


    public DigramMap(DigramType digramType) {
        this.digramType = digramType;
    }

    public DigramRule getNextDigramRule() {
        int mostFrequentOccurences = 0;
        DigramRule nextDigramRule = null;
        for (DigramRule digramRule : digramRuleList) {
            if (digramRule.getNumOccurences() > mostFrequentOccurences) {
                mostFrequentOccurences = digramRule.getNumOccurences();
                nextDigramRule = digramRule;
            }
        }
        return nextDigramRule;
    }

    public int getNumDigramRules() {
        return digramRuleList.size();
    }

    public DigramType getDigramType() {
        return digramType;
    }

    public synchronized void addOccurences(Node node1, Node node2, Edge edge1, Edge edge2) {
        switch (getDigramType()) {
            case BASIC_NODE_DIGRAM:

                break;
            case CIRCLE_DIGRAM:

                break;
            case ADJACENCY_DIGRAM:

                break;
            default:
                throw new IllegalArgumentException("Wrong DigramType");
        }


    }


    @Override
    public String toString() {
        String string = "\nDigramMap " + digramType + ": ";
        for (DigramRule digramRule : digramRuleList) {
            string += "\n----" + digramRule.toString();
        }
        return string;
    }

    public DigramRule getDigramRule(DigramRule digramRule) {
        for (DigramRule rule : digramRuleList) {
            if (digramRule.equals(rule)) {
                return rule;
            }
        }
        digramRuleList.add(digramRule);
        return digramRule;
    }
}
