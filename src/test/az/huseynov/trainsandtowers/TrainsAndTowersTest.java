package test.az.huseynov.trainsandtowers;

import main.az.huseynov.trainsandtowers.graph.Edge;
import main.az.huseynov.trainsandtowers.graph.Graph;
import main.az.huseynov.trainsandtowers.graph.Vertex;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class TrainsAndTowersTest {

    static Graph graph;
    static Vertex vertexA, vertexB, vertexC, vertexD, vertexE;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        graph = new Graph(); //Build graph

        vertexA = new Vertex("A");
        vertexB = new Vertex("B");
        vertexC = new Vertex("C");
        vertexD = new Vertex("D");
        vertexE = new Vertex("E");

        /*Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7*/
        graph.getGraph().put(vertexA, new Edge(vertexA, vertexB, 5).next(new Edge(vertexA, vertexD, 5).next(new Edge(vertexA, vertexE, 7))));
        graph.getGraph().put(vertexB, new Edge(vertexB, vertexC, 4));
        graph.getGraph().put(vertexC, new Edge(vertexC, vertexD, 8).next(new Edge(vertexC, vertexE, 2)));
        graph.getGraph().put(vertexD, new Edge(vertexD, vertexC, 8).next(new Edge(vertexD, vertexE, 6)));
        graph.getGraph().put(vertexE, new Edge(vertexE, vertexB, 3));
    }

    /* TEST 1 */
    @Test
    public void testDistanceBetween_ABC() throws Exception {
        ArrayList<Vertex> route = new ArrayList<Vertex>();
        route.add(vertexA);
        route.add(vertexB);
        route.add(vertexC);
        assertEquals(9, graph.distanceBetween(route));
    }

    /* TEST 2 */
    @Test
    public void testDistanceBetween_AD() throws Exception {
        ArrayList<Vertex> route = new ArrayList<Vertex>();
        route.add(vertexA);
        route.add(vertexD);
        assertEquals(5, graph.distanceBetween(route));
    }

    /* TEST 3 */
    @Test
    public void testDistanceBetween_ADC() throws Exception  {
        ArrayList<Vertex> route = new ArrayList<Vertex>();
        route.add(vertexA);
        route.add(vertexD);
        route.add(vertexC);
        int d = graph.distanceBetween(route);
        assertEquals(13, d);
    }

    /* TEST 4 */
    @Test
    public void testDistanceBetween_AEBCD() throws Exception  {
        ArrayList<Vertex> route = new ArrayList<Vertex>();
        route.add(vertexA);
        route.add(vertexE);
        route.add(vertexB);
        route.add(vertexC);
        route.add(vertexD);
        assertEquals(22, graph.distanceBetween(route));
    }

    /* TEST 5 */
    @Test()
    public void testDistanceBetween_AED() throws Exception  {
        ArrayList<Vertex> route = new ArrayList<Vertex>();
        route.add(vertexA);
        route.add(vertexE);
        route.add(vertexD);
        assertEquals(-1, graph.distanceBetween(route));
    }

    /* TEST 6 */
    @Test
    public void testNumStops_CC3() throws Exception {
        int numStops = graph.numberOfStops(vertexC, vertexC, 3);
        assertEquals(2, numStops);
    }

    /* TEST 7 */
    @Test
    public void testNumStops_AC4() throws Exception {
        int numStops = graph.numberOfStopsStepsPreserved(vertexA, vertexC, 4);
        assertEquals(3, numStops);
    }

    /* TEST 8 */
    @Test
    public void testShortestRoute_AC() throws Exception {
        int shortestRoute = graph.shortestRoute(vertexA, vertexC);
        assertEquals(9, shortestRoute);
    }

    /* TEST 9 */
    @Test
    public void testShortestRoute_BB() throws Exception {
        int shortestRoute = graph.shortestRoute(vertexB, vertexB);
        assertEquals(9, shortestRoute);
    }

    /* TEST 10 */
    @Test
    public void numRoutesWithin_CC30() throws Exception {
        int numRoutesWithin = graph.numRoutesWithin(vertexC, vertexC, 30);
        assertEquals(7, numRoutesWithin);
    }

    /* TEST 11 */
    @Test
    public void testEquals() {
        Vertex a1 = new Vertex("A");
        Vertex a2 = new Vertex("A");
        Vertex b = new Vertex("B");

        assertEquals(true, a1.equals(a2));
        assertEquals(false, a1.equals(b));
        assertEquals(true, (new Vertex("Test").equals(new Vertex("Test"))));
    }


}
