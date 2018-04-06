package main.az.huseynov.trainsandtowers.graph;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class Edge {

    private Vertex source;
    private Vertex destination;
    private int distance;
    private Edge next;

    /**
     * Constructor
     *
     * @param source Vertex
     * @param destination Vertex
     * @param distance int
     */
    public Edge(Vertex source, Vertex destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.next = null;
    }

    /**
     * Sets the next edge of the current one and returns it
     *
     * @param edge Edge
     * @return Edge
     */
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
