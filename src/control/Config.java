package control;

import model.Digram.DigramType;
import model.Graph.Edge;
import model.Graph.GraphElement;
import model.Graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    private static Map<DigramType, Integer> digramTypeWeighed = new HashMap<>();
    public static Map<String, Class> digramTypesWichReplacesToNodes = new HashMap<>();
    private static Map<DigramType, Boolean> digranTypeUsed = new HashMap<>();
    private static boolean executeTranformGraph = false;
    private static boolean executePruning = false;
    private static boolean executeEdgeOptimisation = false;

    public static final int NUMBER_OF_SEARCHWORKERS =4;

    static {
        //set Weighed
        digramTypeWeighed.put(DigramType.BASIC_NODE_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.ADJACENCY_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.CIRCLE_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.NODE_ASSOCIATION_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.BASIC_EDGE_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.EDGE_ADJACENCY_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.M3, 1);
        digramTypeWeighed.put(DigramType.M4, 1);
        digramTypeWeighed.put(DigramType.M5, 1);
        digramTypeWeighed.put(DigramType.M6, 1);
        digramTypeWeighed.put(DigramType.M7, 1);
        digramTypeWeighed.put(DigramType.M8, 1);
        digramTypeWeighed.put(DigramType.M9, 1);


        digranTypeUsed.put(DigramType.BASIC_NODE_DIGRAM, true);
        digranTypeUsed.put(DigramType.CIRCLE_DIGRAM, true);
        digranTypeUsed.put(DigramType.ADJACENCY_DIGRAM, false);
        digranTypeUsed.put(DigramType.NODE_ASSOCIATION_DIGRAM, false);
        digranTypeUsed.put(DigramType.BASIC_EDGE_DIGRAM, false);
        digranTypeUsed.put(DigramType.EDGE_ADJACENCY_DIGRAM, true);
        digranTypeUsed.put(DigramType.M3, true);
        digranTypeUsed.put(DigramType.M4, true);
        digranTypeUsed.put(DigramType.M5, true);
        digranTypeUsed.put(DigramType.M6, true);
        digranTypeUsed.put(DigramType.M7, true);
        digranTypeUsed.put(DigramType.M8, true);
        digranTypeUsed.put(DigramType.M9, true);

        digramTypesWichReplacesToNodes.put(DigramType.BASIC_NODE_DIGRAM.toString(), Node.class);
        digramTypesWichReplacesToNodes.put(DigramType.CIRCLE_DIGRAM.toString(), Node.class);
        digramTypesWichReplacesToNodes.put(DigramType.ADJACENCY_DIGRAM.toString(), Node.class);
        digramTypesWichReplacesToNodes.put(DigramType.NODE_ASSOCIATION_DIGRAM.toString(), Node.class);
        digramTypesWichReplacesToNodes.put(DigramType.BASIC_EDGE_DIGRAM.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.EDGE_ADJACENCY_DIGRAM.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.M3.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.M4.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.M5.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.M6.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.M7.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.M8.toString(), Edge.class);
        digramTypesWichReplacesToNodes.put(DigramType.M9.toString(), Edge.class);

    }

    public static Integer getDigramTypeWeight(DigramType digramType) {
        return digramTypeWeighed.get(digramType);
    }


    public static boolean isDigramTypeInUse(DigramType digramType) {
        if (digranTypeUsed.get(digramType) != null && digranTypeUsed.get(digramType)) {
            return true;
        }
        return false;
    }

    public static boolean isExecuteTranformGraph() {
        return executeTranformGraph;
    }

    public static boolean isExecutePruning() {
        return executePruning;
    }

    public static boolean isExecuteEdgeOptimisation() {
        return executeEdgeOptimisation;
    }

}
