package main.az.huseynov.trainsandtowers.graph;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class Graph {

    private Hashtable<Vertex, Edge> routeTable;

    public Graph() {
        this.routeTable = new Hashtable<>();
    }

    public int distanceBetween(ArrayList<Vertex> vertices) throws Exception {
        if (vertices.size() < 2)
            return 0;

        int distance = 0;
        int depth = 0;
        int index = 0;

        while (index < vertices.size() - 1) {
            if (this.routeTable.containsKey(vertices.get(index))) {
                Edge edge = this.routeTable.get(vertices.get(index));
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

    public int numberOfStops(Vertex start, Vertex end, int maxStops) throws Exception {
        return findRoutes(start, end, 0,maxStops);
    }

    public int numberOfStopsStepsPreserved(Vertex start, Vertex end, int maxStops) throws Exception {
        return findRoutesStepsPreserved(start, end, 0,maxStops);
    }

    public int shortestRoute(Vertex start, Vertex end) throws Exception {
        return findShortestRoute(start, end, 0, 0);
    }

    public int numRoutesWithin(Vertex start, Vertex end, int maxDistance) throws Exception {
        return findNumRoutesWithin(start, end, 0, maxDistance);
    }

    public Hashtable<Vertex, Edge> getRouteTable() {
        return this.routeTable;
    }

    private int findRoutes(Vertex start, Vertex end, int depth, int maxStops) throws Exception {
        int routes = 0;
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            depth++;
            if(depth > maxStops)
                return 0;
            start.setVisited(true);
            Edge edge = this.routeTable.get(start);
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
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            depth++;
            if(depth > maxStops)
                return 0;
            start.setVisited(true);
            Edge edge = this.routeTable.get(start);
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
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            start.setVisited(true);
            Edge edge = this.routeTable.get(start);
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
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            Edge edge = this.routeTable.get(start);
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













