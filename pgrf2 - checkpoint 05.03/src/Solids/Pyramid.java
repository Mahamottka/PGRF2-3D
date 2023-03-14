package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Pyramid extends Solid {

    public Pyramid(){

        getVertexBuffer().add(new Vertex(-5,20,2, new Col(0xff0040))); //v0
        getVertexBuffer().add(new Vertex(5,20,2, new Col(0xff0300))); //v1
        getVertexBuffer().add(new Vertex(-5,40,2, new Col(0xff1000))); //v2
        getVertexBuffer().add(new Vertex(5,40,2, new Col(0xff00f0))); //v3
        getVertexBuffer().add(new Vertex(0,30,7, new Col(0xffff0))); //v4

        addIndices(0,1,2,3,
                0,4,1,
                1,4,3,
                3,4,2,
                2,4,0);

        partBuffer.add(new Part(TopologyType.TRIANGLE_STRIP, 0, 2));
        getPartBuffer().add(new Part(TopologyType.TRIANGLE,4,4));


    }

}
