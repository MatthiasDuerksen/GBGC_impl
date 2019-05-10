package control.graphImporter;

import control.CompressionControl;
import model.Graph.Edge;
import model.Graph.Graph;
import model.Graph.Node;
import org.apache.jena.graph.Node_Blank;
import org.apache.jena.graph.Node_Literal;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;
import scala.Int;

import java.util.*;

public class RDFImporter {

    private final static int MAX_LABEL_LENGTH = 50;

    public static Graph readFromRDFFile(String path) {
        Model model = ModelFactory.createDefaultModel();
        Model model1 = model.read(path);
        ExtendedIterator<Triple> tripleExtendedIterator = model1.getGraph().find();

        Map<String, Node> mapUriToNode = new HashMap<>();
        Set<Edge> edges = new HashSet<>();
        int counter = 0;
        while (tripleExtendedIterator.hasNext()) {
//            if (counter >= 10000) {
//                break;
//            }
            Triple triple = tripleExtendedIterator.next();

            String label = getLabel(triple.getSubject());
            Node subject;
            if (mapUriToNode.containsKey(label)) {
                subject = mapUriToNode.get(label);
            } else {
                subject = new Node(shortURILabel(label));
                mapUriToNode.put(label, subject);
            }


            label = getLabel(triple.getObject());
            Node object;
            if(triple.getObject().isURI()){
                if(mapUriToNode.containsKey(label)){
                    object=mapUriToNode.get(label);
                }else{
                    object = new Node(shortURILabel(label));
                    mapUriToNode.put(label, object);
                }
            } else {
                object = new Node(label);
                mapUriToNode.put(label, object);
            }


            LinkedHashMap<Node, Integer> startnodes = new LinkedHashMap<>();
            startnodes.put(subject, 1);
            LinkedHashMap<Node, Integer> endnodes = new LinkedHashMap<>();
            endnodes.put(object, 1);
            edges.add(new Edge(startnodes, endnodes, getLabel(triple.getPredicate())));
            counter++;
        }

        Graph graph = new Graph();
        for (Node node : mapUriToNode.values()) {
            graph.add(node);
        }
        for (Edge edge : edges) {
            graph.add(edge);
        }

        System.out.println(graph.getAllNodes().size());
        System.out.println(graph.getAllEdges().size());

        return graph;

    }

    private static String shortURILabel(String label) {
        //shorten Label
        String shortLabel;
        int labelLength = label.length();
        if(labelLength<MAX_LABEL_LENGTH){
            shortLabel=label.substring(0,labelLength-1);
        }else{
            shortLabel=label.substring(0,MAX_LABEL_LENGTH-1);
        }
        return shortLabel;
    }

    public static String getLabel(org.apache.jena.graph.Node node) {
        String label;
        if (node instanceof Node_Blank) {
            label = node.getBlankNodeId().toString();
        } else if (node instanceof Node_Literal) {
            label = node.getLiteral().toString();
        } else {
            label = node.getURI();

        }

        return label;

    }

    public static void main(String[] args) {
        Graph graph = readFromRDFFile("C:/Users/Matthias-PC/git/GBGC_impl/GraphExamples/rdf/eswc-2014-complete.rdf");
//        Graph graph = readFromRDFFile("C:/Users/Matthias-PC/git/GBGC_impl/GraphExamples/rdf/msw-2011-complete-alignments.rdf");
       int origrinalGraphSize=graph.size();
       long startTime=System.currentTimeMillis();


        CompressionControl compressionControl = new CompressionControl(graph);
        compressionControl.graphCompression();

        long neededTime=System.currentTimeMillis()-startTime;
        System.out.println("CompressionResults: " + origrinalGraphSize + " to " + compressionControl.getGraph().size()+" Rate:"+(double)compressionControl.getGraph().size()/origrinalGraphSize +" Time:"+neededTime/1000+"s");
    }
}
