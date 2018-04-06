package main.az.huseynov.trainsandtowers.graph;

/**
 * Created by Fuad Huseynov on 4/5/2018.
 */
public class Vertex {

    private String name;
    private boolean visited;

    /**
     * Constructor
     *
     * @param name String
     */
    public Vertex(String name) {
        this.name = name;
        this.visited = false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) object;
        return this.name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        if (this.name == null)
            return 0;
        return this.name.hashCode();
    }

    public String getName() {
        return this.name;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
