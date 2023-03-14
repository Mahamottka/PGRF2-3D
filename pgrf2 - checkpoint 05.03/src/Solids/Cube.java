package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Cube extends Solid {

    public Cube() {
        //vytvorit celou cube
        vertexBuffer.add(new Vertex(30, 40, 0, new Col(0xff0000))); //0
        vertexBuffer.add(new Vertex(30, 30, 0, new Col(0xff00ff))); //1
        vertexBuffer.add(new Vertex(40, 40, 0, new Col(0xffff00))); //2
        vertexBuffer.add(new Vertex(40, 30, 0, new Col(0xff0f0f))); //3

        vertexBuffer.add(new Vertex(30, 40, 10, new Col(0xf0ff0f))); //4
        vertexBuffer.add(new Vertex(30, 30, 10, new Col(0x0f00ff))); //5
        vertexBuffer.add(new Vertex(40, 40, 10, new Col(0x00ff00))); //6
        vertexBuffer.add(new Vertex(40, 30, 10, new Col(0xf0ffff))); //7

        addIndices(0, 1, 2, 3, 6, 7, 4, 5, 0, 1,
                1 ,5, 3, 7,
                6, 4, 2, 0);

        partBuffer.add(new Part(TopologyType.LINE_STRIP, 0, 8));
        partBuffer.add(new Part(TopologyType.LINE_STRIP, 10, 2));
        partBuffer.add(new Part(TopologyType.LINE_STRIP, 14, 2));
    }

}

