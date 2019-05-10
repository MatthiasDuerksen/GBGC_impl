package model.Digram;

public enum DigramType {
   BASIC_NODE_DIGRAM("BASIC_NODE_DIGRAM"), CIRCLE_DIGRAM("CIRCLE_DIGRAM"), ADJACENCY_DIGRAM("ADJACENCY_DIGRAM"), NODE_ASSOCIATION_DIGRAM("NODE_ASSOCIATION_DIGRAM"), BASIC_EDGE_DIGRAM("BASIC_EDGE_DIGRAM"), EDGE_ADJACENCY_DIGRAM("EDGE_ADJACENCY_DIGRAM"), M3("M3"), M4("M4"), M5("M5"), M6("M6"), M7("M7"), M8("M8"), M9("M9");

    private final String fieldDescription;

    @Override
    public String toString() {
        return fieldDescription;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }
    DigramType(String label) {
        fieldDescription=label;
    }
}
