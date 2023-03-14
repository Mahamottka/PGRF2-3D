package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class TriangleCrossing extends Solid {

    public TriangleCrossing(){

        getVertexBuffer().add(new Vertex(20,30,18, new Col(0xf0f0f0))); //v0
        getVertexBuffer().add(new Vertex(50,15,25, new Col(0xf0f0f0))); //v1
        getVertexBuffer().add(new Vertex(35,60,5, new Col(0xf0f0f0))); //v2

        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        getIndexBuffer().add(2);


        getPartBuffer().add(new Part(TopologyType.TRIANGLE,0,1));

    }

}
