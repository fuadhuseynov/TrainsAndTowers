package main.az.huseynov.trainsandtowers;

import main.az.huseynov.trainsandtowers.graph.Edge;
import main.az.huseynov.trainsandtowers.graph.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class FileParser {

    private String fileName;
    private File file;

    /**
     * Constructor
     *
     * @param fileName String
     */
    public FileParser(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    /**
     * Parses the file and returns list of vertices and list of edges
     *
     * @return Map<List<Vertex>, List<Edge>>
     */
    public Map<List<Vertex>, List<Edge>> parseFile() {
        Map<List<Vertex>, List<Edge>> graphList = new HashMap<>();

        try {
            Scanner scanner = new Scanner(file);
            List<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() != 3) {
                    System.out.println("WRONG FILE FORMAT");
                    System.exit(0);
                }
                list.add(line);
            }

            List<Vertex> vertexList = identifyVertices(list);
            List<Edge> edgeList = identifyEdges(list, vertexList);
            graphList.put(vertexList, edgeList);
        } catch (FileNotFoundException e) {
            System.out.println("COULD NOT FIND FILE " + fileName);
        }

        return graphList;
    }

    /****** PRIVATE METHODS ******/

    private List<Vertex> identifyVertices(List<String> fileList) {
        List<Vertex> vertexList = new ArrayList<>();
        Vertex vertex = null;

        for (String entry : fileList) {
            vertex = new Vertex(entry.substring(0, 1));
            if (!containsVertex(vertexList, vertex))
                vertexList.add(vertex);
        }
        return vertexList;
    }

    private List<Edge> identifyEdges(List<String> fileList, List<Vertex> vertexList) {
        List<Edge> edgeList = new ArrayList<>();
        Edge edge = null;

        for (String entry : fileList) {
            edge = new Edge(getVertexFromList(entry.substring(0, 1), vertexList),
                    getVertexFromList(entry.substring(1, 2), vertexList),
                    Integer.parseInt(entry.substring(2, 3)));
            edgeList.add(edge);
        }
        return edgeList;
    }

    private boolean containsVertex(List<Vertex> list, Vertex vertex) {
        for (Vertex v : list) {
            if (v.equals(vertex))
                return true;
        }
        return false;
    }

    private Vertex getVertexFromList(String name, List<Vertex> vertexList) {
        for (Vertex vertex : vertexList) {
            if (vertex.getName().equals(name))
                return vertex;
        }
        return null;
    }

}
