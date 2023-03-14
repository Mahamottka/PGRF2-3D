package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Cube extends Solid {

    public Cube() {
        //vytvorit celou cube
        vertexBuffer.add(new Vertex(40, 50, 0, new Col(0xff0000))); //0
        vertexBuffer.add(new Vertex(40, 40, 0, new Col(0xff00ff))); //1
        vertexBuffer.add(new Vertex(50, 50, 0, new Col(0xffff00))); //2
        vertexBuffer.add(new Vertex(50, 40, 0, new Col(0xffffff))); //3

        vertexBuffer.add(new Vertex(40, 50, 10, new Col(0xffffff))); //4
        vertexBuffer.add(new Vertex(40, 40, 10, new Col(0x0000ff))); //5
        vertexBuffer.add(new Vertex(50, 50, 10, new Col(0x00ff00))); //6
        vertexBuffer.add(new Vertex(50, 40, 10, new Col(0x00ffff))); //7

        addIndices(0, 1, 2, 3, 6, 7, 4, 5, 0, 1,
                1 ,5, 3, 7,
                6, 4, 2, 0);

        partBuffer.add(new Part(TopologyType.TRIANGLE_STRIP, 0, 8));
        partBuffer.add(new Part(TopologyType.TRIANGLE_STRIP, 10, 2));
        partBuffer.add(new Part(TopologyType.TRIANGLE_STRIP, 14, 2));
    }

}

