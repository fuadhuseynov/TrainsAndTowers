package main.az.huseynov.trainsandtowers.graph;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class Graph {

    private Hashtable<Vertex, Edge> graph;

    public Graph() {
        this.graph = new Hashtable<>();
    }

    /**
     * Returns the distance between two vertices
     * Returns -1 if no such route exists
     *
     * @param vertices ArrayList<Vertex>
     * @return int
     * @throws Exception
     */
    public int distanceBetween(ArrayList<Vertex> vertices) throws Exception {
        if (vertices.size() < 2)
            return 0;

        int distance = 0;
        int depth = 0;
        int index = 0;

        while (index < vertices.size() - 1) {
            if (this.graph.containsKey(vertices.get(index))) {
                Edge edge = this.graph.get(vertices.get(index));
                while (edge != null) {
                    if (edge.getDestination().equals(vertices.get(index + 1))) {
                        distance += edge.getDistance();
                        depth++;
                        break;
                    }
                    edge = edge.getNext();
                }
            } else throw new Exception("NO SUCH ROUTE");
            index++;
        }

        if(depth != vertices.size() - 1)
            return -1;

        return distance;
    }

    /**
     * Returns the number of trips starting at 'start' and 'end' vertices with
     * maximum 'maxStops' of stops
     *
     * @param start Vertex
     * @param end Vertex
     * @param maxStops int
     * @return int
     * @throws Exception
     */
    public int numberOfStops(Vertex start, Vertex end, int maxStops) throws Exception {
        return findRoutes(start, end, 0,maxStops);
    }

    /**
     * Returns the number of trips starting at 'start' and 'end' vertices with
     * maximum 'maxStops' of stops (equal)
     *
     * @param start Vertex
     * @param end Vertex
     * @param maxStops int
     * @return int
     * @throws Exception
     */
    public int numberOfStopsStepsPreserved(Vertex start, Vertex end, int maxStops) throws Exception {
        return findRoutesStepsPreserved(start, end, 0,maxStops);
    }

    /**
     * Returns the length of the shortest route
     *
     * @param start Vertex
     * @param end Vertex
     * @return int
     * @throws Exception
     */
    public int shortestRoute(Vertex start, Vertex end) throws Exception {
        return findShortestRoute(start, end, 0, 0);
    }

    /**
     * Returns the number of different routes from 'start' to 'end'
     *
     * @param start Vertex
     * @param end Vertex
     * @param maxDistance int
     * @return int
     * @throws Exception
     */
    public int numRoutesWithin(Vertex start, Vertex end, int maxDistance) throws Exception {
        return findNumRoutesWithin(start, end, 0, maxDistance);
    }

    public Hashtable<Vertex, Edge> getGraph() {
        return this.graph;
    }

    /****** PRIVATE METHODS ******/

    private int findRoutes(Vertex start, Vertex end, int depth, int maxStops) throws Exception {
        int routes = 0;
        if (this.graph.containsKey(start) && this.graph.containsKey(end)) {
            depth++;
            if(depth > maxStops)
                return 0;
            start.setVisited(true);
            Edge edge = this.graph.get(start);
            while (edge != null) {
                if(edge.getDestination().equals(end)) {
                    routes++;
                    edge = edge.getNext();
                    continue;
                } else if (!edge.getDestination().isVisited()) {
                    routes += findRoutes(edge.getDestination(), end, depth, maxStops);
                    depth--;
                }
                edge = edge.getNext();
            }
        } else return -1;

        start.setVisited(false);
        return routes;
    }

    private int findRoutesStepsPreserved(Vertex start, Vertex end, int depth, int maxStops) throws Exception {
        int routes = 0;
        if (this.graph.containsKey(start) && this.graph.containsKey(end)) {
            depth++;
            if(depth > maxStops)
                return 0;
            start.setVisited(true);
            Edge edge = this.graph.get(start);
            while (edge != null) {
                if(edge.getDestination().equals(end) && depth == maxStops) {
                    routes++;
                    edge = edge.getNext();
                    continue;
                } else
                    routes += findRoutesStepsPreserved(edge.getDestination(), end, depth, maxStops);
                edge = edge.getNext();
            }
        } else return -1;

        start.setVisited(false);
        return routes;
    }

    private int findShortestRoute(Vertex start, Vertex end, int distance, int shortestRoute) throws Exception {
        if (this.graph.containsKey(start) && this.graph.containsKey(end)) {
            start.setVisited(true);
            Edge edge = this.graph.get(start);
            while (edge != null) {
                if (edge.getDestination() == end || !edge.getDestination().isVisited())
                    distance += edge.getDistance();
                if (edge.getDestination().equals(end)) {
                    if (shortestRoute == 0 || distance < shortestRoute)
                        shortestRoute = distance;
                    start.setVisited(false);
                    return shortestRoute;
                } else if (!edge.getDestination().isVisited()) {
                    shortestRoute = findShortestRoute(edge.getDestination(), end, distance, shortestRoute);
                    distance -= edge.getDistance();
                }
                edge = edge.getNext();
            }
        } else return -1;
        start.setVisited(false);
        return shortestRoute;
    }

    private int findNumRoutesWithin(Vertex start, Vertex end, int distance, int maxDistance) throws Exception {
        int routes = 0;
        if (this.graph.containsKey(start) && this.graph.containsKey(end)) {
            Edge edge = this.graph.get(start);
            while (edge != null) {
                distance += edge.getDistance();
                if (distance <= maxDistance) {
                    if (edge.getDestination().equals(end)) {
                        routes++;
                        routes += findNumRoutesWithin(edge.getDestination(), end, distance, maxDistance);
                        edge = edge.getNext();
                        continue;
                    } else {
                        routes += findNumRoutesWithin(edge.getDestination(), end, distance, maxDistance);
                        distance -= edge.getDistance();
                    }
                } else
                    distance -= edge.getDistance();
                edge = edge.getNext();
            }
        } else return -1;

        return routes;
    }
}













