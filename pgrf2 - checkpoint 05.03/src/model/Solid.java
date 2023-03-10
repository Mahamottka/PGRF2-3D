package model;

import model.Part;
import model.Vertex;

import java.util.ArrayList;
import java.util.List;

public abstract class Solid{

    //index, vertex buff, part

    protected List<Integer> indexBuffer = new ArrayList<>();
    protected List<Vertex> vertexBuffer = new ArrayList<>();
    protected List<Part> partBuffer = new ArrayList<>();

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public List<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Part> getPartBuffer() {
        return partBuffer;
    }
}
