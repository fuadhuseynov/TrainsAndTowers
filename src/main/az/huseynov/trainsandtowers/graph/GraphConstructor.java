package main.az.huseynov.trainsandtowers.graph;

import java.util.List;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class GraphConstructor {

    public static Graph constructGraph(List<Vertex> vertexList, List<Edge> edgeList) {
        Graph graph = new Graph();
        Edge edge;

        for (Vertex vertex : vertexList) {
            edge = null;
            for (Edge e : edgeList) {
                if (e.getSource().getName().equals(vertex.getName())) {
                    if (edge == null) {
                        edge = e;
                    } else {
                        //edge.next(e);
                        getLastEdge(edge).next(e);
                    }
                }
            }
            graph.getGraph().put(vertex, edge);
        }

        return graph;
    }

    /****** PRIVATE METHODS ******/

    private static Edge getLastEdge(Edge firstEdge) {
        Edge lastEdge = firstEdge;
        while (lastEdge.getNext() != null) {
            lastEdge = lastEdge.getNext();
        }
        return lastEdge;
    }

}
