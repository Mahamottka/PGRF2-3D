package Solids;

import model.*;

public class Line extends Solid {

    public Line(){
        getVertexBuffer().add(new Vertex(100,100,100)); //v0
        getVertexBuffer().add(new Vertex(100,200,100)); //v1
        //IndexBuffer
        //LINE
        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        //Part buffer
        getPartBuffer().add(new Part(TopologyType.LINE, 0,1));
    }

}
