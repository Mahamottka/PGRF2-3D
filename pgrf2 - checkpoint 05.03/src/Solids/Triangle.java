package Solids;

import model.*;

public class Triangle extends Solid {

    public Triangle(){
        getVertexBuffer().add(new Vertex(100,100,100)); //v0
        getVertexBuffer().add(new Vertex(200,100,100)); //v1
        getVertexBuffer().add(new Vertex(100,200,100)); //v2

        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        getIndexBuffer().add(2);


        getPartBuffer().add(new Part(TopologyType.TRIANGLE,0,1));
    }

}
