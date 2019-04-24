package model.Graph;

import model.DigramOccurrence.DigramOccurrence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphElementUsage {
    private final Map<Integer,List<DigramOccurrence>> elementUsage= new HashMap<>();


    public synchronized void add(GraphElement graphElement, DigramOccurrence digramOccurrence){
        if(elementUsage.get(graphElement.getId())==null){
            elementUsage.put(graphElement.getId(),new ArrayList<>());
        }
        elementUsage.get(graphElement.getId()).add(digramOccurrence);
    }

    public List<DigramOccurrence> getOccurences(GraphElement graphElement){
        return elementUsage.get(graphElement);
    }

}
