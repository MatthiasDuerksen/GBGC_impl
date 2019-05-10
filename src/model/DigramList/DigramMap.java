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


    @Override
    public String toString() {
        String string = "\nDigramMap " + digramType + ": ";
        for (DigramRule digramRule : digramRuleList) {
            string += "\n----" + digramRule.toString();
        }
        return string;
    }

    public synchronized DigramRule getDigramRule(DigramRule digramRule) {
        for (DigramRule rule : digramRuleList) {
            if (digramRule.equals(rule)) {
                return rule;
            }
        }
        digramRuleList.add(digramRule);
        return digramRule;
    }
}
