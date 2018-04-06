package main.az.huseynov.trainsandtowers;

import main.az.huseynov.trainsandtowers.graph.Edge;
import main.az.huseynov.trainsandtowers.graph.Graph;
import main.az.huseynov.trainsandtowers.graph.GraphConstructor;
import main.az.huseynov.trainsandtowers.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class TrainsAndTowers {

    public static void main(String[] args) throws Exception {
        if (args.length == 0 || args.length > 1) {
            System.out.println("WRONG ARGUMENTS");
            System.out.println("PLEASE PROVIDE FILE NAME AS COMMAND LINE ARGUMENT");
            System.exit(0);
        }

        FileParser fileParser = new FileParser(args[0]);
        Map<List<Vertex>, List<Edge>> parsedFileMap = fileParser.parseFile();
        Map.Entry<List<Vertex>, List<Edge>> entry = parsedFileMap.entrySet().iterator().next();
        List<Vertex> vertexList = entry.getKey();
        List<Edge> edgeList = entry.getValue();

        Graph graph = GraphConstructor.constructGraph(vertexList, edgeList);

        Vertex vertexA = getVertexByname(vertexList, "A");
        Vertex vertexB = getVertexByname(vertexList, "B");
        Vertex vertexC = getVertexByname(vertexList, "C");
        Vertex vertexD = getVertexByname(vertexList, "D");
        Vertex vertexE = getVertexByname(vertexList, "E");

        int result;

        /* TEST 1 */
        ArrayList<Vertex> route = new ArrayList<>();
        route.add(vertexA);
        route.add(vertexB);
        route.add(vertexC);
        result = graph.distanceBetween(route);
        System.out.println("Output #1: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 2 */
        route = new ArrayList<>();
        route.add(vertexA);
        route.add(vertexB);
        result = graph.distanceBetween(route);
        System.out.println("Output #2: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 3 */
        route = new ArrayList<>();
        route.add(vertexA);
        route.add(vertexD);
        route.add(vertexC);
        result = graph.distanceBetween(route);
        System.out.println("Output #3: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 4 */
        route = new ArrayList<>();
        route.add(vertexA);
        route.add(vertexE);
        route.add(vertexB);
        route.add(vertexC);
        route.add(vertexD);
        result = graph.distanceBetween(route);
        System.out.println("Output #4: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 5 */
        route = new ArrayList<>();
        route.add(vertexA);
        route.add(vertexE);
        route.add(vertexD);
        result = graph.distanceBetween(route);
        System.out.println("Output #5: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 6 */
        result = graph.numberOfStops(vertexC, vertexC, 3);
        System.out.println("Output #6: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 7 */
        result = graph.numberOfStopsStepsPreserved(vertexA, vertexC, 4);
        System.out.println("Output #7: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 8 */
        result = graph.shortestRoute(vertexA, vertexC);
        System.out.println("Output #8: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 9 */
        result = graph.shortestRoute(vertexB, vertexB);
        System.out.println("Output #9: " + (result == -1 ? "NO SUCH ROUTE" : result));

        /* TEST 10 */
        result = graph.numRoutesWithin(vertexC, vertexC, 30);
        System.out.println("Output #10: " + (result == -1 ? "NO SUCH ROUTE" : result));
    }

    private static Vertex getVertexByname(List<Vertex> vertexList, String name) {
        for (Vertex v : vertexList) {
            if (v.getName().equals(name))
                return v;
        }
        return null;
    }

}
