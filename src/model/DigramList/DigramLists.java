package model.DigramList;

import control.Config;
import model.Digram.DigramRule;
import model.Digram.DigramType;
import model.DigramOccurrence.DigramOccurrence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigramLists {

    private  Map<DigramType, DigramMap> digramTypeMap = new HashMap<>();
    private static DigramLists instance;

    private DigramLists() {
    }

    public static DigramLists getInstance() {
        if (DigramLists.instance == null) {
            DigramLists.instance = new DigramLists();
        }
        return DigramLists.instance;
    }

    public synchronized DigramMap getDigramMap(DigramType digramType) {
        if (digramTypeMap.get(digramType) == null) {
            digramTypeMap.put(digramType, new DigramMap(digramType));
        }
        return digramTypeMap.get(digramType);
    }

    public DigramRule getNextDigramRule(boolean weighted) {
        int mostFrequentOccurences = 0;
        DigramRule nextDigramRule = null;
        for (DigramMap digramMap : digramTypeMap.values()) {
            DigramRule digramRule = digramMap.getNextDigramRule();
            int numOccurences = digramRule.getNumOccurences();
            //weighted
            if (weighted) {
                numOccurences = Math.multiplyExact(numOccurences, Config.getDigramTypeWeight(digramMap.getDigramType()));
            }
            if (numOccurences > mostFrequentOccurences) {
                mostFrequentOccurences = numOccurences;
                nextDigramRule = digramRule;
            }
        }
        if (mostFrequentOccurences < 2) {
            return null;
        }
        return nextDigramRule;
    }

    @Override
    public String toString() {
        String string = "DigramLists: ";
        for (DigramMap digramMap : digramTypeMap.values()) {
            string += "\n" + digramMap.toString();
        }

        return string;

    }

    public void resetDigramLists(){
        digramTypeMap=new HashMap<>();
    }

}
