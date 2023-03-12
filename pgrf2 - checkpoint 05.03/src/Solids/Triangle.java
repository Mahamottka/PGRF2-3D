package Solids;

import model.*;

public class Triangle extends Solid {

    public Triangle(){
        getVertexBuffer().add(new Vertex(100,100,100)); //v0
        getVertexBuffer().add(new Vertex(200,100,100)); //v1


        getIndexBuffer().add(0);
        getIndexBuffer().add(1);

        getPartBuffer().add(new Part(TopologyType.LINE,0,1));
    }

}
