package Solids;

import model.*;
import transforms.Col;

public class Triangle extends Solid {

    public Triangle(){
        getVertexBuffer().add(new Vertex(20,40,20, new Col(0xff0000))); //v0
        getVertexBuffer().add(new Vertex(40,10,10, new Col(0x00ff00))); //v1
        getVertexBuffer().add(new Vertex(10,10,30, new Col(0x0000ff))); //v2

        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        getIndexBuffer().add(2);


        getPartBuffer().add(new Part(TopologyType.TRIANGLE,0,1));
    }

}
