package model.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class for a Node called Node.
 * <p>
 * A Node is a node of a Graph an has a label.
 *
 * @author Matthias Duerksen
 */
public class Node extends GraphElement {

    private int numEquivClasses;

    /**
     * Constructor of Node.
     *
     * @param label the label of the node.
     */
    public Node(String label) {
        super(label);
        numEquivClasses = 1;
    }

    public Node(String label, int numEquivClasses) {
        super(label);
        this.numEquivClasses = numEquivClasses;
    }

    public Node deepCopy() {
        return new Node(label);
    }

    public int getNumEquivClasses() {
        return numEquivClasses;
    }
}
