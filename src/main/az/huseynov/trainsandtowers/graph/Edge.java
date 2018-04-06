package main.az.huseynov.trainsandtowers.graph;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class Edge {

    private Vertex source;
    private Vertex destination;
    private int distance;
    private Edge next;

    public Edge(Vertex source, Vertex destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.next = null;
    }

    public Edge next(Edge edge) {
        this.next = edge;
        return this;
    }

    public Vertex getSource() {
        return this.source;
    }

    public Vertex getDestination() {
        return this.destination;
    }

    public int getDistance() {
        return this.distance;
    }

    public Edge getNext() {
        return this.next;
    }

}
