package control.commpressionSteps;

import control.SearchWorker;
import model.Digram.AdjacencyDigram;
import model.Digram.BasicDigram;
import model.DigramList.AdjacencyDigramList;
import model.DigramList.BasicDigramList;
import model.Graph.HyperGraph;
import model.Graph.Node;

import java.util.*;

public class DigramFinder {

    private SearchWorker[] workers;

    public void findAllDigrams(HyperGraph graph){
        // start all search workers
        List<String> labels = getAllDuplicatedLabelsLabels(graph);
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new SearchWorker(appliedDigrams, graph, labels);
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

        // gather and compare the results of the workers
        List<BasicDigramList> digramLists = new ArrayList<>();
        for (SearchWorker worker : workers) {
            digramLists.add(worker.digramListBasicDigrams);
        }

        BasicDigramList bestDigramList = digramLists.get(0);
        int bestNoOcc = 0;
        for (BasicDigramList digramList : digramLists) {
            BasicDigram maxDigram = (BasicDigram) digramList.getMaxDigram();
            if (maxDigram != null) {
                if (maxDigram.getOccurrences().size() > bestNoOcc) {
                    bestDigramList = digramList;
                    bestNoOcc = maxDigram.getOccurrences().size();
                }
            }
        }
        this.basicDigramList = bestDigramList;

        List<AdjacencyDigramList> adjacencyDigramLists = new ArrayList<>();
        for (SearchWorker worker : workers) {
            adjacencyDigramLists.add(worker.digramListAdjacencyDigrams);
        }

        AdjacencyDigramList bestAdjDigramList = adjacencyDigramLists.get(0);
        bestNoOcc = 0;
        for (AdjacencyDigramList digramList : adjacencyDigramLists) {
            AdjacencyDigram maxDigram = (AdjacencyDigram) digramList.getMaxDigram();
            if (maxDigram != null) {
                if (maxDigram.getOccurrences().size() > bestNoOcc) {
                    bestAdjDigramList = digramList;
                    bestNoOcc = maxDigram.getOccurrences().size();
                }
            }
        }
        this.adjacencyDigramList = bestAdjDigramList;
    }


    /**
     * This method finds all labels in the graph which are necessary for the compression.
     * <p>
     * A label is called necessary iff the label occurs at least twice in the graph.
     *
     * @param graph the graph for which the method will be executed.
     * @return all necessary different labels.
     */
    public LinkedList<String> getAllDuplicatedLabelsLabels(HyperGraph graph) {
        HashMap<String, Integer> labelCounter = new HashMap<>();

        //Find All different Labels with more then 2 occurences
        //foreach Node in graph
        for (Map.Entry<Integer, Node> entry : graph.getAllNodes().entrySet()) {
            Node node = entry.getValue();
            if (!labelCounter.containsKey(node.getLabel())) {
                labelCounter.put(node.getLabel(), 0);
            }
            labelCounter.replace(node.getLabel(), labelCounter.get(node.getLabel()) + 1);
        }
        LinkedList<String> labels = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : labelCounter.entrySet()) {
            if (entry.getValue() > 1) {
                labels.add(entry.getKey());
            }
        }
        return labels;
    }


}
