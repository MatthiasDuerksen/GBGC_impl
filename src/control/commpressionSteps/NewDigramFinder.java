package control.commpressionSteps;

import control.Config;
import control.SearchWorker;
import model.Digram.DigramType;
import model.Graph.Graph;
import model.Graph.GraphElement;

import java.util.*;
import java.util.List;

public class NewDigramFinder {

    /**
     * Indicated for which digramtypes are searched.
     */
    private final List<DigramType> usedDigramTypes;
    private SearchWorker[] workers= new SearchWorker[Config.NUMBER_OF_SEARCHWORKERS];

    public NewDigramFinder(List<DigramType> usedDigramTypes) {
        this.usedDigramTypes = usedDigramTypes;
    }


    public void seachForDigrams(Graph graph) {
        // start all search workers
        List<GraphElement> graphElements = new ArrayList<>();
        graphElements.addAll(graph.getAllNodes().values());
        graphElements.addAll(graph.getAllEdges().values());
        //List<String> labels = getAllDuplicatedLabelsLabels(graph);

        //split in equal parts
        int partitionSize = ((int) Math.floor(graphElements.size() / Config.NUMBER_OF_SEARCHWORKERS))+1;
        List<List<GraphElement>> partitions = new ArrayList<List<GraphElement>>();
        for (int i = 0; i < graphElements.size(); i += partitionSize) {
            partitions.add(graphElements.subList(i, Math.min(i + partitionSize, graphElements.size())));
        }


        for (int i = 0; i < partitionSize; i++) {
            workers[i] = new SearchWorker(graph, partitions.get(i));
        }

        for (SearchWorker worker : workers) {
            worker.run();
        }

        for (SearchWorker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        }
    }

}


