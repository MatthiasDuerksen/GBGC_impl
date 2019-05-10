package control.commpressionSteps;

import model.DigramList.DigramLists;
import model.Graph.Graph;

public class DigramUpdater {

    public void updateDigrams(NewDigramFinder digramFinder, Graph graph){
        DigramLists.getInstance().resetDigramLists();
        digramFinder.seachForDigrams(graph);
    }
}
