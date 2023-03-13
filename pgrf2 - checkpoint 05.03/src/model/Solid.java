package model;

import model.Part;
import model.Vertex;
import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.ArrayList;
import java.util.List;

public abstract class Solid{

    //index, vertex buff, part
    private Mat4 model = new Mat4Identity();

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

    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }
}
